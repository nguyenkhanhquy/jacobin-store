<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Đăng ký tài khoản</title>
    <link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link href="resources/css/register.css" rel="stylesheet">
</head>

<body>
    <main class="form-register w-100 m-auto">
        <form class="text-center" action="register" method="post">

            <a href="home"><img class="logo" src="resources/img/logo/logo(black).png" alt="logo" width="280"></a>
            
            <h1 class="h3 mb-3 fw-normal">ĐĂNG KÝ TÀI KHOẢN</h1>

            <c:if test="${sessionScope.loginedUser == null}">
				<div class="text-center" id="message"><p class="red"><i>${message}</i></p></div>

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

                        <hr/>

                        <button class="w-100 btn btn-lg btn-primary mb-2" type="submit" id="submit">Đăng ký</button>
                    </div>
                </div>
			</c:if>

            <c:if test="${sessionScope.loginedUser != null}">
				<div class="text-center"><p class="red"><i>Bạn cần đăng xuất trước khi tạo tài khoản mới!</i></p></div>
			
				<a href="logout" class="w-100 btn btn-lg btn-primary mb-2" type="submit">Đăng xuất</a>
                <a href="home" class="w-100 btn btn-lg btn-primary mb-2" type="submit">Về trang chủ</a>
			</c:if>

            <p class="mt-3 mb-3 text-muted">&copy; 2023 Jacobin.com</p>
        </form>
    </main>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
