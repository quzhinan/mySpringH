package com.qzn.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Login {

	private String username;

	@NotBlank(message = "{errors.validation.input.required}")
	@Length(max = 64, message = "{errors.validation.input.maxlength}")
	private String password;

	@NotBlank(message = "{errors.validation.input.required}")
	@Length(max = 64, message = "{errors.validation.input.maxlength}")
	private String repeatPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
