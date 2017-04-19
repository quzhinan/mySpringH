package com.qzn.controllers.publics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;

@Controller
public class TopController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	
	@RequestMapping(value = { "index.htm" })
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = { "frontPage.htm" })
	public String frontPage() {
		return "tiles.admin.frontpage";
	}

	@RequestMapping(value = { "fileOperation.htm" })
	public String fileOperation() {
		return "fileTest";
	}
}
