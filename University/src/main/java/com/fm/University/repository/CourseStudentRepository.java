package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fm.University.model.CourseStudent;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

	@Query("SELECT cs.id FROM CourseStudent cs WHERE cs.student.id = ?1 AND cs.course.id = ?2")
    Long findCourseStudentIdByStudentIdAndCourseId(Long studentId, Long courseId);
}
