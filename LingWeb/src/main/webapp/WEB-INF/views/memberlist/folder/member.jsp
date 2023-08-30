<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        /* 체크박스 비활성화 스타일 */
        input[type="checkbox"]:disabled {
            background-color: #888888; /* 배경색 */
            
            
        }
    </style>
</head>
<body>
<table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">ID</th>
					<th scope="col">PW</th>
					<th scope="col">NAME</th>
					<th scope="col">GENDER</th>
					<th scope="col">EMAIL</th>
					<th scope="col">PHONE</th>
					<th scope="col">BIRTH</th>
					<th scope="col">&nbsp;</th>
				</tr>
                </thead>
                <tbody>
				<c:forEach items="${list}" var="vo">
				<tr>
					<th scope="row"><input type="checkbox" ${vo.couple_num != null ? 'disabled' : ''}/></th>
					<td class="tm-product-name">${vo.id }</td>
					<td>${vo.pw }</td>
					<td>${vo.name }</td>
					<td>${vo.gender }</td>
					<td>${vo.email }</td>
					<td>${vo.phone }</td>
					<td>${vo.birth }</td>
					<td><a href="detailmember?id=${vo.id }" class="tm-product-delete-link"> <i class="fa-solid fa-wrench"></i>
					</a></td>
				</tr>
				</c:forEach>
                </tbody>
              </table>
</body>
</html>