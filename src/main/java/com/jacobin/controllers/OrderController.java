package com.jacobin.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.CartDB;
import com.jacobin.dao.LineItemDB;
import com.jacobin.dao.OrderDB;
import com.jacobin.dao.OrderTrackDB;
import com.jacobin.models.Cart;
import com.jacobin.models.DetailOrder;
import com.jacobin.models.LineItem;
import com.jacobin.models.Order;
import com.jacobin.models.OrderTrack;
import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = { "/order" })
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession	session = req.getSession();
		
		Date currentDate = new Date();
		
		Order order = new Order();
		
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		User user = SessionUtil.getLoginedUser(session);
		String paymentMethod = req.getParameter("shippingMethod");
		String shippingMethod = req.getParameter("paymentMethod");
		String totalPrice = req.getParameter("totalPrice");
		
		OrderTrack orderTrack = OrderTrackDB.selectOrderTrackById(1);
		
		order.setDate(currentDate);
		order.setPhone(phone);
		order.setAddress(address);
		order.setUser(user);
		order.setPaymentMethod(paymentMethod);
		order.setShippingMethod(shippingMethod);
		order.setTotalPrice(totalPrice);
		order.setOrderTrack(orderTrack);
		
		Cart cart = CartDB.selectCartByUser(SessionUtil.getLoginedUser(session));
		List<LineItem> items = cart.getItems();
		// Tạo một danh sách tạm thời để lưu trữ các item cần xóa
		List<LineItem> itemsToDelete = new ArrayList<>();

		for (LineItem item : items) {
		    itemsToDelete.add(item);
		}

		for (LineItem item : itemsToDelete) {
			DetailOrder detailOrder = new DetailOrder();
			detailOrder.setNameProduct(item.getProduct().getName());
			detailOrder.setSize(item.getProduct().getSize());
			detailOrder.setQuantity(item.getQuantity());
			detailOrder.setPrice(item.getProduct().getPrice());
			order.addItem(detailOrder);
			
		    cart.removeItem(item);
		    CartDB.update(cart); 
		    LineItemDB.delete(item);
		}
		OrderDB.insert(order);
		session.setAttribute("cart", cart);
		
		String url = "/WEB-INF/views/customer/successView.jsp";
		String message = "Đặt hàng thành công!";
		
		req.setAttribute("message", message);
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
