package org.example.kqz.services.impls;

import org.example.kqz.dtos.user.CreateUserRequestDto;
import org.example.kqz.dtos.user.UpdateUserRequestDto;
import org.example.kqz.dtos.user.UserDetailsDto;
import org.example.kqz.dtos.user.UserListingDto;
import org.example.kqz.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Override
    public CreateUserRequestDto add(CreateUserRequestDto entity) {
        return null;
    }

    @Override
    public List<UserListingDto> findAll() {
        return List.of();
    }

    @Override
    public UserDetailsDto findById(Long id) {
        return null;
    }

    @Override
    public void modify(UpdateUserRequestDto entity, Long id) {

    }

    @Override
    public void remove(Long id) {

    }
}
