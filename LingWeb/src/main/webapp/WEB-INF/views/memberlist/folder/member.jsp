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
<table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
<!-- 				<th scope="col"><a href="javascript:list_id_by('id')">ID</a></th> -->
					<th scope="col"><a class="sort" data-idsort='${idsort}' data-order='id'>ID</a></th>
					<th scope="col">PW</th>
<!-- 					<th scope="col"><a href="javascript:list_id_by('name')">NAME</a></th> -->
					<th scope="col"><a class="sort" data-namesort='${namesort}' data-order='name'>NAME</a></th>
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