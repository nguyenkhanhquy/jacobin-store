<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Chi tiết đơn hàng</title>
    <link rel="icon" href="../resources/img/icon/favicon.ico" type="image/x-icon">
    
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    
    <link href="resources/css/managerOrder.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Chi tiết <b>Đơn Hàng</b></h2>
                    </div>
                    <div class="col-sm-6">
                    	<a href="manager-order" class="btn btn-outline-primary rounded">
                    		<i class="fa-solid fa-gear">&nbsp;</i><strong>Về trang đơn hàng</strong>
                    	</a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                    	<th>STT</th>
                        <th>Tên</th>
                        <th>Size</th>
                        <th>Đơn giá</th>
                        <th>Số tiền</th>
                        <th>Số lượng</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${order.details}" var="d" varStatus="loop">
                        <tr>
                        	<td>${loop.index + 1}</td>
                            <td>${d.nameProduct}</td>
                            <td>${d.size}</td>
                            <td>${d.priceCurrencyFormat}</td>
                            <td>${d.totalCurrencyFormat}</td>
                            <td>${d.quantity}</td>       
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>Tên: ${order.user.firstName}</p>
            <p>Số điện thoại: ${order.user.phone}</p>
            <p>Địa chỉ: ${order.address}</p>
            <p>Phương thức vận chuyển: ${order.shippingMethod}</p>
            <p>Phương thức thanh toán: ${order.paymentMethod}</p>
            <p>Ngày đặt hàng: ${order.orderDateDefaultFormat}</p>
            <p>Tổng thanh toán: ${order.totalCurrencyFormat}</p>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
