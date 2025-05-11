package org.example.kqz.mappers;

import org.example.kqz.dtos.user.*;
import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface UserMapper extends ZSimpleMapper<UserEntity, CreateUserRequestDto> {
    UserDetailsDto toDetailsDto(UserEntity userEntity);

    List<UserListingDto> toUsersListingDto(List<UserEntity> users);

}