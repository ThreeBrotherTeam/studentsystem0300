package com.training.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.training.form.UserForm;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserForm userForm = (UserForm) target;

		if (userForm.isNew()) {
			String name = userForm.getName();
			String password = userForm.getPassword();
			String confirmPassword = userForm.getConfirmPassword();
			String mobile = userForm.getMobile();
			String verifyCode = userForm.getVerifyCode();

			if (StringUtils.isBlank(name)) {
				errors.rejectValue(UserForm.NAME, "user.name.empty");
			}
			if (!StringUtils.equals(password, confirmPassword)) {
				errors.rejectValue(UserForm.PASSWORD, "user.password.equal.erro");
			}
			if (StringUtils.isBlank(mobile)) {
				errors.rejectValue(UserForm.MOBILE, "user.mobile.empty");
			}
			if (StringUtils.isBlank(verifyCode)) {
				errors.rejectValue(UserForm.VERIFYCODE, "user.verifyCode.empty");
			}
		}
		String name = userForm.getName();
		String password = userForm.getPassword();
		String verifyCode = userForm.getVerifyCode();
		if (StringUtils.isBlank(name)) {
			errors.rejectValue(UserForm.NAME, "user.name.empty");
			return;
		}
		if (StringUtils.isBlank(password)) {
			errors.rejectValue(UserForm.PASSWORD, "user.password.empty");
			return;
		}
		if (StringUtils.isBlank(verifyCode)) {
			errors.rejectValue(UserForm.VERIFYCODE, "user.verifyCode.empty");
			return;
		}
	}

}
