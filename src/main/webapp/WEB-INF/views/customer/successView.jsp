<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Thao tác thành công</title>
	<link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link href="resources/css/success.css" rel="stylesheet">
</head>

<body>
    <div class="form-success text-center m-auto">
        <a href="home"><img class="logo mb-4" src="resources/img/logo/logo.jpg" alt="logo" width="150"></a>
        <h1 class="h3 mb-3 fw-normal">${message}</h1>
	    <p>Sẽ tự động chuyển về trang chủ sau 3s.</p>
	    <a href="home" class="w-100 btn btn-lg btn-primary mb-2">Trang chủ</a>
    </div>
	
	<script>
		setTimeout(function() {
			window.location.href = 'home';
		}, 3000);
	</script>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
