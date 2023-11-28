package com.jacobin.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.CartDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Cart;
import com.jacobin.models.LineItem;
import com.jacobin.models.Product;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = { "/cart" })
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String url = "/WEB-INF/views/_cartView.jsp";

		String action = req.getParameter("action");
		if (action == null) {
			action = "view";
		}

		if (action.equals("add") || action.equals("update")) {
			String productIdString = req.getParameter("productId");
			int productId = Integer.parseInt(productIdString);
			String quantityString = req.getParameter("quantity");

			HttpSession session = req.getSession();
			Cart cart;
			final Object lock = req.getSession().getId().intern();
			synchronized (lock) {
				cart = (Cart) session.getAttribute("cart");
			}
			
//			HttpSession session = req.getSession();
//			Cart cart;
//			cart = CartDB.selectCartByUser(SessionUtil.getLoginedUser(session));
//
			if (cart == null) {
				cart = new Cart();
			}

			int quantity;
			if (action.equals("add")) {
				quantity = 1;
			} else {
				try {
					quantity = Integer.parseInt(quantityString);
					if (quantity < 0) {
						quantity = -1;
					}
				} catch (NumberFormatException nfe) {
					quantity = -1;
				}
			}

			Product product = ProductDB.selectProductById(productId);

			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			lineItem.setQuantity(quantity);
			if (quantity != 0) {
				if (action.equals("add")) {
					cart.addItem(lineItem);
				} else {
					cart.updateItem(lineItem);
				}
			} else if (quantity == 0) {
				cart.removeItem(lineItem);
			}

			synchronized (lock) {
				session.setAttribute("cart", cart);
			}
//			CartDB.update(cart);

			resp.sendRedirect("cart");
			return;
		} else if (action.equals("checkout")) {
			url = "/WEB-INF/views/checkoutView.jsp";
		} else if (action.equals("home")) {
			resp.sendRedirect("home");
			return;
		}
		
		req.getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
