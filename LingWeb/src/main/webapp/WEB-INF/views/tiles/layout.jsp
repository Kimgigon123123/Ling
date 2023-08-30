<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ling Admin - 관리자 모드</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<!-- https://fonts.google.com/specimen/Roboto -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<!-- https://fontawesome.com/ -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- https://getbootstrap.com/ -->

<link rel="stylesheet" href="css/templatemo-style.css">
<!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
	<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
	<!-- https://jquery.com/download/ -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
	<!-- https://momentjs.com/ -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<!-- http://www.chartjs.org/docs/latest/ -->
	
	<!-- https://getbootstrap.com/ -->
	<script src="js/tooplate-scripts.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script src="js/common.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	
	
</head>

<body id="reportsPage">
	<div class="" id="home">
		<nav class="navbar navbar-expand-xl">
			<div class="container h-100">
				<a class="navbar-brand" href="admin">
					<h1 class="tm-site-title mb-0">Product Admin</h1>
				</a>
				<button class="navbar-toggler ml-auto mr-0" type="button"
					data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-bars tm-nav-icon"></i>
				</button>

<!-- active_category -->

${active_category}
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mx-auto h-100">
					
					
					<li class="nav-item dropdown"><a
							class="nav-link ${active_category eq 'admin' ? 'active' : '' }" href="admin" id="navbarDropdown">
							<i class="fa-solid fa-house-user"></i> <span>
									관리자 모드 
							</span>
						</a>
							</li>
						<li class="nav-item"><a class="nav-link ${active_category eq 'lingmember' ? 'active' : '' }" href="lingmember">
								<i class="far fa-user"></i> 회원관리
						</a></li>
						<li class="nav-item dropdown"><a class="nav-link ${active_category eq 'travel' ? 'active' : '' } dropdown-toggle" href="#" 
						id="navbarDropdown"	role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="far fa-file-alt"></i> <span>
						데이트 <i class="fas fa-angle-down"></i></span>
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="travel">여행</a> <a
									class="dropdown-item" href="restaurant">맛집</a> <a
									class="dropdown-item" href="festival">축제</a>
							</div></li>
						<li class="nav-item dropdown"><a class="nav-link ${active_category eq 'store' ? 'active' : '' }  dropdown-toggle " href="#"
						id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><i class="far fa-file-alt"></i><span>
						스토어<i class="fas fa-angle-down"></i></span>
						</a>
						
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="store">상품관리/매출확인</a> <a
									class="dropdown-item" href="store_delivery">주문조회</a> <a
									class="dropdown-item" href="store_return">환불목록</a>
							</div></li>

						<li class="nav-item">
							<a class="nav-link  ${active_category eq 'board' ? 'active' : '' }" href="board">
							<i class="fa-regular fa-clipboard"></i> 게시판 <spanclass="sr-only"></span>
						</a></li>
					</ul>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link d-block"
							href="logout"> admin, <b>Logout</b>
						</a></li>
					</ul>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link d-block"
							href="/lingweb"> admin, <b>home</b>
						</a></li>
					</ul>
				</div>
			</div>

		</nav>
		<div id="container" class="container-fluid my-0">
                    <tiles:insertAttribute name="container"/>
        </div>
		
		<footer class="tm-footer row tm-mt-small">
			<div class="col-12 font-weight-light">
				<p class="text-center text-white mb-0 px-4 small">
					Copyright &copy; <b>2023</b> All rights reserved C-Team <a
						rel="nofollow noopener" href="#"
						class="tm-footer-link">Project LING</a>
				</p>
			</div>
		</footer>
	</div>

	
	
</body>

</html>