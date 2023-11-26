package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.CategoryDB;
import com.jacobin.models.Category;

@WebServlet("/admin/add-category")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 11 // 11MB
		)
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

	    List<Category> listC = CategoryDB.selectAllCategory();

	    int idC;

	    if (!listC.isEmpty()) {
	        Category lastC = listC.get(listC.size() - 1);
	        idC = lastC.getCategoryId() + 1;
	    } else {
	        idC = 1;
	    }

	    Category category = new Category();
	    category.setName(name);

	    CategoryDB.insert(category);

	    String message = "Thêm thành công danh mục có mã: " + idC;
	    req.setAttribute("message", message);

	    doGet(req, resp);
	}

}
