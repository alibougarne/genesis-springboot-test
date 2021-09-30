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
    ├── src                                 # Test files (alternatively `spec` or `tests`)
    │   ├── main                            # Load and stress tests
    │   │   ├── java                        # java folder
    │   │   ├   ├── com.genesis.test        # code logic
    │   │   ├   ├   ├── config              # config classes
    │   │   ├   ├   ├── features            # contain separated featers (in our case contact and enterprise)
    │   │   ├   ├   ├        ├── feature        # feature package
    │   │   ├   ├   ├        ├── dto        # dto folder
    │   │   ├   ├   ├        ├── entityClass       
    │   │   ├   ├   ├        ├── controller 
    │   │   ├   ├   ├        ├── service        
    │   │   ├   ├   ├        ├── repository  
    │   │   ├   ├   ├── helpers             # 
    │   │   ├   ├       ├── exceptions      # Exception handling
    │   │   ├   ├       ├── seeders         # Data seeders
    │   │   ├── com.genisis.test.helpers.seeds                    # data seeds
    │   ├
    │   ├── test                            # tests (not implemented yet)
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