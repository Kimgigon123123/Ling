<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<!DOCTYPE html>
<html lang="ko">


<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Ling</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Google fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />


<style>
a {
	color: inherit
}

a:not(.btn):link, a:not(.btn):hover {
	text-decoration: none;
	color: inherit;
}

.nav-link {
	color: #000000;
}

.nav-link:hover {
	cursor: pointer;
}

#chart {
	margin-bottom: 20px;
	margin-bottom: 20px;
	background-color: ;
}

#chart_back {
	background-image: url('<c:url value="/images/back.jpg"/>');
}

.custom-link {
	color: #DCDCDC; /* 원하는 색상으로 변경 */
	/* 다른 스타일 속성 추가 */
}


/* .bg-light { */
/* 	background-image: url('<c:url value="/resources/images/faq_back.jpg"/>'); */
/* 	background-size: cover; /* 화면에 꽉 차게 배경 이미지 조정 */ */
/* 	background-repeat: no-repeat; /* 이미지 반복 없음 */ */
/* 	background-position: center center; /* 이미지 중앙 정렬 */ */
/* } */

#legend-container .legend-list li span {
	width: 44px !important;
	height: 17px  !important;
	margin-right: 5px;
}

#legend-container .legend-list li {
	display: flex;
	align-items: center;
}
</style>
</head>



<!-- Mashead header-->
<header class="masthead">
	<div class="container px-5">
		<div class="row gx-5 align-items-center">
			<div class="col-lg-6">
				<!-- Mashead text and app badges-->
				<div class="mb-5 mb-lg-0 text-center text-lg-start">
					<h1 class="display-1 lh-1 mb-3">
						Ama et fac quod vis!<br> Amor aeternus, LING!
					</h1>
					<p class="lead fw-normal text-muted mb-5">
						The most stingy words before meeting you, <br> the words I
						lived with after meeting you...
					</p>
					<div class="d-flex flex-column flex-lg-row align-items-center">
						<a class="me-lg-3 mb-4 mb-lg-0" href="#!"><img
							class="app-badge" src="img/google-play-badge.svg" alt="..." /></a> <a
							href="#!"><img class="app-badge"
							src="img/app-store-badge.svg" alt="..." /></a>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<!-- Masthead device mockup feature-->
				<div class="masthead-device-mockup">
					<svg class="circle" viewBox="0 0 100 100"
						xmlns="http://www.w3.org/2000/svg">
                                <defs>
                                    <linearGradient id="circleGradient"
							gradientTransform="rotate(45)">
                                        <stop
							class="gradient-start-color" offset="0%"></stop>
                                        <stop class="gradient-end-color"
							offset="100%"></stop>
                                    </linearGradient>
                                </defs>
                                <circle cx="50" cy="50" r="50"></circle></svg
                            ><svg class="shape-1 d-none d-sm-block" viewBox="0 0 240.83 240.83" xmlns="http://www.w3.org/2000/svg">
                                <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(120.42 -49.88) rotate(45)"></rect>
                                <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(-49.88 120.42) rotate(-45)"></rect></svg
                            ><svg class="shape-2 d-none d-sm-block" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg"><circle cx="50" cy="50" r="50"></circle></svg>
                            <div class="container">
    <div class="container" data-device="iPhoneX" data-orientation="portrait" data-color="black">
            <div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="<c:url value='/resources/images/couple.jpg'/>" class="d-block" width="600px" height="600px" alt="Image 1">
                    </div>
<!--                     <div class="carousel-item"> -->
<%--                         <img src="<c:url value='/resources/images/ling_app_main.png'/>" class="d-block" width="600px" height="600px"  alt="Image 2"> --%>
<!--                     </div> -->
                    <div class="carousel-item">
                        <img src="<c:url value='/resources/images/ling_slogun.png'/>" class="d-block" width="600px" height="600px" alt="Image 3">
                    </div>
                </div>
        </div>
    </div>
</div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Quote/testimonial aside-->
        <aside class="text-center bg-gradient-primary-to-secondary">
            <div class="container px-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-xl-8">
                        <div class="h2 fs-1 text-white mb-auto">"내 짝궁과 늘 예쁘게 웃을 수 있는 세상은 <br> 바로 지금입니다. (LING♡)"</div>
                        <img src="img/linglogo.png" alt="..." style="height: 10rem" />
                    </div>
                </div>
            </div>
        </aside>
        
        <!-- App features section-->
        <section id="features">
            <div class="container px-5 mb-8">
                <div class="row gx-5 align-items-center">
                    <div class="col-lg-8 order-lg-1 mb-5 mb-lg-0">
                        <div class="container-fluid px-5">
                            <div class="row gx-5">
                                <div class="col-md-6 mb-5">
                                    <!-- Feature item-->zzz
                                    <div class="text-center">
                                        <img src="<c:url value='/resources/images/menu_homefrag_img1.png'/>" class="justify-content-center w-50 h-50" alt="Image 2">
                                        <h3 class="font-alt">위치추적</h3>
                                        <p class="text-muted mb-0">내 짝궁과만 나누는 비밀대화 채팅방</p>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-5">
                                    <!-- Feature item-->
                                    <div class="text-center">
                                        <img src="<c:url value='/resources/images/menu_homefrag_img3.png'/>" class="justify-content-between w-50 h-50" alt="Image 2">
                                        <h3 class="font-alt">캘린더</h3>
                                        <p class="text-muted mb-0">짝궁과의 기념일 기록과 데이트 및 일정 관리</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-5 mb-md-0">
                                    <!-- Feature item-->
                                    <div class="text-center">
                                        <img src="<c:url value='/resources/images/menu_homefrag_img2.png'/>" class="justify-content-between w-50 h-50" alt="Image 2">
                                        <h3 class="font-alt">메모장</h3>
                                        <p class="text-muted mb-0">짝궁과 나눈 사소한것 하나까지도 기록</p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <!-- Feature item-->
                                    <div class="text-center">
                                        <img src="<c:url value='/resources/images/menu_homefrag_img4.png'/>" class="justify-content-center w-50 h-50" alt="Image 2">
                                        <h3 class="font-alt">타입캡슐</h3>
                                        <p class="text-muted mb-0">과거의 우리가 미래의 우리에게 보내는 메시지</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 order-lg-0">
                        <!-- Features section device mockup-->
                        <div class="features-device-mockup">
                            <svg class="circle" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                                <defs>
                                    <linearGradient id="circleGradient" gradientTransform="rotate(45)">
                                        <stop class="gradient-start-color" offset="0%"></stop>
                                        <stop class="gradient-end-color" offset="100%"></stop>
                                    </linearGradient>
                                </defs>
                                <circle cx="50" cy="50" r="50"></circle></svg
                            ><svg class="shape-1 d-none d-sm-block" viewBox="0 0 240.83 240.83" xmlns="http://www.w3.org/2000/svg">
                                <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(120.42 -49.88) rotate(45)"></rect>
                                <rect x="-32.54" y="78.39" width="305.92" height="84.05" rx="42.03" transform="translate(-49.88 120.42) rotate(-45)"></rect></svg
                            ><svg class="shape-2 d-none d-sm-block" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg"><circle cx="50" cy="50" r="50"></circle></svg>
                            <div class="device-wrapper">
                                <div class="device" data-device="iPhoneX" data-orientation="portrait" data-color="black">
                                     <div class="screen bg-black"> 
<!--                                         PUT CONTENTS HERE: -->
<!--                                         * * This can be a video, image, or just about anything else. -->
<!--                                         * * Set the max width of your media to 100% and the height to -->
<!--                                         * * 100% like the demo example below. -->
<!--                                           	<video muted="muted" autoplay="" loop="" style="max-width: 100%; height: 100%"> -->
                                          	<video muted="muted" autoplay="" loop="" style="max-width: 200%; height: 100%; display: block; margin: 0 auto;">

											
                                          	
                                          	<source src="img/play_screen.webm" type="video/mp4" />
											</video>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Basic features section-->
        <section class="chartt bg-light mb-5" id="chartt">
        
        <div id='tab-content' class="m-md-2 m-lg-3" style='height: 300px'>
            <div class="container px-5">
            <h3 class="my-4">Ling Chart</h3>
                <ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link">연령별</a></li>
		<li class="nav-item"><a class="nav-link">연애기간별</a></li>
		<li class="nav-item"><a class="nav-link">아이템 Top10</a></li>
	</ul>
            </div>
            <canvas id="chart" class="h-100 mb-4 mt-5"></canvas>
            </div>
            <div class="mt-5" style="height:50px"></div>
            <div id='legend-container' class="mt-4"></div>
        </section>
        <!-- Call to action section-->
        <section class="cta" id="cta">
            <div class="cta-content">
                <div class="container px-5">
                    <h2 class="text-white display-1 lh-1 mb-4">
                        Couple Playground
                        <br />
                        With My Partner With LING.
                    </h2>
                    <a class="btn btn-outline-light py-3 px-4 rounded-pill" href="#" target="_blank">커플 밸런스 게임하러가기</a>
                </div>
            </div>
            </section>
        <!-- App badge section-->
        <section class="bg-gradient-primary-to-secondary" id="download">
        
            <div class="container px-5">
                <h2 class="text-center text-white font-alt mb-4">Get the app now!</h2>
                <div class="d-flex flex-column flex-lg-row align-items-center justify-content-center">
                    <a class="me-lg-3 mb-4 mb-lg-0" href="https://www.apple.com/kr/app-store/"><img class="app-badge" src="img/google-play-badge.svg" alt="..." /></a>
                    <a href="https://play.google.com/store/games?device=windows&utm_source=apac_med&hl=ko-KR&utm_medium=hasem&utm_content=Jul0121&utm_campaign=Evergreen&pcampaignid=MKT-EDR-apac-kr-1003227-med-hasem-py-Evergreen-Jul0121-Text_Search_BKWS-BKWS%7CONSEM_kwid_43700058439438730_creativeid_477136209358_device_c&gclid=Cj0KCQjwi7GnBhDXARIsAFLvH4kRrVXAETlE64-KVozud3vOt5dRFWmlZF-Uo3DlmR7P7jM8faftiOsaAoZQEALw_wcB&gclsrc=aw.ds"><img class="app-badge" src="img/app-store-badge.svg" alt="..." /></a>
                </div>
            </div>
        </section>
        



<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js/dist/chart.umd.js"></script>
<!-- 차트 라이브러리  -->
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<!-- 데이터 라이브러리  -->
<!-- <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-autocolors"></script>  -->
<!-- 색상자동생성 라이브러리 -->
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-plugin-autocolors@0.2.2/dist/chartjs-plugin-autocolors.min.js"></script>

<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

<script>
        
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
        

        
        
        
        $('ul.nav-tabs li').on({
        	'click' : function(){
        		$('ul.nav-tabs li a').removeClass('active');
        		$(this).children('a').addClass('active');
        		
         		var idx = $(this).index();
        		$('#tab-content .tab').addClass('d-none');
        		$('#tab-content .tab').eq(idx).removeClass('d-none');
        		$('#legend-container').empty();
        		if(idx==0)		age(); //연령별 조회
        		else if(idx==1) period();
        		else if(idx==2) item();
        	},
        	
        	'mouseover' : function(){
        		$(this).addClass('shadow');
        	},
        	'mouseleave' : function(){
        		$(this).removeClass('shadow');
        	},
        })
        
        function initCanvas(){
			$('#legend').remove();
			$('canvas#chart').remove();
			$('#tab-content').append(`<canvas id="chart" class="h-100 m-auto mt-5"></canvas>`);
		}
        
      	$(function(){
      		$('ul.nav-tabs li').eq(0).trigger('click')
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
        		info.category = [], info.datas = [], info.itemNames = [], info.colors = [], info.lineData = [];
        		$(response).each(function(idx){
        			info.category.push(this.RANK);
        			info.datas.push(this.SALES);
        			info.lineData.push(this.POPULAR);
        			info.itemNames.push(this.ITEM_NAME); 
        			info.colors.push(colors[idx % 10]);
        		})
        		
        		
        		console.log('data',info);
       			barChart(info);
        	})
        }
      	
      	
        //도넛 차트
        function donutChart(info){
        	$('#tab-content').css('height', '700');
        	
        	//각 수치데이터에 대한 백분율 구하기
        	var sum = 0;
        	$(info.datas).each(function(){
        		sum += this;
        	})
        	//배열정보로 새로운 배열정보를 만들어주는 함수: map
        	info.pct = info.datas.map(function(data){
        		return Math.round(data / sum * 10000)/100;
        	})
        	
        	var labelAnchors = ['start', 'center', 'end', 'end'];
        	
        	visual = new Chart($('#chart'), {
        		type: 'doughnut',
        		data: {
        		      labels: info.category,
        		      datasets: [{
        		        label: '연령별',
        		        data: info.datas,
        		        hoverOffset: 5, //마우스 올렸을 때 데이터 조각이 offset되는 정도
        		      }]
        		    },
        		    options: {
        		    	cutout: '60%', //내부 원을 몇% 잘라낼 것인지, 0: 파이
        		    	maintainAspectRatio: false, // 크키조정시 캔버스 가로세로 비율 유지X(기본O)
        		    	responsive: true, //컨테이너 크기 변경시 캔버스 크기 조정X(기본O)
        		    	plugins:{
        		    		 tooltip: {
        		                    callbacks: {
        		                        label: function(context, data) {

        		                            let label = context.dataset.label || '';
        		                            return label + ': ' + context.parsed.y + '명';
        		                        }
        		                    }
        		                },
        		    		autocolors: {mode:'data'},
        		    		datalabels: {
        		    			anchor: labelAnchors, //도넛조각 내부에 데이터 위치하게
        		    			formatter: function(value, item){
        		    				console.log(item);
        		    				return `\${value}명(\${info.pct[item.dataIndex]}%)`;
        		    				
        		    			}
        		    		}
        		    	}
        		    }
        	});
        	
        }
      //선 그래프

        function lineChart(info){
        	$('#tab-content').css('height', '700');
        	visual = new Chart($('#chart'),{
        		type: 'line',
        		data: {
        		      labels: info.category,
        		      datasets: [{
        		        label: '연애기간별',
        		        data: info.datas,
        		        borderColor: '#0000ff', //그래프선, point테두리에 적용
        		        tension: 0, //0:완전꺾은선, 1:곡선
        		        pointRadius: 5,
        		        pointBackgroundColor: '#ff0000',
        		      }]
        		    },
        		    options: {
        		    	maintainAspectRatio: false, // 크키조정시 캔버스 가로세로 비율 유지X(기본O)
        		    	responsive: false, //컨테이너 크기 변경시 캔버스 크기 조정X(기본O)
        		    	layout: {
        		    		padding: {top:30}
        		    	},
        		    	plugins: {
        		    		 tooltip: {
        		                    callbacks: {
        		                        label: function(context, data) {

        		                            let label = context.dataset.label || '';
        		                            return label + ': ' + context.parsed.y + '명';
        		                        }
        		                    }
        		                },
        		    		
        		    		legend: {display: false},
        		    		datalabels: {
        		    			formatter: function(value){
        		    				//return value + '명';
        		    				return `\${value}명`;
        		    			}
        		    		},

        		    	},
        		    	
        		    	
        	    	scales: {
        		        y: {
        			          beginAtZero: true,
        			          title: {text: '연애기간별 회원수', display:true}
        			         }
        			      },
        		    },
        		
        	})
        }
        
        
        var colors = ['#85a832', '#a83294', '#c7c922', '#db4ba6', '#d4aca7', '#8be0c0', '#5340cf', '#021438','#d4cc83','#c1f0b4'];
      


//혼합그래프(막대, 선) 
function barChart(info) {
    $('#tab-content').css('height', '700');
    visual = new Chart($('#chart'), {
        type: 'bar',
        data: {
            labels: info.category,
            datasets: [
            	{
                    label: '상품 조회',
                    data: info.lineData,
                    fill: false,
                   	type: 'line',
                    borderColor: '#0000ff',
                    tension: 0,
                    pointRadius: 5,
                    pointBackgroundColor: '#ff0000',
                },
                {
                    label: '상품 판매',
                    data: info.datas,
                    borderWidth: 2,
                    barPercentage: 0.5,
                    backgroundColor: info.colors,
                },
                
            ],
        },
        options: {
            maintainAspectRatio: false,
            responsive: false,
            layout: {
                padding: { top: 30, bottom: 20 },
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context, data) {

                            let label = context.dataset.label || '';
                            return label + ': ' + context.parsed.y + '개';
                        }
                    }
                },
                legend: {
                    display: true,
                },
                autocolors: {
                    mode: 'data',
                },
                datalabels: {
                    display: false,
                },
            },
            scales: {
                x: {
                    beginAtZero: true,
                    title: { text: '순위', display: true },
                    position: 'bottom',
                },
                y: {
                    beginAtZero: true,
                    title: { text: '아이템 구매횟수', display: true },
                },
            },
        },
    });
    makeLegend(info);
}




//혼합 그래프의 범례표시
function makeLegend(info) {
    var tag = `<ul class="nav legend-list row d-flex justify-content-center m-0 p-0 mt-5">`;
        
    for (var i = 0; i < info.itemNames.length; i++) {
//         tag += `<li><span style="background-color: ${info.colors[i]}"></span>${info.itemNames[i]}</li>`;
        tag += `<li class="col-auto"><span style="width: 44px; height:17px; background-color: \${info.colors[i]}"></span>\${info.itemNames[i]}</li>`;
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
