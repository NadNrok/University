package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fm.University.model.ClassroomStudent;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Long> {
}