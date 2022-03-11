package com.wallet.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserDTO;
import com.wallet.entities.User;
import com.wallet.response.Response;
import com.wallet.service.UserService;
import com.wallet.utils.Util;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<UserDTO>> createUser(@Valid @RequestBody UserDTO userDto, BindingResult bindingResult) 
	{
		Response<UserDTO> response = new Response<UserDTO>();
		
		User user = userService.save(Util.convertUserDtoToEntity(userDto));
		
		response.setData(Util.convertEntityToUserDto(user));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
