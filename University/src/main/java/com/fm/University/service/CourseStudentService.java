package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.CourseStudent;
import com.fm.University.repository.CourseStudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseStudentService {
	
    private final CourseStudentRepository courseStudentRepository;

    @Autowired
    public CourseStudentService(CourseStudentRepository courseStudentRepository) {
        this.courseStudentRepository = courseStudentRepository;
    }

    public List<CourseStudent> getAllCourseStudents() {
        return courseStudentRepository.findAll();
    }

    public Optional<CourseStudent> getCourseStudentById(Long id) {
        return courseStudentRepository.findById(id);
    }

    public CourseStudent createCourseStudent(CourseStudent courseStudent) {
        return courseStudentRepository.save(courseStudent);
    }

    public CourseStudent updateCourseStudent(Long id, CourseStudent courseStudent) {
        courseStudent.setId(id);
        return courseStudentRepository.save(courseStudent);
    }

    public void deleteCourseStudentById(Long id) {
        courseStudentRepository.deleteById(id);
    }
}
