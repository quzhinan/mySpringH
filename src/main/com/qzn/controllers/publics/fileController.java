package com.qzn.controllers.publics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class fileController {
	private static final Logger log = LoggerFactory.getLogger(fileController.class);

	@RequestMapping(value = { "fileUpload.htm" })
	public String fileUpload() {
		return "index";
	}

}
