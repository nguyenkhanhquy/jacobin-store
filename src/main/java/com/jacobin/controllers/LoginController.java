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
import com.jacobin.utils.CookieUtil;
import com.jacobin.utils.PasswordEncryptorUtil;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// Forward (chuyển hướng) tới trang /WEB-INF/views/customer/loginView.jsp
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customer/loginView.jsp");

		dispatcher.forward(req, resp);
	}
	
	// Khi người nhập userName & password, và nhấn Submit.
	// Phương thức này sẽ được thực thi.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String rememberMeStr = req.getParameter("rememberMe");
		boolean rememberMe = "Y".equals(rememberMeStr);
		
		User user = UserDB.selectUserByUserName(userName);
		
		String url = "/WEB-INF/views/customer/loginView.jsp";
		String message;
		
		// Trường hợp có lỗi thì forward (chuyển hướng) tới /WEB-INF/views/customer/loginView.jsp
		if (user == null || !user.getPassword().equals(PasswordEncryptorUtil.toSHA1(password))) {
			message = "Tên đăng nhập hoặc mật khẩu không đúng!";
			
			// Lưu các thông tin vào request attribute trước khi forward.
			req.setAttribute("message", message);
			
			// Forward (Chuyển tiếp) tới trang /WEB-INF/views/customer/loginView.jsp
			getServletContext().getRequestDispatcher(url).forward(req, resp);
		} 
		// Trường hợp không có lỗi.
		// Lưu thông tin người dùng vào Session Và chuyển hướng sang trang home.
		else {
			
			HttpSession	session = req.getSession();
			SessionUtil.storeLoginedUser(session, user);
			
			// Nếu người dùng chọn tính năng "Remember me".
			if (rememberMe) {
				CookieUtil.storeUserCookie(resp, user);
			}
			
			url = "/home";
			resp.sendRedirect(req.getContextPath() + url);
			//resp.sendRedirect("http://localhost:8080/jacobin-store" + url);
		}
	}
}
