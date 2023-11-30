package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = { "/admin/add-user" })
public class AddUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		List<Role> listR = RoleDB.selectAllRole();
		req.setAttribute("ListR", listR);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/addUserView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String passwordAgain = req.getParameter("passwordAgain");
		String roleIdString = req.getParameter("role");
		int roleId = Integer.parseInt(roleIdString);
		Role role = RoleDB.selectRoleByID(roleId);
		
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
		req.setAttribute("user", user);
		
		String message = "";
		String messageError = "";
		if (UserDB.checkEmailExists(user.getEmail())) {
			messageError = "Địa chỉ Email đã tồn tại.<br>" + "Vui lòng điền một địa chỉ Email khác.";
		} else if (UserDB.checkPhoneExists(user.getPhone())) {
			messageError = "Số điện thoại đã tồn tại.<br>" + "Vui lòng điền số điện thoại khác.";
		} else if (UserDB.checkUserNameExists(user.getUserName())) {
			messageError = "Tên đăng nhập đã tồn tại.<br>" + "Vui lòng điền tên đăng nhập khác.";
		} else if (!user.getPassword().equals(passwordAgain)) {
			messageError = "Mật khẩu không khớp.<br>" + "Vui lòng nhập lại.";
		} else {
			message = "Thêm thành công người dùng có tên người dùng là: " + userName;
			password = PasswordEncryptorUtil.toSHA1(password);
			user.setPassword(password);
			UserDB.insert(user);
			
			if (user.getRole().getRoleId() != 1) {
				Cart cart = new Cart();
				cart.setUser(user);
				CartDB.insert(cart);
			}			
			
			req.removeAttribute("user");
		}
		
		req.setAttribute("message", message);
		req.setAttribute("messageError", messageError);
		
		doGet(req, resp);
	}
}
