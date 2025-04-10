### 🛒 Grocery App Backend - Spring Boot

This is the backend of the Grocery App built using Spring Boot. It provides REST APIs for user authentication, product management, cart operations, and order processing.
---



# 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- REST APIs
---



# 📁 Project Structure

src/ ├── main/ │ ├── java/ │ │ └── com.ecommerce.backend/ │ │ ├── controller/ │ │ ├── model/ │ │ ├── repository/ │ │ ├── service/ │ │ └── GroceryAppApplication.java │ └── resources/ │ ├── application.properties
---




### 🚀 Getting Started

# 1. Clone the repository

bash
git clone https://github.com/Ashutosh-Dhodad/grocery-app-backend-springboot.git
cd grocery-app-backend-springboot


# 2. Set up the database

Create a database in MySQL:
CREATE DATABASE grocery_app;


# 3. Configure application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_Db
spring.datasource.username=root
spring.datasource.password=PFH#23kgrw9
spring.jpa.hibernate.ddl-auto=update


# 4. Run the application

./mvnw spring-boot:run




📫 API Endpoints

Method	      Endpoint	                                Description

POST	        /api/users/register	                      Register new user

POST	        /api/auth/authenticate	                  Authenticate user

GET           /api/categories/get                       Get all categories

POST          /api/categories/post                      Create new category

DELETE        /api/categories/delete/{id}               Delete category by id

GET	          /api/products/all	                        List all products

GET           /api/products/category/{category}         List products by category

POST          /api/products/add/{category}              Add product in category

PUT           /api/products/update/{id}                 Update product by id

DELETE        /api/products/delete/{id}                 Delete product by id

GET          	/api/cart/{userId}/get                    Get all products from cart

POST          /api/cart/{userId}/add                    Add product in cart

PUT	          /api/cart/{userId}/updateQuantity         Update item quantity

DELETE	      /api/cart/{userId}/remove/{itemId}	      Remove item from cart

DELETE        /api/cart/{userId}/clear                  Clear cart

POST	        /orders/buy	                              Place an order

GET           /orders/all                               Get all orders

PUT           /orders/{orderId}/status                  Update order status

GET           /admin/stats                              Check order status

GET           /admin/recent-orders                      Check recent-orders

GET           /admin/best-selling-products              Get best-selling-products



✅ Features
* User registration & login
* Admin can add/update/delete categories
* Admin can add/update/delete products
* Users can view products and manage cart
* Order placement with cart items
* Quantity update and cart persistence




📌 To Do
* Payment Integration
* Email Notifications
* Swagger UI for API Testing


📃 License
This project is licensed under the MIT License - see the LICENSE file for details.
