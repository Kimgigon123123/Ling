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
<!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
</head>
<body>
	<div class="container mt-5">
		<div class="row tm-content-row">
			<div class="col-sm-12 col-md-12 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-products">
					<div class="tm-product-table-container">
						<table class="table tm-table-small tm-product-table">
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">이름</th>
									<th scope="col">주소</th>
									<th scope="col">전화번호</th>
									<th scope="col">영업시간</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list">
								<tr>
									<th scope="row"><input type="checkbox" name="selectedItems" value="${list.date_id }"/></th>
									<td class="tm-product-name">${list.date_name }</td>
									<td>${list.date_address }</td>
									<td>${list.tel }</td>
									<td>${list.open } - ${list.end }</td>
									<td><a href="info?date_id=${list.date_id }" class="tm-product-info-link">
									<i class="fa-solid fa-circle-info" style="color: #ffffff;"></i>
									</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a href="new"
						class="btn btn-primary btn-block text-uppercase mb-3">음식점 등록</a>
					<button type="button" class="btn btn-primary btn-block text-uppercase" id="deleteBtn">
						선택 항목 삭제</button>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- https://jquery.com/download/ -->
	<!-- <script src="js/bootstrap.min.js"></script> -->
	<!-- https://getbootstrap.com/ -->
	<script>
		document.getElementById("deleteBtn").addEventListener("click", function() {
	        var checkboxes = document.getElementsByName("selectedItems");
	        var checkedItems = [];
	
	        checkboxes.forEach(function(checkbox) {
	            if (checkbox.checked) {
	                checkedItems.push(checkbox.value);
	            }
	        });
	        
	        if (checkedItems.length > 0) {
	            // document.getElementById("deleteForm").submit();
	            $.ajax({
	          	  url: 'multipledelete',
	          	  data: {tdata: checkedItems.join(), date_category_code:'RE'},
	          	  success:function(){
	          		  location="restaurant"
	          	  }
	            })
	        } else {
	        	alert("삭제할 항목을 선택해주세요.");
	        }
	    })
    </script>
</body>
</html>