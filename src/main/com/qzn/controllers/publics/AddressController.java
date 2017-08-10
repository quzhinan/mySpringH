package com.qzn.controllers.publics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressController {

	@RequestMapping(value = { "address.htm" })
	public String address() {
		return "tiles.admin.address";
	}
}
