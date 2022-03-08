package com.wallet.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import com.wallet.entities.User;
import com.wallet.repository.UserRepository;

@SpringBootTest
@Profile("test")
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@BeforeEach
	public void setUp() {
		BDDMockito.given(userRepository.findByEmailEquals(anyString())).willReturn(Optional.of(new User()));
	}
	
	@Test
	public void shouldReturnEmail()
	{
		Optional<User> user = userService.findByEmail("email@test.com");
		
		assertTrue(user.isPresent());
		
	}

}
