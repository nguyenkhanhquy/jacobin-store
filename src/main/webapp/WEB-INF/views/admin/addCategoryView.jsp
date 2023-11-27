<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Thêm danh mục</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    
    <link href="../resources/css/managerCategory.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Thêm <b>Danh Mục</b></h2>
                    </div>
                    <div class="col-sm-6">
                    	<a href="manager-category"><button type="button" class="btn btn-primary">Về trang quản lý sản phẩm</button></a>
                    </div>
                </div>
            </div>
        	<strong class="text-success">${message}</strong>
        	<strong class="text-danger">${messageError}</strong>
        	<form action="add-category" method="post">
                
                <div class="row">
                    <div class="col-sm-6">
	                    <div class="form-group mb-4">
	                        <label>Tên:</label>
	                        <input name="name" type="text" class="form-control" required>
	                        <hr/>
                        	<button class="w-100 btn btn-lg btn-success mb-2" type="submit">Xác nhận</button>
	                    </div>
                    </div>
                </div>
			</form>
        </div>
    </div>
    
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
