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
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;

@WebServlet("/admin/edit-category")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 11 // 11MB
		)
public class EditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);
		
		String editIdString = req.getParameter("editId");
		if (editIdString == null) {
			editIdString = (String) req.getAttribute("editId");
		}
		
		int editId = Integer.parseInt(editIdString);
		Category category = CategoryDB.selectCategoryById(editId);
		req.setAttribute("category", category);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/admin/editCategoryView.jsp");
    	
		dispatcher.forward(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String idString = req.getParameter("id");
	    String name = req.getParameter("name");

	    try {
	        int id = Integer.parseInt(idString);

	        // Tạo đối tượng Category với thông tin mới
	        Category updatedCategory = new Category();
	        updatedCategory.setCategoryId(id);
	        updatedCategory.setName(name);

	        // Gọi phương thức update trong lớp CategoryDB để cập nhật database
	        CategoryDB.update(updatedCategory);

	        String message = "Chỉnh sửa thành công danh mục có mã: " + idString;
	        req.setAttribute("message", message);
	        req.setAttribute("editId", idString);

	    } catch (NumberFormatException e) {
	        // Xử lý nếu giá trị idString không phải là số nguyên
	        e.printStackTrace();
	        String errorMessage = "Lỗi: Mã danh mục không hợp lệ.";
	        req.setAttribute("errorMessage", errorMessage);
	    }

	    // Chuyển hướng về trang chỉnh sửa với thông báo hoặc lỗi
	    doGet(req, resp);
	}

}
