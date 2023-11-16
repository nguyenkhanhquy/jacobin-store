package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.data.RoleDB;
import com.jacobin.data.UserDB;
import com.jacobin.models.Role;
import com.jacobin.models.User;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String url = "/register.jsp";

		String action = request.getParameter("action");
		if (action == null) {
			action = "join"; 
		}

		if (action.equals("join")) {
			url = "/register.jsp";
		} else if (action.equals("add")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			
			int roleId = 2;
			Role role = RoleDB.selectRole(roleId);
			
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

			String message;
			if (UserDB.checkExists(user.getEmail())) {
				message = "Địa chỉ Email đã được đăng ký.<br>" + "Vui lòng điền một địa chỉ Email khác.";
				url = "/register.jsp";
			} 
			else if (UserDB.checkExists(user.getPhone())) {
				message = "Số điện thoại đã được đăng ký.<br>" + "Vui lòng điền số điện thoại khác.";
				url = "/register.jsp";
			}
			else if (UserDB.checkExists(user.getUserName())) {
				message = "Tên đăng nhập đã được đăng ký.<br>" + "Vui lòng điền tên đăng nhập khác.";
				url = "/register.jsp";
			}
			else {
				message = "";
				url = "/thanks.jsp";
				UserDB.insert(user);
			}
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
