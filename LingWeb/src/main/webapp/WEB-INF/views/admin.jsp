<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<p class="text-white mt-5 mb-5">
					Welcome back, <b>Admin</b>
				</p>
			</div>
		</div>

		<!-- row -->
		<div class="row tm-content-row">
			<div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
				<div class="tm-bg-primary-dark tm-block">
					<h2 class="tm-block-title">Latest Hits</h2>
					<canvas id="lineChart"></canvas>
				</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
				<div class="tm-bg-primary-dark tm-block">
					<h2 class="tm-block-title">Performance</h2>
					<canvas id="barChart"></canvas>
				</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
				<div class="tm-bg-primary-dark tm-block tm-block-taller">
					<h2 class="tm-block-title">Storage Information</h2>
					<div id="pieChartContainer">
						<canvas id="pieChart" class="chartjs-render-monitor" width="200"
							height="200"></canvas>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
					<h2 class="tm-block-title">Developer List</h2>
					<div class="tm-notification-items">
						<div class="media tm-notification-item">
							<div class="tm-gray-circle">
								<img src="img/aegigon.jpg" alt="Avatar Image"
									class="rounded-circle">
							</div>
							<div class="media-body">
								<p class="mb-2">
									<b>김기곤</b> 입니다. <br> <a href="store"
										class="tm-notification-link">스토어</a>
									담당입니다.
								</p>
								<span class="tm-small tm-text-color-secondary">조장</span>
							</div>
						</div>
						<div class="media tm-notification-item">
							<div class="tm-gray-circle">
								<img src="img/ym.jpg" alt="Avatar Image"
									class="rounded-circle">
							</div>
							<div class="media-body">
								<p class="mb-2">
									<b>YM'slave</b> 입니다. <br> <a
										href="lingmember" class="tm-notification-link">회원관리</a>, 
										<a
										href="board" class="tm-notification-link">게시판</a>
									담당입니다.
								</p>
								<span class="tm-small tm-text-color-secondary">굴러온 돌</span>
							</div>
						</div>
						<div class="media tm-notification-item">
							<div class="tm-gray-circle">
								<img src="img/suwon.jpg" alt="Avatar Image"
									class="rounded-circle">
							</div>
							<div class="media-body">
								<p class="mb-2">
									<b>정수원</b> 입니다. <br> <a href="#"
										class="tm-notification-link">어려운거</a>
										담당입니다.
								</p>
								<span class="tm-small tm-text-color-secondary">조원</span>
							</div>
						</div>
						<div class="media tm-notification-item">
							<div class="tm-gray-circle">
								<img src="img/hm.jpg" alt="Avatar Image"
									class="rounded-circle">
							</div>
							<div class="media-body">
								<p class="mb-2">
									<b>김혜민</b> 입니다. <br> <a href="travel"
										class="tm-notification-link">데이트</a>담당입니다.
								</p>
								<span class="tm-small tm-text-color-secondary">홍일점</span>
							</div>
						</div>
					
					</div>
				</div>
			</div>
			<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">Orders List</h2>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">ORDER NO.</th>
								<th scope="col">STATUS</th>
								<th scope="col">OPERATORS</th>
								<th scope="col">LOCATION</th>
								<th scope="col">DISTANCE</th>
								<th scope="col">START DATE</th>
								<th scope="col">EST DELIVERY DUE</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><b>#122349</b></th>
								<td>
									<div class="tm-status-circle moving"></div>Moving
								</td>
								<td><b>Oliver Trag</b></td>
								<td><b>London, UK</b></td>
								<td><b>485 km</b></td>
								<td>16:00, 12 NOV 2018</td>
								<td>08:00, 18 NOV 2018</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- https://jquery.com/download/ -->
<!-- 	<script src="js/bootstrap.min.js"></script> -->
	<!-- https://getbootstrap.com/ -->
	<script>
      $(function() {
        $(".tm-product-name").on("click", function() {
          window.location.href = "edit-product.html";
        });
      });
    
        Chart.defaults.global.defaultFontColor = 'white';
        let ctxLine,
            ctxBar,
            ctxPie,
            optionsLine,
            optionsBar,
            optionsPie,
            configLine,
            configBar,
            configPie,
            lineChart;
        barChart, pieChart;
        // DOM is ready
        $(function () {
            drawLineChart(); // Line Chart
            drawBarChart(); // Bar Chart
            drawPieChart(); // Pie Chart

            $(window).resize(function () {
                         
            });
        })
    </script>
		
</body>
</html>