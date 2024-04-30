package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fm.University.model.CourseTeacher;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {
	
	@Query("SELECT cs.id FROM CourseTeacher cs WHERE cs.teacher.id = ?1 AND cs.course.id = ?2")
	Long findCourseTeacherIdByTeacherIdAndCourseId(Long teacherId, Long courseId);
}
