package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.user.CreateUserRequestDto;
import org.example.kqz.dtos.user.UpdateUserRequestDto;
import org.example.kqz.dtos.user.UserDetailsDto;
import org.example.kqz.dtos.user.UserListingDto;
import org.example.kqz.services.base_services.*;

public interface UserService extends
        FindAll<UserListingDto>,
        FindById<UserDetailsDto, Long>,
        Addable<CreateUserRequestDto>,
        Modifiable<UpdateUserRequestDto, Long>,
        Removable<Long> {

}