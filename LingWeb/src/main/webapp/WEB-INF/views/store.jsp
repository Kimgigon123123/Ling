<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Product Page - Admin HTML Template</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
<!-- https://fonts.google.com/specimen/Roboto -->
<link rel="stylesheet" href="css/fontawesome.min.css" />
<!-- https://fontawesome.com/ -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet" href="css/templatemo-style.css">


</head>
<body>
	<div class="container mt-5">
		<div class="row tm-content-row">
			<div class="col-sm-12 col-md-12 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-products">
					<div class="tm-product-table-container">
						<table class="table table-hover tm-table-small tm-product-table">
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">상품명</th>
									<th scope="col">상품가격</th>
									<th scope="col">판매갯수</th>
									<th scope="col">매출액</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
							
							
							<c:forEach items="${list}" var="list">
								<tr>
									<th scope="row"><input type="checkbox" /></th>
									<td class="tm-product-name"  style="padding-right: 20px;">${list.item_name}</td>
									<td  style="padding-right: 20px;">${list.item_price }</td>
									<td  style="padding-right: 50px;">${list.sales }</td>
									<td  style="padding-right: 20px;">${list.sales_amount }</td>
									<td><a href="#" class="tm-product-delete-link"> 
									<i class='fa-solid fa-circle-info'></i>
									</a></td>
								</tr>
								</c:forEach>
								
								<tr>
									<th scope="row"></th>
									<td class="tm-product-name"  style="padding-right: 20px;">총 매출</td>
									<td  style="padding-right: 20px;"></td>
									<td  style="padding-right: 50px;"></td>
									<td  style="padding-right: 20px;">${total_sales}</td>
									<td></td>
								</tr>
								
							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a href="addstore"
						class="btn btn-primary btn-block text-uppercase mb-3">새상품 등록</a>
					<button class="btn btn-primary btn-block text-uppercase">
					상품 삭제</button>
				</div>
			</div>
<!-- 			<div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col"> -->
<!-- 				<div class="tm-bg-primary-dark tm-block tm-block-product-categories"> -->
<!-- 					<h2 class="tm-block-title">상품 정보</h2> -->
<!-- 					<div class="tm-product-table-container"> -->
					
<!-- 					<image alt="" src=""> -->
						
<!-- 					</div> -->
					<!-- table container -->
					
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- https://jquery.com/download/ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<script>
   
    </script>
</body>
</html>