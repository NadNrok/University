package com.fm.University.controller;

import com.fm.University.model.Classroom;
import com.fm.University.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {

	private final ClassroomService classroomService;

	@Autowired
	public ClassroomController(ClassroomService classroomService) {
		this.classroomService = classroomService;
	}

	@GetMapping("")
	public String getAllClassrooms(Model model) {
		List<Classroom> classrooms = classroomService.getAllClassrooms();
		model.addAttribute("classrooms", classrooms);
		model.addAttribute("classroom", new Classroom());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);
		return "classroom";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
		Classroom classroom = classroomService.getClassroomById(id).orElse(null);
		return ResponseEntity.ok(classroom);
	}

	@PostMapping("/")
	public String createClassroom(@ModelAttribute Classroom classroom) {
		classroomService.createClassroom(classroom);
		return "redirect:/classrooms";
	}

	@PostMapping("/{id}")
	public String deleteClassroom(@PathVariable Long id) {
		classroomService.deleteClassroomById(id);
		return "redirect:/classrooms";
	}
}
