<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css">
</head>
<body>
	<h3 class="my-4">FAQ 수정</h3>
<form method="post" action="faq_update">
<input type="hidden" name="faq_no" value='${vo.faq_no }'>
<div class="container mt-9">
  <table class="table table-bordered" id="info_table" style="border-radius: 10px;">
    <colgroup>
      <col width="90px">
      <col width="380px">
    </colgroup>
    
    <tr>
      <th>카테고리</th>
      <td>
        <select name="faq_category" class="form-select" style="width: 100%;" required>
          <option value="" disabled selected>카테고리 선택</option>
           <option value="이용방법" ${vo.faq_category eq '이용방법' ? 'selected' : ''}>이용방법</option>
           <option value="교환반품" ${vo.faq_category eq '교환반품' ? 'selected' : ''}>교환반품</option>
           <option value="계정" ${vo.faq_category eq '계정' ? 'selected' : ''}>계정</option>
        </select>
      </td>
    </tr>
    <tr>
      <th>제목</th>
      <td><input type="text" value="${fn: escapeXml(vo.faq_title) }"
               name="faq_title" class="check-empty form-control" title="제목"></td>
    </tr>
    <tr>
      <th>내용</th>
      <td><textarea name="faq_content" class="check-empty form-control"
                    title="내용" style="height: 300px;">${vo.faq_content }</textarea></td>
    </tr>
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