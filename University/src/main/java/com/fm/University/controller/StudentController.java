package com.fm.University.controller;

import com.fm.University.model.Student;
import com.fm.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/students")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("")
	public String getAllStudents(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
	    model.addAttribute("role", role);
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		model.addAttribute("student", new Student());
		return "student";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id)
				.orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + id));
		return ResponseEntity.ok(student);
	}

	@PostMapping("/")
	public String createStudent(@ModelAttribute Student student) {
		studentService.createStudent(student);
		return "redirect:/students";
	}

	@PostMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

}
