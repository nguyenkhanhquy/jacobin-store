<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    
    <link href="../resources/css/managerProduct.css" rel="stylesheet" type="text/css" />
    
    <style>
        #image-preview {
            max-width: 100%;
            max-height: 300px;
            border: 1px solid #ddd;
        }

        #file-input-label {
            cursor: pointer;
        }
    </style>
</head>

<body>
	<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Thêm <b>Sản Phẩm</b></h2>
                    </div>
                    <div class="col-sm-6">
                    	<a href="manager-product"><button type="button" class="btn btn-primary">Về trang quản lý sản phẩm</button></a>
                    </div>
                </div>
            </div>
        	<strong class="text-success">${message}</strong>
        	<form action="add-product" method="post" enctype="multipart/form-data">
                
                <div class="row">
                    <div class="col-sm-6">
	                    <div class="form-group mb-4">
	                        <label>Tên:</label>
	                        <input name="name" type="text" class="form-control" required>
	                    </div>

                        <div class="form-group mb-4">
                            <label>Size:</label>
                        	<input name="size" type="text" class="form-control" required>
                        </div>
                        
                        <div class="form-group mb-4">
                            <label>Giá:</label>
                        	<input name="price" type="text" class="form-control" required>
                        </div>

                        <div class="form-group mb-4">
                            <label>Tiêu đề:</label>
                        	<textarea name="title" class="form-control" required></textarea>
                        </div>

                        <div class="form-group mb-4">
                            <label>Mô tả:</label>
                        	<textarea name="description" class="form-control" required></textarea>
                        </div>
                    </div>

                    <div class="col-sm-6">
                    	<div class="form-group mb-4">
                            <label>Danh mục:</label>
	                        <select name="category" class="form-select" required>
	                            <option value="" selected disabled>Chọn một danh mục</option>
	                            <c:forEach items="${ListC}" var="c">
	                                <option value="${c.categoryId}">${c.name}</option>
	                            </c:forEach>
	                        </select>
                        </div>
                        
                        <div class="form-group mb-4" align="center">
                            <label id="file-input-label" for="file-input">
		            			<img id="image-preview" src="https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg" alt="Ảnh sản phẩm">
		            			<br><span class="btn btn-secondary mt-4">Thêm ảnh</span>     
		            			<input type="file" id="file-input" name="file" accept="image/*" style="display:none;" required>
	        				</label>
                        </div>
                        <hr/>
                        <button class="w-100 btn btn-lg btn-success mb-2" type="submit">Xác nhận</button>
                    </div>
                </div>
			</form>
        </div>
    </div>
    
    <script>
        document.getElementById('file-input').addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const imageUrl = URL.createObjectURL(file);
                document.getElementById('image-preview').src = imageUrl;
            }
        });
    </script>
    
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
