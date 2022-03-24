package com.wallet.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wallet.dto.UserDTO;
import com.wallet.dto.WalletDTO;
import com.wallet.entities.User;
import com.wallet.entities.Wallet;

public class Util {

	public static User convertUserDtoToEntity(UserDTO userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(Util.getHash(userDto.getPassword()));
		return user;
	}

	public static UserDTO convertEntityToUserDto(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		return userDto;
	}
	
	public static String getHash(String password) {

		if (null == password) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);

	}
	
	public static Wallet convertWalletDtoToEntity(WalletDTO walletDto) {
		Wallet wallet = new Wallet();
		wallet.setId(walletDto.getId());
		wallet.setName(walletDto.getName());
		wallet.setValue(walletDto.getValue());
		return wallet;
	}

	public static WalletDTO convertEntityToWalletDto(Wallet wallet) {
		WalletDTO walletDto = new WalletDTO();
		walletDto.setId(wallet.getId());
		walletDto.setName(wallet.getName());
		walletDto.setValue(wallet.getValue());
		return walletDto;
	}
}
