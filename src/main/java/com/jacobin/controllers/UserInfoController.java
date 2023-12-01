package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.UserDB;
import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = { "/user-info" })
public class UserInfoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession	session = req.getSession();
		User user = SessionUtil.getLoginedUser(session);
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customer/userInfoView.jsp");
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/userInfoView.jsp";
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		HttpSession session = req.getSession();
		User userS = SessionUtil.getLoginedUser(session);
		
		
		String message = "";
		if (!email.equals(userS.getEmail()) && UserDB.checkEmailExists(email)) {
			message = "Địa chỉ Email đã tồn tại.<br>" + "Vui lòng điền một địa chỉ Email khác.";
		} else if (!phone.equals(userS.getPhone()) && UserDB.checkPhoneExists(phone)) {
			message = "Số điện thoại đã tồn tại.<br>" + "Vui lòng điền số điện thoại khác.";
		} else {
			url = "/WEB-INF/views/customer/successView.jsp";
			userS.setFirstName(firstName);
			userS.setLastName(lastName);
			userS.setDateOfBirth(dateOfBirth);
			userS.setAddress(address);
			userS.setEmail(email);
			userS.setPhone(phone);
			UserDB.update(userS);
			message = "Cập nhật thành công!";
		}
		
		User userR = new User();
		userR.setFirstName(firstName);
		userR.setLastName(lastName);
		userR.setDateOfBirth(dateOfBirth);
		userR.setAddress(address);
		userR.setEmail(userS.getEmail());
		userR.setPhone(userS.getPhone());
		userR.setRole(userS.getRole());
		
		req.setAttribute("user", userR);
		req.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
}
