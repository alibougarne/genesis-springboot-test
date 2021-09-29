package com.genisis.test.helpers.seeders;

import com.genisis.test.features.contact.ContactRepository;
import com.genisis.test.features.enterprise.EnterpriseRepository;
import db.seeds.ContactSeeds;
import db.seeds.EnterprisesSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Data seeder.
 */
@Configuration
public class DataSeeder {

    /**
     * The enterprise repository.
     */
    @Autowired
    EnterpriseRepository enterpriseRepository;

    /**
     * The enterprise repository.
     */
    @Autowired
    ContactRepository contactRepository;

    /**
     * Command line runner command line runner.
     * used to seed some data on app start-up
     *
     * @return the command line runner
     */
    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        try {
            return args -> {
                System.out.println("🚀 Seeding data Begin...");
                // comment line for interrupt data seeding
                EnterprisesSeeds.seedData(enterpriseRepository);
                ContactSeeds.seedData(contactRepository, enterpriseRepository);
                System.out.println("🎯 Seeding data completed...");
            };
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
    
    
}
