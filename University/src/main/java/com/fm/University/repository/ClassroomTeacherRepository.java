package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fm.University.model.ClassroomTeacher;

@Repository
public interface ClassroomTeacherRepository extends JpaRepository<ClassroomTeacher, Long> {
	
	@Query("SELECT cs.id FROM ClassroomTeacher cs WHERE cs.classroom.id = ?1 AND cs.teacher.id = ?2")
    Long findClassroomTeacherIdByClassroomIdAndTeacherId(Long classroomId, Long teacherId);
}