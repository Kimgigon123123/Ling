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
					<div class="row mx-0 justify-content-between align-items-center mb-3">
	          <h2 class="tm-block-title col-lg-4 px-0">상품 정보</h2>
					
	            <select class="custom-select col-lg-4" id="filterSelect">
	                <option value="0">전체</option>
	                <option value="1">커플옷</option>
	                <option value="2">커플반지</option>
	                 <option value="3">상품권</option>
	                <option value="4">기타</option>
	             
	            </select>
            </div>
					<div class="tm-product-table-container">
						<table class="table table-hover tm-table-small tm-product-table-container2">
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">상품명</th>
									<th scope="col">상품가격</th>
									<th scope="col">판매갯수</th>
									<th scope="col">매출액</th>
									<th scope="col">&nbsp;</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${list}" var="list">
									<tr>
									<th></th>
<%-- 										<th scope="row"><a onclick="openSmallWindow('${list.item_img}')" --%>
<!-- 											class="tm-product-delete-link"> <i -->
<!-- 												class='fa-solid fa-circle-info'></i> -->
<!-- 										</a></th> -->
										<td class="tm-product-name">${list.item_name}</td>
										<td>${list.item_price }</td>
										<td>${list.sales }</td>
										<td>${list.sales_amount }</td>
										<td><a onclick="openSmallWindow('${list.item_img}')"
											class="tm-product-delete-link"> <i
												class='fa-solid fa-circle-info'></i>
										</a></td>
										<td><a href="store_update?item_code=${list.item_code }"
											class="tm-product-delete-link">
											<i class="fa-solid fa-wrench"></i>
										</a></td>
									</tr>
								</c:forEach>

								<tr>
									<th scope="row"></th>
									<td class="tm-product-name">총 매출</td>
									<td></td>
									<td></td>
									<td>${total_sales}</td>
									<td></td>
									<td></td>
								</tr>

							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a href="addstore"
						class="btn btn-primary btn-block text-uppercase mb-3">새상품 등록</a> 
						
					
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
<!-- 	<script src="js/bootstrap.min.js"></script> -->
	<!-- https://getbootstrap.com/ -->
	<script>
   
	function openSmallWindow(url) {
	    var width = 400; // 새 창 너비
	    var height = 300; // 새 창 높이
	    var left = (window.innerWidth - width) / 2; // 창 가로 가운데 정렬
	    var top = (window.innerHeight - height) / 2; // 창 세로 가운데 정렬

	    // 새 창 열기
	    window.open(url, '_blank', 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top);
	}
	
	$(document).ready(function() {
		$("#filterSelect").change(function(){
	   		  console.log($(this).val())
	       		$.ajax({
	    		   url:'storelist', 
	    		   data: {tablename:$(this).val()}
	    	   }).done(function(response){
	    		   $('.tm-product-table-container').html(response)
	    	     }) 
	   	  }) 
	        
	    });
	    
    </script>
</body>
</html>