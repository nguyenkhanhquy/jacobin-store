<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=vi>
<head>
<meta charset="UTF-8">
<title>Đăng ký thành công</title>
</head>
<body>
	<h1>Đăng ký tài khoản thành công!</h1>
	<p>Sẽ tự động chuyển về trang chủ sau 5s nữa.</p>]
	<script>
		setTimeout(function() {
			window.location.href = 'home';
		}, 5000);
	</script>
	<a href="home"><button type="button">Trang chủ</button></a>
</body>
</html>