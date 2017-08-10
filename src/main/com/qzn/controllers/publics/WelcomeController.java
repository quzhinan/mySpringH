package com.qzn.controllers.publics;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qzn.auth.ActiveUser;
import com.qzn.auth.Authenticator;
import com.qzn.controllers.AbstractController;
import com.qzn.controllers.Page;
import com.qzn.models.Login;
import com.qzn.models.User;
import com.qzn.services.UserService;
import com.qzn.utils.DateUtil;
import com.qzn.utils.PropertyUtil;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	@Autowired
	private UserService userService;

	@ModelAttribute("login")
	public Login loadLogin() throws Exception {
		return new Login();
	}

	@RequestMapping("")
	public Page welcome() throws Exception {
		return Page("filters-welcome");
	}

	@RequestMapping("/login")
	public Page login(Login login) throws Exception {
		User loginUser = userService.auth(login.getUsername(), login.getPassword());
		getSession().invalidate();
		if (loginUser == null) {
			return Page("filters-welcome");
		}
		if (loginUser.getLoginLockStatus() == User.LOGIN_LOCK_STATUS_LOCKING) {
			return Page("filters-welcome");
		}
		if (loginUser.getDeleteFlag() == User.DELETE_FLAG_DELETED) {
			return Page("filters-welcome");
		}
		String timeout = PropertyUtil.getPropertyValue("password.date.timeout");
		Date addTime = DateUtil.addTime(loginUser.getUpdateDatetime(), Calendar.DATE, Integer.parseInt(timeout));
		String dateFormat = DateUtil.dateFormat(addTime, "yyyy-MM-dd");
		Date oldTime = DateUtil.fromString(dateFormat, "yyyy-MM-dd");
		Date newDate = DateUtil.fromString(DateUtil.dateFormat(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
		if (oldTime.getTime() < newDate.getTime() && loginUser.getPasswordStatus() == User.PASSWORD_STATUS_USERRESET) {
			loginUser.setPasswordStatus(User.PASSWORD_STATUS_SYSINIT);
			userService.saveUser(loginUser);
		}
		Authenticator.saveActiveUser(new ActiveUser<>(loginUser));
		if (loginUser.getLoginErrorCount() > 0) {
			loginUser.setLoginErrorCount(0);
			userService.saveUser(loginUser);
		}
		if (loginUser.getPasswordStatus() == User.PASSWORD_STATUS_SYSINIT) {
			return RedirectPage("dashboard");
		} else {
			getSession().invalidate();
			return Page("filters-welcome");
		}
	}

	@RequestMapping("/logout")
	public Page logout() {
		Authenticator.clearActiveUser();
		getSession().invalidate();
		return Page("filters-welcome");
	}

}
