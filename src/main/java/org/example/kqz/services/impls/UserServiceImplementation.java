package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.user.CreateUserRequestDto;
import org.example.kqz.dtos.user.UpdateUserRequestDto;
import org.example.kqz.dtos.user.UserDetailsDto;
import org.example.kqz.dtos.user.UserListingDto;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.enums.RoleEnum;
import org.example.kqz.exceptions.*;
import org.example.kqz.mappers.UserMapper;
import org.example.kqz.repositories.SuffrageRepository;
import org.example.kqz.repositories.UserRepository;
import org.example.kqz.services.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final SuffrageRepository suffrageRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserRequestDto add(CreateUserRequestDto dto) {
        validateUser(dto);

        var entity = mapper.toEntity(dto);

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encryptedPassword);

        var savedUser = userRepository.save(entity);

        return mapper.toDto(savedUser);
    }

    private void validateUser(CreateUserRequestDto dto) {
        if (!suffrageRepository.existsByPersonalNoAndFirstNameAndLastName(dto.getPersonalNo(), dto.getFirstName(), dto.getLastName())) {
            throw new NotKosovoCitizenException("There is no such person with personal number " + dto.getPersonalNo() + "and name " + dto.getFirstName() + " " + dto.getLastName() + " in Kosovo");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new PasswordAndConfirmDontMatch("Passwords and confirm password do not match");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("User with email " + dto.getEmail() + " already exists");
        }
        if (userRepository.existsByPersonalNo(dto.getPersonalNo())) {
            throw new PersonalNumberAlreadyExists("User with personal number " + dto.getPersonalNo() + " already exists");
        }
        if (dto.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("User must be at least 18 years old to register");
        }

    }

    @Override
    public List<UserListingDto> findAll() {
        var usersList = userRepository.findAll();
        return mapper.toUsersListingDto(usersList);
    }

    @Override
    public UserDetailsDto findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        System.out.println("Found user: " + userEntity.getFirstName() + " " + userEntity.getLastName()); // Shtoni log
        return mapper.toDetailsDto(userEntity);
    }


    @Override
    public CreateUserRequestDto modify(UpdateUserRequestDto dto, Long id) {
        var userFromDB = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String email = AuthServiceImplementation.getLoggedInUserEmail();

        UserEntity adminUser = userRepository.findByEmailAndRole(email, RoleEnum.ADMIN)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        String updatedBy = adminUser.getFirstName() + " " + adminUser.getLastName();

        userFromDB.setBirthDate(dto.getBirthDate());
        userFromDB.setEmail(dto.getEmail());
        userFromDB.setNationality(dto.getNationality());
        userFromDB.setUpdatedAt(LocalDateTime.now());
        userFromDB.setUpdatedBy(updatedBy);

        var updatedUser = userRepository.save(userFromDB);
        return mapper.toDto(updatedUser);
    }

    @Override
    public void removeById(Long id) {
        var userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.deleteById(id);
    }

}
