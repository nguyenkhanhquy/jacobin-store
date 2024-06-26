<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Thông tin tài khoản</title>
    <link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <link href="resources/css/userInfo.css" rel="stylesheet">
</head>

<body>
    <main class="form-info w-100 m-auto">
		<form class="text-center" action="user-info" method="post">

			<a href="home"><img class="logo mb-4" src="resources/img/logo/logo(black).png" alt="logo" width="280"></a>

			<h1 class="h3 mb-3 fw-normal">THÔNG TIN TÀI KHOẢN</h1>

			<c:if test="${sessionScope.loginedUser == null}">
				<div class="text-center"><p class="red"><i>Bạn chưa đăng nhập vào hệ thống. Vui lòng đăng nhập!</i></p></div>
			
				<a href="login" class="w-100 btn btn-lg btn-primary mb-2" type="submit">Tới trang đăng nhập</a>
			</c:if>

			<c:if test="${sessionScope.loginedUser != null}">
				<h5><span class="badge text-bg-primary">Quyền truy cập: ${user.getRole().getRoleName()}</span></h5>
				<div class="text-center"><p class="red"><i>${message}</i></p></div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="ten" placeholder="Tên" name="firstName" value="${user.firstName}" required disabled> 
                            <label for="ten">Tên<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="ho" placeholder="Họ" name="lastName" value="${user.lastName}" required disabled> 
                            <label for="ho">Họ<span class="red">*</span></label>
                        </div>
                        
                        <div class="form-floating mb-4">
                            <input type="date" class="form-control" id="ngaySinh" placeholder="Ngày sinh" name="dateOfBirth" value="${user.dateOfBirth}" required disabled> 
                            <label for="ngaySinh">Ngày sinh<span class="red">*</span></label>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-floating mb-4">
                            <input type="text" class="form-control" id="diaChiKhachHang" placeholder="Địa chỉ" name="address" value="${user.address}" required disabled> 
                            <label for="diaChiKhachHang">Địa chỉ<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="email" class="form-control" id="diaChiEmail" placeholder="Email" name="email" value="${user.email}" required disabled> 
                            <label for="diaChiEmail">Email<span class="red">*</span></label>
                        </div>

                        <div class="form-floating mb-4">
                            <input type="tel" class="form-control" id="dienThoai" placeholder="Số điện thoại" name="phone" value="${user.phone}" required disabled> 
                            <label for="dienThoai">Số điện thoại<span class="red">*</span></label>
                        </div>
                        
                        <hr/>
                        <button id="edit-btn" type="button" class="w-100 btn btn-lg btn-primary mb-2">Chỉnh sửa</button>

                        <button id="save-btn" class="w-100 btn btn-lg btn-primary mb-2" type="submit" disabled>Lưu thông tin</button>
                    </div>
                </div>
			</c:if>

			<p class="mt-5 mb-3 text-muted">&copy; 2023 Jacobin.com</p>
		</form>
	</main>
	
    <script>
        document.getElementById('edit-btn').addEventListener('click', function() {
            document.getElementById('ten').removeAttribute('disabled');
            document.getElementById('ho').removeAttribute('disabled');
            document.getElementById('ngaySinh').removeAttribute('disabled');
            document.getElementById('diaChiKhachHang').removeAttribute('disabled');
            document.getElementById('diaChiEmail').removeAttribute('disabled');
            document.getElementById('dienThoai').removeAttribute('disabled');

            document.getElementById('edit-btn').setAttribute('disabled', 'true');
            document.getElementById('save-btn').removeAttribute('disabled');
        });
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
