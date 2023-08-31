<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

  <body id="reportsPage">
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">글 등록 및 수정 (${board_cd})</h2>
              
              
              <form action="insertboard" method="post" class="tm-signup-form row">
           		<input type="hidden" value="${board_cd}" name="board_cd">
<!-- 	            <select class="custom-select col-lg-4" id="filterSelect" name="board_cd"> -->
<%-- 	                <option value="NOTICE"  ${board_cd eq 'NOTICE' ? 'selected' : ''}>공지사항</option> --%>
<%-- 	                <option value="FREE"  ${board_cd eq 'FREE' ? 'selected' : ''}>자유게시판</option> --%>
<%-- 	                <option value="WORRY"  ${board_cd eq 'WORRY' ? 'selected' : ''}>고민상담소</option> --%>
<%-- 	                <option value="PLAY"  ${board_cd eq 'PLAY' ? 'selected' : ''}>짝궁놀이터</option> --%>
<!-- 	            </select> -->
                <div class="form-group col-lg-6">
                  <label for="title">title</label>
                  <tbody>
    				<tr>
    				<td>
        			<div class="input-group" >
            
            		<div style="width:88%;">
                	<input type="text" class="form-control validate" minlength="2" maxlength="50" placeholder="글 제목" name="title" value="${vo.title}" maxlength="40" style="width: 100%;">
            		</div>
        			</div>
   					</td>
					</tr>
					</div>
					<div class="form-group col-lg-12">
					<label for="content">content</label>
      				<tr>
					<td><textarea  type="text" class="form-control validate" id="content" placeholder="글 내용을 작성하세요" name="content" value="${vo.content}" maxlength="1024" style="height: 400px;"></textarea></td>

      				</tr>

    				</tbody>
                  	</div>
                
                
                  
                  
                
                <div class="form-group col-lg-6">
                  <label for="name">writer</label>
                  <input
                    id="writer"
                    name="writer"
                    type="text"
                    placeholder="admin"
                    value="admin"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label class="tm-hide-sm">&nbsp;</label>
                  <button
                    type="submit"
                    class="btn btn-primary btn-block text-uppercase">
                    신규저장
                  </button>
                </div>
            	    
              </form>
            </div>
          </div>
        </div>
        <!-- row -->
        <div class="row tm-content-row">
          <div class="tm-block-col tm-col-avatar">
            
          </div>
          <div class="tm-block-col tm-col-account-settings">
            
          </div>
        </div>
      </div>
<script>
  $(function() {
      $("form").submit(function(event) {
          // 각 입력 필드의 값을 가져옴
          console.log(rtnNameFindValue("title"));
          console.log(rtnNameFindValue("content"));
          console.log(rtnNameFindValue("writer"));
          
          if (rtnNameFindValue("title") != "" && rtnNameFindValue("content") != "" && $(".writer").val() != "") {
         
          }else{
        	     alert("모든 입력 항목을 채워주세요.");
                 event.preventDefault(); // 제출을 막음
          }
      });
  });
  
  function rtnNameFindValue(name) {
	 return  $("[name=" + name + "]").val() ;
	}
  </script>
 
  </body>
 
 
  

</html>
