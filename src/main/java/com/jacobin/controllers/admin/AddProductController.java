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
import javax.servlet.http.Part;

import com.jacobin.utils.S3Util;
import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;

@WebServlet(urlPatterns = { "/admin/add-product" })
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 11 // 11MB
		)
public class AddProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/admin/addProductView.jsp");
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String size = req.getParameter("size");
		String categoryIdString = req.getParameter("category");
		int categoryId = Integer.parseInt(categoryIdString);
		Category category = CategoryDB.selectCategoryById(categoryId);
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(Double.parseDouble(price));
		product.setTitle(title);
		product.setDescription(description);
		product.setSize(size);
		product.setCategory(category);	
		ProductDB.insert(product);
		
		List<Product> listP = ProductDB.selectProductByIdDesc();
		
		int idP;
		
		if (!listP.isEmpty()) {
		    Product lastP = listP.get(0);
		    idP = lastP.getProductId();
		} else {
			idP = 1;
		}
		
		Part part = req.getPart("file");
		
		String originalFileName = S3Util.getFileName(part);
		
		String newFileName = idP + originalFileName.substring(originalFileName.lastIndexOf('.'));
		
		String fileName = S3Util.AWS_URL_FOLDER + newFileName;

		S3Util.uploadFile(fileName, part.getInputStream());
		
		String urlImage = "https://" + S3Util.AWS_BUCKET + ".s3.amazonaws.com/" + fileName;
		
		product.setImage(urlImage);
		ProductDB.update(product);
		
		String message = "Thêm thành công sản phẩm có mã: " + idP;
		req.setAttribute("message", message);
		
		doGet(req, resp);
	}
}
