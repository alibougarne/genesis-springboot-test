package com.genisis.test.helpers.seeds;

import com.genisis.test.model.Contact;
import com.genisis.test.repository.ContactRepository;
import com.genisis.test.model.Enterprise;
import com.genisis.test.repository.EnterpriseRepository;
import com.github.javafaker.Faker;
import com.sun.istack.NotNull;

import java.util.*;

public class ContactSeeds {
    public static void seedData(@NotNull ContactRepository contactRepository, @NotNull EnterpriseRepository enterpriseRepository) {
        System.out.println("🧩 Seeding Contacts data...");
        // create and save contacts
        Set<Contact> contacts = new HashSet<>();
        // get enterprises
        List<Enterprise> enterprises = new ArrayList<>();
        enterprises = enterpriseRepository.findAll();
        Random random= new Random();
        Faker faker = new Faker();
        if (enterprises.size() != 0) {
            for (int i = 0; i <= 10; i++) {
                Contact contact = new Contact();
                contact.setFirstName(faker.name().firstName());
                contact.setLastName(faker.name().lastName());
                contact.setAddress(faker.address().fullAddress());
                contact.setTvaNumber(null);
                int numberOfRandomValues = random.nextInt(7) + 4;
                Set<Enterprise> newEnterpriseList = new HashSet<>();
                for(int  j = 0; j < numberOfRandomValues; j++) {
                    int randomPosition = random.nextInt(enterprises.size());
                    newEnterpriseList.add(enterprises.get(randomPosition));
                }
                contact.setEnterprises(newEnterpriseList);
                contacts.add(contact);
            }
            contactRepository.saveAll(contacts);
        }
        // create some freelancer contacts
        System.out.println("🧩 Seeding Freelancers data...");
        contacts = new HashSet<>();
        for (int i = 0; i <= 5; i++) {
            Contact contact = new Contact();
            contact.setFirstName(faker.name().firstName());
            contact.setLastName(faker.name().lastName());
            contact.setAddress(faker.address().fullAddress());
            contact.setTvaNumber(faker.number().numberBetween(1000, 5000));
            contacts.add(contact);
        }
        contactRepository.saveAll(contacts);

    }
}
