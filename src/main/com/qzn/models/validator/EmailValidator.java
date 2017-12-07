package com.qzn.models.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.qzn.models.validator.constraints.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {

	private static final String REGX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void initialize(Email constraintAnnotation) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email == null || email == "" || email.matches(REGX);
	}

}
