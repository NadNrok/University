package com.fm.University.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fm.University.model.Course;
import com.fm.University.model.CourseStudent;
import com.fm.University.model.CourseTeacher;
import com.fm.University.model.Student;
import com.fm.University.model.Teacher;
import com.fm.University.service.CourseService;
import com.fm.University.service.CourseStudentService;
import com.fm.University.service.CourseTeacherService;
import com.fm.University.service.StudentService;
import com.fm.University.service.TeacherService;

@Controller
@RequestMapping("/course-details")
public class CourseDetailsController {

	private final CourseService courseService;
	private final StudentService studentService;
	private final CourseStudentService courseStudentService;
	private final TeacherService teacherService;
	private final CourseTeacherService courseTeacherService;

	@Autowired
	public CourseDetailsController(CourseService courseService, StudentService studentService,
			CourseStudentService courseStudentService, TeacherService teacherService,
			CourseTeacherService courseTeacherService) {
		this.courseService = courseService;
		this.studentService = studentService;
		this.courseStudentService = courseStudentService;
		this.teacherService = teacherService;
		this.courseTeacherService = courseTeacherService;

	}

	@GetMapping("/{id}")
	public String getCourseDetails(@PathVariable Long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);
		Course course = courseService.getCourseById(id).orElse(null);
		model.addAttribute("course", course);

		List<Student> students = new ArrayList<>();
		List<Student> availableStudents = new ArrayList<>();
		if (course != null) {
			List<CourseStudent> courseStudents = course.getStudents();
			for (CourseStudent cs : courseStudents) {
				students.add(cs.getStudent());
			}
		}

		List<Student> allStudents = studentService.getAllStudents();
		for (Student student : allStudents) {
			if (!students.contains(student)) {
				availableStudents.add(student);
			}
		}

		model.addAttribute("students", students);
		model.addAttribute("availableStudents", availableStudents);

		List<Teacher> teachers = new ArrayList<>();
		List<Teacher> availableTeachers = new ArrayList<>();
		if (course != null) {
			List<CourseTeacher> courseTeachers = course.getTeachers();
			for (CourseTeacher cs : courseTeachers) {
				teachers.add(cs.getTeacher());
			}
		}

		List<Teacher> allTeachers = teacherService.getAllTeachers();
		for (Teacher teacher : allTeachers) {
			if (!teachers.contains(teacher)) {
				availableTeachers.add(teacher);
			}
		}

		model.addAttribute("teachers", teachers);
		model.addAttribute("availableTeachers", availableTeachers);

		return "course-details";
	}

	@PostMapping("/{id}/add-student")
	public String addStudentToCourse(@PathVariable Long id, @RequestParam Long studentId) {
		Student student = studentService.getStudentById(studentId).orElse(null);
		if (student != null) {
			courseService.addStudentToCourse(id, student);
		}
		return "redirect:/course-details/" + id;
	}

	@PostMapping("/{courseId}/remove-student")
	public String removeStudentFromCourse(@PathVariable("courseId") Long courseId,
			@RequestParam("studentId") Long studentId) {
		Long courseStudentId = courseStudentService.getCourseStudentIdByStudentIdAndCourseId(studentId, courseId);
		if (courseStudentId != null) {
			courseStudentService.deleteCourseStudentById(courseStudentId);
		}
		return "redirect:/course-details/" + courseId;
	}

	@PostMapping("/{id}/add-teacher")
	public String addTeacherToCourse(@PathVariable Long id, @RequestParam Long teacherId) {
		Teacher teacher = teacherService.getTeacherById(teacherId).orElse(null);
		if (teacher != null) {
			courseService.addTeacherToCourse(id, teacher);
		}
		return "redirect:/course-details/" + id;
	}

	@PostMapping("/{courseId}/remove-teacher")
	public String removeTeacherFromCourse(@PathVariable("courseId") Long courseId,
			@RequestParam("teacherId") Long teacherId) {
		Long courseTeacherId = courseTeacherService.getCourseTeacherIdByTeacherIdAndCourseId(teacherId, courseId);
		if (courseTeacherId != null) {
			courseTeacherService.deleteCourseTeacherById(courseTeacherId);
		}
		return "redirect:/course-details/" + courseId;
	}

	@PostMapping("/{id}/change-name")
	public String changeCourseName(@PathVariable Long id, @RequestParam String newName) {
		Course course = courseService.getCourseById(id).orElse(null);
		if (course != null) {
			course.setName(newName);
			courseService.saveCourse(course);
		}
		return "redirect:/course-details/" + id;
	}

}
