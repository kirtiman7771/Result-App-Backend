# ResultApp Backend

This project is a Spring Boot application built using **Spring Boot 3.3.0** and **Java 17**. It provides REST APIs for managing student results with role-based authentication for Students and Teachers.

---

## 🚀 Features

* User Registration (Student / Teacher)
* Role-based authentication using Spring Security (Basic Auth)
* Teacher can:

  * Add or update student results
* Student can:

  * View their own results
* JPA + Hibernate integration with MySQL
* Global exception handling
* DTO-based clean architecture

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.3.0
* Spring Security
* Spring Data JPA (Hibernate)
* MySQL
* Lombok

---

## ⚙️ Setup Instructions

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

Using IntelliJ or CLI:

```bash
mvn spring-boot:run
```

Once started, backend runs on:

```
http://localhost:8081
```

---

## 🔐 Authentication

This project uses **Basic Authentication**.

Use Postman or frontend with:

* Username: your registered username
* Password: your password

---

## 📌 API Endpoints

### 🔹 Auth APIs

#### Register User

```
POST /api/auth/register
```

Sample Request:

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

### 🔹 Teacher APIs (ROLE_TEACHER)

#### Add / Update Result

```
POST /api/teachers/results
```

Request Body:

```json
{
  "studentId": 1,
  "courseId": 1,
  "marksObtained": 85
}
```

---

### 🔹 Student APIs (ROLE_STUDENT)

#### Get My Results

```
GET /api/students/results
```

---

## 🗄️ Database Tables

* users
* courses
* results

---

### Insert Sample Courses

```sql
INSERT INTO courses (course_code, course_name) VALUES ('MATH101', 'Mathematics');
INSERT INTO courses (course_code, course_name) VALUES ('PHY101', 'Physics');
INSERT INTO courses (course_code, course_name) VALUES ('CHEM101', 'Chemistry');
```

---

## 🧪 Testing APIs

Use **Postman**:

1. Register Student & Teacher
2. Use Basic Auth for secured APIs
3. Teacher → add result
4. Student → view result

---

## ⚠️ Common Issues

### 1. 401 Unauthorized

* Missing Basic Auth in request
* Wrong username/password

---

### 2. Database Connection Error

* Check MySQL is running
* Verify DB credentials

---

### 3. Public Key Retrieval Error

Already fixed using:

```properties
allowPublicKeyRetrieval=true
```

---

## 📦 Build

```bash
mvn clean install
```

---

## ▶️ Run Jar

```bash
java -jar target/resultapp.jar
```

---

## 📚 Future Improvements

* JWT Authentication
* Role-based UI (Angular integration)
* Pagination & filtering
* Admin dashboard

---

## 👨‍💻 Author

Kirtiman Singh

---
