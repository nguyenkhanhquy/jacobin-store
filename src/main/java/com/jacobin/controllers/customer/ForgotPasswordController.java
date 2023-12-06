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

import com.jacobin.dao.UserDB;
import com.jacobin.models.User;
import com.jacobin.utils.MailUtilGmail;
import com.jacobin.utils.PasswordEncryptorUtil;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/forgotPasswordView.jsp";
		
        HttpSession	session = req.getSession();
		String email = null;
		String message = null;
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		} else {
			email = req.getParameter("email");
			if (action.equals("send")) {
				User user = UserDB.selectUserByEmail(email);
				if (user == null) {
					message = "Email không tồn tại trên hệ thống!";
				}
				else {
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
				}
			} else if (action.equals("confirm")) {
				String otp = req.getParameter("otp");
				
				String otpSend = (String) session.getAttribute("otpSend");
				
				if (otp.equals(otpSend)) {					
					url = "/WEB-INF/views/customer/newPasswordView.jsp";	
				}
				else {
					message = "Mã OTP bạn nhập không đúng!";
				}
			} else if (action.equals("newpass")){
				url = "/WEB-INF/views/customer/newPasswordView.jsp";
				
				String newPassword = req.getParameter("newPassword");
				String newPasswordAgain = req.getParameter("newPasswordAgain");
				
				email = req.getParameter("email");
				User user = UserDB.selectUserByEmail(email);

				if (!newPassword.equals(newPasswordAgain)) {
					message = "Mật khẩu nhập lại không khớp!";
				} else {
					message = "Đổi mật khẩu thành công!";
					url = "/WEB-INF/views/customer/successView.jsp";
					newPassword = PasswordEncryptorUtil.toSHA1(newPassword);
					user.setPassword(newPassword);
					UserDB.update(user);
					
					session.removeAttribute("otpSend");
				}
			} else {
				message = "";
			}
		}
		req.setAttribute("email", email);
		req.setAttribute("message", message);
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
