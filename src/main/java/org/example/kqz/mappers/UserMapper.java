package org.example.kqz.mappers;

import org.example.kqz.dtos.user.*;
import org.example.kqz.entities.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface UserMapper extends ZSimpleMapper<UserEntity, CreateUserRequestDto> {
    UserDetailsDto toDetailsDto(UserEntity userEntity);

    List<UserListingDto> toUsersListingDto(List<UserEntity> users);

    UpdateUserRequestDto toUpdateDto(UserEntity userEntity);

}