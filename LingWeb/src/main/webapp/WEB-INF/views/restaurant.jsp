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
			<form method="post" action="restaurant">
			<div class="row justify-content-between">
			<div class="col-4">
				<div class="input-group mb-4">
					<input type="text" autocomplete="off" value="${page.search }" name="search" placeholder="검색어를 입력하세요." class="form-control custom-search-input">
					<button class="btn px-3 py-2">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
				</div>
				<div class="col-auto">
					<select class="custom-select px-4" name="pageList">
						<c:forEach var="i" begin="1" end="5">
							<option value="${10*i }">${10*i }개씩</option>
						</c:forEach>
					</select>
					</div>
					<input type="hidden" name="curPage" value="1">
					</div>
			</form>
				<div class="tm-bg-primary-dark tm-block tm-block-products2">
					<div class="tm-product-table-container2">
						<table class="table tm-table-small">
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
							<c:forEach items="${page.list}" var="vo">
								<tr>
									<th scope="row"><input type="checkbox" name="selectedItems" value="${vo.date_id }"/></th>
									<td class="tm-product-name">${vo.date_name }</td>
									<td>${vo.date_address }</td>
									<td>${vo.tel }</td>
									<td>${vo.open } - ${vo.end }</td>
									<td><a href="info?date_id=${vo.date_id }" class="tm-product-info-link">
									<i class="fa-solid fa-circle-info" style="color: #ffffff;"></i>
									</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<jsp:include page="/WEB-INF/views/page.jsp" />
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
	$(function(){
		if($('.tm-product-table-container2 table tbody tr').length > 10) {
			$('.tm-product-table-container2 table').addClass('tm-product-table')
		}
	})
		document.getElementById("deleteBtn").addEventListener("click", function() {
	        var checkboxes = document.getElementsByName("selectedItems");
	        var checkedItems = [];
	
	        checkboxes.forEach(function(checkbox) {
	            if (checkbox.checked) {
	                checkedItems.push(checkbox.value);
	            }
	        });
	        
	        if (checkedItems.length > 0) {
	        	var confirmation = confirm("삭제하시겠습니까?");
	        	if(confirmation) {
	            // document.getElementById("deleteForm").submit();
	            $.ajax({
	          	  url: 'multipledelete',
	          	  data: {tdata: checkedItems.join(), date_category_code:'RE'},
	          	  success:function(){
	          		  location="restaurant"
	          	  }
	            });
	          }
	        } else {
	        	alert("삭제할 항목을 선택해주세요.");
	        }
	    })
	    
	    // 조회목록갯수변경시
		$('[name=pageList]').change(function(){
			// 목록갯수 변경시는 총 페이지수가 달라지므로 항상 1페이지에 위치해야 함.
			if ($('[name=curPage]').val(${page.curPage}))
			$('form').submit()
		})
		// 해당 목록갯수가 선택되어있게
		$('[name=pageList]').val(${page.pageList}).prop('selected', true);
    </script>
</body>
</html>