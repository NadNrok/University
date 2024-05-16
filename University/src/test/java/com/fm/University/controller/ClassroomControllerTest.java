package com.fm.University.controller;

import com.fm.University.model.Classroom;
import com.fm.University.service.ClassroomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClassroomController.class)
@AutoConfigureMockMvc
class ClassroomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClassroomService classroomService;

	@Test
	@WithMockUser(username = "testuser", roles = { "USER" })
	void getAllClassrooms() throws Exception {
		Classroom classroom = new Classroom();
		classroom.setName("Class A");

		doReturn(Collections.singletonList(classroom)).when(classroomService).getAllClassrooms();

		mockMvc.perform(get("/classrooms")).andExpect(status().isOk()).andExpect(view().name("classroom"))
				.andExpect(model().attributeExists("classrooms"))
				.andExpect(model().attribute("classrooms", Collections.singletonList(classroom)));
	}

	@Test
	@WithMockUser(username = "testuser", roles = { "ADMIN" })
	void getClassroomById() throws Exception {
		Classroom classroom = new Classroom();
		classroom.setName("Class A");

		doReturn(Optional.ofNullable(classroom)).when(classroomService).getClassroomById(1L);

		mockMvc.perform(get("/classrooms/{id}", 1L)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Class A"));
	}

	@Test
	void createClassroom() throws Exception {
		mockMvc.perform(post("/classrooms").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("name", "Class A"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void testDeleteClassroom() {
		Long classroomId = 1L;
		ClassroomService classroomService = mock(ClassroomService.class);
		ClassroomController controller = new ClassroomController(classroomService);

		String result = controller.deleteClassroom(classroomId);

		verify(classroomService, times(1)).deleteClassroomById(classroomId);
		assertEquals("redirect:/classrooms", result);
	}
}
