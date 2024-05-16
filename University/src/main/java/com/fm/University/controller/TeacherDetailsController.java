package com.fm.University.controller;

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
import com.fm.University.model.Teacher;
import com.fm.University.service.TeacherService;

@Controller
@RequestMapping("/teacher-details")
public class TeacherDetailsController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherDetailsController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/{id}")
	public String getTeacherDetails(@PathVariable Long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);

		Teacher teacher = teacherService.getTeacherById(id).orElse(null);
		if (teacher != null) {
			model.addAttribute("teacher", teacher);
		}
		return "teacher-details";
	}

	@PostMapping("/{id}/edit-name")
	public String editTeacherName(@PathVariable Long id, @RequestParam String newName) {
		Teacher teacher = teacherService.getTeacherById(id).orElse(null);
		if (teacher != null) {
			teacher.setName(newName);
			teacherService.saveTeacher(teacher);
		}
		return "redirect:/teacher-details/" + id;
	}
}