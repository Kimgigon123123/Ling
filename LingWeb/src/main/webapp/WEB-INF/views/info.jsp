<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
<!-- https://fonts.google.com/specimen/Roboto -->
<link rel="stylesheet" href="css/fontawesome.min.css" />
<!-- https://fontawesome.com/ -->
<!-- http://api.jqueryui.com/datepicker/ -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet" href="css/templatemo-style.css">
<!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
<style>
.tm-product-table th:first-child {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row tm-content-row">
			<div class="col-sm-12 col-md-12 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-products3">
					<div class="row">
						<div class="col-12">
							<h2 class="tm-block-title d-inline-block">상세정보</h2>
						</div>
					</div>
					<div class="tm-product-table-container4">
						<table class="table tm-table-small tm-product-table">
							<colgroup>
								<col width="180px">
								<col>
							</colgroup>
							<tr>
								<th>이름</th>
								<td>${vo.date_name }</td>
							</tr>
							<tr>
								<th>주소</th>
								<td>${vo.date_address }</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td>${vo.tel }</td>
							</tr>
							<tr>
								<th>OPEN</th>
								<td>${vo.open }</td>
							</tr>
							<tr>
								<th>END</th>
								<td>${vo.end }</td>
							</tr>
							<tr>
								<th>상세정보</th>
								<td>${vo.date_intro }</td>
							</tr>
							<tr>
								<th>상세이미지</th>
								<td><img src="${vo.date_img }" width="700" height="500"></td>
							</tr>
						</table>
						<div class="btn-toolbar gap-2 mt-5 justify-content-center">
							<button id="listbtn" class="btn btn-primary">목록으로</button>
							<button class="btn btn-info"
								onclick="location='modify?date_id=${vo.date_id}'">정보수정</button>
							<button class="btn btn-danger"
								onclick="if(confirm('삭제하시겠습니까?') ) location='deletedate?date_id=${vo.date_id}&date_category_code=${vo.date_category_code }'">삭제하기</button>
						</div>
						<script>
								var date_category_code = "${vo.date_category_code}";
								document.getElementById("listbtn").onclick = function() {
								if(date_category_code === "TO") {
									window.location.href = "travel";
								} else if(date_category_code === "RE") {
									window.location.href = "restaurant";
								} else if(date_category_code === "FE") {
									window.location.href = "festival";
								} else {
									window.location.href = "travel";
								}
							};
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>