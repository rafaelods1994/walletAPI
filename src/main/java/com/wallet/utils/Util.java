package com.wallet.utils;

import com.wallet.dto.UserDTO;
import com.wallet.entities.User;

public class Util {

	public static User convertUserDtoToEntity(UserDTO userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;
	}

	public static UserDTO convertEntityToUserDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
