# Transaction Management System (Spring Boot + JPA)

## 📌 Overview
This is a Spring Boot-based backend application for managing customers, items, orders, and order items with relational database mapping using JPA/Hibernate. The system supports order creation, order item management, and paginated order detail retrieval.

---

## ⚙️ Tech Stack
- Java 17
- Spring Boot 3.3.4
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- ModelMapper
- Maven

---

## 🏗️ Project Structure
com.transaction.transaction
│
├── controller
├── service
│ └── impl
├── repo
├── entity
├── dto
│ ├── request
│ └── response
├── exception
├── pagination
├── response
├── QueryInterface
└── config

---

## 🧩 Main Entities

### Customer
- id
- name
- email
- activeState
- orders (One-to-Many)

### Orders
- id
- product
- total
- orderDate
- activeState
- customer (Many-to-One)
- orderItems (One-to-Many)

### OrderItem
- id
- itemName
- quantity
- amount
- orders (Many-to-One)
- items (Many-to-One)

### Items
- id
- name
- price (based on implementation)

---

## 🔌 API Endpoints

### 📦 Customer APIs
- `POST /api/customers` → Save customer
- `GET /api/customers/{id}` → Get customer by ID
- `GET /api/customers` → Get all customers
- `DELETE /api/customers/{id}` → Delete customer

---

### 📦 Items APIs
- `POST /api/items` → Save item
- `GET /api/items/{id}` → Get item by ID
- `GET /api/items` → Get all items
- `DELETE /api/items/{id}` → Delete item

---

### 📦 Order APIs
- `POST /api/orders/save` → Create order with order items
- `GET /api/orders/{id}` → Get order by ID
- `GET /api/orders` → Get all orders
- `DELETE /api/orders/{id}` → Delete order

---

### 📊 Order Details (Paginated)
- `GET /api/orders/get-order-details?stateType={ACTIVE|INACTIVE}&page={page}&size={size}`

Returns:
- Customer name
- Email
- Order date
- Total amount
- Total record count

---

## 📥 Example Request (Create Order)

```json
{
  "customerId": 1,
  "product": "Laptop Order",
  "total": 1500.00,
  "orderDate": "2026-04-28T10:00:00",
  "items": [
    {
      "itemId": 1,
      "itemName": "Laptop",
      "quantity": 1,
      "amount": 1500.00
    }
  ]
}

Standard API Response
{
  "code": 200,
  "data": {},
  "msg": "Success"
}

Exception Handling
Global Exception Handler implemented using @RestControllerAdvice
Custom exception: OrderSaveException
Consistent error response structure
🗄️ Database Configuration

Configured in application.properties:

MySQL database: transactionDb
Hibernate auto schema update enabled
SQL logging enabled

🗄️ Database Configuration

Configured in application.properties:

MySQL database: transactionDb
Hibernate auto schema update enabled
SQL logging enabled
🚀 Features
Customer management
Item management
Order creation with multiple items
Relational mapping (OneToMany, ManyToOne)
Transactional order saving
Pagination support
Native SQL projection queries
Global exception handling
Standard API response format
🧪 How to Run
1️⃣ Clone project
git clone <repository-url>
2️⃣ Configure database

Update credentials in:

spring.datasource.username=root
spring.datasource.password=root
3️⃣ Run application
mvn spring-boot:run
📌 Notes
All services use layered architecture
Transactions are handled using @Transactional
DTOs are used for request handling
Native SQL used for optimized order detail queries
👨‍💻 Author

Spring Boot Transaction Management System Demo Project


---

If you want, I can also:
✔ :contentReference[oaicite:0]{index=0}
✔ Or :contentReference[oaicite:1]{index=1}
✔ Or :contentReference[oaicite:2]{index=2}

Just tell 👍
