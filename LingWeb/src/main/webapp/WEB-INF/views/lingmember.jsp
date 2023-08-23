<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  
<body id="reportsPage">

<div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-12 col-12 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
          <div class="row mx-0 justify-content-between align-items-center mb-3">
	          <h2 class="tm-block-title col-lg-4 px-0">회원 리스트</h2>
					
	            <select class="custom-select col-lg-4">
	                <option value="0">LING_MEMBER</option>
	                <option value="1">COUPLE</option>
	            </select>
            </div>
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">ID</th>
					<th scope="col">PW</th>
					<th scope="col">NAME</th>
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
					<th scope="row"><input type="checkbox" /></th>
					<td class="tm-product-name">${vo.id }</td>
					<td>${vo.pw }</td>
					<td>${vo.name }</td>
					<td>${vo.gender }</td>
					<td>${vo.email }</td>
					<td>${vo.phone }</td>
					<td>${vo.birth }</td>
					<td><a href="#" class="tm-product-delete-link"> <i class="fa-solid fa-wrench"></i>
					</a></td>
				</tr>
				</c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a href="detailmember" class="btn btn-primary btn-block mb-3">신규등록</a>
			<button class="btn btn-primary btn-block col-lg-4">선택 삭제</button>
          </div>
        </div>
        
      </div>
    </div>

</body>
</html>