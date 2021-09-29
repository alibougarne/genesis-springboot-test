package com.genisis.test.helpers.seeders;

import com.genisis.test.features.contact.ContactRepository;
import com.genisis.test.features.enterprise.EnterpriseRepository;
import db.seeds.ContactSeeds;
import db.seeds.EnterprisesSeeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${database.seed}")
    private boolean useSeeds;
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
                System.out.println("🚀 useSeeds? : "+useSeeds);
                if(useSeeds){
                    System.out.println("🚀 Seeding data Begin...");
                    // comment line for interrupt data seeding
                    EnterprisesSeeds.seedData(enterpriseRepository);
                    ContactSeeds.seedData(contactRepository, enterpriseRepository);
                    System.out.println("🎯 Seeding data completed...");
                }else System.out.println("🎯 Seeding data not allowed...");
            };
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
    
    
}
