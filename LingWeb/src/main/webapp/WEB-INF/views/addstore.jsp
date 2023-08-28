<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Add Product - Dashboard HTML Template</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Roboto:400,700"
    />
    <!-- https://fonts.google.com/specimen/Roboto -->
    <link rel="stylesheet" href="css/fontawesome.min.css" />
    <!-- https://fontawesome.com/ -->
    <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
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
                <h2 class="tm-block-title d-inline-block">상품등록</h2>
              </div>
            </div>
            
            <form method ="post" action="store_insert" class="tm-edit-product-form"enctype="multipart/form-data">
            <div class="row tm-edit-product-row">
              <div class="col-xl-6 col-lg-6 col-md-12">
                <form action="store_insert" class="tm-edit-product-form" enctype="multipart/form-data" method="post">
                  <div class="form-group mb-3">
                    <label
                      for="name"
                      >상품이름
                    </label>
                    <input
                      id="item_name"
                      name="item_name"
                      type="text"
                      class="form-control validate"
                      required
                    />
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="description"
                      >상품설명</label
                    >
                    <textarea
                      id="item_content"
                      name="item_content"
                      class="form-control validate"
                      rows="3"
                      required
                    ></textarea>
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="category"
                      >카테고리</label
                    >
                    <select
                      class="custom-select tm-select-accounts"
                      id="category_code"
                      name="category_code"
                    >
                      <option selected>카테고리선택</option>
                      <option value="Dr">커플옷</option>
                      <option value="Ri">커플반지</option>
                      <option value="Gi">선물</option>
                      <option value="Etc">기타</option>
                    </select>
                  </div>
                  <div class="row">
                      <div class="form-group mb-3 col-xs-12 col-sm-6">
                          <label
                            for="expire_date"
                            >가격
                          </label>
                          <input
                            id="item_price"
                            name="item_price"
                            type="number"
                            class="form-control validate"
                            data-large-mode="true"
                          />
                        </div>
                        
                  </div>
                  
              </div>
              <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                <div class="tm-product-img-dummy mx-auto">
                 <img class="tm-product-img-dummy mx-auto" id="preview" />
                </div>
                <div class="custom-file mt-3 mb-3">
                  <input id="fileInput" name="file" type="file" style="display:none" onchange="readURL(this)"; />
                  <input
                    type="button"
                    class="btn btn-primary btn-block mx-auto"
                    value="상품 이미지"
                    onclick="document.getElementById('fileInput').click();"
                  />
                </div>
              </div>
              
              
              
              <div class="col-12">
              <div class="d-flex flex-row justify-content-center">
                <button type="submit" class="btn btn-primary btn-block text-uppercase mr-3" >상품 등록하기</button>
                <button
										class="btn btn-success mr-3 text-uppercase" onclick="history.go(-1)">취소</button>
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
    <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
    <!-- https://jqueryui.com/download/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script>
      $(function() {
        $("#expire_date").datepicker();
      });
      
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