<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="vi">

<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Jacobin Store</title>
	<link rel="icon" href="resources/img/icon/favicon.ico" type="image/x-icon">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	
	<link href="resources/css/home.css" rel="stylesheet">
	
	<style>
        #image-preview {
            max-width: 100%;
            max-height: 100%;
            border: 1px solid #ddd;
        }
    </style>
</head>

<body>
	<!-- Navbar -->
	<jsp:include page="includes/header.jsp" />
	<!-- End Navbar -->

	<div class="container mt-4">
		<div class="row">
            <!-- Menu left -->
			<jsp:include page="includes/left.jsp" />
			<!-- End Menu left -->
			
			<!-- Detail Product -->
			<div class="col-sm-9">
				<div class="container">
					<div class="card">
						<div class="row">
							<aside class="col-sm-5 border-right">
								<article class="gallery-wrap">
									<div class="img-big-wrap">
										<div>
											<img id="image-preview" src="${product.image}">
										</div>
									</div>
									<!-- slider-product.// -->
									<div class="img-small-wrap"></div>
									<!-- slider-nav.// -->
								</article>
								<!-- gallery-wrap .end// -->
							</aside>
							<aside class="col-sm-7">
								<article class="card-body p-5">
									<h3 class="title mb-3">${product.name}</h3>

									<p class="price-detail-wrap">
										<span class="price h3 text-warning"> 
										<span class="currency"><fmt:formatNumber type="number" value="${product.price}" pattern="#,##0" /> VNĐ</span>
										</span>
									</p>
									<!-- price-detail-wrap .// -->
									<dl class="item-property">
										<dt>Mô tả</dt>
										<dd>
											<p>${product.description}</p>
										</dd>
									</dl>

									<hr>
									<div class="row">
										<div class="col-sm-5">
											<dl class="param param-inline">
												<dt>Số lượng:</dt>
												<dd>
													<select class="form-control form-control-sm"
														style="width: 70px;">
														<option>1</option>
														<option>2</option>
														<option>3</option>
													</select>
												</dd>
											</dl>
											<!-- item-property .// -->
										</div>
										<!-- col.// -->

									</div>
									<!-- row.// -->
									<hr>
									<a href="#" class="btn btn-lg btn-primary text-uppercase">
										Thanh toán </a> <a href="#"
										class="btn btn-lg btn-outline-primary text-uppercase"> <i
										class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
									</a>
								</article>
								<!-- card-body.// -->
							</aside>
							<!-- col.// -->
						</div>
						<!-- row.// -->
					</div>
					<!-- card.// -->
				</div>
			</div>
			<!-- End Detail Product -->
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="includes/footer.jsp" />
	<!-- End footer -->
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>