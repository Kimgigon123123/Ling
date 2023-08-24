<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Privacy</title>
<link href="css/login.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="my-0 vh-100 w-100 d-flex align-items-center" id="mainBgn">
  <div class="formContainer">
    <div class="text-center mb-1 pb-3">
      <img src="img/linglogo.png" alt="Company Logo" height="48">
    </div>
    <form id="loginForm" action="default/login" method="post">
      <div>
        <span class="inputLogo"><i class="fas fa-lock"></i></span><input type="text" class="form-control rounded-pill" name="id" placeholder="AdminId">
      </div>
      <div class="my-2">
        <span class="inputLogo"><i class="fas fa-key"></i></span><input type="password" class="form-control rounded-pill" name="pw" placeholder="password">
      </div>
      <button class="btn btn-accent rounded-pill w-100" type="submit">Login</button>
    </form>
  </div>
</div>

<script>
$(document).ready(function() {
  $("#loginForm").submit(function(event) {
    event.preventDefault(); // 기본 제출 동작 방지

    var formData = $(this).serialize(); // 폼 데이터를 직렬화

    $.ajax({
        type: "POST",
        url: $(this).attr("action"),
        data: formData,
        success: function(response) {
          // 서버 응답을 처리하는 코드 작성
          // 예: 로그인 성공 시 페이지 리다이렉트
          if (response === "success") {
            window.location.href = "admin";
          } else {
            alert("로그인 실패! 아이디와 비밀번호를 확인하세요.");
          }
        },
        error: function() {
          alert("오류가 발생했습니다.");
        }
      });
    });
  });
  </script>

</body>
</html>