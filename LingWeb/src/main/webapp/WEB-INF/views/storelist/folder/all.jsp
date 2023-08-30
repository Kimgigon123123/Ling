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
<style>
a {
  color: white; /* 기본 색상을 흰색으로 설정 */
  text-decoration: none; /* 밑줄 제거 */
  transition: color 0.3s; /* 색상 변화에 부드러운 트랜지션 추가 */
}

/* 링크를 마우스로 호버할 때의 색상 설정 */
a:hover {
  color: your-desired-hover-color; /* 호버 시 색상 변경 */
}
</style>


</head>
<body>


	
				
					
					
						<table class="table table-hover tm-table-small tm-product-table-container2">
							<thead>
								<tr>
									<th scope="col"><a href="store">아이템번호</a></th>
									<th scope="col"><a href="store_name_by">상품명</a></th>
									<th scope="col"><a href="store_price_by">상품가격</a></th>
									<th scope="col"><a href="store_sales_by">판매갯수</th>
									<th scope="col"><a href="store_sales_amount">매출액</th>
									<th scope="col">&nbsp;</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${list}" var="list">
									<tr>
									<th>${list.item_code}</th>
<%-- 										<th scope="row"><a onclick="openSmallWindow('${list.item_img}')" --%>
<!-- 											class="tm-product-delete-link"> <i -->
<!-- 												class='fa-solid fa-circle-info'></i> -->
<!-- 										</a></th> -->
										<td class="tm-product-name">${list.item_name}</td>
										<td>${list.st_item_price }</td>
										<td>${list.sales }</td>
										<td>${list.st_sales_amount }</td>
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
					
					
						
					
				
			

				
			

</body>
</html>