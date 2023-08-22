<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  
<body id="reportsPage">


<div class="col-sm-12 col-md-12 col-xl-8 col-lg-10 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-products">
				<h2 class="tm-block-title col-lg-4">회원 리스트</h2>
				<select class="custom-select col-lg-4">
                <option value="0">Select account</option>
                <option value="1">Admin</option>
                <option value="2">Editor</option>
                <option value="3">Merchant</option>
                <option value="4">Customer</option>
              </select>
            
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
									<td><a href="#" class="tm-product-delete-link"> <i
											class="far fa-trash-alt tm-product-delete-icon"></i>
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

</body>
</html>