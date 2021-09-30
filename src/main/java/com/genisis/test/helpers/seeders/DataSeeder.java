package com.genisis.test.helpers.seeders;

import com.genisis.test.repository.ContactRepository;
import com.genisis.test.repository.EnterpriseRepository;
import com.genisis.test.helpers.seeds.ContactSeeds;
import com.genisis.test.helpers.seeds.EnterprisesSeeds;
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
                // change database.seed value in application.properties to interrupt data seeding
                if(useSeeds){
                    System.out.println("🚀 Seeding data Begin...");
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
