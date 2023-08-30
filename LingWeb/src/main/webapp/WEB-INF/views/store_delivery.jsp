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
        /* 체크박스 비활성화 스타일 */
        input[type="checkbox"]:disabled {
            background-color: #888888; /* 배경색 */
            
            
        }
    </style>


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
									<th scope="col">배송상태</th>
									<th scope="col">주소</th>
									<th scope="col">주문정보</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>
							
							
							<c:forEach items="${list}" var="vo">
								<tr>
									<th scope="row">
									<input type="checkbox" 
									${vo.delivery_state == '배송완료' || vo.delivery_state == '배송취소' ? 'disabled' : ''} />
											<input type="hidden" class="order_num" value="${vo.order_num}">
									</th>
							
									<td class="tm-product-name"  style="padding-right: 20px;">${vo.item_name}</td>
									<td    data-code="${vo.order_num} ">${vo.delivery_state }</td>
									<td  >${vo.address }</td>
									<td  >${vo.st_item_price} * ${vo.purchase_cnt }개 <br> ${vo.st_total_price }원</td>
									<td><a 
											onclick="openSmallWindow('${vo.item_img}')"
											class="tm-product-delete-link"> <i
												class='fa-solid fa-circle-info'></i>
										</a></td>
								</tr>
								</c:forEach>
								
								
								
							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a 
						class="btn btn-primary btn-block text-uppercase mb-3" onclick="CompleteDeliveryFunc();">배송완료</a>
					<a class="btn btn-primary btn-block text-uppercase" onclick="CancelDeliveryFunc();">
					배송취소</a>
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
<!-- 	<!-- https://getbootstrap.com/ --> -->
	<script>
//     $(function() {
//         $(".tm-product-name").on("click", function() {
//           window.location.href = "edit-product.html";
//         });
//       }); 
    
    
    function CompleteDeliveryFunc() {
		var chkBoxs = $("input[type=checkbox]:checked", ".table");
		var checkedValue='';
		for(var i = 0 ; i<chkBoxs.length ; i ++){
			console.log($(chkBoxs[i]).siblings().value);
			checkedValue += (checkedValue==''?'':',') +  $(chkBoxs[i]).siblings().val();
		}	

		 $.ajax({
		        type: "GET", //get
		        url: "accept_delivery", // 컨트롤러의 URL 설정
		        data: { orderNums: checkedValue }, // 선택된 return_code를 컨트롤러로 전송
		        success: function(response) {
		        	
		        	if(  parseInt(response) >0){
		        		alert("배송완료처리가 되었습니다.");
		        		$(chkBoxs).each(function(){
		        			$(this).prop('checked', false);
		        			$(this).closest('tr').children('td:eq(1)').text('배송완료');
		        			location.reload();
		        		})
		        		
		        		
			            // 처리 완료 후 필요한 동작을 수행
		        	}else{
				            alert("실패");
				  
		        	}
		            
		        },
		        
		    });
		}
    
    
    
    function CancelDeliveryFunc() {
		var chkBoxs = $("input[type=checkbox]:checked", ".table");
		var checkedValue='';
		for(var i = 0 ; i<chkBoxs.length ; i ++){
			console.log($(chkBoxs[i]).siblings().value);
			checkedValue += (checkedValue==''?'':',') +  $(chkBoxs[i]).siblings().val();
		}	

		 $.ajax({
		        type: "GET", //get
		        url: "cancel_delivery", // 컨트롤러의 URL 설정
		        data: { orderNums: checkedValue }, // 선택된 return_code를 컨트롤러로 전송
		        success: function(response) {
		        	
		        	if(  parseInt(response) >0){
		        		alert("배송취소 되었습니다.");
		        		$(chkBoxs).each(function(){
		        			$(this).prop('checked', false);
		        			$(this).closest('tr').children('td:eq(1)').text('배송취소');
		        			location.reload(); 
		        		})
		        		
		        		
			            // 처리 완료 후 필요한 동작을 수행
		        	}else{
				            alert("실패");
				  
		        	}
		            
		        },
		        
		    });
		}
    
    function openSmallWindow(url) {
	    var width = 400; // 새 창 너비
	    var height = 300; // 새 창 높이
	    var left = (window.innerWidth - width) / 2; // 창 가로 가운데 정렬
	    var top = (window.innerHeight - height) / 2; // 창 세로 가운데 정렬

	    // 새 창 열기
	    window.open(url, '_blank', 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top);
	}
		
    </script>
</body>
</html>