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

import com.fm.University.model.Classroom;
import com.fm.University.model.ClassroomStudent;
import com.fm.University.model.ClassroomTeacher;
import com.fm.University.model.Student;
import com.fm.University.model.Teacher;
import com.fm.University.service.ClassroomService;
import com.fm.University.service.ClassroomStudentService;
import com.fm.University.service.ClassroomTeacherService;
import com.fm.University.service.StudentService;
import com.fm.University.service.TeacherService;

@Controller
@RequestMapping("/classroom-details")
public class ClassroomDetailsController {

	private final ClassroomService classroomService;
	private final StudentService studentService;
	private final ClassroomStudentService classroomStudentService;
	private final TeacherService teacherService;
	private final ClassroomTeacherService classroomTeacherService;

	@Autowired
	public ClassroomDetailsController(ClassroomService classroomService, StudentService studentService,
			ClassroomStudentService classroomStudentService, TeacherService teacherService,
			ClassroomTeacherService classroomTeacherService) {
		this.classroomService = classroomService;
		this.studentService = studentService;
		this.classroomStudentService = classroomStudentService;
		this.teacherService = teacherService;
		this.classroomTeacherService = classroomTeacherService;

	}

	@GetMapping("/{id}")
	public String getClassroomDetails(@PathVariable Long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);

		Classroom classroom = classroomService.getClassroomById(id).orElse(null);
		model.addAttribute("classroom", classroom);

		List<Student> students = new ArrayList<>();
		List<Student> availableStudents = new ArrayList<>();
		if (classroom != null) {
			List<ClassroomStudent> classroomStudents = classroom.getStudents();
			for (ClassroomStudent cs : classroomStudents) {
				students.add(cs.getStudent());
			}
		}

		List<Student> enrolledStudents = studentService.getStudentsEnrolledInCourses();
		enrolledStudents.removeAll(students);
		availableStudents.addAll(enrolledStudents);

		model.addAttribute("students", students);
		model.addAttribute("availableStudents", availableStudents);

		List<Teacher> teachers = new ArrayList<>();
		List<Teacher> availableTeachers = new ArrayList<>();
		if (classroom != null) {
			List<ClassroomTeacher> classroomTeachers = classroom.getTeachers();
			for (ClassroomTeacher cs : classroomTeachers) {
				teachers.add(cs.getTeacher());
			}
		}

		List<Teacher> enrolledTeachers = teacherService.getTeachersEnrolledInCourses();
		enrolledTeachers.removeAll(teachers);
		availableTeachers.addAll(enrolledTeachers);

		model.addAttribute("teachers", teachers);
		model.addAttribute("availableTeachers", availableTeachers);

		return "classroom-details";
	}

	@PostMapping("/{id}/add-student")
	public String addStudentToClassroom(@PathVariable Long id, @RequestParam Long studentId) {
		Student student = studentService.getStudentById(studentId).orElse(null);
		if (student != null) {
			classroomService.addStudentToClassroom(id, student);
		}
		return "redirect:/classroom-details/" + id;
	}

	@PostMapping("/{classroomId}/remove-student")
	public String removeStudentFromClassroom(@PathVariable("classroomId") Long classroomId,
			@RequestParam("studentId") Long studentId) {
		classroomStudentService.deleteClassroomStudentById(studentId);
		return "redirect:/classroom-details/" + classroomId;
	}

	@PostMapping("/{id}/add-teacher")
	public String addTeacherToClassroom(@PathVariable Long id, @RequestParam Long teacherId) {
		Teacher teacher = teacherService.getTeacherById(teacherId).orElse(null);
		if (teacher != null) {
			classroomService.addTeacherToClassroom(id, teacher);
		}
		return "redirect:/classroom-details/" + id;
	}

	@PostMapping("/{classroomId}/remove-teacher")
	public String removeTeacherFromClassroom(@PathVariable("classroomId") Long classroomId,
			@RequestParam("teacherId") Long teacherId) {
		classroomTeacherService.deleteClassroomTeacherById(teacherId);
		return "redirect:/classroom-details/" + classroomId;
	}

	@PostMapping("/{id}/change-name")
	public String changeClassroomName(@PathVariable Long id, @RequestParam String newName) {
		Classroom classroom = classroomService.getClassroomById(id).orElse(null);
		if (classroom != null) {
			classroom.setName(newName);
			classroomService.saveClassroom(classroom);
		}
		return "redirect:/classroom-details/" + id;
	}
}