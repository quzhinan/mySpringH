package com.qzn.controllers.publics;

import java.util.Calendar;
import java.util.Date;

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
		User findUser = userService.findByProperty("username", user.getUsername());
		if (findUser != null) {
			return Page("filters-register", "msg", "errors.validation.register.usernameexists");
		}
		userService.registerUser(user);
		return RedirectPage("welcome");
	}
	
	public Integer getAge(Date birthDate) {
		if (birthDate == null) {
			return null;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(birthDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(new Date());
		int pastYear  = endCalendar.get(Calendar.YEAR)  - startCalendar.get(Calendar.YEAR);
		int pastMonth = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		int pastDate  = endCalendar.get(Calendar.DATE)  - startCalendar.get(Calendar.DATE);
		if (pastMonth > 0) {
			return pastYear;
		} else if (pastMonth == 0) {
			return pastDate >= 0 ? pastYear : pastYear - 1;
		} else {
			return pastYear - 1;
		}
	}

}
