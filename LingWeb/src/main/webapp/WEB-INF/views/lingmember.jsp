<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
        /* 체크박스 비활성화 스타일 */
        input[type="checkbox"]:disabled {
            background-color: #888888; /* 배경색 */
            
            
        }
        a {
  color: white; /* 기본 색상을 흰색으로 설정 */
  text-decoration: none; /* 밑줄 제거 */
  transition: color 0.3s; /* 색상 변화에 부드러운 트랜지션 추가 */
}

/* 링크를 마우스로 호버할 때의 색상 설정 */
a:hover {
  color: your-desired-hover-color; /* 호버 시 색상 변경 */
}
    </style>
</head>
  
<body id="reportsPage">

<div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-12 col-12 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
          <div class="row mx-0 justify-content-between align-items-center mb-3">
	          <h2 class="tm-block-title col-lg-4 px-0">회원 리스트</h2>
					
	            <select class="custom-select col-lg-4" id="filterSelect">
	                <option value="0">LING_MEMBER</option>
	                <option value="1">COUPLE</option>
	                <option value="2">ADMIN</option>
	            </select>
            </div>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col"><a class="sort" data-idsort='0' data-order='id'>ID</a></th>
					<th scope="col">PW</th>
					<th scope="col"><a class="sort" data-namesort='0' data-order='name'>NAME</a></th>
					<th scope="col">GENDER</th>
					<th scope="col">EMAIL</th>
					<th scope="col">PHONE</th>
					<th scope="col">BIRTH</th>
					<th scope="col">&nbsp;</th>
				</tr>
                </thead>
                <tbody>
				<c:forEach items="${list}" var="vo">
				<tr>
					<th scope="row"><input type="checkbox" ${vo.couple_num != null ? 'disabled' : ''} /></th>
					<td class="tm-product-name">${vo.id }</td>
					<td>${vo.pw }</td>
					<td>${vo.name }</td>
					<td>${vo.gender }</td>
					<td>${vo.email }</td>
					<td>${vo.phone }</td>
					<td>${vo.birth }</td>
					<td><a href="detailmember?id=${vo.id}" class="tm-product-delete-link"> <i class="fa-solid fa-wrench"></i>
					</a></td>
				</tr>
				</c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a href="insertmember" class="btn btn-primary btn-block mb-3">신규등록</a>
			<button class="btn btn-primary btn-block col-lg-4" id="delete">선택 삭제</button>
          </div>
        </div>
        
      </div>
    </div>
<script>
    $(document).ready(function() {
        $("#delete").click(function() {
    	 

            var selectedIds = [];
            $(".tm-product-table tbody input[type='checkbox']:checked").each(function() {
                selectedIds.push("'"+$(this).closest("tr").find(".tm-product-name").text() + "'");
                
            });
           //alert('???'+ selectedIds.length + '[' +selectedIds.join(",")+']')     
            console.log('1', selectedIds)
            if (selectedIds.length > 0) {
                if (confirm("선택한 항목을 삭제하시겠습니까?")) {
                    var selectedIdsStr = selectedIds.join(",");
                    window.location.href = "delete?ids=" + selectedIdsStr;
                }
            } else {
                alert("삭제할 항목을 선택하세요.");
            }
        });
   	  $("#filterSelect").change(function(){
   		  console.log($(this).val())
       		$.ajax({
    		   url:'changelist', 
    		   data: {tablename:$(this).val()}
    	   }).done(function(response){
    		   $('.tm-product-table-container').html(response)
    	     }) 
   	  }) 
        
   	  // 첫 페이지 로딩 시
    checkAdminFilter();
   
    // ADMIN 선택 시 신규등록 버튼 보이기/숨기기
    $("#filterSelect").change(function() {
        checkAdminFilter();
        
    });

    function checkAdminFilter() {
        var selectedValue = $("#filterSelect").val();
        if (selectedValue === "2") { // ADMIN
            $(".btn-block.mb-3").show(); // 신규등록 버튼 보이기
        } else {
            $(".btn-block.mb-3").hide(); // 신규등록 버튼 숨기기
        }
        
    }
    });
    $(document).on('click','a.sort',function(){

    
    	var sort = $(this).data($(this).data('order')+'sort')==0?1:0;
    	$(this).data($(this).data('order')+'sort',sort )
    	list_id_by($(this).data('order'), sort)
    })
    function list_id_by(order, sort){
//     	location='list_id_by?filter='+$('#filterSelect').val()+ '&order='+ order
		$.ajax({
			url:'list_id_by',
			data:{filter:$('#filterSelect').val(), order:order, sort:sort}
		}).done(function(response){
			$('.tm-product-table-container').html(response)
		})
	}
    
    
</script>
</body>
</html>