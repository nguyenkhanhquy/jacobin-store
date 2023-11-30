package com.jacobin.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.CartDB;
import com.jacobin.dao.RoleDB;
import com.jacobin.dao.UserDB;
import com.jacobin.models.Cart;
import com.jacobin.models.Role;
import com.jacobin.models.User;
import com.jacobin.utils.PasswordEncryptorUtil;

import com.jacobin.utils.MailUtilGmail;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customer/registerView.jsp");

		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String url = "/WEB-INF/views/customer/registerView.jsp";
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String passwordAgain = req.getParameter("passwordAgain");
		
		Role role = RoleDB.selectRoleByID(2);
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(dateOfBirth);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		user.setUserName(userName);
		user.setPassword(password);
		user.setRole(role);
		
		String message;
		if (UserDB.checkEmailExists(user.getEmail())) {
			message = "Địa chỉ Email đã tồn tại.<br>" + "Vui lòng điền một địa chỉ Email khác.";
		} else if (UserDB.checkPhoneExists(user.getPhone())) {
			message = "Số điện thoại đã tồn tại.<br>" + "Vui lòng điền số điện thoại khác.";
		} else if (UserDB.checkUserNameExists(user.getUserName())) {
			message = "Tên đăng nhập đã tồn tại.<br>" + "Vui lòng điền tên đăng nhập khác.";
		} else if (!user.getPassword().equals(passwordAgain)) {
			message = "Mật khẩu không khớp.<br>" + "Vui lòng nhập lại.";
		} else {
			message = "Đăng ký thành công!";
			url = "/WEB-INF/views/customer/successView.jsp";
			password = PasswordEncryptorUtil.toSHA1(password);
			user.setPassword(password);
			UserDB.insert(user);
			
			Cart cart = new Cart();
			cart.setUser(user);
			CartDB.insert(cart);
			
			// Gửi email đến email của user
			String to = email;
			String from = "shop.javamail@gmail.com";
			String subject = "Chào mừng đến với Jacobin Store";
			String body = "Chào " + firstName + ",\n\n"
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
		}
		
		req.setAttribute("user", user);
		req.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
}
