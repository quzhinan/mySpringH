package com.qzn.controllers.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.auth.Authenticator;
import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.Login;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private UserService userService;

	@ModelAttribute("login")
	public Login loadLogin() throws Exception {
		return new Login();
	}
	
	@RequestMapping("")
	public Page welcome() throws Exception {
		return Page("filters-welcome");
	}
	
	@RequestMapping("/login")
	public Page login(User user) throws Exception {

		getSession().invalidate();
		return RedirectPage("welcome");
	}

	@RequestMapping("/logout")
	public Page logout() {
		Authenticator.clearActiveUser();
		getSession().invalidate();
		return RedirectPage("welcome");
	}

}
