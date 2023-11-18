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
import com.jacobin.utils.PasswordEncryptorUtil;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = {"/update-password"})
public class UpdatePasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customer/updatePasswordView.jsp");
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/updatePasswordView.jsp";
		
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String newPasswordAgain = req.getParameter("newPasswordAgain");
		
		HttpSession session = req.getSession();
		User user = SessionUtil.getLoginedUser(session);
		
		String message;
		if (!PasswordEncryptorUtil.toSHA1(password).equals(user.getPassword())) {
			message = "Mật khẩu hiện tại không chính xác!";
		} else if (!newPassword.equals(newPasswordAgain)) {
			message = "Mật khẩu nhập lại không khớp!";
		} else {
			message = "";
			url = "/WEB-INF/views/customer/successView.jsp";
			newPassword = PasswordEncryptorUtil.toSHA1(newPassword);
			user.setPassword(newPassword);
			UserDB.update(user);
		}
		
		req.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
}
