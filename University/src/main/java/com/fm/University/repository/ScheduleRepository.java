package com.fm.University.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fm.University.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("SELECT COUNT(c) FROM Schedule c")
	Long getCount();
}
