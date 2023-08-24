<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <body id="reportsPage">
      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <div class="row">
					<div class="col-12">
						<h2 class="tm-block-title d-inline-block">정보수정</h2>
					</div>
				</div>
              <form action="update" method="post" class="tm-signup-form row">
                <div class="form-group col-lg-6">
                  <label for="name">이름</label>
                  <input
                    id="name"
                    name="name"
                    type="text"
                    placeholder="${vo.date_name}"
                    value="${vo.date_name}"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="address">주소</label>
                  <input
                    id="address"
                    name="address"
                    type="text"
                    placeholder="${vo.date_address}"
                    value="${vo.date_address}"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="tel">전화번호</label>
                  <input
                    id="tel"
                    name="tel"
                    type="text"
                    placeholder="${vo.tel}"
                    value="${vo.tel}"
                    class="form-control validate"
                  />
                </div>
                <%-- <div class="form-group col-lg-6">
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
                </div> --%>
                 <div class="form-group col-lg-6">
                  <label for="open">OPEN</label>
                  <input
        				id="open"
        				name="open"
				        type="text" 
				        value="${vo.open}"
				        class="form-control validate" 
				        placeholder="${vo.open}" 
				    />
                </div>
                <div class="form-group col-lg-6">
                  <label for="end">END</label>
                  <input
                    id="end"
                    name="end"
                    type="text"
                    placeholder="${vo.end}"
                    value="${vo.end}"
                    class="form-control validate"
                  />
                </div>
                <div class="form-group col-lg-6">
                  <label for="description">상세정보</label>
                  <textarea rows="3" class="form-control validate" placeholder="${vo.date_intro }">
                  </textarea>
                  <%-- <input
                    id="description"
                    name="description"
                    type="text"
                    placeholder="${vo.date_intro}"
                    value="${vo.date_intro}"
                    class="form-control validate"
                  /> --%>
                </div>
                <div class="form-group col-lg-6">
                  <label class="tm-hide-sm">&nbsp;</label>
	              <div class="col-12">
	                <button type="submit" class="btn btn-primary btn-block text-uppercase">완료</button>
	              </div>
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
