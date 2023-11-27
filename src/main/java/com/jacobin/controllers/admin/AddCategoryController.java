package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.CategoryDB;
import com.jacobin.models.Category;

@WebServlet(urlPatterns = { "/admin/add-category" })
public class AddCategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/admin/addCategoryView.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String name = req.getParameter("name");
		
		String message;
		String messageError;
		if (CategoryDB.checkNameExists(name)) {
			messageError = "Tên danh mục đã tồn tại! Vui lòng điền tên khác.";
			message = "";
		}
		else {
			Category category = new Category();
			category.setName(name);

			CategoryDB.insert(category);
			
			category = CategoryDB.selectCategoryByName(category.getName());
			int idC = category.getCategoryId();

			message = "Thêm thành công danh mục có mã: " + idC;
			messageError = "";
		}
		
		req.setAttribute("message", message);
		req.setAttribute("messageError", messageError);
		doGet(req, resp);
	}

}
