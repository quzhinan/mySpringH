package com.qzn.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.qzn.models.AdminUser;
import com.qzn.services.UserService;

/**
 * Servlet implementation class TestServlet
 */

@WebServlet("/TestServlet")
public class TestServlet extends ServletProxy {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminUser adminUser = userService.findByProperty("username", username);
		response.getWriter().print(adminUser);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AdminUser.class);
		Criterion multipleCriteria1 = Restrictions.eq("username", username);
		detachedCriteria.add(multipleCriteria1);
		Criterion multipleCriteria2 = Restrictions.eq("password", password);
		detachedCriteria.add(multipleCriteria2);
		List<AdminUser> adminUsers = userService.findAllByCriteria(detachedCriteria);
		response.getWriter().print(adminUsers);
		List<AdminUser> adminUsers1 = userService.findTopByCriteria(detachedCriteria, 10,
				new Order[] { Order.desc("id") });
		response.getWriter().print(adminUsers1);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
