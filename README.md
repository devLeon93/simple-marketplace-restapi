#  REST API marketplace with Spring Boot,PostgreSQL,JPA and Hibernate

## Steps to Setup

**1. Clone the application**
  
 ```bash
 https://github.com/devLeon93/simple-marketplace-restapi.git
 ```
 
 **2. Create PostgreSQL database**
 ```bash
 create database marketplace_db
 ```
 
**3. Change postgreSQL username and password as per your installation**
 
+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your postgreSQL installation

**4. Build and run the app using maven**

 ```bash
mvn package
java -jar target/marketplace-0.0.1-SNAPSHOT.jar

```

The app will start running at <http://localhost:8085>.



## Explore Rest APIs

The app defines following Endpoints.

    POST /api/auth/register

    POST /api/auth/signin

    GET /api/product   
    
    GET /api/product/all
    
    POST /api/product
    
    GET /api/product/{id}
    
    GET /api/product/{pageNo}/{pageSize}
    
    PUT /api/product/{id}
    
    PATCH /api/product/{productId}/{username}/like
    
    PATCH /api/product/{productId}/{username}/unlike
    
    DELETE /api/product/{id} 
    
**5. All endpoints can be tested in Swagger UI** 

```bash
http://localhost:8085/swagger-ui/index.html#
```
 
    
    
    
    
    
  
