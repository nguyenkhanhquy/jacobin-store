<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
	<meta charset="UTF-8">
	<title>Thao tác thành công</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" 
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    <link href="resources/css/success.css" rel="stylesheet">
</head>

<body>
    <div class="form-success text-center m-auto">
        <a href="home"><img class="logo mb-4" src="resources/img/logo/logo.jpg" alt="logo" width="150"></a>
        <h1 class="h3 mb-3 fw-normal">Thao tác thành công!</h1>
	    <p>Sẽ tự động chuyển về trang chủ sau 3s.</p>
	    <a href="home" class="w-100 btn btn-lg btn-primary mb-2">Trang chủ</a>
    </div>
	
	<script>
		setTimeout(function() {
			window.location.href = 'home';
		}, 3000);
	</script>
</body>

</html>
