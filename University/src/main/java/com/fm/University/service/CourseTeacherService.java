package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.CourseTeacher;
import com.fm.University.repository.CourseTeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseTeacherService {
	
    private final CourseTeacherRepository courseTeacherRepository;

    @Autowired
    public CourseTeacherService(CourseTeacherRepository courseTeacherRepository) {
        this.courseTeacherRepository = courseTeacherRepository;
    }

    public List<CourseTeacher> getAllCourseTeachers() {
        return courseTeacherRepository.findAll();
    }

    public Optional<CourseTeacher> getCourseTeacherById(Long id) {
        return courseTeacherRepository.findById(id);
    }

    public CourseTeacher createCourseTeacher(CourseTeacher courseTeacher) {
        return courseTeacherRepository.save(courseTeacher);
    }

    public CourseTeacher updateCourseTeacher(Long id, CourseTeacher courseTeacher) {
        courseTeacher.setId(id);
        return courseTeacherRepository.save(courseTeacher);
    }

    public void deleteCourseTeacherById(Long id) {
        courseTeacherRepository.deleteById(id);
    }
    public Long getCourseTeacherIdByTeacherIdAndCourseId(Long teacherId, Long courseId) {
        return courseTeacherRepository.findCourseTeacherIdByTeacherIdAndCourseId(teacherId, courseId);
    }
}
