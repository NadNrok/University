package com.fm.University.controller;

import com.fm.University.model.Course;
import com.fm.University.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("")
	public String getAllCourses(Model model) {
		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("courses", courses);
		model.addAttribute("course", new Course());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
		model.addAttribute("role", role);
		return "course";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		Course course = courseService.getCourseById(id).orElse(null);
		return ResponseEntity.ok(course);
	}

	@PostMapping("/")
	public String createCourse(@ModelAttribute Course course) {
		courseService.createCourse(course);
		return "redirect:/courses";
	}

	@PostMapping("/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return "redirect:/courses";
	}
}
