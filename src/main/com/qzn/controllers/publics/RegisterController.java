package com.qzn.controllers.publics;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User loadUser(Long id) throws Exception {
		return userService.loadUser(id);
	}

	@RequestMapping("")
	public Page register() throws Exception {
		return Page("filters-register");
	}

	@RequestMapping("/save")
	public Page save(@Valid User user, Errors errors) throws Exception {
		if (errors.hasErrors()) {
			return Page("filters-register");
		}
		userService.registerUser(user);
		return RedirectPage("welcome");
	}

}
