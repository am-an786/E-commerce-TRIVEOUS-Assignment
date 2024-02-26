# Triveous E-Commerce Application
 ## Overview
 This is a small-scale E-Commerce application developed as part of the Triveous hiring process. The application includes four controllers: Admin, Authentication, Customer, and Public. Each controller serves specific functionality and the application works with the following models: User, Product, Order, Category, and Cart.

## Tech Stacks

- Spring Boot 
- Spring Framework
- Spring Data JPA 
- MySQL 
- Hibernate
- Java
- Layered Architechture
- Lombok
- Postman
- Spring Security

## Modules
- User Module
-	Category Module
-	Products Module
-	Cart Module
-	Orders Module


## Installation & Run
- Clone the Repository in your Local system.
- Before running the API server, you have to update the database configuration inside the application.properties file
- Update the port number, username and password as per your local database configuration.
- Make sure to create a database with name "ecommerce_triveousDB" in MySQL.
````
    server.port=8085

    #db specific properties
    spring.datasource.url=jdbc:mysql://localhost:3306/triveous_assign
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
    
````

## API Endpoints
  ### Public URLs 
  Public URLs are accessible to anyone, including users who are not authenticated. These URLs typically include endpoints for general information or actions that do not require user-specific or privileged data. In an e-commerce application, public URLs could be used for features like user registration, fetching product details, or retrieving general information that doesn't depend on the user's identity.
  
        POST /triv/auth/users/signup :- User registration
        POST /triv/auth/admins/signup   :- Admin registration
        GET /triv/public/all-categories :- Get all categories
        GET /triv/public/get-product-of-specific-category/{categoryId}  :- Get products of a specific category
        GET /triv/public/get-product/{productId}    :-  Get details of a specific product
        GET /triv/public/all-products   :-  Get all products

  ### User and Admin URLs
  User and Admin URLs represent endpoints that require authentication and are categorized based on the user role—either user or admin. These URLs often involve actions related to user accounts, authentication, or other operations that require a certain level of privilege.
  
        POST /triv/auth/users/signin :- User authentication
        POST /triv/auth/admins/signin   :- Admin authentication
        PUT /triv/auth/users/update-user    :- Update user information

  ### User URLs
  User URLs are designed for actions and features that regular authenticated users can access. These URLs often involve operations that are relevant to individual user accounts, such as managing their shopping cart, viewing order history, or updating personal information.
  
        POST /triv/users/addProduct-to-cart/{productId}/{quantity} :- Add a product to the cart
        GET /triv/users/getAllProducts-from-cart :- Get all products from the cart
        PUT /triv/users/update-quantity-of-product-in-cart/{quantity} :- Update quantity of a product in the cart

   ### Admin URLs
   Admin URLs are designed for users with administrative privileges. These endpoints typically involve actions that are reserved for administrators, such as managing users, adding new categories, or saving new products.
   
        DELETE /triv/admins/delete-user :- Delete a user
        POST /triv/admins/save-category :- Save a new category
        POST /triv/admins/save-product :- Save a new product

## Authentication and Authorization
JWT (JSON Web Token) is used for authentication and authorization. Make sure to select Basic Authentication in the Authorization settings when making requests. Provide the username and password used during registration. A JWT token will be received in the Headers section labeled as "Authorization." Retain this JWT token for further requests.


## Registration as Admin or Customer:-
   # SignUp
    * Registration Or SignUp to the application as Customer or Admin
    * API Endpoints
       POST ( http://localhost:8085/triv/auth/users/signup ) :- Register a user.
       POST ( http://localhost:8085/triv/auth/users/signup ) :- Register a admin.
    * RequestBody:- 
         {
            "firstName": "Sakshi",
            "lastName": "Choudhary",
            "contact": "9958090188",
            "email": "sakshi@gmail.com",
            "password": "12345",
            "dateOfBirth": "2002-02-05",
            "address": {
                "addressLine1": "D/O Kavita Choudhary",
                "addressLine2": "",
                "buildingName": "Surya Appartment",
                "landMark": "Ramind Showroom",
                "city": "Noida",
                "state": "UP",
                "country": "Indian",
                "pincode": "201301"
            }
        }

  # Login 
     * API Endpoints
        GET ( http://localhost:8085/triv/auth/users/signin ) :- Login to the Application as User
        GET ( http://localhost:8085/triv/auth/admins/signin ) :- Login to the Application as Admin

        ## Note -- 
             Ensure that you choose Basic Authentication in the Authorization settings and provide the username and password used during registration. The system will respond with a JWT token in the Headers section, labeled as "Authorization." Please retain this JWT token as it will be necessary for subsequent requests.

##   ER_Diagram                                            



