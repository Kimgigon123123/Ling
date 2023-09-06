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
body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
           
        }
        
        .container2 {
            width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .product {
            display: flex;
            border: 1px solid #ddd;
            padding: 20px;
            background-color: #4e657a;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            color: #fff;
        }
        
        .product-image {
            flex: 1;
            margin-right: 20px;
        }
        
        .product-image img {
            max-width: 100%;
            border-radius: 10px;
        }
        
        .product-details {
            flex: 2;
        }
        
        .product-title {
            font-size: 28px;
            margin: 0;
        }
        
        .product-content {
            font-size: 18px;
            margin-top: 10px;
        }
        
        .product-price {
            font-size: 32px;
            margin-top: 20px;
        }
        
      
 
        }
</style>


   
</head>
<body>
   <div class="container2">
        <div class="product">
            <div class="product-image">
                <img src="${vo.item_img}" width="400" height="250" alt="Product Image">
            </div>
            <div class="product-details">
                <h1 class="product-title">
                    ${vo.item_name}
                    <button onclick="goBack()"><i class="fa-solid fa-arrow-left"></i></button>
                </h1>
                <p class="product-content">${vo.item_content}</p>
                <p class="product-price">${vo.item_price}원</p>
            </div>
        </div>
    </div>
    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>



<!-- </head> -->
<!-- <body> -->
<!-- 	<div class="container mt-5"> -->
<!-- 		<div class="row tm-content-row"> -->
<!-- 			<div class="col-sm-12 col-md-12 tm-block-col"> -->
<!-- 				<div class="tm-bg-primary-dark tm-block tm-block-products2"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-12"> -->
<!-- 							<h2 class="tm-block-title d-inline-block">상세정보</h2> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="tm-product-table-container2"> -->
<!-- 						<table class="table tm-table-small tm-product-table"> -->
<%-- 							<colgroup> --%>
<%-- 								<col width="180px"> --%>
<%-- 								<col> --%>
<%-- 							</colgroup> --%>
<!-- 							<tr> -->
<!-- 								<th>이름</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>주소</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>전화번호</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>OPEN</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>END</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>상세정보</th> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<th>상세이미지</th> -->
<!-- 								<td><img src="" width="700" height="500"></td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 						<div class="btn-toolbar gap-2 mt-5 justify-content-center">
<!-- 							<button class="btn btn-primary" onclick="loation='list.cu'">목록으로</button> -->
<!-- 							<button class="btn btn-info" -->
<!-- 								onclick="location='modify?id='">정보수정</button> -->
<!-- 							<button class="btn btn-danger" -->
<!-- 								onclick="if(confirm('삭제하시겠습니까?') ) location='delete?id='">삭제하기</button> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </body> -->
<!-- </html> -->