<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Thêm người dùng</title>
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
                        <h2>Thêm <b>Người Dùng</b></h2>
                    </div>
                    <div class="col-sm-6">
                    	<a href="manager-user" class="btn btn-outline-secondary rounded">
                    		<i class="fa-solid fa-user-gear">&nbsp;</i><strong>Về trang quản lý người dùng</strong>
                    	</a>
                    </div>
                </div>
            </div>
        	<strong class="text-success">${message}</strong>
        	<strong class="text-danger">${messageError}</strong>
        	<form action="add-user" method="post">
                
                <div class="row">
                    <div class="col-sm-6">
	                    <p><strong>Thông tin khách hàng</strong></p>

                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="ten" placeholder="Tên" name="firstName" value="${user.firstName}" required> 
                            <label for="ten">Tên<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="ho" placeholder="Họ" name="lastName" value="${user.lastName}" required> 
                            <label for="ho">Họ<span class="red">*</span></label>
                        </div>
                        
                        <div class="form-floating mb-4">
                            <input type="date" class="form-control" id="ngaySinh" placeholder="Ngày sinh" name="dateOfBirth" value="${user.dateOfBirth}" required> 
                            <label for="ngaySinh">Ngày sinh<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="diaChiKhachHang" placeholder="Địa chỉ" name="address" value="${user.address}" required> 
                            <label for="diaChiKhachHang">Địa chỉ<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="email" class="form-control" id="diaChiEmail" placeholder="Email" name="email" value="${user.email}" required> 
                            <label for="diaChiEmail">Email<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="tel" class="form-control" id="dienThoai" placeholder="Số điện thoại" name="phone" value="${user.phone}" required> 
                            <label for="dienThoai">Số điện thoại<span class="red">*</span></label>
                        </div>
                    </div>

                    <div class="col-sm-6">
                    	<p><strong>Tài khoản</strong></p>

                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="tenDangNhap" placeholder="Tên đăng nhập" name="userName" value="${user.userName}" required> 
                            <label for="tenDangNhap">Tên đăng nhập<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="matKhau" placeholder="Mật khẩu" name="password" required> 
                            <label for="matKhau">Mật khẩu<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="matKhauNhapLai" placeholder="Nhập lại mật khẩu" name="passwordAgain" required> 
                            <label for="matKhauNhapLai">Nhập lại mật khẩu<span class="red">*</span></label>
                        </div>
                        
                        <p><strong>Quyền</strong></p>
                        <select name="role" class="form-select" required>
                            <option value="" selected disabled>Chọn một quyền</option>
                            <c:forEach items="${ListR}" var="r">
                                <option value="${r.roleId}">${r.roleName}</option>
                            </c:forEach>
                        </select>
                        <hr/>
                        <button class="w-100 btn btn-lg btn-success mb-2" type="submit">Xác nhận</button>
                    </div>
                </div>
			</form>
        </div>
    </div>
    
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
