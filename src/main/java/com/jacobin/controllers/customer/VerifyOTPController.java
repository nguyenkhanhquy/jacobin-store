package com.jacobin.controllers.customer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.CartDB;
import com.jacobin.dao.UserDB;
import com.jacobin.models.Cart;
import com.jacobin.models.User;
import com.jacobin.utils.MailUtilGmail;

@WebServlet(urlPatterns = { "/verify-otp" })
public class VerifyOTPController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.sendRedirect("home");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/verifyOTPView.jsp";
		
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		
		HttpSession	session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		String message;
		if (action.equals("send")) {
			message = "Đã gửi mã OTP tới email của bạn!";
			
			Random random = new Random();
	        int otp = random.nextInt(900000) + 100000;
	        String otpString = String.valueOf(otp);
	        session.setAttribute("otpSend", otpString);
			
			// Gửi email đến email của user
			String to = user.getEmail();
			String from = "shop.javamail@gmail.com";
			String subject = "Xác minh email";
			String body = "Chào " + user.getFirstName() + ",\n\n"
					+ "Mã OTP của bạn là: " + otpString;
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
		} else if (action.equals("confirm")) {
			String otp = req.getParameter("otp");
			
			String otpSend = (String) session.getAttribute("otpSend");
			if (otp.equals(otpSend)) {
				message = "Đăng ký thành công!";
				url = "/WEB-INF/views/customer/successView.jsp";				
				UserDB.insert(user);
				
				Cart cart = new Cart();
				cart.setUser(user);
				CartDB.insert(cart);
				
				// Gửi email đến email của user
				String to = user.getEmail();
				String from = "shop.javamail@gmail.com";
				String subject = "Chào mừng đến với Jacobin Store";
				String body = "Chào " + user.getFirstName() + ",\n\n"
						+ "Chúng tôi rất vui mừng thông báo rằng bạn đã đăng ký thành công tài khoản mới tại Jacobin Store!\n\n"
						+ "Chào mừng bạn đến với cửa hàng của chúng tôi và cảm ơn bạn đã chọn chúng tôi để trải nghiệm mua sắm trực tuyến.\n\n"
						+ "Hãy khám phá thế giới mua sắm tuyệt vời tại Jacobin Store ngay bây giờ.\n\n"
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
				
				session.removeAttribute("user");
				session.removeAttribute("otpSend");
			}
			else {
				message = "Mã OTP bạn nhập không đúng!";
			}
		} else {
			message = "";
		}
		
		req.setAttribute("message", message);
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
