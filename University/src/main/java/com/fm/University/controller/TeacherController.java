package com.fm.University.controller;

import com.fm.University.model.Teacher;
import com.fm.University.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("")
	public String getAllTeachers(Model model) {
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers", teachers);
		model.addAttribute("teacher", new Teacher());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
	    model.addAttribute("role", role);
		return "teacher";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
		Teacher teacher = teacherService.getTeacherById(id).orElse(null);
		return ResponseEntity.ok(teacher);
	}

	@PostMapping("/")
	public String createTeacher(@ModelAttribute Teacher teacher) {
		teacherService.createTeacher(teacher);
		return "redirect:/teachers";
	}

	@PostMapping("/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teachers";
	}
}
