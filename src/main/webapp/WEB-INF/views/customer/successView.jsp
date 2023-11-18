<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thành công</title>
</head>
<body>
	<h1>Thao tác thành công!</h1>
	<p>Sẽ tự động chuyển về trang chủ sau 2s.</p>
	<script>
		setTimeout(function() {
			window.location.href = 'home';
		}, 2000);
	</script>
	<a href="home"><button type="button">Trang chủ</button></a>
</body>
</html>