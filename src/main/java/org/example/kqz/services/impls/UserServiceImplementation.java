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
import org.example.kqz.repositories.CitizensRepository;
import org.example.kqz.repositories.UserRepository;
import org.example.kqz.services.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final CitizensRepository citizensRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserRequestDto add(CreateUserRequestDto dto) {
        validateUser(dto);

        var entity = mapper.toEntity(dto);
        entity.setCity(dto.getCity());

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encryptedPassword);

        var savedUser = userRepository.save(entity);

        return mapper.toDto(savedUser);
    }

    private void validateUser(CreateUserRequestDto dto) {
        if (!citizensRepository.existsByPersonalNoAndFirstNameAndLastNameAndBirthDate(dto.getPersonalNo(), dto.getFirstName(), dto.getLastName(), dto.getBirthDate())) {
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

        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
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
    public void removeById(Long id) {
        var userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (userFromDB.isHasVoted()) {
            throw new UserCannotBeDeletedException("User has already voted");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UpdateUserRequestDto modify(UpdateUserRequestDto dto, Long id) {
        var userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String email = AuthServiceImplementation.getLoggedInUserEmail();

        UserEntity adminUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        String updatedBy = adminUser.getFirstName() + " " + adminUser.getLastName();

        userFromDB.setNationality(dto.getNationality());
        userFromDB.setUpdatedAt(LocalDateTime.now());
        userFromDB.setUpdatedBy(updatedBy);
        userFromDB.setCity(dto.getCity());

        var updatedUser = userRepository.save(userFromDB);
        return mapper.toUpdateDto(updatedUser);
    }

}
