<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Đăng ký tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" 
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <style>
        .red {
            color: red;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="text-center mt-4 mb-4">
            <a href="home"><img class="mb-4" src="resources/img/logo/logo.jpg" alt="logo" width="150"></a>
            <h1 class="h3">ĐĂNG KÝ TÀI KHOẢN</h1>
        </div>

        <div class="red" id="message">
            <p><i>${message}</i></p>
        </div>
        <form class="form" action="register" method="post">
            <div class="row">
                <div class="col-sm-6">
                    <h3>Thông tin khách hàng</h3>
                    <div class="mb-3">
                        <label for="firstName" class="form-label">Tên<span class="red">*</span></label> 
                        <input type="text" class="form-control" id="ho" name="firstName" required value="${user.firstName}">
                    </div>

                    <div class="mb-3">
                        <label for="ten" class="form-label">Họ<span class="red">*</span></label> 
                        <input type="text" class="form-control" id="ten" name="lastName" required value="${user.lastName}">
                    </div>

                    <div class="mb-3">
                        <label for="ngaySinh" class="form-label">Ngày sinh<span class="red">*</span></label> 
                        <input type="date" class="form-control" id="ngaySinh" name="dateOfBirth" required value="${user.dateOfBirth}">
                    </div>
                    
                    <hr/>

                    <div class="mb-3">
                        <label for="diaChiKhachHang" class="form-label">Địa chỉ khách hàng<span class="red">*</span></label> 
                        <input type="text" class="form-control" id="diaChiKhachHang" name="address" required value="${user.address}">
                    </div>

                    <div class="mb-3">
                        <label for="diaChiEmail" class="form-label">Địa chỉ Email<span class="red">*</span></label> 
                        <input type="email" class="form-control" id="diaChiEmail" name="email" required value="${user.email}">
                    </div>

                    <div class="mb-3">
                        <label for="dienThoai" class="form-label">Điện thoại<span class="red">*</span></label> 
                        <input type="tel" class="form-control" id="dienThoai" name="phone" required value="${user.phone}">
                    </div>
                </div>

                <div class="col-sm-6">
                    <h3>Tài khoản</h3>
                    <div class="mb-3">
                        <label for="tenDangNhap" class="form-label">Tên đăng nhập<span class="red">*</span></label> 
                        <input type="text" class="form-control" id="tenDangNhap" name="userName" required value="${user.userName}">
                    </div>

                    <div class="mb-3">
                        <label for="matkhau" class="form-label">Mật khẩu<span class="red">*</span></label> 
                        <input type="password" class="form-control" id="matkhau" name="password" required">
                    </div>

                    <div class="mb-3">
                        <label for="matKhauNhapLai" class="form-label">Nhập lại mật khẩu<span class="red">*</span></label> 
                        <input type="password" class="form-control" id="matKhauNhapLai" name="passwordAgain" required>
                    </div>

                    <hr/>

                    <div class="mb-3">
                        <label for="dongYDieuKhoan" class="form-label">
                            Đồng ý với <a>điều khoản của Jacobin</a><span class="red">*</span>
                        </label> 
                        <input type="checkbox" class="form-check-input" id="dongYDieuKhoan" name="dongYDieuKhoan" required="required" onchange="xuLyChonDongY()">
                    </div>

                    <div class="mb-3">
                        <label for="dongYNhanMail" class="form-label">Đồng ý nhận email về sản phẩm</label> 
                        <input type="checkbox" class="form-check-input" id="dongYNhanMail" name="dongYNhanMail">
                    </div>

                    <input class="btn btn-primary form-control" type="submit" value="Đăng ký" name="submit" id="submit" style="visibility: hidden;" />
                </div>
            </div>
        </form>
    </div>
</body>

<script>
    function xuLyChonDongY() {
        dongYDeuKhoan = document.getElementById("dongYDieuKhoan");
        if (dongYDeuKhoan.checked == true) {
            document.getElementById("submit").style.visibility = "visible";
        } else {
            document.getElementById("submit").style.visibility = "hidden";
        }
    }
</script>

</html>
