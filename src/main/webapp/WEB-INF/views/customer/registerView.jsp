<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=vi>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="resources/css/main.css" type="text/css"/>
</head>
<body>
    <h1>Đăng ký tài khoản</h1>
    <p><i>${message}</i></p>
    <form action="register" method="post">
    	<input type="hidden" name="action" value="add">  
		<div>
			<input type="text" name="firstName" value="${user.firstName}" 
			placeholder="Họ" id="first_name" class="text" size="30" required>
		</div>
		
		<div>
			<input type="text" name="lastName" value="${user.lastName}" 
			placeholder="Tên" id="last_name" class="text" size="30" required>
		</div>
		
		<div>
			<input type="radio" name="sex" value="Nam" ${user.sex eq 'Nam' ? 'checked' : ''} required>Nam
           	<input type="radio" name="sex" value="Nữ" ${user.sex eq 'Nữ' ? 'checked' : ''}>Nữ
           	<input type="radio" name="sex" value="Khác" ${user.sex eq 'Khác' ? 'checked' : ''}>Khác
		</div>
		
		<div>
			<input type="text" name="dateOfBirth" value="${user.dateOfBirth}" 
			placeholder="dd/mm/yyyy" id="date_of_birth" class="text" size="30">
		</div>
		
		<div>
			<input type="text" name="address" value="${user.address}" 
			placeholder="Địa chỉ" id="address" class="text" size="30">
		</div>
		
		<div>
			<input type="email" name="email" value="${user.email}" 
			placeholder="Email" id="email" class="text" size="30" required>
		</div>
		
		<div>
			<input type="text" name="phone" value="${user.phone}" 
			placeholder="Số điện thoại" id="phone" class="text" size="30" required>
		</div>
		
		<div>
			<input type="text" name="userName" value="${user.userName}" 
			placeholder="Tên đăng nhập" id="user_name" class="text" size="30" required>
		</div>
		
		<div>
			<input type="text" name="password" value="${user.password}" 
			placeholder="Mật khẩu" id="password" class="text" size="30" required>
		</div>
		
		<div>
			<input class="btn" type ="submit" value="Đăng ký">
		</div>
    </form>
</body>
</html>
