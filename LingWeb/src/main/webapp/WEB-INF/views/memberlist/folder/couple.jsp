<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col"><a class="sort" data-couple_numsort='${couplesort}' data-order='couple_num'>COUPLE_NUM</a></th>
					<th scope="col">MID</th>
					<th scope="col">FID</th>
					<th scope="col">CREATE_DATE</th>
					<th scope="col">&nbsp;</th>
				</tr>
                </thead>
                <tbody>
				<c:forEach items="${list}" var="vo">
				<tr>
					<th scope="row"><input type="checkbox" /></th>
					<td class="tm-product-name">${vo.couple_num }</td>
					<td>${vo.mid }</td>
					<td>${vo.fid }</td>
					<td>${vo.create_date }</td>
					<td><a href="#" class="tm-product-delete-link"> <i class="fa-solid fa-wrench"></i>
					</a></td>
				</tr>
				</c:forEach>
                </tbody>
              </table>
</body>
</html>