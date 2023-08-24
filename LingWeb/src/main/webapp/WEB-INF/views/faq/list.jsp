<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--         <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        Google fonts
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap" rel="stylesheet" />
        Core theme CSS (includes Bootstrap)
        <link href="css/styles.css" rel="stylesheet" /> -->
        
        <style>
         img{ float: left;}
         
         .p2{ clear: both;}
        
   .tb-list {
        width: 100%;
        border-collapse: collapse;
        border-radius: 16px;
        overflow:hidden;
        
    }

    .tb-list th,
    .tb-list td {
        padding: 8px;
        border: 1px solid #ddd;
    }

    .tb-list th:first-child,
    .tb-list td:first-child {
        width: 100px;
    }

    .tb-list th:nth-child(2),
    .tb-list td:nth-child(2) {
    width: 120px;
    }

    .tb-list th:nth-child(3),
    .tb-list td:nth-child(3) {
    width: auto;
        
    }

    .tb-list th:nth-child(4),
    .tb-list td:nth-child(4) {
        width: 120px;
    }
    
    table.type11 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;

}
table.type11 thead th {
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #369;
  text-align: center;
  background-color: #f3f6f7;
  border-bottom: 3px solid #036;
}
table.type11 tbody td {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  text-align: center;
  border-bottom: 1px solid #ccc;
  
}

container {

	
}


</style>
</head>
<body>
<h3 class="my-4">FAQ</h3>


<div class="container my-4">
<form method="post" action="list">
<input type="hidden" name="curPage" value="1"/>
<div class="row justify-content-between mb-3 mt-3">

	<div class="col-auto">
		<div class="input-group">
			<select name="search" class="form-select">
				<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
				<option value="category" ${page.search eq 'category' ? 'selected' : '' }>카테고리</option>
				<option value="content" ${page.search eq 'content' ? 'selected' : '' }>내용</option>
			</select>
		  <input type="text" name="keyword" value="${page.keyword }" class="form-control">
		 <button class="btn btn-primary px-3"><i class="fa-solid fa-magnifying-glass"></i></button>
		</div>
	</div>
<!-- 	관리자로 로그인 되어 있는 경우만 새글쓰기 가능 -->
	<c:if test="${loginInfo.admin eq 'Y' }"></c:if>

	<div class="col-auto">
		<a class="btn btn-primary" href="new">새글쓰기</a>
	</div>
</div>
</form>

<table class="tb-list type11 mb-4">

<thead>
<tr>
	<th>번호</th>
	<th>카테고리</th>
	<th>제목</th>
	<th>작성일자</th>
</tr>
</thead>
<tbody>
<c:forEach items="${list}" var="vo">
			<tr>
				<%-- <td><a class="text-link" href="info.cu?id=${vo.id }">${vo.faqname }</a></td> --%>
				
				<td>${vo.faq_no }</td>
				<td>${vo.faq_category }</td>
				<td>${vo.faq_title }</td>
				<td>${vo.faq_date }</td>
			</tr>
		</c:forEach>
</tbody>
</table>
<div style="overflow: auto; border: 2px solid black; padding: 2px;">
 <img src="<c:url value= '/image/couple.jpg'/>" alt="image" width="160" height="120">
        <!-- 왼쪽 마진 적용해보기 : 잘 안됨 -->
        <div style="text-align: left;"><strong class="f-xlarge"><font style="font-size: 16px; text-align: left;">Ling에 대해서 궁금증을 해결하셨나요?</font></strong></div>
		<p class="f-large"><strong style="font-size: 16px; text-align: left; margin:0">여기서 모두 해결하고 가세요!</strong></p>
		<p class="f-large"><strong style="font-size: 16px; text-align: left; margin:0"> 여러분들의 행복한 연애생활을</strong></p>
		<p class="f-large"><strong style="font-size: 16px; text-align: left; margin:0">Ling에서 즐겨보세요!</strong></p>
 

</div>
<!-- 삽입내용 시작 -->

<div class="clearboth"></div>
</div>
<!-- 삽입내용 끝 -->



<div class="content_clear"></div>

</body>
</html>