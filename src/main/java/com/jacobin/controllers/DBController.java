package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.DBUtil;

@WebServlet(urlPatterns = { "/createdb" })
public class DBController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DBUtil.getEmFactory();

		// Forward tới trang /WEB-INF/views/homeView.jsp
		// (Người dùng không bao giờ truy cập trực tiếp được vào các trang JSP đặt trong WEB-INF)
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
