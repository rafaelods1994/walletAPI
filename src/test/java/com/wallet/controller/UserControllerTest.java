package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

	private static final Long ID = 1L;
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

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.password").doesNotExist());
	}
	
	@Test
	public void shouldNotSaveInvalidUser() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("Email Invalido"));

	}

	public User getMockUser() {
		User user = new User();
		user.setId(ID);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setEmail(EMAIL);
		return user;
	}

	public String getJsonPayload(Long id, String email, String name, String password) throws JsonProcessingException {

		UserDTO userDto = new UserDTO();
		userDto.setId(id);
		userDto.setName(name);
		userDto.setPassword(password);
		userDto.setEmail(email);

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(userDto);

	}

}
