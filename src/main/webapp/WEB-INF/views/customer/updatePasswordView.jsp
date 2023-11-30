<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Đổi mật khẩu</title>
    <link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link href="resources/css/updatePassword.css" rel="stylesheet">
</head>

<body >
    <main class="form-signin w-100 m-auto">
		<form class="text-center" action="update-password" method="post">

			<a href="home"><img class="logo" src="resources/img/logo/logo(black).png" alt="logo" width="280"></a>

			<h1 class="h3 mb-3 fw-normal">ĐỔI MẬT KHẨU</h1>

			<c:if test="${sessionScope.loginedUser == null}">
				<div class="text-center"><p class="red"><i>Bạn chưa đăng nhập vào hệ thống. Vui lòng đăng nhập!</i></p></div>
			
				<a href="login" class="w-100 btn btn-lg btn-primary mb-2" type="submit">Tới trang đăng nhập</a>
			</c:if>

			<c:if test="${sessionScope.loginedUser != null}">
				<div class="text-center"><p class="red"><i>${message}</i></p></div>

				<div class="form-floating">
					<input type="password" class="form-control" id="matKhauHienTai"
						placeholder="Mật khẩu hiện tại" name="password" required> <label for="matKhauHienTai">Mật khẩu hiện tại</label>
				</div>

				<div class="form-floating">
					<input type="password" class="form-control" id="matKhauMoi"
						placeholder="Mật khẩu mới" name="newPassword" required> <label for="matKhauMoi">Mật khẩu mới</label>
				</div>

				<div class="form-floating">
					<input type="password" class="form-control" id="matKhauMoiNhapLai"
						placeholder="Mật khẩu mới nhập lại" name="newPasswordAgain" required> <label for="matKhauMoiNhapLai">Mật khẩu mới nhập lại</label>
				</div>

				<button class="w-100 btn btn-lg btn-primary mb-2" type="submit">Lưu mật khẩu</button>
			</c:if>

			<p class="mt-5 mb-3 text-muted">&copy; 2023 Jacobin.com</p>
		</form>
	</main>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
