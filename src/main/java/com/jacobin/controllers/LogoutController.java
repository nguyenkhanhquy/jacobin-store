package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.utils.CookieUtil;

@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		String url = "/WEB-INF/views/customer/successView.jsp";
		String message = "Đăng xuất thành công!";
		
		// Xóa Cookie
		CookieUtil.deleteUserCookie(resp);
		
		// Xoá Session
		HttpSession session = req.getSession();
		session.removeAttribute("loginedUser");
		session.removeAttribute("cart");
	
		req.setAttribute("message", message);
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
