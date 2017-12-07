package com.qzn.models.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.qzn.models.validator.constraints.Password;

public class PasswordValidator implements ConstraintValidator<Password, Object> {

	private static final String strReg = "^(" + "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])" // 英小文字、英大文字、数字
			+ "|" + "(?=.*[a-z])(?=.*[A-Z])(?=.*[!-/:-@\\[-`{-~])" // 英小文字、英大文字、記号
			+ "|" + "(?=.*[a-z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])" // 英小文字、数字、記号
			+ "|" + "(?=.*[A-Z])(?=.*[0-9])(?=.*[!-/:-@\\[-`{-~])" // 英大文字、数字、記号
			+ ")" + "[a-zA-Z0-9!-/:-@\\[-`{-~]{8,}$"; // 英大文字、英小文字、数字、記号8文字以上;

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (obj != null && StringUtils.isNotEmpty(obj.toString())) {
			return Pattern.matches(strReg, obj.toString());
		}
		return true;
	}

	@Override
	public void initialize(Password arg0) {
		// TODO Auto-generated method stub

	}
}
