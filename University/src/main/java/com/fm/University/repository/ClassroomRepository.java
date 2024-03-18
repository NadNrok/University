package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fm.University.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
	@Query("SELECT COUNT(c) FROM Classroom c")
	Long getCount();
}
