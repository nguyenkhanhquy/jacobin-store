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

import com.jacobin.dao.DBUtil;
import com.jacobin.dao.CartDB;
import com.jacobin.dao.CategoryDB;
import com.jacobin.models.Cart;
import com.jacobin.models.Category;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Product;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		DBUtil.getEmFactory();
		
		HttpSession	session = req.getSession();
		
		// Lấy thông tin giỏ hàng của người dùng
		Cart cart = CartDB.selectCartByUser(SessionUtil.getLoginedUser(session));
		session.setAttribute("cart", cart);
		
		String cId = null;
		req.setAttribute("cId", cId);
		
    	List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);
		List<Product> listP = ProductDB.select20FirstProduct();
    	req.setAttribute("ListP", listP);
    	
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
    	
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
