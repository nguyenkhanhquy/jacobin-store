package com.jacobin.controllers.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.OrderDB;
import com.jacobin.dao.OrderTrackDB;
import com.jacobin.models.Order;
import com.jacobin.models.OrderTrack;
import com.jacobin.utils.MailUtilGmail;

@WebServlet(urlPatterns = {"/admin/manager-order"})
public class ManagerOrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String message = "";
		
		String checkIdString = req.getParameter("checkId");
		if (checkIdString != null) { 
			int checkId = Integer.parseInt(checkIdString);
			Order order = OrderDB.selectOrderById(checkId);
			OrderTrack orderTrack = OrderTrackDB.selectOrderTrackById(2);
			order.setOrderTrack(orderTrack);
			OrderDB.update(order);
			
			// Gửi email đến email của user
			String to = order.getUser().getEmail();
			String from = "shop.javamail@gmail.com";
			String subject = "Xác nhận đơn hàng";
			String body = "Chào " + order.getUser().getFirstName() + ",\n\n"
					+ "Chúng tôi báo rằng đơn hàng có mã đơn hàng: " + order.getOrderId() + " của bạn đã được xác nhận!\n\n"
					+ "Đơn hàng sẽ sớm được gửi đến địa chỉ của bạn.\n\n"
					+ "Chúc bạn có những trải nghiệm mua sắm thú vị và hài lòng!\n\n" 
					+ "Trân trọng, Jacobin Store.";
			boolean isBodyHTML = false;
			
			try {
				MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			message = "Xác nhận thành công đơn hàng có mã: " + checkId;
		}
		
		int count = OrderDB.getTotalOrder();
		int endPage = count/10;
    	if (count % 10 != 0) {
    		endPage ++;
    	}
    	req.setAttribute("endP", endPage);
		if (count != 0) {
	    	int index;
			String indexPage = req.getParameter("index");
			try {
				index = Integer.parseInt(indexPage);
			}
			catch (NumberFormatException nfe) {
				index = 1;
			}
			
			if (index <= 0) {
				index = 1;
			} else if (index > endPage) {
				index = endPage;
			}
			req.setAttribute("tag", index);
			
			List<Order> listO = OrderDB.pagingOrder(index);
			req.setAttribute("ListO", listO);
		}
    	
		req.setAttribute("message", message);
		
    	req.getRequestDispatcher("/WEB-INF/views/admin/managerOrderView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
