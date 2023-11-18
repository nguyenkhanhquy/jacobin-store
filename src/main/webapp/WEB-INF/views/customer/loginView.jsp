<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" 
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>    

    <link href="resources/css/login.css" rel="stylesheet">
</head>

<body>
	<main class="form-signin w-100 m-auto">
		<form class="text-center" action="login" method="post">

			<a href="home"><img class="logo mb-4" src="resources/img/logo/logo.jpg" alt="logo" width="150"></a>

			<h1 class="h3 mb-3 fw-normal">ĐĂNG NHẬP</h1>

			<div class="text-center"><p class="red"><i>${message}</i></p></div>

			<div class="form-floating">
				<input type="text" class="form-control" id="tenDangNhap"
					placeholder="Tên đăng nhập" name="userName" required> <label for="tenDangNhap">Tên đăng nhập</label>
			</div>

			<div class="form-floating">
				<input type="password" class="form-control" id="matKhau"
					placeholder="Mật khẩu" name="password" required> <label for="matKhau">Mật khẩu</label>
			</div>

			<div class="checkbox mb-3">
				<label><input type="checkbox" name="rememberMe" value="Y"> Ghi nhớ tài khoản này</label>
			</div>

			<button class="w-100 btn btn-lg btn-primary mb-2" type="submit">Đăng nhập</button>
			
			<a href="register">Đăng ký tài khoản mới</a>
			<p class="mt-5 mb-3 text-muted">&copy; 2023 Jacobin.com</p>
		</form>
	</main>
</body>

</html>
