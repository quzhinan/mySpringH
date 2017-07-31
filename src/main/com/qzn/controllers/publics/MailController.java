package com.qzn.controllers.publics;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qzn.controllers.AbstractController;
import com.qzn.models.Email;
import com.qzn.utils.PropertyUtil;
import com.qzn.utils.SpringEmailUtil;
import com.qzn.utils.VelocitiesUtil;

@Controller
@RequestMapping("/manager/emailFunc")
public class MailController {

	private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

	@RequestMapping(value = { "/email" })
	public String email(Model model) {
		Email email = new Email();
		email.setFromEmailAddress(PropertyUtil.getPropertyValue("mail.fromAddress"));
		email.setFromPersonName(PropertyUtil.getPropertyValue("mail.username"));
		email.setToEmailAddresses(new String[] { "sendToAddress" });
		email.setSubject("标题");
		Map<String, Object> velocityContext = new HashMap<>();
		velocityContext.put("name", "name");
		velocityContext.put("content", "content");
		String content = VelocitiesUtil.getVelocityText("email.vm", velocityContext);
		email.setContent(content);
		model.addAttribute("email", email);
		return "tiles.admin.email";
	}

	@RequestMapping(value = { "sendEmail" }, method = RequestMethod.POST)
	public String sendEmail(@ModelAttribute Email email, Model model) {
		// model.addAttribute("email", email);
		email.setToEmailAddresses(email.getToEmailAddresses()[0].split(","));
		try {
			SpringEmailUtil.sendEmail(email);
			model.addAttribute("result", "发送邮件成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", e.getMessage());
		}
		return "tiles.admin.email";
	}


}
