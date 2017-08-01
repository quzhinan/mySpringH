package com.qzn.controllers.publics;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.AdminUser;
import com.qzn.services.UserService;

@Controller
public class TopController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public Page frontPage() throws Exception {
		AdminUser adminUser = userService.findById(1L);
		List<AdminUser> adminUserList = userService.loadAll();
		return Page("tile-index-index", "adminUser", adminUser, "adminUserList", adminUserList);
	}
}
