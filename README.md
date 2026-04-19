# ResultApp Backend

This project is a Spring Boot application built using Java 17 and Spring Boot 3.3.0. It provides REST APIs for managing student results with role-based authentication for Students and Teachers.

---

## Features

* User registration (Student / Teacher)
* Role-based authentication using Spring Security (Basic Auth)
* Teacher can add or update student results
* Student can view their own results
* JPA and Hibernate with MySQL
* DTO-based structure

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL
* Lombok

---

## Setup Instructions

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd resultapp
```

---

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resultdb?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
server.port=8081
```

---

### 3. Run the Application

```bash
mvn spring-boot:run
```

Backend will run on:

```
http://localhost:8081
```

---

## Authentication

Basic Authentication is used.

Provide username and password in Postman or frontend requests.

---

## API Endpoints

### Auth

**Register User**

```
POST /api/auth/register
```

Sample request:

```json
{
  "username": "student1",
  "password": "12345",
  "emailId": "student1@test.com",
  "firstName": "Rahul",
  "lastName": "Sharma",
  "role": "ROLE_STUDENT"
}
```

---

### Teacher APIs (ROLE_TEACHER)

**Add or Update Result**

```
POST /api/teachers/results
```

Request body:

```json
{
  "studentId": 1,
  "courseId": 1,
  "marksObtained": 85
}
```

---

### Student APIs (ROLE_STUDENT)

**Get Results**

```
GET /api/students/results
```

---

## Database Tables

* users
* courses
* results

---

## Insert Sample Courses

```sql
INSERT INTO courses (course_code, course_name) VALUES ('MATH101', 'Mathematics');
INSERT INTO courses (course_code, course_name) VALUES ('PHY101', 'Physics');
INSERT INTO courses (course_code, course_name) VALUES ('CHEM101', 'Chemistry');
```

---

## Build

```bash
mvn clean install
```

---

## Run Jar

```bash
java -jar target/resultapp.jar
```

---

## Author

Kirtiman Singh
