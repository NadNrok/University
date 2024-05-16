package com.fm.University.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import javax.transaction.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AppUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAddUserForm() throws Exception {
		mockMvc.perform(get("/appusers")).andExpect(status().isOk()).andExpect(view().name("user"))
				.andExpect(model().attributeExists("users")).andExpect(model().attributeExists("appuser"))
				.andExpect(model().attributeExists("role")).andExpect(model().attributeExists("roles"));
	}

	@Test
	void testCreateUser() throws Exception {
		mockMvc.perform(
				post("/appusers/").param("username", "testuser").param("password", "testpassword").param("role", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/appusers"));
	}

	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/appusers/{id}", 1L)).andExpect(status().isMethodNotAllowed());
	}
}
