package com.qzn.controllers.publics;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.controllers.Pagination;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Controller
public class TopController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public Page frontPage() throws Exception {
		User user = userService.findById(1L);
		Pagination<User> pagination = userService.findAllUsersByPage(10, 0, new HashMap<>());
		return Page("functions-index", "user", user, "pagination", pagination);
	}
	
	@RequestMapping("/elements")
	public Page elements() throws Exception {
		return Page("functions-elements");
	}
	
	@RequestMapping("/charts")
	public Page charts() throws Exception {
		return Page("functions-charts");
	}
	
	@RequestMapping("/panels")
	public Page panels() throws Exception {
		return Page("functions-panels");
	}
	
	@RequestMapping("/notifications")
	public Page notifications() throws Exception {
		return Page("functions-notifications");
	}
	
	@RequestMapping("/profile")
	public Page profile() throws Exception {
		return Page("functions-profile");
	}
	
	@RequestMapping("/tables")
	public Page tables() throws Exception {
		return Page("functions-tables");
	}
	
	@RequestMapping("/typography")
	public Page typography() throws Exception {
		return Page("functions-typography");
	}
	
	@RequestMapping("/icons")
	public Page icons() throws Exception {
		return Page("functions-icons");
	}
	
	@RequestMapping("/login")
	public Page login() throws Exception {
		return Page("filters-login");
	}
	
	@RequestMapping("/lockscreen")
	public Page lockscreen() throws Exception {
		return Page("filters-lockscreen");
	}
	
}
