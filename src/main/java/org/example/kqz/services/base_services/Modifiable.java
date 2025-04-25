package org.example.kqz.services.base_services;

public interface Modifiable<T, Tid> {
    void modify(T entity, Tid id);
}
