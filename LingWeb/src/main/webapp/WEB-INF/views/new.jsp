<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Add Product - Dashboard HTML Template</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
<!-- https://fonts.google.com/specimen/Roboto -->
<link rel="stylesheet" href="css/fontawesome.min.css" />
<!-- https://fontawesome.com/ -->
<!-- http://api.jqueryui.com/datepicker/ -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet" href="css/templatemo-style.css">
<!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
</head>
<body>
	<div class="container tm-mt-big tm-mb-big">
		<div class="row">
			<div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="row">
						<div class="col-12">
							<h2 class="tm-block-title d-inline-block">등록하기</h2>
						</div>
					</div>
					<form action="register" method="post" enctype="multipart/form-data" autocomplete="off"
						class="tm-edit-product-form" onsubmit="return validateForm();">
						<div class="row tm-edit-product-row">
							<div class="col-xl-6 col-lg-6 col-md-12">
								<div class="form-group mb-3">
									<label for="category">카테고리</label> <select
										class="custom-select tm-select-accounts" id="category" name="date_category_code" required>
										<option value="" selected>카테고리 선택</option>
										<option value="TO">여행</option>
										<option value="RE">맛집</option>
										<option value="FE">축제</option>
									</select>
								</div>
								<div class="form-group mb-3">
									<label for="name">이름 </label> <input
										id="name" name="date_name" type="text"
										class="form-control validate" required />
								</div>
								<div class="form-group mb-3">
									<label for="address">주소 </label> <input
										id="address" name="date_address" type="text"
										class="form-control validate" />
								</div>
								<div class="form-group mb-3">
									<label for="tel">전화번호 </label> <input
										id="tel" name="tel" type="text" class="form-control validate" />
								</div>
								<div class="form-group mb-3">
									<label for="open">OPEN </label> <input
										id="open" name="open" type="text"
										class="form-control validate" />
								</div>
								<div class="form-group mb-3">
									<label for="end">END </label> <input
										id="end" name="end" type="text" class="form-control validate" />
								</div>
								<div class="form-group mb-3">
									<label for="description">상세정보</label>
									<textarea id="description" name="date_intro" class="form-control validate" rows="3"></textarea>
								</div>
							</div>
							<div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
								<div class="tm-product-img-dummy mx-auto">
			                 		<img class="tm-product-img-dummy mx-auto" id="preview" />
                				</div>
								<div class="custom-file mt-3 mb-3">
									<input id="fileInput" name="file" type="file" style="display: none;" onchange="readURL(this)"/>
									<input
										type="button" class="btn btn-primary btn-block mx-auto"
										value="이미지 업로드"
										onclick="document.getElementById('fileInput').click();" />
								</div>
							</div>
							<div class="col-12">
							<div class="btn-toolbar gap-2 my-3 justify-content-center">
								<button type="submit" class="btn btn-primary">저장</button>
								<button type="button" class="btn btn-danger" onclick="history.go(-1)">취소</button>
							</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- https://jquery.com/download/ -->
	<!-- https://jqueryui.com/download/ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
	<script>
    function validateForm() {
        // 선택된 이미지 파일 가져오기
        var fileInput = document.getElementById('fileInput');
        
        // 이미지가 선택되었는지 확인
        if (fileInput.files.length === 0) {
            alert('이미지를 업로드해주세요.'); // 경고 표시
            return false; // 양식 제출 방지
        }
        return true;
    }
		function readURL(input) {
			if (input.files && input.files[0]) {
    	    var reader = new FileReader();
    	    reader.onload = function(e) {
    	      document.getElementById('preview').src = e.target.result;
    	    };
    	    reader.readAsDataURL(input.files[0]);
    	  } else {
    	    document.getElementById('preview').src = "";
    	  }
		}
	</script>
</body>
</html>