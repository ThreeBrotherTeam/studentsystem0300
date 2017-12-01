package com.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@Resource
	private Validator validator;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, UserForm userForm, BindingResult bindingResult, HttpSession session,
			HttpServletResponse response) {

		validator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/login";
		}

		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!StringUtils.equalsIgnoreCase(verifyCode, userForm.getVerifyCode())) {
			return "user/login";
		}

		UserData userData = userService.findUser(userForm);
		if (userData == null) {
			return "user/login";
		}

		if (userForm.isRememberMe()) {
			Cookie cookie = new Cookie("token", userForm.getName() + "(-)" + userForm.getPassword());
			cookie.setMaxAge(60 * 60 * 24 * 3);
			response.addCookie(cookie);
		}

		session.setAttribute("userData", userData);

		return "user/success";
	}
}
