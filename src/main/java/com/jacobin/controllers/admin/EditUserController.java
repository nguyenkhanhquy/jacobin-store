package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.RoleDB;
import com.jacobin.dao.UserDB;
import com.jacobin.models.Role;
import com.jacobin.models.User;

@WebServlet(urlPatterns = { "/admin/edit-user" })
public class EditUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		List<Role> listR = RoleDB.selectAllRole();
		req.setAttribute("ListR", listR);
		
		String editIdString = req.getParameter("editId");
		if (editIdString != null) {
			int editId = Integer.parseInt(editIdString);
			User user = UserDB.selectUserById(editId);
			req.setAttribute("user", user);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/admin/editUserView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String idString = req.getParameter("id");
		int id = Integer.parseInt(idString);
		User userS = UserDB.selectUserById(id);
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateOfBirth = req.getParameter("dateOfBirth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String roleIdString = req.getParameter("role");
		int roleId = Integer.parseInt(roleIdString);
		Role role = RoleDB.selectRoleByID(roleId);

		String message = "";
		String messageError = "";
		if (!email.equals(userS.getEmail()) && UserDB.checkEmailExists(email)) {
			messageError = "Địa chỉ Email đã tồn tại.<br>" + "Vui lòng điền một địa chỉ Email khác.";
		} else if (!phone.equals(userS.getPhone()) && UserDB.checkPhoneExists(phone)) {
			messageError = "Số điện thoại đã tồn tại.<br>" + "Vui lòng điền số điện thoại khác.";
		} else {
			message = "Chỉnh sửa thành công người dùng có tên đăng nhập là: " + userS.getUserName();
			userS.setFirstName(firstName);
			userS.setLastName(lastName);
			userS.setDateOfBirth(dateOfBirth);
			userS.setAddress(address);
			userS.setEmail(email);
			userS.setPhone(phone);
			UserDB.update(userS);
			req.removeAttribute("userS");
		}
		
		User user = new User();
		user.setUserId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(dateOfBirth);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRole(role);
		req.setAttribute("user", user);
		
		req.setAttribute("message", message);
		req.setAttribute("messageError", messageError);
		
		doGet(req, resp);
	}
}
