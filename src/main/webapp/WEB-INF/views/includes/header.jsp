<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="home" style="margin: 0;">
            <img class="logo" src="resources/img/logo/logo(black).png" alt="logo" width="160">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Trang chủ</a></li>
                
                <c:if test="${sessionScope.loginedUser != null && loginedUser.getRole().getRoleId() != 1}">
                	<li class="nav-item"><a class="nav-link active" aria-current="page" href="manager-order">Đơn hàng</a></li>
                </c:if>
                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-user">&nbsp;</i>
                        <c:if test="${sessionScope.loginedUser == null}">Tài khoản</c:if>
                        <c:if test="${sessionScope.loginedUser != null}">
                            <c:out value='${loginedUser.firstName} ${loginedUser.lastName}'/>
                        </c:if>  
                    </a>
                    <ul class="dropdown-menu">
                        <c:if test="${sessionScope.loginedUser == null}">
                            <li><a class="dropdown-item" href="login">Đăng nhập</a></li>
                            <li><a class="dropdown-item" href="register">Đăng ký</a></li>
                        </c:if>
                        <c:if test="${sessionScope.loginedUser != null}">
                            <li><a class="dropdown-item" href="user-info">Thông tin tài khoản</a></li>
                            <li><a class="dropdown-item" href="update-password">Đổi mật khẩu</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
            <c:if test="${loginedUser.role.roleId != 1}">
	            <form action="search" class="d-flex" role="search">
	                <input class="form-control me-2" type="search" placeholder="Nội dung tìm kiếm"
	                    aria-label="Search" name="pName" value="${pName}">
	                <button class="btn btn-outline-success me-2" type="submit">Tìm</button>
	            </form>
            	<a class="btn btn-success position-relative" href="cart">
		        	<i class="fa fa-shopping-cart">&nbsp;</i>Giỏ hàng
		        	<c:if test="${loginedUser != null}">
				        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
							${cart.count}
						</span>
					</c:if>
		       	</a> 
            </c:if>          
        </div>
    </div>
</nav>
