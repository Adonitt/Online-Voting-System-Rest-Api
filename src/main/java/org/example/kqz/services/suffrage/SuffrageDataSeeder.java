package org.example.kqz.services.suffrage;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.SuffrageEntity;
import org.example.kqz.entities.enums.NationalityEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SuffrageDataSeeder implements CommandLineRunner {

    private final SuffrageService suffrageService;

    @Override
    public void run(String... args) {

        List<String> firstNames = List.of("Adonit", "Fatlind", "Arben", "Elira", "Besnik", "Vlera", "Dren", "Arta", "Blerim", "Gentiana", "Valon", "Liridona");
        List<String> lastNames = List.of("Halili", "Gashi", "Berisha", "Krasniqi", "Hoxha", "Gashi", "Shala", "Rama", "Ismaili", "Morina", "Sadiku", "Zeqiri");
        Random random = new Random();

        if (suffrageService.count() > 150) return;

        int created = 0;

        for (int i = 100; i <= 999 && created < 30; i++) {
            String personalNo = "1234567" + i;

            if (suffrageService.existsByPersonalNo(personalNo)) {
                continue;
            }

            SuffrageEntity entity = new SuffrageEntity();
            entity.setPersonalNo(personalNo);
            entity.setFirstName(firstNames.get(random.nextInt(firstNames.size())));
            entity.setLastName(lastNames.get(random.nextInt(lastNames.size())));

            LocalDate birthDate = created % 2 == 0 ?
                    LocalDate.of(1990 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28)) :
                    LocalDate.of(2004 + random.nextInt(5), 1 + random.nextInt(12), 1 + random.nextInt(28));
            entity.setBirthDate(birthDate);

            NationalityEnum[] nationalities = NationalityEnum.values();
            entity.setNationality(nationalities[random.nextInt(nationalities.length)]);

            suffrageService.add(entity);
            created++;
        }
    }


}
