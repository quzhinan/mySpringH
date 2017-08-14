package com.qzn.controllers.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Controller
@RequestMapping("/resetpassword")
public class ResetPasswordController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public Page changepassword() throws Exception {
		return Page("filters-resetpassword");
	}

	@RequestMapping("/save")
	public Page save(String username) throws Exception {
		if (!StringUtils.isEmpty(username)) {
			User user = userService.findByProperty("username", username);
			if (user == null) {
				return Page("filters-resetpassword", "username", username, "msg",
						"errors.validation.resetpassword.usernamenotexist");
			} else {
				userService.resetPassword(username);
			}
		}
		return Page("filters-resetpassword", "msg", "重置成功，已向您的邮箱发送邮件");
	}
}
