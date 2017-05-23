package com.qzn.controllers.publics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;

@Controller
public class AddressController {
	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@RequestMapping(value = { "address.htm" })
	public String address() {
		return "address";
	}
}
