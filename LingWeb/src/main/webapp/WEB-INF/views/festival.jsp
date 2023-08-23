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
									<th scope="col">기간</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="list">
								<tr>
									<th scope="row"><input type="checkbox" /></th>
									<td class="tm-product-name">${list.date_name }</td>
									<td>${list.date_address }</td>
									<c:if test="${list.tel == null}">
									    <td> - </td>
									</c:if>
									<c:if test="${list.tel != null}">
									    <td>${list.tel}</td>
									</c:if>
									<td>${list.open } - ${list.end }</td>
									<td><a href="info" class="tm-product-info-link">
									<i class="fa-solid fa-circle-info" style="color: #ffffff;"></i>
									</a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a href="new"
						class="btn btn-primary btn-block text-uppercase mb-3">축제 등록</a>
					<button class="btn btn-primary btn-block text-uppercase">
						선택 항목 삭제</button>
				</div>
			</div>
<!-- 			<div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-product-categories">
					<h2 class="tm-block-title">Product Categories</h2>
					<div class="tm-product-table-container">
						<table class="table tm-table-small tm-product-table">
							<tbody>
								<tr>
									<td class="tm-product-name">Product Category 1</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 2</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 3</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 4</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 5</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 6</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 7</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 8</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 9</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 10</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
								<tr>
									<td class="tm-product-name">Product Category 11</td>
									<td class="text-center"><a href="#"
										class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
									</a></td>
								</tr>
							</tbody>
						</table>
					</div>
					table container
					<button class="btn btn-primary btn-block text-uppercase mb-3">
						Add new category</button>
				</div>
			</div> -->
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- https://jquery.com/download/ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<script>
      /* $(function() {
        $(".tm-product-name").on("click", function() {
          window.location.href = "edit-product.html";
        });
      }); */
    </script>
</body>
</html>