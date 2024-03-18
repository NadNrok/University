package com.fm.University.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fm.University.model.Course;
import com.fm.University.model.CourseStudent;
import com.fm.University.model.CourseTeacher;
import com.fm.University.model.Student;
import com.fm.University.model.Teacher;
import com.fm.University.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}

	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	public Course updateCourse(Long id, Course course) {
		course.setCourseId(id);
		return courseRepository.save(course);
	}

	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}

	public Course addStudentToCourse(Long courseId, Student student) {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			CourseStudent courseStudent = new CourseStudent(course, student);
			course.getStudents().add(courseStudent);
			return courseRepository.save(course);
		}
		return null;
	}

	public void removeStudentFromCourse(Long courseId, Long studentId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			course.getStudents().removeIf(cs -> cs.getStudent().getStudentId().equals(studentId));
			courseRepository.save(course);
		}
	}

	public Course addTeacherToCourse(Long courseId, Teacher teacher) {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			CourseTeacher courseTeacher = new CourseTeacher(course, teacher);
			course.getTeachers().add(courseTeacher);
			return courseRepository.save(course);
		}
		return null;
	}

	public void removeTeacherFromCourse(Long courseId, Long teacherId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			course.getTeachers().removeIf(ct -> ct.getTeacher().getTeacherId().equals(teacherId));
			courseRepository.save(course);
		}
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}
}
