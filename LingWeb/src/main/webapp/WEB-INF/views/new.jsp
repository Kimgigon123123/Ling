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
                <h2 class="tm-block-title d-inline-block">등록하기</h2>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-6 col-lg-6 col-md-12">
                <form action="register" method="post" autocomplete="off" class="tm-edit-product-form">
                <div class="form-group mb-3">
                    <label
                      for="category"
                      >카테고리</label
                    >
                    <select
                      class="custom-select tm-select-accounts"
                      id="category"
                    >
                      <option selected>카테고리 선택</option>
                      <option value="1">여행</option>
                      <option value="2">맛집</option>
                      <option value="3">축제</option>
                    </select>
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="name"
                      >이름
                    </label>
                    <input
                      id="name"
                      name="name"
                      type="text"
                      class="form-control validate"
                      required
                    />
                    </div>
                    <div class="form-group mb-3">
                    <label
                      for="address"
                      >주소
                    </label>
                    <input
                      id="address"
                      name="address"
                      type="address"
                      class="form-control validate"
                    />
                  </div>
                    <div class="form-group mb-3">
                    <label
                      for="tel"
                      >전화번호
                    </label>
                    <input
                      id="tel"
                      name="tel"
                      type="tel"
                      class="form-control validate"
                    />
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="open"
                      >OPEN
                    </label>
                    <input
                      id="open"
                      name="open"
                      type="text"
                      class="form-control validate"
                    />
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="end"
                      >END
                    </label>
                    <input
                      id="END"
                      name="END"
                      type="text"
                      class="form-control validate"
                    />
                  </div>
                  <div class="form-group mb-3">
                    <label
                      for="description"
                      >상세정보</label
                    >
                    <textarea
                      class="form-control validate"
                      rows="3"
                    ></textarea>
                  </div>
                  <!-- <div class="row">
                      <div class="form-group mb-3 col-xs-12 col-sm-6">
                          <label
                            for="expire_date"
                            >Expire Date
                          </label>
                          <input
                            id="expire_date"
                            name="expire_date"
                            type="text"
                            class="form-control validate"
                            data-large-mode="true"
                          />
                        </div>
                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                          <label
                            for="stock"
                            >Units In Stock
                          </label>
                          <input
                            id="stock"
                            name="stock"
                            type="text"
                            class="form-control validate"
                            required
                          />
                        </div>
                  </div> -->
                  
              </div>
              <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                <div class="tm-product-img-dummy mx-auto">
                  <i
                    class="fas fa-cloud-upload-alt tm-upload-icon"
                    onclick="document.getElementById('fileInput').click();"
                  ></i>
                </div>
                <div class="custom-file mt-3 mb-3">
                  <input id="fileInput" type="file" style="display:none;" />
                  <input
                    type="button"
                    class="btn btn-primary btn-block mx-auto"
                    value="이미지 업로드"
                    onclick="document.getElementById('fileInput').click();"
                  />
                </div>
              </div>
              <div class="col-12">
                <button id="btn-save" type="submit" class="btn btn-primary btn-block text-uppercase">완료</button>
              </div>
            </form>
            </div>
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
      /* $(function() {
        $("#expire_date").datepicker();
      }); */
      $('#btn-save').click(function() {
			$('form').submit()
		}
	})
    </script>
</body>
</html>