package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fm.University.model.CourseStudent;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
}
