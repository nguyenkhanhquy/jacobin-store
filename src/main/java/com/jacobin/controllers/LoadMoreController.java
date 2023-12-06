package com.jacobin.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.ProductDB;
import com.jacobin.models.Product;

@WebServlet(urlPatterns = { "/load" })
public class LoadMoreController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
		String categoryIdString = req.getParameter("cId");
		
		String amount = req.getParameter("exits");
		int iamount = Integer.parseInt(amount);
		
		List<Product> listP = ProductDB.selectNext10Product(iamount);
		if (categoryIdString != null && !categoryIdString.isEmpty()) {
			int categoryId = Integer.parseInt(categoryIdString);
			listP = ProductDB.selectNext5ProductByCategoryId(categoryId, iamount);
		}
		
    	PrintWriter out = resp.getWriter();
    
    	for (Product p : listP ) {
    		out.println("<div class=\"product col-lg-4 col-md-6 mb-4\">\r\n"
    				+ "	                            <div class=\"card h-100\">\r\n"
    				+ "	                                <a href=\"detail?pId=${p.productId}\"><img class=\"card-img-top\" src=\""+p.getImage()+"\" alt=\"\"></a>\r\n"
    				+ "	                                <div class=\"card-body\">\r\n"
    				+ "	                                    <h4 class=\"card-title\">\r\n"
    				+ "	                                        <a href=\"detail?pId="+p.getProductId()+"\">"+p.getName()+"</a> - "+p.getSize()+"\r\n"
    				+ "	                                    </h4>\r\n"
    				+ "	                                    <h5>"+p.getPriceCurrencyFormat()+"</h5>\r\n"
    				+ "	                                    <p class=\"card-text\">"+p.getTitle()+"</p>\r\n"
    				+ "	                                </div>\r\n"
    				+ "	                                <div class=\"card-footer\">\r\n"
    				+ "	                                	<a href=\"detail?pId="+p.getProductId()+"\" class=\"btn btn-outline-dark\">Chi tiết</a>\r\n"
    				+ "	                                	<form action=\"cart\" method=\"post\" style=\"display: inline-block;\">\r\n"
    				+ "	                                		<input type=\"hidden\" name=\"action\" value=\"add\">\r\n"
    				+ "									        <input type=\"hidden\" name=\"productId\" value=\""+p.getProductId()+"\">\r\n"
    				+ "									        <button class=\"btn btn-outline-primary\" type=\"submit\"><i class=\"fas fa-shopping-cart\">&nbsp;</i>Thêm vào giỏ hàng</button>\r\n"
    				+ "								      	</form>\r\n"
    				+ "	                                </div>\r\n"
    				+ "	                            </div>\r\n"
    				+ "	                        </div>");
    	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
