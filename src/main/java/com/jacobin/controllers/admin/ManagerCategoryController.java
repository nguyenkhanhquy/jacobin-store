package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;

@WebServlet("/admin/manager-category")
public class ManagerCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String message = "";
		
		String deleteIdString = req.getParameter("deleteId");
		if (deleteIdString != null) {
			int deleteId = Integer.parseInt(deleteIdString);
			Category category = CategoryDB.selectCategoryById(deleteId);	
			CategoryDB.delete(category);
			message = "Xoá thành công danh mục có mã: " + deleteId;
		}
		
		int count = ProductDB.getTotalProduct();
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
			
			List<Category> listC = CategoryDB.selectAllCategory();
			req.setAttribute("ListC", listC);
		}
		
		req.setAttribute("message", message);
    	String url = "/WEB-INF/views/admin/managerCategoryView.jsp";
    	req.getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
