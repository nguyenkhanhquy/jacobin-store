package com.jacobin.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jacobin.dao.DBUtil;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Product;

@WebServlet(urlPatterns = { "/load" })
public class LoadMoreController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
		String amount = req.getParameter("exits");
		int iamount = Integer.parseInt(amount);
		
		DBUtil.getEmFactory();
		
		List<Product> listP = ProductDB.selectNext10Product(iamount);
    	PrintWriter out = resp.getWriter();
    	
    	for (Product p : listP ) {
    		out.println("<div class=\"product col-lg-4 col-md-6 mb-4\">\r\n"
    				+ "                            <div class=\"card h-100\">\r\n"
    				+ "                                <a href=\"#\"><img class=\"card-img-top\" src=\""+p.getImage()+"\" alt=\"\"></a>\r\n"
    				+ "                                <div class=\"card-body\">\r\n"
    				+ "                                    <h4 class=\"card-title\">\r\n"
    				+ "                                        <a href=\"#\">"+p.getName()+"</a>\r\n"
    				+ "                                    </h4>\r\n"
    				+ "                                    <h5><fmt:formatNumber type=\"number\" value=\""+p.getPrice()+"\" pattern=\"#,##0\" /> VNĐ</h5>\r\n"
    				+ "                                    <p class=\"card-text\">"+p.getDescription()+"</p>\r\n"
    				+ "                                </div>\r\n"
    				+ "                                <div class=\"card-footer\">\r\n"
    				+ "                                    <small class=\"text-muted\">&#9733; &#9733; &#9733; &#9733; &#9734;</small>\r\n"
    				+ "                                </div>\r\n"
    				+ "                            </div>\r\n"
    				+ "                        </div>");
    	}
	}
}
