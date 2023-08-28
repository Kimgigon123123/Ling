<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css">

<style>
	container{
		margin: 1em auto 1em;
	}

</style>

</head>
<body>
<form method="post" action="faq_insert">
<div class="container my-4">

  <h2>새로운 FAQ 추가</h2>          

  <table class="table table-hover mt-7">

    <tbody>

    <tr>
    <td>
        <div class="input-group" >
            <select name="faq_category" class="form-select" style="width:14%;" required>
                <option value="" disabled selected>카테고리 선택</option>
                <option value="이용방법">이용방법</option>
                <option value="교환반품">교환반품</option>
                <option value="계정">계정</option>
            </select>
            <div style="width:86%;">
                <input type="text" class="form-control" placeholder="글 제목" name="faq_title" maxlength="40" style="width: 100%;">
            </div>
        </div>
    </td>
</tr>

      <tr>

      	<td><textarea class="form-control" placeholder="글 내용을 작성하세요" name="faq_content" maxlength="1024" style="height: 400px;"></textarea></td>

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
				if($('[name=faq_category]').val()==null){
					alert('카테고리를 선택해주세요')
					$('[name=faq_category]').focus()
				}else if($('[name=faq_title]').val()==''){
					alert('제목을 입력해주세요')
					$('[name=faq_title]').focus()
				}else if($('[name=faq_content]').val()==''){
					alert('제목을 입력해주세요')
					$('[name=faq_content]').focus()
				}
				else
				$('form').submit()
		})
	</script>
</body>
</html>