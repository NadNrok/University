University Web Application
This repository contains the source code for a simple web application named "University." The application is built using Spring Boot and provides functionalities related to university management, such as managing courses, students, instructors, classrooms, and schedules.

Project Information:
Group ID: com.fm
Artifact ID: University
Version: 0.0.1-SNAPSHOT
Java Version: 17
Description: Demo project for Spring Boot
Dependencies:
The project uses various dependencies managed by Maven. Some of the key dependencies include:

Spring Boot Starter Data JPA: For working with JPA databases.
Spring Boot Starter Thymeleaf: For server-side templating.
Spring Boot Starter Web: For building web applications.
Spring Boot Starter Security: For implementing security features.
Flyway Core: For database migrations.
PostgreSQL Driver: For connecting to the PostgreSQL database.
Bootstrap WebJars: For front-end styling.
Thymeleaf Extras Spring Security 5: For integrating Spring Security with Thymeleaf.
Database Schema:
The application uses PostgreSQL database. Below is the database schema represented in SQL:

sql
Copy code
V1__create_university_tables.sql:

CREATE TABLE course (
    course_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL
);
CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE teacher (
    teacher_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE role (
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE classroom (
    classroom_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE schedule (
    schedule_id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    time TIME NOT NULL,
    classroom_id INT,
    teacher_id INT,
    FOREIGN KEY (classroom_id) REFERENCES classroom(classroom_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);
CREATE TABLE app_user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE app_user_roles (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);
CREATE TABLE course_student (
	id SERIAL PRIMARY KEY,
    course_id INT,
    student_id INT,
    FOREIGN KEY (course_id) REFERENCES course(course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);
CREATE TABLE course_teacher (
	id SERIAL PRIMARY KEY,
    course_id INT,
    teacher_id INT,
    FOREIGN KEY (course_id) REFERENCES course(course_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);
CREATE TABLE classroom_student (
	id SERIAL PRIMARY KEY,
    classroom_id INT,
    student_id INT,
    FOREIGN KEY (classroom_id) REFERENCES classroom(classroom_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);
CREATE TABLE classroom_teacher (
	id SERIAL PRIMARY KEY,
    classroom_id INT,
    teacher_id INT,
    FOREIGN KEY (classroom_id) REFERENCES classroom(classroom_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);

Database Configuration:
The application is configured to connect to a PostgreSQL database using the following properties:

properties
Copy code
application.properties:

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url=jdbc:postgresql://localhost:5432/University
spring.datasource.username=<your_postgres_username>
spring.datasource.password=<your_postgres_password>

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.mvc.throw-exception-if-no-handler-found=true
spring.resources.add-mappings=false
Templates:
The application uses Thymeleaf templates for rendering views. The templates are located in the templates directory and include:

classroom.html
classroom-details.html
course.html
course-details.html
index.html
login.html
schedule.html
schedule-details.html
student.html
student-details.html
teacher.html
teacher-details.html
user.html
Project Structure:
The project follows a typical Spring Boot application structure with packages for controllers, models, repositories, services, and configuration classes.

How to Run:
Clone this repository to your local machine.
Ensure you have JDK 17 and Maven installed.
Set up a PostgreSQL database and update the database configuration properties in application.properties.
Run the application using Maven: mvn spring-boot:run.
Access the application in your web browser at http://localhost:8080.
Contribution:
Contributions are welcome! If you have any suggestions or would like to contribute to improving this application, feel free to submit a pull request.

Author:
NadNrok

Contact:
For any inquiries or support, please contact danil.kornieiev.e@gmail.com.
