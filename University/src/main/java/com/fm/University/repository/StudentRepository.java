package com.fm.University.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fm.University.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT COUNT(c) FROM Student c")
	Long getCount();
	@Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.courses")
    List<Student> findStudentsEnrolledInCourses();
}
