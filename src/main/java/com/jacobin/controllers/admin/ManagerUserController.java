package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

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
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/manager-user"})
public class ManagerUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String message = "";
		
		String deleteIdString = req.getParameter("deleteId");
		if (deleteIdString != null) {
			int deleteId = Integer.parseInt(deleteIdString);
			User user = UserDB.selectUserById(deleteId);
			
			HttpSession session = req.getSession();
			User userInSession = SessionUtil.getLoginedUser(session);
			
			if (user != null && user.getUserName() != userInSession.getUserName()) {
				if (user.getRole().getRoleId() != 1) {
					Cart cart = CartDB.selectCartByUser(user);
					CartDB.delete(cart);
				}
				UserDB.delete(user);
				message = "Xoá thành công người dùng có mã: " + deleteId;
			}
		}
		
		int count = UserDB.getTotalUser();
		int endPage = count/4;
    	if (count % 4 != 0) {
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
			
			List<User> listU = UserDB.pagingUser(index);
			req.setAttribute("ListU", listU);
		}
    	
		req.setAttribute("message", message);
		
    	req.getRequestDispatcher("/WEB-INF/views/admin/managerUserView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
