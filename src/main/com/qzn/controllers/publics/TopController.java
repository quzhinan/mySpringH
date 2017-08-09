package com.qzn.controllers.publics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;

@Controller
public class TopController extends AbstractController {

	@RequestMapping("/index")
	public Page frontPage() throws Exception {
		return Page("filters-index");
	}

	@RequestMapping("/lockscreen")
	public Page lockscreen() throws Exception {
		return Page("filters-lockscreen");
	}

	@RequestMapping("/dashboard")
	public Page dashboard() throws Exception {
		return Page("functions-dashboard");
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

}
