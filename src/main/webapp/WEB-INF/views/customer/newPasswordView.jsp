<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tạo mặt khẩu mới</title>
    <link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link href="resources/css/login.css" rel="stylesheet">
</head>

<body>
	<main class="form-signin w-100 m-auto">
		<form class="text-center" action="forgot-password" method="post">
			<input type="hidden" name="action" value="newpass">
			<input type="hidden" name="email" value="${email}">
			<a href="home"><img class="logo" src="resources/img/logo/logo(black).png" alt="logo" width="280"></a>

			<h1 class="h3 mb-3 fw-normal">TẠO MẬT KHẨU MỚI</h1>

			<div class="text-center"><p class="red"><i>${message}</i></p></div>

			<div class="form-floating">
				<input type="password" class="form-control" id="matKhau"
					placeholder="Mật khẩu mới" name="newPassword" required> <label for="matKhau">Mật khẩu mới<span class="red">*</span></label>
			</div>
			
			<div class="form-floating mb-4">
                 <input type="password" class="form-control" id="matKhauNhapLai" 
                 placeholder="Nhập lại mật khẩu mới" name="newPasswordAgain" required> <label for="matKhauNhapLai">Nhập lại mật khẩu mới<span class="red">*</span></label>
             </div>
             
			<button class="w-100 btn btn-lg btn-primary mb-2" type="submit">Xác nhận</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2023 Jacobin.com</p>
		</form>
	</main>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
