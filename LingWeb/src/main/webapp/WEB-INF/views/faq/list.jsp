<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
  />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css">


</head>
<body>
<h3 class="my-4">FAQ</h3>


<div class="container mt-6 mb-4">
<form method="post" action="list">
<input type="hidden" name="curPage" value="1"/>
<div class="row justify-content-between mb-3 mt-3">

	<div class="col-auto">
		<div class="input-group">
			<select name="search" class="form-select">
				<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
				<option value="category" ${page.search eq 'category' ? 'selected' : '' }>카테고리</option>
				<option value="title" ${page.search eq 'content' ? 'selected' : '' }>제목</option>
			</select>
		  <input type="text" name="keyword" value="${page.keyword }" class="form-control">
		 <button class="btn btn-primary px-3"><i class="fa fa-search"></i></button>
		</div>
	</div>
<!-- 	관리자로 로그인 되어 있는 경우만 새글쓰기 가능 -->
	<c:if test="${loginId eq 'admin'}">
		<div class="col-auto">
			<a class="btn btn-primary" id="btn-faqList" href="new">FAQ 등록</a>
		</div>
	</c:if>


</div>
</form>

<%-- <table class="tb-list type11 mb-4 mt-2">

<thead>
<tr>
	<th>번호</th>
	<th>카테고리</th>
	<th>제목</th>
	<th>작성일자</th>
</tr>
</thead>
<tbody>
<c:forEach items="${page.list}" var="vo">
			<tr>
				
				<td>${vo.faq_no }</td>
				<td>${vo.faq_category }</td>
				<td><a class="text-link" href="faq_info?faq_no=${vo.faq_no}">${vo.faq_title }</a></td>
				<td>${vo.faq_date }</td>
			</tr>
		</c:forEach>
</tbody>
</table>

<jsp:include page="page.jsp"></jsp:include>
<div class="foot-banner custom-footer-banner" style="overflow: auto; border: 1px solid black; padding: 2px; display: flex; align-items: center;">
  <img src="<c:url value='/image/couple.jpg'/>" alt="image" width="160" height="120" style="vertical-align: middle; margin-right: 10px;">
  <div style="text-align: left; display: inline-block;">
    <strong class="f-xlarge" style="font-size: 16px;">Ling에 대해서 궁금증을 해결하셨나요?</strong>
    <p class="f-large" style="margin: 0;">Ling을 사용하시면서 모르는 부분들은 여기서 알아가세요.</p>
    <p class="f-large" style="margin: 0;">여러분들의 행복한 연애생활을 Ling에서도 즐기세요.</p>
    <p class="f-large" style="margin: 0;">행복. 그 외의 문의사항 TEL.010-9511-3749 OR ghk1998@naver.com</p>
  </div> --%>
<!-- </div> -->



<c:forEach items="${page.list}" var="vo" varStatus="loop">
	
<div class="accordion accordion-flush">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse-${loop.index}" aria-expanded="false" aria-controls="flush-collapseOne">
        [${vo.faq_category }]  ${vo.faq_title }
      </button>
    </h2>
    <div id="flush-collapse-${loop.index}" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
  <div class="accordion-body" style="max-height: 300px; overflow-y: auto;">
    <div class="d-flex justify-content-between">
      <pre>${vo.faq_content }</pre>
      <c:if test="${loginId eq 'admin'}">
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton-${loop.index}" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           ···
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton-${loop.index}">
          <a class="dropdown-item" href="faq_modify?faq_no=${vo.faq_no}">수정</a>
          <a class="dropdown-item" href="javascript:if(confirm('이 FAQ 글을 삭제하시겠습니까?'))
                                                     {location='faq_delete?faq_no=${vo.faq_no }'}">삭제</a>
        </div>
      </div>
      </c:if>
    </div>
  </div>
</div>
  </div>
  
</div>
</c:forEach>

<jsp:include page="page.jsp"></jsp:include>

<div class="clearboth"></div>
</div>
<!-- 삽입내용 끝 -->



<div class="content_clear"></div>

<script>
    // 페이지 로드 시 실행되는 함수
    window.onload = function() {
        // select 요소의 값이 변경될 때마다 호출되는 함수
        document.querySelector('select[name="search"]').addEventListener('change', function() {
            // 선택된 옵션의 값 가져오기
            var selectedValue = this.value;

            // 만약 '전체' 옵션을 선택한 경우, keyword input 값을 초기화
            if (selectedValue === 'all') {
                document.getElementById('keyword-input').value = '';
            }
        });
    };
</script>
</body>
</html>