package com.jacobin.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;
import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = {"/manager-product"})
public class ManagerProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String url = "/WEB-INF/views/admin/managerProductView.jsp";
		
		HttpSession session = req.getSession();
		User userS = SessionUtil.getLoginedUser(session);
		
		if (userS.getRole().getRoleId() == 2) {
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		} else {
			List<Category> listC = CategoryDB.selectAllCategory();
			req.setAttribute("ListC", listC);
			List<Product> listP = ProductDB.selectAllProduct();
	    	req.setAttribute("ListP", listP);
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(url);
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
