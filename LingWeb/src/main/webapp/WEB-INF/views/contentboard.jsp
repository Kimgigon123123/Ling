<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
        /* 스타일 설정 */
        /* .comment-section {
            display: none;
            margin-top: 20px;
            border-top: 1px solid #ccc;
            padding-top: 20px;
        } */
        .comment {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #50697f;
    		color: #fff;
        }
        .comment .author {
            font-weight: bold;
        }
        .comment .content {
            margin-top: 10px;
        }
        .comment .time {
            font-size: 12px;
            color: #888;
        }
       
        
    </style>
</head>
  <body id="reportsPage">
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">상세정보 (${vo.board_cd})</h2>
              
              
              
           		<input type="hidden" value="${vo.board_cd}" name="board_cd">
           		<input type="hidden" value="${vo.id}" name="id">
<!-- 	            <select class="custom-select col-lg-4" id="filterSelect" name="board_cd"> -->
<%-- 	                <option value="NOTICE"  ${board_cd eq 'NOTICE' ? 'selected' : ''}>공지사항</option> --%>
<%-- 	                <option value="FREE"  ${board_cd eq 'FREE' ? 'selected' : ''}>자유게시판</option> --%>
<%-- 	                <option value="WORRY"  ${board_cd eq 'WORRY' ? 'selected' : ''}>고민상담소</option> --%>
<%-- 	                <option value="PLAY"  ${board_cd eq 'PLAY' ? 'selected' : ''}>짝궁놀이터</option> --%>
<!-- 	            </select> -->
                <div class="row">
						<div class="col-12">
							<h2 class="tm-block-title d-inline-block"></h2>
						</div>
					</div>
					
						<table class="table tm-table-small tm-product-table">
							<colgroup>
								<col width="180px">
								<col>
							</colgroup>
							<tr>
								<th>제목</th>
								<td>${vo.title}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${vo.writer}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>${vo.content}</td>
							</tr>
						</table>
						<div class="btn-toolbar gap-2 mt-5">
							<button class="btn btn-primary" onclick="goBack()">목록으로</button>
							<button class="btn btn-info" onclick="toggleComments()">댓글보기</button>
						</div>
					

    				
                  	</div>
            	    
              
            </div>
          </div>
          
          <div id="commentSection" style="display: none;">
          <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              
   <h2 class="tm-block-title">↳ 댓글 (${vo.count})</h2>
           
   
					
		<c:forEach items="${cvo}" var="cvo">
			<div class="comment">
            <div class="author">${cvo.writer}</div>
            <div class="content">${cvo.content}</div>
            <div class="time">${cvo.writedate }</div>
            <button onclick="toggleReComments(${cvo.id},$(this))">답글(${cvo.count})</button>
            	<div class="recommentSection" style="display: none; margin-left: 30px;margin-top: 20px;">
                  	<div class="row">
					<div class="col-10">
					<h3 class="tm-block-title">↳ 답글</h3>
					</div>
					</div>
          
<%--                 <c:forEach items="${rvo}" var="rvo"> --%>
<!-- 		            <div class="recomment"> -->
<%-- 		            <div class="author">${rvo.writer}</div> --%>
<%-- 		            <div class="content">${rvo.content}</div> --%>
<%-- 		            <div class="time">${rvo.writedate}</div> --%>
<!-- 		            </div> -->
<%--             	</c:forEach>     --%>
          		</div>
          </div>
        </c:forEach>    
            </div>
                  	</div>
                  	
        </div>
        </div>
        </div>

  </body>
<script>
function goBack() {
    window.history.back();
}
function toggleComments() {
    var commentSection = document.getElementById("commentSection");
    if (commentSection.style.display === "none") {
        commentSection.style.display = "block";
    } else {
        commentSection.style.display = "none";
    }
}
function toggleReComments(id,button) {
	console.log(button.text())
	
    var recommentSection = button.siblings('.recommentSection');
    if ($(recommentSection).css('display') === "none") {
    	$.ajax({
    		url:'recomment',
    		data:{comment_id:id},
    	}).done(function(response){
    		console.log(response)
    			var recomment = '';
    		$(response).each(function(){
    	    		recomment +=
    	    		`<div class="recomment">
    	    		<div class="author">\${this.writer}</div>
    	            <div class="content">\${this.content}</div>
    	            <div class="time">\${this.writedate}</div></div>`
    		})
    		button.siblings('.recommentSection').append(recomment)
    	})
        $(recommentSection).css('display', "block");
    } else {
    	$(recommentSection).css('display', "none");
    }
}
</script>
</html>