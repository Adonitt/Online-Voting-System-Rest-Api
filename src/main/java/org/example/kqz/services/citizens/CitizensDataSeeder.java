package org.example.kqz.services.citizens;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.CitizensEntity;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.entities.enums.NationalityEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CitizensDataSeeder implements CommandLineRunner {

    private final CitizensService suffrageService;

    @Override
    public void run(String... args) {

        List<String> firstNames = List.of("Adonit", "Fatlind", "Arben", "Elira", "Besnik", "Vlera", "Dren", "Arta", "Blerim", "Gentiana", "Valon", "Liridona");
        List<String> lastNames = List.of("Halili", "Gashi", "Berisha", "Krasniqi", "Hoxha", "Gashi", "Shala", "Rama", "Ismaili", "Morina", "Sadiku", "Zeqiri");
        Random random = new Random();

        if (suffrageService.count() > 500) return;

        int created = 0;

        for (int i = 100; i <= 999 && created < 30; i++) {
            String personalNo = "1234567" + i;

            if (suffrageService.existsByPersonalNo(personalNo)) {
                continue;
            }

            CitizensEntity entity = new CitizensEntity();
            entity.setPersonalNo(personalNo);
            entity.setFirstName(firstNames.get(random.nextInt(firstNames.size())));
            entity.setLastName(lastNames.get(random.nextInt(lastNames.size())));

            LocalDate birthDate = created % 2 == 0 ?
                    LocalDate.of(1990 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28)) :
                    LocalDate.of(2004 + random.nextInt(5), 1 + random.nextInt(12), 1 + random.nextInt(28));
            entity.setBirthDate(birthDate);

            NationalityEnum[] nationalities = NationalityEnum.values();
            entity.setNationality(nationalities[random.nextInt(nationalities.length)]);
            CityEnum[] cities = CityEnum.values();
            entity.setCity(cities[random.nextInt(cities.length)]);

            suffrageService.add(entity);
            created++;
        }
    }


}
