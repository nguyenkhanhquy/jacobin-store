package com.jacobin.controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.OrderDB;
import com.jacobin.models.Order;

@WebServlet(urlPatterns = { "/admin/detail-order" })
public class DetailOrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String detailIdString = req.getParameter("detailId");
		
		Order order = new Order();
		if (detailIdString != null) {
			int detailId = Integer.parseInt(detailIdString);
			order = OrderDB.selectOrderById(detailId);
		}
		
		req.setAttribute("order", order);

		String url = "/WEB-INF/views/admin/detailOrderView.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
