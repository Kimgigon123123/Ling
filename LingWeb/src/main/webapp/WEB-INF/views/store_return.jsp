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
				
				
				
				<div class="row mx-0 justify-content-between align-items-center mb-3">
	          <h2 class="tm-block-title col-lg-4 px-0">상품 정보</h2>
					
	            <select class="custom-select col-lg-4" id="DeliverySelect">
	                <option value="0">전체</option>
	                <option value="1" >환불처리중</option>
	                <option value="2" >환불처리완료</option>
	                 <option value="3" >환불취소</option>
	               
	             
	            </select>
            </div>
				
				
				
					<div class="tm-product-table-container">
						<table class="table table-hover tm-table-small tm-product-table">
							<thead>
								<tr>
									<th scope="col">&nbsp;</th>
									<th scope="col">상품명</th>
									<th scope="col">환불상태</th>
									<th scope="col">주소</th>
									<th scope="col">주문정보</th>
									<th scope="col">&nbsp;</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${list}" var="vo">
									<tr>
										<th scope="row"><input type="checkbox"
											${vo.return_state == '환불처리완료' || vo.return_state == '환불취소' ? 'disabled' : ''} />
											<input type="hidden" class="return_code"
											value="${vo.return_code }"> <input type="hidden"
											class="userid" value="${vo.id }"></th>
										<td class="tm-product-name">${vo.item_name}</td>
										<td data-code="${vo.return_code}">${vo.return_state}</td>
										<td>${vo.address}</td>
										<td>${vo.st_item_price} * ${vo.purchase_cnt }개 <br>
											${vo.st_total_price}원
										</td>
										<td><a onclick="openSmallWindow('${vo.item_img}')"
											class="tm-product-delete-link"> <i
												class='fa-solid fa-circle-info'></i>
										</a></td>
									</tr>
								</c:forEach>



							</tbody>
						</table>
					</div>
					<!-- table container -->
					<a class="btn btn-primary btn-block text-uppercase mb-3"
						onclick="refundFunc();">환불처리</a> <a
						class="btn btn-primary btn-block text-uppercase"
						onclick="cancelFunc();"> 환불거절</a>
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

	function refundFunc() {
		var chkBoxs = $("input[type=checkbox]:checked", ".table");
		var return_codes ='';
		var userids ='';
		for(var i = 0 ; i<chkBoxs.length ; i ++){
			return_codes += (return_codes==''?'':',') +  $(chkBoxs[i]).siblings('.return_code').val();
			userids += (userids==''?'':',') +  $(chkBoxs[i]).siblings('.userid').val();
		}	
		
		console.log(return_codes);

	 	 $.ajax({
		        type: "GET", //get
		        url: "accept_return", // 컨트롤러의 URL 설정
		        data: { returnCodes: return_codes , userids: userids } , // 선택된 return_code를 컨트롤러로 전송
		        success: function(response) {
		        	
		        	if(  parseInt(response) >0){
		        		 alert("환불처리 실패");
				            
		        		
		        		
			            // 처리 완료 후 필요한 동작을 수행
		        	}else{
				           
				            alert("환불처리가 완료되었습니다.");
			        		$(chkBoxs).each(function(){
			        			$(this).prop('checked', false);
			        			$(this).closest('tr').children('td:eq(1)').text('환불처리완료');
			        			 location.reload(); 
			        		})
				  
		        	}
		            
		        },
		        
		    }); 
		}
	
	
	
	function cancelFunc() {
		var chkBoxs = $("input[type=checkbox]:checked", ".table");
		var checkedValue='';
		for(var i = 0 ; i<chkBoxs.length ; i ++){
			checkedValue += (checkedValue==''?'':',') +  $(chkBoxs[i]).siblings().val();
		}	

		 $.ajax({
		        type: "GET", //get
		        url: "cancel_return", // 컨트롤러의 URL 설정
		        data: { returnCodes: checkedValue }, // 선택된 return_code를 컨트롤러로 전송
		        success: function(response) {
		        	
		        	if(  parseInt(response) >0){
		        		alert("환불처리가 취소되었습니다.");
		        		$(chkBoxs).each(function(){
		        			$(this).prop('checked', false);
		        			$(this).closest('tr').children('td:eq(1)').text('환불취소');
		        			 location.reload(); 
		        		})
		        		
		        		
			            // 처리 완료 후 필요한 동작을 수행
		        	}else{
				            alert("환불처리 실패");
				  
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