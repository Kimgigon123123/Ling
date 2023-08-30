<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table class="table table-hover tm-table-small tm-product-table-container2">
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

</body>
</html>