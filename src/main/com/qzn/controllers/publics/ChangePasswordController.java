package com.qzn.controllers.publics;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.Login;
import com.qzn.models.User;
import com.qzn.services.UserService;
import com.qzn.utils.KeyUtil;

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController extends AbstractController {

	@Autowired
	private UserService userService;

	@ModelAttribute("login")
	public Login loadLogin() throws Exception {
		return new Login();
	}

	@RequestMapping("")
	public Page changepassword() throws Exception {
		return Page("filters-changepassword");
	}

	@RequestMapping("/save")
	public Page save(@Valid Login login, Errors errors) throws Exception {
		if (errors.hasErrors()) {
			return Page("filters-changepassword", "login", login);
		}
		if (!login.getPassword().equals(login.getRepeatPassword())) {
			errors.rejectValue("repeatPassword", "errors.validation.repeatPassword.notequal");
			return Page("filters-changepassword", "login", login);
		}
		User loginUser = userService.loadUser(Long.parseLong(getLoginUid()));
		if (loginUser != null) {
			if (KeyUtil.md5(login.getPassword()).equals(loginUser.getPassword())) {
				errors.rejectValue("repeatPassword", "errors.validation.password.notchange");
				return Page("filters-changepassword", "login", login);
			}
			loginUser.setPasswordStatus(User.PASSWORD_STATUS_USERRESET);
			loginUser.setPassword(KeyUtil.md5(login.getPassword()));
			userService.saveUser(loginUser);
			setLoginUser(loginUser);
		}
		return RedirectPage("dashboard");
	}
}
