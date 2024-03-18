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