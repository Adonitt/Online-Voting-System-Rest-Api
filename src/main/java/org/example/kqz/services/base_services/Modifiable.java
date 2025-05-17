package org.example.kqz.services.base_services;

import org.example.kqz.dtos.user.UpdateUserRequestDto;

public interface Modifiable<T, Tid> {
    T modify(T entity, Tid id);
}
