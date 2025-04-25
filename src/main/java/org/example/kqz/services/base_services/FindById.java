package org.example.kqz.services.base_services;

public interface FindById<T, Tid> {
    T findById(Tid id);
}
