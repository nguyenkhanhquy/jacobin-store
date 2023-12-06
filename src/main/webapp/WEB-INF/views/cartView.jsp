<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
	<meta charset="UTF-8">
    <title>Jacobin Store</title>
    <link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	
	<link href="resources/css/cart.css" rel="stylesheet">
</head>

<body>
    <!-- Navbar -->
	<jsp:include page="includes/header.jsp" />
	<!-- End Navbar -->
    <div class="shopping-cart">
        <div class="px-4 px-lg-0">
            <div class="pb-5">
                <div class="container" style="max-width: 90%; margin: auto;">
                    <div class="row">
                        <div class="col-lg-12 p-5 bg-white rounded shadow-sm my-2">
                            <!-- Shopping cart table -->
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                        	<th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3">Tên</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3">Ảnh</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2">Danh Mục</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2">Size</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2">Đơn Giá</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2">Số tiền</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2">Số Lượng</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2"></div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${cart.items}" var="item">
                                            <tr>
                                                <%-- <th scope="row">
                                                    <div class="p-2">
                                                        <div class="ml-3 d-inline-block align-middle">
                                                            <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${item.product.name}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                        </div>
                                                    </div>
                                                </th> --%>
                                                <td class="align-middle">
                                                	<strong><a href="detail?pId=${item.product.productId}" class="text-dark d-inline-block">${item.product.name}</a></strong>
                                                </td>
                                                <td class="align-middle"><img src="${item.product.image}" alt="" width="70" class="img-fluid rounded shadow-sm"></td>
                                                <td class="align-middle">${item.product.category.name}</td>
                                                <td class="align-middle">${item.product.size}</td>
                                                <td class="align-middle">${item.product.price}</td>
                                                <td class="align-middle"><strong>${item.totalCurrencyFormat}</strong></td>
                                                <td class="align-middle" style="max-width: 120px;">
	                                                <form action="cart" method="post">
												      	<input type="hidden" name="action" value="update">
												        <input type="hidden" name="productId" value="<c:out value='${item.product.productId}'/>">
												        <input type=text name="quantity" value="<c:out value='${item.quantity}'/>" id="quantity">
												        <input class="btn btn-outline-success" type="submit" value="Cập nhật">
											      	</form>
                                                    <%-- <a href="#"><button class="btnSub">-</button></a> 
                                                    <strong>${item.quantity}</strong>
                                                    <a href="#"><button class="btnAdd">+</button></a> --%>
                                                </td>
                                                <td class="align-middle">
                                                	<form action="cart" method="post">
												      	<input type="hidden" name="action" value="update">
												        <input type="hidden" name="productId" value="<c:out value='${item.product.productId}'/>">
												        <input type="hidden" name="quantity" value="0">
												        <input class="btn btn-outline-danger" type="submit" value="Xoá">
											      	</form>
                                                </td>
                                            </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- End -->
                    </div>
                </div>
                
                <form action="order" method="post" class="row p-5 bg-white rounded shadow-sm" style="max-width: 90%; margin: auto;">
                    
                    <div class="col-lg-6">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thông tin đặt hàng</div>
                        <div class="p-4">
                        	<div class="form-floating mb-4">
	                            <input type="text" class="form-control" id="ten" placeholder="Tên" name="firstName" value="${loginedUser.firstName}" required readonly> 
	                            <label for="ten">Tên <span class="red">*</span></label>
	                        </div>
	                        	                        
	                        <div class="form-floating mb-4">
	                            <input type="tel" class="form-control" id="dienThoai" placeholder="Số điện thoại" name="phone" value="${loginedUser.phone}" required readonly> 
	                            <label for="dienThoai">Số điện thoại <span class="red">*</span></label>
	                        </div>
	                        
	                        <div class="form-floating mb-4">
	                            <input type="text" class="form-control" id="diaChiKhachHang" placeholder="Địa chỉ" name="address" value="${loginedUser.address}" required> 
	                            <label for="diaChiKhachHang">Địa chỉ <span class="red">*</span></label>
	                        </div>
	                        
	                        <select name="paymentMethod" class="form-select mb-4" required>
	                            <option value="" selected disabled>Chọn một phương thức vận chuyển</option>
	                            <option value="Giao hàng nhanh">Giao hàng nhanh</option>
	                        </select>
	                        
	                        <select name="shippingMethod" class="form-select mb-4" required>
	                            <option value="" selected disabled>Chọn một phương thức thanh toán</option>
	                            <option value="Thanh toán khi nhận hàng">Thanh toán khi nhận hàng</option>
	                        </select>
                            <!-- <div class="input-group mb-4 border rounded-pill p-2">
                                <input type="text" placeholder="Nhập Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                <div class="input-group-append border-0">
                                    <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2">&nbsp;</i>Sử dụng</button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                        <div class="p-4">
                            <ul class="list-unstyled mb-4">
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${cart.totalCurrencyFormat}</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Miễn phí vận chuyển</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong><strong>${cart.totalCurrencyFormat}</strong></li>
                            </ul>
                            <button class="w-100 btn btn-primary rounded-pill py-2 btn-block" type="submit" ${cart.count != 0?'':'style="visibility: hidden;"'}>Đặt hàng</button>
                        </div>
                    </div>
	            	<input type="hidden" name="totalPrice" value="${cart.totalCurrencyFormat}">
                </form>
            </div>
        </div>
    </div>
    
    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />
    <!-- End footer -->
    
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
