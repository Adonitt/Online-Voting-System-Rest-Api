package org.example.kqz.mappers;

import org.example.kqz.dtos.user.*;
import org.example.kqz.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends ZSimpleMapper<UserEntity, CreateUserRequestDto> {
    List<UserDetailsDto> toUsersListingDto(List<UserEntity> users);
}