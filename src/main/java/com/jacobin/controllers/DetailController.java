package com.jacobin.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;

@WebServlet(urlPatterns = { "/detail" })
public class DetailController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String productIdString = req.getParameter("pId");

		List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);
		
		try {
			int productId = Integer.parseInt(productIdString);
			Product product = ProductDB.selectProductById(productId);
			req.setAttribute("product", product);
		}
		catch (NumberFormatException nfe) {
			System.out.print("error");
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/detailProductView.jsp");
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
