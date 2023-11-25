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

import com.jacobin.dao.CategoryDB;
import com.jacobin.dao.ProductDB;
import com.jacobin.models.Category;
import com.jacobin.models.Product;
import com.jacobin.utils.S3Util;

@WebServlet(urlPatterns = { "/admin/edit-product" })
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 11 // 11MB
		)
public class EditProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Category> listC = CategoryDB.selectAllCategory();
		req.setAttribute("ListC", listC);
		
		String editIdString = req.getParameter("editId");
		if (editIdString == null) {
			editIdString = (String) req.getAttribute("editId");
		}
		
		int editId = Integer.parseInt(editIdString);
		Product product = ProductDB.selectProductById(editId);
		req.setAttribute("product", product);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/admin/editProductView.jsp");
    	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String idString = req.getParameter("id");
		int id = Integer.parseInt(idString);
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String size = req.getParameter("size");
		String categoryIdString = req.getParameter("category");
		int categoryId = Integer.parseInt(categoryIdString);
		Category category = CategoryDB.selectCategoryById(categoryId);
		
		
		String urlImage = req.getParameter("imageOld");
		
		Part part = req.getPart("file");
		
		if (part != null && part.getSize() > 0) {
			String originalFileName = S3Util.getFileName(part);
			  
			String newFileName = idString + originalFileName.substring(originalFileName.lastIndexOf('.'));
	  
			String fileName = S3Util.AWS_URL_FOLDER + newFileName;
	  
			S3Util.uploadFile(fileName, part.getInputStream());
			
			urlImage = "https://" + S3Util.AWS_BUCKET + ".s3.amazonaws.com/" + fileName;
		}
  
		Product product = new Product(); 
		product.setProductId(id);
		product.setName(name); product.setPrice(Double.parseDouble(price));
		product.setTitle(title); product.setDescription(description);
		product.setSize(size); product.setCategory(category);
		product.setImage(urlImage);
  
		ProductDB.update(product);
  
		String message = "Chỉnh sửa thành công sản phẩm có mã: " + idString;
		req.setAttribute("message", message);
		req.setAttribute("editId", idString);
		
		doGet(req, resp);
	}
}
