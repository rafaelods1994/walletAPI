package com.wallet.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	@Length(min = 3, max = 50, message = "O Nome deve conter entre 3 e 50 caracteres")
	private String Name;
	@Email(message = "Email Invalido")
	private String Email;
	@NotNull
	@Length(min = 6, message = "A senha deve conter no minimo 6 caracteres")
	private String Password;
}
