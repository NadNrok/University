package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fm.University.model.ClassroomStudent;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Long> {
	
	@Query("SELECT cs.id FROM ClassroomStudent cs WHERE cs.classroom.id = ?1 AND cs.student.id = ?2")
    Long findClassroomStudentIdByClassroomIdAndStudentId(Long classroomId, Long studentId);
}