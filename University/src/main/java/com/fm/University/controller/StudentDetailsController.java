package com.fm.University.controller;

import com.fm.University.model.Student;
import com.fm.University.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student-details")
public class StudentDetailsController {

	private final StudentService studentService;

	@Autowired
	public StudentDetailsController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/{id}")
	public String getStudentDetails(@PathVariable Long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);
		
		Student student = studentService.getStudentById(id).orElse(null);
		if (student != null) {
			model.addAttribute("student", student);
		}
		return "student-details";
	}

	@PostMapping("/{id}/edit-name")
	public String editStudentName(@PathVariable Long id, @RequestParam String newName) {
		Student student = studentService.getStudentById(id).orElse(null);
		if (student != null) {
			student.setName(newName);
			studentService.saveStudent(student);
		}
		return "redirect:/student-details/" + id;
	}
}
