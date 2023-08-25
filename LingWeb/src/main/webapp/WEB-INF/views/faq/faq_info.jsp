<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="tb-row mt-5">
	<colgroup>
	<col width="180px"><col><col width="160px"><col width="160px"><col width="100px"><col width="100px">
	</colgroup>
		<tr>
			<th>제목</th>
			<td colspan="5">${vo.faq_title}</td>
		</tr>
		
		<tr>
			<th>카테고리</th>
			<td>${vo.faq_category}</td>
			<th>작성일자</th>
			<td>${vo.faq_date }</td>
		</tr>

		<tr>
			<th>내용</th>
			<td colspan="5">${vo.faq_content}</td>
		</tr>


	</table>
	<c:choose>
    <c:when test="${loginId eq 'admin'}">
        <div class="btn-toolbar gap-2 my-3 justify-content-center">
            <a class="btn btn-primary" id="btn-modify">FAQ 수정</a>
            <a class="btn btn-primary" id="btn-list">FAQ 목록</a>
            <a class="btn btn-primary" id="btn-delete">FAQ 삭제</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-auto">
            <a class="btn btn-primary" id="btn-list">FAQ 목록</a>
        </div>
    </c:otherwise>
</c:choose>
	
</body>
</html>