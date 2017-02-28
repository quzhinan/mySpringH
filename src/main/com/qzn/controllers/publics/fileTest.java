package com.qzn.controllers.publics;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;

@Controller
public class fileTest {
	private static final Logger log = LoggerFactory.getLogger(fileTest.class);
	
	
	@RequestMapping(value = { "fileUpload.htm" })
	public String fileUpload() {
		return "index";
	}

}
