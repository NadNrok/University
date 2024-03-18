package com.fm.University.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fm.University.model.AppUser;
import com.fm.University.model.AppUserRole;
import com.fm.University.model.Role;
import com.fm.University.service.RoleService;
import com.fm.University.service.AppUserRoleService;
import com.fm.University.service.AppUserService;

@Controller
@RequestMapping("/appusers")
public class AppUserController {

	private final AppUserRoleService appUserRoleService;
    private final RoleService roleService;
    private final AppUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserController(RoleService roleService, AppUserService userService, PasswordEncoder passwordEncoder, AppUserRoleService appUserRoleService) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.appUserRoleService = appUserRoleService;
    }

    @GetMapping("")
    public String getAddUserForm(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream().findFirst().orElse(null).getAuthority();
    	List<AppUser> users = userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("appuser", new AppUser());
        model.addAttribute("role", role);
        model.addAttribute("roles", roleService.getAllRoles());
        return "user";
    }

    @PostMapping("/")
    public String createUser(AppUser user, @RequestParam("role") Long roleId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser savedUser = userService.createUser(user);
        
        Role role = roleService.getRoleById(roleId)
                               .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setUser(savedUser);
        appUserRole.setRole(role);
        
        appUserRoleService.createAppUserRole(appUserRole);
        
        return "redirect:/appusers";
    }

    @PostMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "redirect:/appusers";
	}
}
