# 🎓 Learning Management System (LMS) Backend

A full-featured backend REST API for a Learning Management System built using **Spring Boot**, **JWT Authentication**, **PostgreSQL**, and **JPA/Hibernate**. Designed for flexibility, modularity, and extensibility, this project supports course management, lesson tracking, quizzes, enrollment, progress tracking, and admin-user roles.

---

## 🚀 Features

- 🔐 JWT-based user authentication and authorization
- 👤 Admin/User role management
- 📚 Course, Lesson, Quiz, Question, and Choice management
- ✅ Quiz attempts and automatic evaluation
- 📈 Lesson progress tracking
- 👥 User enrollment and progress analytics
- 📦 RESTful APIs tested via Postman collection
- 🧪 Postman seeding script to populate the database for demo/testing

---

## 🧱 Tech Stack

| Layer         | Technology                             |
|--------------|-----------------------------------------|
| Backend       | Java 21, Spring Boot 3                  |
| Database      | PostgreSQL                              |
| ORM           | Spring Data JPA (Hibernate)             |
| Auth          | Spring Security + JWT                   |
| Mapping       | MapStruct + Lombok                     |
| Testing       | Postman + Collection Runner             |
| Build Tool    | Maven                                   |

---

## 📂 Project Structure

```
src/
├── config/             # Spring Security & JWT configuration
├── controllers/        # REST controllers
├── dto/                # Data Transfer Objects
├── entities/           # JPA Entities
│   ├── enrollment/     # Enrollment-related entities
├── exception/          # Custom exceptions
├── mappers/            # MapStruct mappers
├── repo/               # JPA repositories
├── security/           # JWT Filter and Token utils
├── services/           # Interfaces for business logic
├── services/impl/      # Implementations of services
└── LmsApplication.java # Main class
```

---

## 🧪 Running Locally

### ✅ Prerequisites

- Java 21
- Maven
- PostgreSQL (with a database created, e.g. `lms`)
- [Postman](https://www.postman.com/) (for API testing)

---

### 🔧 Configuration

Update your `application.properties` or `application.yml`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lms
spring.datasource.username=your_pg_username
spring.datasource.password=your_pg_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
```

---

### ▶️ Start the Server

```bash
mvn spring-boot:run
```

Server will start on `http://localhost:8080`.

---

## 📫 API Overview

| Method | Endpoint                              | Description                      |
|--------|----------------------------------------|----------------------------------|
| POST   | `/auth/register`                       | Register user (admin/user)       |
| POST   | `/auth/login`                          | Login and get JWT token          |
| POST   | `/course`                              | Create a course (Admin only)     |
| GET    | `/course`                              | List all courses                 |
| POST   | `/courses/{id}/lessons`                | Add lesson to course             |
| POST   | `/courses/{id}/quizzes`                | Add quiz to course               |
| POST   | `/quizzes/{id}/questions`              | Add question to quiz             |
| POST   | `/questions/{id}/choices`              | Add choices to question          |
| POST   | `/quizzes/{id}/attempts`               | Submit a quiz attempt            |
| POST   | `/progress/complete`                   | Mark lesson completed            |
| POST   | `/enrollments`                         | Enroll user in a course          |

> All routes (except `/auth/**`) require JWT token in `Authorization: Bearer <token>` header.

---

## 🧪 Postman Collection

✅ A complete Postman collection with seeding script and environment variables is included.

Features:

- Automatically registers users and logs in to store JWT token
- Seeds:
  - Courses
  - Lessons
  - Quizzes + Questions + Choices
  - User Enrollments
  - Lesson Progress
  - Quiz Attempts

You can run the collection in **Runner** mode to create 100+ rows per table.

---

## 🔐 Role-Based Access

- **Admin**: Can create courses, lessons, quizzes, questions
- **User**: Can enroll in courses, view content, attempt quizzes
- Role is assigned during registration via:
```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "secret123",
  "admin": true
}
```

---

## 🧑‍💻 Auth Flow

1. **Register** via `/auth/register`  
2. **Login** via `/auth/login` → Get JWT  
3. Use JWT token in all protected routes via:

```
Authorization: Bearer <your_token>
```

---

## ✅ To Do / Future Enhancements

- Course reviews and ratings
- Certificate generation after course completion
- Admin dashboard for analytics
- Support for video uploads (e.g., AWS S3 or YouTube embed)
- Email notifications for progress & assignments

---

## 👤 Author

**Sohil Lamba**  
🔗 [GitHub](https://github.com/sohillamba) | [LinkedIn](https://www.linkedin.com/in/sohil-lamba-ab471516a/)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
