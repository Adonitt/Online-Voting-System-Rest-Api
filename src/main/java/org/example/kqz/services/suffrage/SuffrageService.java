package org.example.kqz.services.suffrage;

import org.example.kqz.entities.SuffrageEntity;
import org.example.kqz.services.base_services.Addable;
import org.example.kqz.services.base_services.FindAll;

public interface SuffrageService extends Addable<SuffrageEntity>, FindAll<SuffrageEntity> {
    long count();

    boolean existsByPersonalNo(String personalNo);

}
