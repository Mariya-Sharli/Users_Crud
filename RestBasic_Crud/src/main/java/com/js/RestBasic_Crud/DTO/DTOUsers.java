package com.js.RestBasic_Crud.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOUsers {
	
	@NotBlank(message= "name cannot be blank")
	private String name;
	@Email(message = "invalid email")
	private String email;

}
