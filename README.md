# 📌 Employee Management System -- Backend

A Spring Boot--based backend application for managing employees,
departments, and attendance within an organization. This service exposes
RESTful APIs for CRUD operations and integrates with a database for
persistent storage.

## 🚀 Features

### 👨‍💼 Employee Management

-   Create, update, delete employees
-   Get all employees
-   Get single employee by ID

### 🏢 Department Management

-   Create and manage departments
-   Assign employees to departments

### 🕒 Attendance Management

-   Mark attendance
-   Get attendance by employee
-   Daily attendance summary

## 🛠️ Tech Stack

-   Java 17+
-   Spring Boot 3+
-   MySQL / PostgreSQL
-   Spring Data JPA
-   Lombok
-   Swagger/OpenAPI

## 📁 Project Structure

    ems-backend/
    │── src/main/java/com/finalproject/ems/
    │── src/main/resources/
    └── pom.xml

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository

``` sh
git clone <your-repo-url>
cd ems-backend
```

### 2️⃣ Configure Database

Update `application.properties`.

### 3️⃣ Run the Project

``` sh
mvn spring-boot:run
```

## 📚 API Endpoints

### Employees

  Method   Endpoint           Description
  -------- ------------------ -------------------
  GET      `/api/employees`   Get all employees
  POST     `/api/employees`   Add employee

### Departments

  Method   Endpoint             Description
  -------- -------------------- ---------------------
  GET      `/api/departments`   Get all departments
  POST     `/api/departments`   Add department

### Attendance

  Method   Endpoint                         Description
  -------- -------------------------------- ---------------------
  POST     `/api/attendance`                Mark attendance
  GET      `/api/attendance/{employeeId}`   Employee attendance

## 👤 Author

**Arjun R Nath**
