package com.qzn.controllers.publics;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzn.controllers.AbstractController;
import com.qzn.models.User;
import com.qzn.services.UserService;

@Controller
public class JavaController extends AbstractController{

	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);
	
	@Autowired
	private UserService userService;
	
	
	public static String getJsonString(Object data) throws Exception {
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}
	
}
