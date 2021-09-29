package db.seeds;

import com.genisis.test.features.enterprise.Enterprise;
import com.genisis.test.features.enterprise.EnterpriseRepository;
import com.github.javafaker.Faker;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EnterprisesSeeds {
    public static void seedData(@NotNull EnterpriseRepository enterpriseRepository) {
        System.out.println("🧩 Seeding Enterprises data...");
        // create and save an enterprise
        Set<Enterprise> enterprises = new HashSet<>();
        Long countEnterprises = enterpriseRepository.count();
        if (countEnterprises == 0) {
            Faker faker = new Faker();
            for (int i = 0; i <= 10; i++) {
                Enterprise enterprise = new Enterprise();
                enterprise.setName(faker.company().name());
                enterprise.setAddress(faker.address().fullAddress());
                enterprise.setTvaNumber(faker.number().numberBetween(10000, 50000));
                enterprises.add(enterprise);
            }
            enterpriseRepository.saveAll(enterprises);
        }
    }
}
