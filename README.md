# genesis-springboot-test
an exercise test with spring boot

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
    ├── src                                                 # Test files (alternatively `spec` or `tests`)
    │   ├── main                                            # Load and stress tests
    │   │   ├── java                                        # java folder
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

