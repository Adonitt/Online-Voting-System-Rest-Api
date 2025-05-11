package org.example.kqz.services.base_services;

import org.example.kqz.dtos.user.CreateUserRequestDto;

public interface Modifiable<T, Tid> {
    CreateUserRequestDto modify(T entity, Tid id);
}
