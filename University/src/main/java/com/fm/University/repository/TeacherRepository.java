package com.fm.University.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fm.University.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT COUNT(c) FROM Teacher c")
	Long getCount();
	@Query("SELECT DISTINCT s FROM Teacher s JOIN FETCH s.courses")
    List<Teacher> findTeachersEnrolledInCourses();
}
