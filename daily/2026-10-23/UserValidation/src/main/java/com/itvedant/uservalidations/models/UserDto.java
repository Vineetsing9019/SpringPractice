// Learning entity models in bean validation in Spring - practice class from UserValidation, pushed 2026-10-23
package com.itvedant.uservalidations.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
@NotNull(message = "username is compulsory")
	private String username;

@NotNull(message = "password is compulsory")
@Size(min=6, max=12)
@Pattern(regexp = "[a-zA-Z0-9@#_]*")

	private String password;
@NotNull(message = "confirmPassword is compulsory")
	private String confirmPassword;
@NotNull(message = "email is compulsory")
@Email
	private String email;
	@Override
	public String toString() {
		return "UserDto [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", email=" + email + "]";
	}
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

