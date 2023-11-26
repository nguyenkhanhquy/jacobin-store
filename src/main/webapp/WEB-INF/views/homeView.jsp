<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Jacobin Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <link href="resources/css/home.css" rel="stylesheet">
</head>

<body>
	<!-- Navbar -->
	<jsp:include page="includes/header.jsp" />
	<!-- End Navbar -->

    <!-- Page content -->
    <div class="container mt-4">
        <div class="row">
            <!-- Menu left -->
			<jsp:include page="includes/left.jsp" />
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
                <div id="content" class="row">
                    <c:forEach items="${ListP}" var="p">
                        <div class="product col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <a href="detail?pId=${p.productId}"><img class="card-img-top" src="${p.image}" alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="detail?pId=${p.productId}">${p.name}</a>
                                    </h4>
                                    <h5><fmt:formatNumber type="number" value="${p.price}" pattern="#,##0" /> VNĐ</h5>
                                    <p class="card-text">${p.title}</p>
                                </div>
                                <div class="card-footer">
                                    <a href="detail?pId=${p.productId}" class="btn btn-outline-dark">Chi tiết</a>
                                	<a href="#" class="btn btn-outline-primary">
                                		<i class="fas fa-shopping-cart">&nbsp;</i>Thêm vào giỏ hàng
                                	</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach> 
                </div>
                <!-- End Products -->
                <button onclick="loadMore(${cId})" class="btn btn-primary">Xem thêm</button>
            </div>
            <!-- End Slider and Products -->
        </div>
    </div>
    <!-- End Page content -->

    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />
    <!-- End footer -->
    
    <% boolean isSearchPage = false; %>
    
    <%
    String productNameString = request.getParameter("pName");
    if (productNameString != null && !productNameString.isEmpty()) {
        isSearchPage = true;
    }
	%>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>  
    <script>
    	function loadMore(categoryId) {
    		var amount = document.getElementsByClassName("product").length;
    		var url = '/jacobin-store/load';
    	    if (categoryId !== undefined) {
    	        url += '?cId=' + categoryId;
    	    }
    		$.ajax({  
    			url: url,  
    			type: 'GET',
    			data: {
                    exits: amount
                },
    			success: function(data) {  
    			    var row = document.getElementById("content");
    			    row.innerHTML += data;
    			},
    			error: function(xhr) {
    				//do something to handle error
    			}
    		});  
    	}
    </script>

    <script>
        // Kiểm tra nếu là trang tìm kiếm thì ẩn nút "Load more"
        <% if (isSearchPage) { %>
            var loadMoreBtn = document.querySelector('.btn.btn-primary');
            if (loadMoreBtn) {
                loadMoreBtn.style.display = 'none';
            }
        <% } %>
    </script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>
