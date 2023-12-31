<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

  <body id="reportsPage">
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">회원정보 수정</h2>
              <form action="updatemember" class="tm-signup-form row">
                <div class="form-group col-lg-6">
                  <label for="id">ID</label>
                  
                  <input
                    id="id"
                    name="id"
                    type="text"
                    placeholder="${vo.id}"
                    value="${vo.id}"
                    class="form-control validate"
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
                    class="form-control validate"
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
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="gender">GENDER</label>
                  	<div class="form-check form-check-inline">
    					<label class="form-check-label">
        				<input class="form-check-input" type="radio" name="gender" value="남" ${vo.gender == '남' ? 'checked' : ''}>남
    					</label>
				 	</div>
					<div class="form-check form-check-inline">
    					<label class="form-check-label">
        				<input class="form-check-input" type="radio" name="gender" value="여" ${vo.gender == '여' ? 'checked' : ''}>여
    					</label>
					</div>
                </div>
                 <div class="form-group col-lg-6">
                  <label for="birth">BIRTH</label>
                  <input
        				id="birth"
        				name="birth"
				        type="text" 
				        value="${vo.birth}"
				        class="form-control validate" 
				        placeholder="${vo.birth}" 
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
                    변경저장
                  </button>
                </div>
                <div class="col-12">
                  <button
                    type="submit"
                    class="btn btn-primary btn-block text-uppercase"
                  >
                    Delete Your Account
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
     
  </body>
</html>
