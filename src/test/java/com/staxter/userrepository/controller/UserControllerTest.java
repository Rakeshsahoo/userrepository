/**
 * 
 */
package com.staxter.userrepository.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.userrepository.models.User;

/**
 * This class is responsible for controller test without mocking the Service
 * response as most of the exceptions are thrown from service
 * 
 * @author srake
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	// @MockBean
	// private UserRepositoryService userRepositoryService;

	@Test
	@Order(1)
	void shouldCreateNewUser() throws Exception {
		/*
		 * Mockito.when(userRepositoryService.createUser(getUser())).thenReturn(
		 * getResponseUser()); ResponseEntity<ResponseUser> response =
		 * userController.create(getUser());
		 * assertEquals(HttpStatus.OK,response.getStatusCode());
		 */

		mockMvc.perform(MockMvcRequestBuilders.post("/userservice/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getUser()))).andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void shouldReturn409() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/userservice/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getUser()))).andExpect(status().isConflict());
	}

	@Test
	@Order(3)
	void shouldLogin() throws Exception {
		User user = new User();
		user.setUserName("rakesh18");
		user.setHashedPassword("Sahoo1");

		mockMvc.perform(MockMvcRequestBuilders.post("/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void shouldFailedLoginWrongUser() throws Exception {
		User user = new User();
		user.setUserName("rakesh2");
		user.setHashedPassword("Sahoo2");

		mockMvc.perform(MockMvcRequestBuilders.post("/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isConflict());
	}

	@Test
	@Order(5)
	void shouldFailedLoginWrongPassword() throws Exception {
		User user = new User();
		user.setUserName("rakesh18");
		user.setHashedPassword("Sahoo2");

		mockMvc.perform(MockMvcRequestBuilders.post("/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isConflict());
	}

	private User getUser() {
		User user = new User();
		user.setFirstName("Rakesh");
		user.setLastName("Sahoo");
		user.setUserName("rakesh18");
		user.setHashedPassword("Sahoo1");
		return user;
	}
}
