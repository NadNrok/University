package com.fm.University.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
	    model.addAttribute("role", role);
		return "index";
	}
}
