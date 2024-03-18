package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fm.University.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("SELECT COUNT(c) FROM Course c")
	Long getCount();
}
