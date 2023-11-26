<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản lý người dùng</title>
    <link rel="icon" href="../resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    
    <link href="../resources/css/managerUser.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Quản lý <b>Người Dùng</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="add-user" class="btn btn-outline-success rounded">
                        	<i class="fa-solid fa-user-plus">&nbsp;</i><strong>Thêm người dùng mới</strong>
                        </a>
                    	<a href="../home" class="btn btn-outline-primary rounded">
                    		<i class="fa-solid fa-house">&nbsp;</i><strong>Về trang chủ</strong>
                    	</a>
                    </div>
                </div>
            </div>
            <strong class="text-success">${message}</strong>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tên đăng nhập</th>
                        <th>Tên</th>
                        <th>Họ</th>
                        <th>Ngày sinh</th>
                        <th>Số điện thoại</th>
                        <th>Email</th>
                        <th>Quyền</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ListU}" var="u">
                        <tr>
                            <td>${u.userId}</td>
                            <td>${u.userName}</td>
                            <td>${u.firstName}</td>
                            <td>${u.lastName}</td>
                            <td>${u.dateOfBirth}</td>
                            <td>${u.phone}</td>
                            <td>${u.email}</td>
                            <td>${u.getRole().getRoleName()}</td>
                            <td>
                            	<a href="edit-user?editId=${u.userId}" class="edit">
                            		<i class="fa-solid fa-user-pen" style="font-size: 18px;" data-toggle="tooltip" title="Chỉnh sửa"></i>
                            	</a>
                            	<c:if test="${u.getUserName() != loginedUser.getUserName()}">
                            		<a href="manager-user?deleteId=${u.userId}" class="delete">
	                                	<i class="fa-solid fa-user-xmark" style="font-size: 18px;" data-toggle="tooltip" title="Xoá"></i>
	                                </a>
                            	</c:if>
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
                	<li class="page-item" ${tag > 1?'':'style="visibility: hidden;"'}><a href="manager-user?index=${tag-1}">Lùi</a></li>
                    <c:forEach begin="1" end="${endP}" var="i">
                    	<li class="page-item ${tag == i?'active':''}"><a href="manager-user?index=${i}" class="page-link">${i}</a></li>
                    </c:forEach>
                    <li class="page-item" ${tag < endP?'':'style="visibility: hidden;"'}><a href="manager-user?index=${tag+1}">Tiến</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
