<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

  <body id="reportsPage">
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">회원등록</h2>
              <form action="insertmember" method="post" class="tm-signup-form row">
                <div class="form-group col-lg-6">
                  <label for="id">ID</label>
                  
                  <input
                    id="id"
                    name="id"
                    type="text"
                    placeholder="${vo.id}"
                    value="${vo.id}"
                    class="form-control validate" minlength="5" maxlength="16"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="password">PW</label>
                  <input
                    id="pw"
                    name="pw"
                    type="password"
                    placeholder="${vo.pw}"
                    value="${vo.pw}"
                    class="form-control validate" minlength="8" maxlength="17"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="name">NAME</label>
                  <input
                    id="name"
                    name="name"
                    type="text"
                    placeholder="${vo.name}"
                    value="${vo.name}"
                    class="form-control validate" minlength="2" maxlength="17"
                  />
                </div>
                <div class="form-group col-lg-3">
                  <label for="admin">ADMIN</label>
                  <div>
                 <div class="form-check form-check-inline">
    					<label class="form-check-label">
        				<input class="form-check-input" checked type="radio" name="admin" value="Y" >Y
    					</label>
				 	</div>
					
					</div>
                </div>
                <div class="form-group col-lg-3">
                  <label for="gender">GENDER</label>
                  <div>
                 <div class="form-check form-check-inline">
    					<label class="form-check-label">
        				<input class="form-check-input" type="radio" name="gender" value="남" >남
    					</label>
				 	</div>
					<div class="form-check form-check-inline">
    					<label class="form-check-label">
        				<input class="form-check-input" type="radio" name="gender" value="여" >여
    					</label>
					</div>
					</div>
                </div>
                 <div class="form-group col-lg-6">
                  <label for="birth">BIRTH</label>
                  <input
                    id="birth"
                    name="birth"
                    type="text"
                    placeholder="${vo.birth}"
                    value="${vo.birth}"
                    style="background-color: #54657d;"
                    class="form-control validate date"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="phone">PHONE</label>
                  <input
                    id="phone"
                    name="phone"
                    type="tel"
                    placeholder="${vo.phone}"
                    value="${vo.phone}"
                    class="form-control validate"
                  />
                </div>
                
                <div class="form-group col-lg-6">
                  <label for="email">EMAIL</label>
                  <input
                    id="email"
                    name="email"
                    type="email"
                    placeholder="${vo.email}"
                    value="${vo.email}"
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
      $(function(){
	var today = new Date();
	//년도 : 13년전 해의 12월 31일 까지는 선택 가능 
	//var endDay = new Date(today.getFullYear()-13, today.getMonth(), today.getDate()-1);
	//만 나이를 적용한다면: 13년전 해의 오늘날짜 이전까지는 선택 가능
	var endDay = new Date(today.getFullYear()-13, 11, 31);
	//$('[name=birth]').datepicker('option', 'maxDate', endDay);
	
})
$(function() {
    $("form").submit(function(event) {
        // 각 입력 필드의 값을 가져옴
        var id = $("#id").val();
        var pw = $("#pw").val();
        var name = $("#name").val();
        var birth = $("#birth").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        // 각 입력 필드의 값을 검사하여 빈 값인지 확인
        if (id === "" || pw === "" || name === "" || birth === "" || phone === "" || email === "") {
            alert("모든 입력 항목을 채워주세요.");
            event.preventDefault(); // 제출을 막음
        }
    });
});
</script>
  </body>
</html>
