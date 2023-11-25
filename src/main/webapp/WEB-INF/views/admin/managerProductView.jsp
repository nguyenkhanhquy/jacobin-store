<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    
    <link href="../resources/css/managerProduct.css" rel="stylesheet" type="text/css" />
    <style>
        img {
            max-width: 100%;
            max-height: 100px;
            border: 1px solid #ddd;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Quản lý <b>Sản Phẩm</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="add-product" class="btn btn-success" data-toggle="modal">
                        	<i class="material-icons">&#xE147;</i> <span>Thêm sản phẩm mới</span>
                        </a>
                    	<a href="../home"><button type="button" class="btn btn-primary">Về trang chủ</button></a>
                    </div>
                </div>
            </div>
            <strong class="text-success">${message}</strong>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Ảnh</th>
                        <th>Size</th>
                        <th>Giá</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ListP}" var="p">
                        <tr>
                            <td>${p.productId}</td>
                            <td>${p.name}</td>
                            <td>
                                <img src="${p.image}">
                            </td>
                            <td>${p.size}</td>
                            <td>${p.price} VNĐ</td>
                            <td>
                            	<a href="edit-product?editId=${p.productId}" class="edit" data-toggle="modal"><i
                                        class="material-icons" data-toggle="tooltip" title="Chỉnh sửa">&#xE254;</i></a>
                                <a href="manager-product?deleteId=${p.productId}" class="delete" data-toggle="modal"><i
                                        class="material-icons" data-toggle="tooltip" title="Xoá">&#xE872;</i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="clearfix">
            	<c:if test="${endP > 0}">
            		<div class="hint-text">Mục số <b>${tag}</b> trong tổng số <b>${endP}</b> mục</div>
            	</c:if>       
                <ul class="pagination">
                	<c:if test="${tag > 1}">
                		<li class="page-item"><a href="manager-product?index=${tag-1}">Lùi</a></li>
                	</c:if>
                    <c:forEach begin="1" end="${endP}" var="i">
                    	<li class="page-item ${tag == i?'active':''}"><a href="manager-product?index=${i}" class="page-link">${i}</a></li>
                    </c:forEach>
                    <c:if test="${tag < endP}">
                    	<li class="page-item"><a href="manager-product?index=${tag+1}">Tiến</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
