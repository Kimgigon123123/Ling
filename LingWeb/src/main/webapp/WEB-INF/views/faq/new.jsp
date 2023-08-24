<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	container{
		margin: 1em auto 1em;
	}

</style>

</head>
<body>
<form method="post" action="faq_insert.jsp">
<div class="container my-4">

  <h2>새로운 FAQ 추가</h2>          

  <table class="table table-hover">

    <tbody>

    <tr>
    <td>
        <div class="input-group" >
            <select name="search" class="form-select" style="width:12%;" required>
                <option value="" disabled selected>카테고리 선택</option>
                <option value="use">이용방법</option>
                <option value="change">교환반품</option>
                <option value="account">계정</option>
            </select>
            <div style="width:88%;">
                <input type="text" class="form-control" placeholder="글 제목" name="contentTitle" maxlength="40" style="width: 100%;">
            </div>
        </div>
    </td>
</tr>

      <tr>

      	<td><textarea type="text" class="form-control" placeholder="글 내용을 작성하세요" name="contentDetail" maxlength="1024" style="height: 400px;"></textarea></td>

      </tr>

    </tbody>

  </table>


</div>

</form>

<div class="btn-toolbar my-3 gap-2 justify-content-center">
		<button class="btn btn-primary px-4" id="btn-save">저장</button>
		<button class="btn btn-danger px-4" type="button"
			onclick="history.go(-1)">취소</button>
	</div>
	<script src="<c:url value='/resources/js/jquery-3.3.1.min.js'/>"></script>
	<script src="<c:url value='/resources/js/scripts.js'/>"></script>
	
		<script>
		$('#btn-save').on('click', function() {
				$('form').submit()
		})
	</script>
</body>
</html>