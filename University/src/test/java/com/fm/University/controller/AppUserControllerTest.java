package com.fm.University.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fm.University.model.AppUser;
import com.fm.University.model.Role;
import com.fm.University.service.RoleService;
import com.fm.University.service.AppUserRoleService;
import com.fm.University.service.AppUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppUserController.class)
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AppUserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private AppUserRoleService appUserRoleService;

    @InjectMocks
    private AppUserController appUserController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(appUserController).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAddUserForm() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new AppUser()));
        when(roleService.getAllRoles()).thenReturn(Arrays.asList(new Role()));

        mockMvc.perform(get("/appusers"))
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("appuser"))
                .andExpect(model().attributeExists("role"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateUser() throws Exception {
        Role role = new Role();
        role.setRoleId(1L);
        when(roleService.getRoleById(1L)).thenReturn(java.util.Optional.of(role));
        when(userService.createUser(any(AppUser.class))).thenReturn(new AppUser());

        mockMvc.perform(post("/appusers/")
                .param("username", "test")
                .param("password", "password")
                .param("roleId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appusers"));

        verify(userService, times(1)).createUser(any(AppUser.class));
        verify(roleService, times(1)).getRoleById(1L);
        verify(appUserRoleService, times(1)).createAppUserRole(any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteUser() throws Exception {
        mockMvc.perform(post("/appusers/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/appusers"));

        verify(userService, times(1)).deleteUserById(1L);
    }
}
