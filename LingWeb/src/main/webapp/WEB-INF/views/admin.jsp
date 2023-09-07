<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.image-container {
    text-align: center; /* 이미지를 수평 중앙에 정렬합니다. */
}

.image-container img {
    max-width: 100%; /* 이미지를 부모 컨테이너에 맞게 조절합니다. */
    height: auto; /* 이미지의 가로 세로 비율을 유지합니다. */
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<p class="text-white mt-5 mb-5">
					<b>영진문경훈조</b> <br>이상으로 발표를 마치겠습니다.</> 
				</p>
			</div>
		</div>

		<!-- row -->
		<div class="row tm-content-row">
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
						
<!-- 			<div class="col-12 tm-block-col"> -->
<!-- 				<div class="tm-bg-primary-dark tm-block tm-block-taller"> -->
<!-- 					<img src="img/022.png"/>				 -->
<!-- 				</div> -->
<!-- 			</div> -->
<div class="col-12 tm-block-col">
    <div class="tm-bg-primary-dark ">
        <div class="image-container">
            <img src="img/022.png" alt="이미지 설명">
        </div>
    </div>
</div>
		</div>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- https://jquery.com/download/ -->
<!-- 	<script src="js/bootstrap.min.js"></script> -->
	<!-- https://getbootstrap.com/ -->
	<script>
//       $(function() {
//         $(".tm-product-name").on("click", function() {
//           window.location.href = "edit-product.html";
//         });
//       });
    
//         Chart.defaults.global.defaultFontColor = 'white';
//         let ctxLine,
//             ctxBar,
//             ctxPie,
//             optionsLine,
//             optionsBar,
//             optionsPie,
//             configLine,
//             configBar,
//             configPie,
//             lineChart;
//         barChart, pieChart;
//         // DOM is ready
//         $(function () {
//             drawLineChart(); // Line Chart
//             drawBarChart(); // Bar Chart
//             drawPieChart(); // Pie Chart

//             $(window).resize(function () {
                         
//             });
//         })
Chart.defaults.font.size = 16;
      //const defaultLegendClickHandler = Chart.defaults.plugins.legend.onClick;
      Chart.defaults.set('plugins.legend', {
      	position: 'bottom',
      })


      Chart.register(ChartDataLabels); // Register the plugin to all charts:
      Chart.register(window['chartjs-plugin-autocolors']); // All charts autoColors


      //데이터라벨 default 적용 지정
      Chart.defaults.set('plugins.datalabels', {
      	anchor: 'end', //데이터위치
      	align: 'start',//앵커기준으로 한 위치
      	offset: -20,//얼마나 떨어져있게 할 것인지
      	color: '#000', //폰트 색상
      	font: {weight: 'bold'}, //폰트 굵게
      })
        

        function initCanvas(){
			$('#legend').remove();
			$('canvas#chart').remove();
			$('#tab-content').append(`<canvas id="chart" class="h-100 m-auto"></canvas>`);
		}
        
      	$(function(){
      		$('ul.nav-tabs li').eq(2).trigger('click')
      	})
        //연령별 인원 수 조회
        function age(){
        	initCanvas();
        	
        	$.ajax({
        		url: 'age',
        	}).done(function(response){
        		console.log(response)
        		var info = {};
        		info.category = [], info.datas = [];
        		$(response).each(function(){
        			info.category.push(this.AGE_TEXT);
        			info.datas.push(this.COUNT);
        		})
        		console.log('data',info);
       			donutChart(info);
        	})
        }
      	
      	function period(){
      		initCanvas();
      		$.ajax({
      			url: 'period',
      		}).done(function(response){
      			var info = {};
      			info.category = [], info.datas = [];
      			$(response).each(function(){
      				info.category.push(this.PERIOD_TEXT);
      				info.datas.push(this.COUNT);
      				
      			})
      			lineChart(info);
      		})
      		
      	}
      	
      	function item(){
        	initCanvas();
        	
        	$.ajax({
        		url: 'item_rank',
        	}).done(function(response){
        		console.log(response)
        		var info = {};
        		info.category = [], info.datas = [], info.itemNames = [], info.colors = [];
        		$(response).each(function(idx){
        			info.category.push(this.RANK);
        			info.datas.push(this.SALES);
        			info.itemNames.push(this.ITEM_NAME); 
        			info.colors.push(colors[idx]);
        		})
        		console.log('data',info);
       			barChart(info);
        	})
        }
      	
      	
        
        
        
        var colors = ['#85a832', '#a83294', '#c7c922', '#db4ba6', '#d4aca7', '#8be0c0', '#5340cf', '#021438','#d4cc83','#c1f0b4'];
      
      //막대 그래프
function barChart(info) {
    	  console.log('barChart> ', info.itemNames)
    $('#tab-content').css('height', '700');
    visual = new Chart($('#chart'), {
        type: 'bar',
        data: {
            labels: info.category,
            datasets: [{
                data: info.datas,
                borderWidth: 2,
                barPercentage: 0.5,
                backgroundColor: info.colors,
            }]
        },
        options: {
            maintainAspectRatio: false,
            responsive: false,
            layout: {
                padding: {top:30, bottom:20}
            },
            plugins: {
                legend: {
                    display: false
                },
                autocolors: {
                    mode: 'data'
                },
                datalabels:{
                	display:false
                },
                
                
            },
            
            scales: {
                x: { // x축 설정
                    beginAtZero: true,
                    title: {text: '순위', display:true},
                    position: 'bottom' // x축 위치 설정
                },
                y: { // y축 설정
                    beginAtZero: true,
                    title: {text: '아이템 구매횟수', display:true}
                }
            }
        }
    });

//     makeLegend(info);
}

        //데이터수치 범위에 해당하는 범례 만들기
/* function makeLegend() {
    var tag =
        `<ul class="row d-flex justify-content-center m-0 mt-4 p-0 small" id='legend'>`;
        
    for (var no = 0; no <= 6; no++) {
        tag +=
            `<li class="col-auto"><span></span><font>${no * 10}~${no * 10 + 9}명</font></li>`;
    }
    tag +=
        `<li class="col-auto"><span></span><font>${no * 10}명 이상</font></li>
        </ul>`;
    
    // 범례를 그래프 아래에 위치시키기 위해 그래프 아래에 추가하도록 변경
    $('#chart').after(tag);
    $('#legend span').each(function(idx, item) {
        $(this).css('background-color', colors[idx]);
    });
} */

function makeLegend(info) {
    var tag = `<ul class="nav legend-list row d-flex justify-content-center m-0 p-0 ">`;
        
    for (var i = 0; i < info.itemNames.length; i++) {
//         tag += `<li><span style="background-color: ${info.colors[i]}"></span>${info.itemNames[i]}</li>`;
        tag += `<li class="col-auto"><span style="background-color: \${info.colors[i]}"></span>\${info.itemNames[i]}</li>`;
    }
    tag += `</ul>`;
	console.log('2> ', tag)
    
    $('#legend-container').html(tag);
}

        

        
        
        const carousel = new bootstrap.Carousel(document.getElementById('carouselExample'), {
            interval: 5000, // 이미지 전환 간격 (밀리초)
            pause: true,   // 마우스 오버시 자동 재생 일시 중단 여부
          });
    </script>
		
</body>
</html>