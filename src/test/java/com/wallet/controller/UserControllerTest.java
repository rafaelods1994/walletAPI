package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entities.User;
import com.wallet.service.UserService;

@SpringBootTest
@Profile("test")
public class UserControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private static final String NAME = "User Test";
	private static final String PASSWORD = "123456";
	private static final String EMAIL = "email@test.com";
	private static final String URL = "/user";

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldSaveUser() throws Exception {
		BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());		

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	public User getMockUser() {
		User user = new User();
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setEmail(EMAIL);
		return user;
	}

	public String getJsonPayload() throws JsonProcessingException {

		UserDTO userDto = new UserDTO();
		userDto.setName(NAME);
		userDto.setPassword(PASSWORD);
		userDto.setEmail(EMAIL);

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(userDto);

	}

}
