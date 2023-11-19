package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.RoleDB;
import com.jacobin.dao.UserDB;
import com.jacobin.models.Role;
import com.jacobin.models.User;
import com.jacobin.utils.PasswordEncryptorUtil;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customer/registerView.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/registerView.jsp";
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String passwordAgain = req.getParameter("passwordAgain");
		
		Role role = RoleDB.selectRoleByID(2);
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(dateOfBirth);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		user.setUserName(userName);
		user.setPassword(password);
		user.setRole(role);
		
		String message = "";
		if (UserDB.checkExists(user.getEmail())) {
			message = "Địa chỉ Email đã tồn tại.<br>" + "Vui lòng điền một địa chỉ Email khác.";
		} else if (UserDB.checkExists(user.getPhone())) {
			message = "Số điện thoại đã tồn tại.<br>" + "Vui lòng điền số điện thoại khác.";
		} else if (UserDB.checkExists(user.getUserName())) {
			message = "Tên đăng nhập đã tồn tại.<br>" + "Vui lòng điền tên đăng nhập khác.";
		} else if (!user.getPassword().equals(passwordAgain)) {
			message = "Mật khẩu không khớp.<br>" + "Vui lòng nhập lại.";
		} else {
			url = "/WEB-INF/views/customer/successView.jsp";
			password = PasswordEncryptorUtil.toSHA1(password);
			user.setPassword(password);
			UserDB.insert(user);
		}
		
		req.setAttribute("user", user);
		req.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
}
