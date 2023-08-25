<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  
<body id="reportsPage">

<div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-12 col-12 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
          <div class="row mx-0 justify-content-between align-items-center mb-3">
	          <h2 class="tm-block-title col-lg-4 px-0">게시판</h2>
					<form action="board" class="col-lg-4" method="get">
					

	            <select class="custom-select" id="filterSelect" name="board_cd">
	                <option value="NOTICE"  ${board_cd eq 'NOTICE' ? 'selected' : ''}>공지사항</option>
	                <option value="FREE"  ${board_cd eq 'FREE' ? 'selected' : ''}>자유게시판</option>
	                <option value="WORRY"  ${board_cd eq 'WORRY' ? 'selected' : ''}>고민상담소</option>
	                <option value="PLAY"  ${board_cd eq 'PLAY' ? 'selected' : ''}>짝궁놀이터</option>
	            </select>
	            
	            					</form>
            </div>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
					<th scope="col">&nbsp;</th>
				</tr>
                </thead>
                <tbody>
				<c:forEach items="${list}" var="vo">
				<tr>
					<th scope="row"><input type="checkbox" /></th>
					<td>${vo.id}</td>
					<td class="tm-board-show">${vo.title}</td>
					<td>${vo.writer}</td>
					<td>${vo.writedate}</td>
					<td>${vo.readcnt}</td>
					<td><a href="detailboard?id=${vo.id }" class="tm-product-delete-link"> <i class="fa-solid fa-wrench"></i>
					</a></td>
				</tr>
				</c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a href="insertboard?board_cd=${board_cd}" class="btn btn-primary btn-block mb-3">신규등록</a>
			<button class="btn btn-primary btn-block col-lg-4" id="deleteboard">선택 삭제</button>
          </div>
        </div>
        
      </div>
    </div>
<script>
    $(document).ready(function() {
    	var selectedValue;
        $("#deleteboard").click(function() {
    	 

            var selectedIds = [];
            $(".tm-product-table tbody input[type='checkbox']:checked").each(function() {
                selectedIds.push("'"+$(this).closest("tr").find(".tm-product-name").text() + "'");
                
            });
           //alert('???'+ selectedIds.length + '[' +selectedIds.join(",")+']')     
            console.log('1', selectedIds)
            if (selectedIds.length > 0) {
                if (confirm("선택한 항목을 삭제하시겠습니까?")) {
                    var selectedIdsStr = selectedIds.join(",");
                    //window.location.href = "deleteboard?ids=" + selectedIdsStr;
                    $.ajax({
                    	url: "deleteboard",
                    	data:{
                    		ids:selectedIdsStr, board_cd: "${board_cd}"
                    	},
                    	success: function(response){
                    		$('.tm-product-table-container').html(response)
                    	}		
                    })
                }
            } else {
                alert("삭제할 항목을 선택하세요.");
            }
        });
   	  $("#filterSelect").change(function(){
   		  $('form').submit();
   	/* 	  selectedValue= $(this).val(); // 선택한 값을 변수에 저장
   		  console.log($(this).val())
       		$.ajax({
    		   url:'changeboardlist', 
    		   data: {board_cd:selectedValue}
			   	        		
       		//$(this).val()
    	   }).done(function(response){
    		   $('.tm-product-table-container').html(response)
    	     })  */
   	  }) 
        
    });
   /*  $(function() {
        $(".tm-board-show").on("click", function() {
          window.location.href = "detailboard?id=${vo.id }";
        });
      }); */
    
</script>
</body>
</html>