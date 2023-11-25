package com.jacobin.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;
import com.jacobin.models.User;
import com.jacobin.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/manager-product"})
public class ManagerProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		int count = ProductDB.getTotalProduct();
    	int endPage = count/4;
    	if (count % 4 != 0) {
    		endPage ++;
    	}
    	req.setAttribute("endP", endPage);
    	
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
		List<Product> listP = ProductDB.pagingProduct(index);
		req.setAttribute("ListP", listP);
    	
//    	List<Product> list = ProductDB.pagingProduct(1);
//    	for (Product o: list ) {
//    		System.out.println(o);
//    	}
    	
    	String url = "/WEB-INF/views/admin/managerProductView.jsp";
    	req.getRequestDispatcher(url).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doGet(req, resp);
	}
}
