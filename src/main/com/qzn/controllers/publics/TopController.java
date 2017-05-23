package com.qzn.controllers.publics;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.models.AdminUser;
import com.qzn.models.JsonData;
import com.qzn.services.UserService;

@Controller
public class TopController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "index.htm" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "frontPage.htm" })
	public String frontPage(Model model) throws Exception {
		model.addAttribute("mark", "admin");
		AdminUser adminUser = userService.findById(1L);
		model.addAttribute("adminUser", adminUser);
		List<AdminUser> adminUserList = userService.loadAll();
		model.addAttribute("adminUserList", JsonData.toJsonString(adminUserList));
		return "tiles.admin.frontpage";
	}

}
