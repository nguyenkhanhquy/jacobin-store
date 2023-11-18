<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Jacobin Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" 
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</head>

<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
				<img src="resources/img/logo/logo.jpg" alt="logo" width="150">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Trang chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Sản phẩm</a></li>
                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false"> Thể loại </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Quần Jean</a></li>
                            <li><a class="dropdown-item" href="#">Áo thun</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Áo sơ mi</a></li>
                        </ul>
                    </li>
                    <li class="nav-item"><a class="nav-link disabled">Hết hàng</a>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Nội dung tìm kiếm"
                        aria-label="Search">
                    <button class="btn btn-outline-success me-2" type="submit">Tìm</button>
                    <c:choose>
    					<c:when test="${not empty sessionScope.loginedUser}">
					        <a class="btn btn-primary me-2" style="white-space: nowrap;" href="#">
					            <c:out value='${loginedUser.firstName}'/>
					        </a>
					        <a class="btn btn-primary me-2" style="white-space: nowrap;" href="logout">Đăng xuất</a>
    					</c:when>
					    <c:otherwise>
					        <a class="btn btn-primary me-2" style="white-space: nowrap;" href="login">Đăng nhập</a>
					        <a class="btn btn-primary me-2" style="white-space: nowrap;" href="register">Đăng ký</a>
					    </c:otherwise>
					</c:choose>
                </form>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->

    <!-- Page content -->
    <div class="container mt-4">
        <div class="row">
            <!-- Menu left -->
            <div class="col-lg-3">
                <div class="list-group ">
                    <a href="#" class="list-group-item list-group-item-action">Thời trang nam </a> 
                    <a href="#" class="list-group-item list-group-item-action">Thời trang nữ</a> 
                    <a href="#" class="list-group-item list-group-item-action">Dành cho bé</a>
                </div>
            </div>
            <!-- End Menu left -->

            <!-- Slider and Products -->
            <div class="col-lg-9">
                <!-- Slider -->
                <div id="carouselExampleIndicators" class="carousel slide mb-4" data-bs-ride="true">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
                            class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                    </div>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="resources/img/slider/1.png" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="resources/img/slider/2.png" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
                <!-- End Slider -->
                <!-- Products -->
                <div class="row">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a href="#"><img class="card-img-top" src="resources/img/product/1.png" alt=""></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">Áo thun Pro-S1</a>
                                </h4>
                                <h5>50.000</h5>
                                <p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ màu sắc tốt.</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">&#9733; &#9733; &#9733;
                                    &#9733; &#9734;</small>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a href="#"><img class="card-img-top" src="resources/img/product/2.png" alt=""></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">Áo thun Pro-S1</a>
                                </h4>
                                <h5>50.000</h5>
                                <p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ màu sắc tốt.</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">&#9733; &#9733; &#9733;
                                    &#9733; &#9734;</small>
                            </div>
                        </div>
                    </div>


                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a href="#"><img class="card-img-top" src="resources/img/product/3.png" alt=""></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">Áo thun Pro-S1</a>
                                </h4>
                                <h5>50.000</h5>
                                <p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ màu sắc tốt.</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">&#9733; &#9733; &#9733;
                                    &#9733; &#9734;</small>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card h-100">
                            <a href="#"><img class="card-img-top" src="resources/img/product/1.png" alt=""></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="#">Áo thun Pro-S1</a>
                                </h4>
                                <h5>50.000</h5>
                                <p class="card-text">Sản phẩm thoáng mát, có độ bền tốt, giữ màu sắc tốt.</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">&#9733; &#9733; &#9733;
                                    &#9733; &#9734;</small>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Products -->
            </div>
            <!-- End Slider and Products -->
        </div>
    </div>
    <!-- End Page content -->

    <!-- Footer -->
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Trag chủ</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định giao hàng</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Quy định trả hàng</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Liên hệ</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Blogs</a></li>
        </ul>
        <p class="text-center text-muted">&copy; 2023 Jacobin.com</p>
    </footer>
    <!-- End footer -->


    <div class="input-group mb-3">

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInputGroup1" placeholder="Username"> <label
                for="floatingInputGroup1">Username</label>
        </div>

        <span class="input-group-text">@gmail.com</span>
    </div>
</body>

</html>
