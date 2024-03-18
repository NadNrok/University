package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fm.University.model.ClassroomTeacher;

@Repository
public interface ClassroomTeacherRepository extends JpaRepository<ClassroomTeacher, Long> {
}