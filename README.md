# genesis-springboot-test
an exercise test with spring boot
<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.5.5-brightgreen.svg" />
    </a>
    <a alt="H2 Database">
        <img src="https://img.shields.io/badge/H2%20Database-runtime-red.svg" />
    </a>
    <a alt="Maven">
        <img src="https://img.shields.io/badge/Maven-v3-blue.svg" />
    </a>
    <a alt="Swagger UI">
        <img src="https://img.shields.io/badge/spring%20doc%20openapi--ui-v1.5.2-yellowgreen.svg" />
    </a>
    <a alt="Dependencies">
        <img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" />
    </a>
    <a alt="License">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
    </a>
</p>

## stack
- spring boot 2.5.5
- maven
- H2 database
- swagger ui
- java faker for seeding data
- spring data
- lombok
## project structure

    .
    ├── ...
    ├── src                                                 # Source folder
    │   ├── main                                            # main package
    │   │   ├── java                                        # java
    │   │   ├   ├── com.genesis.test                        # code logic
    │   │   ├   ├   ├── config                              # config classes
    │   │   ├   ├   ├── controller.v1                       # contain v1 controllers
    │   │   ├   ├   ├        ├── ContactController          # controller
    │   │   ├   ├   ├        ├── EnterpriseController       # controller
    │   │   ├   ├   ├── dto                                 # DTOs
    │   │   ├   ├   │   ├── contact                         # all contact DTO's
    │   │   ├   ├   │   ├── enterprise                      # all enterprise DTO's
    │   │   ├   ├   ├── exception                           # contains all exception handlers  
    │   │   ├   ├   ├── helpers                             # contains seeds 
    │   │   ├   ├   │   ├── seeders                         # seed callers
    │   │   ├   ├   │   ├── seeds                           # data
    │   │   ├   ├   ├── model                               # contains entities 
    │   │   ├   ├   ├── repository                          # contains repositories 
    │   │   ├   ├   ├── service                             # contains services 
    │   │   ├   ├   ├── utils                               # contains utils classes
    │   ├── test                                            # tests (not implemented yet)
    │
    └── ...
## Data seeds
i used java faker to generate data seeders
replace the following code in application.properties to interrupting seeds
```properties
database.seed = true
```
with
```properties
database.seed = false
```
## H2 console
http://localhost:5000/h2console/

## Swagger ui
http://localhost:5000/swagger-ui/index.html
make sur to put the following into the explore input
```
/api-docs/
```

