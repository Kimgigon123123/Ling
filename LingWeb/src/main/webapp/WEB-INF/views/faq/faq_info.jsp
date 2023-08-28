<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css">
</head>
<body>
	<div class="container mt-9">
  <table class="table table-bordered" id="info_table"  style="border-radius: 10px;">
    <colgroup>
      <col width="90px">
      <col width="380px">
      <col width="380px">
      <col width="130px">
      <col width="160px">
      <col width="100px">
      <col width="150px">
    </colgroup>
    <tr>
      <th>제목</th>
      <td colspan="2" class="td-center">${vo.faq_title}</td>
      <th>카테고리</th>
      <td class="td-center">${vo.faq_category}</td>
      <th>작성일자</th>
      <td class="td-center">${vo.faq_date}</td>
    </tr>
    <tr>
      <th>내용</th>
      <td class="td-margin content-line-height" colspan="6">${vo.faq_content}</td>
    </tr>
  </table>
</div>
	<c:choose>
    <c:when test="${loginId eq 'admin'}">
        <div class="btn-toolbar gap-2 my-3 justify-content-center">
            <a class="btn btn-primary" id="btn-faqList" href="faq_modify?faq_no=${vo.faq_no}">FAQ 수정</a>
            <a class="btn btn-primary" id="btn-faqList" href="list">FAQ 목록</a>
            <a class="btn btn-primary" id="btn-faqList" href="javascript:if(confirm('이 FAQ 글을 삭제하시겠습니까?'))
            											 {location='faq_delete?faq_no=${vo.faq_no }'}">FAQ 삭제</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-auto text-center">
            <a class="btn btn-primary" id="btn-faqList" href="list">FAQ 목록</a>
        </div>
    </c:otherwise>
</c:choose>
	
</body>
	

</html>