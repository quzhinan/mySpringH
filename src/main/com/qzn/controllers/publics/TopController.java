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
		return Page("tile-index", "user", user, "pagination", pagination);
	}
	
	@RequestMapping("/elements")
	public Page elements() throws Exception {
		return Page("tile-elements");
	}
}
