<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Cập nhật mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" 
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    <link href="resources/css/updatePassword.css" rel="stylesheet">
</head>

<body >
    <main class="form-signin w-100 m-auto">
		<form class="text-center" action="update-password" method="post">

			<a href="home"><img class="logo mb-4" src="resources/img/logo/logo.jpg" alt="logo" width="150"></a>

			<h1 class="h3 mb-3 fw-normal">CẬP NHẬT MẬT KHẨU</h1>

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
</body>

</html>
