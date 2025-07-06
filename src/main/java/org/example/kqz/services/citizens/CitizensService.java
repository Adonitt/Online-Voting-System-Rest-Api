package org.example.kqz.services.citizens;

import org.example.kqz.entities.CitizensEntity;
import org.example.kqz.services.base_services.Addable;
import org.example.kqz.services.base_services.FindAll;

import java.util.List;

public interface CitizensService extends Addable<CitizensEntity>, FindAll<CitizensEntity> {
    long count();

    boolean existsByPersonalNo(String personalNo);

    List<CitizensEntity> findAllByPersonalNo(String personalNo);

}
