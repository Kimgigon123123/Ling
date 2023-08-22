<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Privacy</title>
<link href="css/login.css" rel="stylesheet" />
</head>
<body>
<div class="my-0 vh-100 w-100 d-flex align-items-center" id="mainBgn">
  <div class="formContainer">
    <div class="text-center mb-1 pb-3">
      <img src="img/linglogo.png" alt="Company Logo" height="48">
    </div>
    <form action="adminlogin" method="post">
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
</body>
</html>