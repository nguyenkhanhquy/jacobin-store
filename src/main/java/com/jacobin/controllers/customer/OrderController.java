package com.jacobin.controllers.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.OrderDB;
import com.jacobin.models.Order;
import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = {"/manager-order"})
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession	session = req.getSession();
		
		User user = SessionUtil.getLoginedUser(session);
				
		List<Order> listO = OrderDB.selectOrderByUser(user);
		req.setAttribute("ListO", listO);
		
    	req.getRequestDispatcher("/WEB-INF/views/customer/orderView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
