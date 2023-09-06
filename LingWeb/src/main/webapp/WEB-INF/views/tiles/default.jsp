<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Ling</title>
        <link rel="icon" type="image/x-icon" href="<c:url value='/resources/assets/favicon.ico'/>" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Google fonts-->
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<c:url value='/css/styles.css'/>" rel="stylesheet" />
        
            <style>
        .smooth-scroll {
            scroll-behavior: smooth;
        }
    </style>
    </head>
        <body id="page-top">
         <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm" id="mainNav">
            <div class="container px-5">
                <a class="navbar-brand fw-bold" href="<c:url value= '/'/>">LING</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="bi-list"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
                       <li class="nav-item"><a class="nav-link me-lg-3" href="<c:url value= '/#features'/>">LING 소개</a></li>
					   <li class="nav-item"><a class="nav-link me-lg-3" href="<c:url value= '/#chartt'/>">Chart</a></li>
					   <li class="nav-item"><a class="nav-link me-lg-3" href="<c:url value= '/#cta'/>">Playground</a></li>
                       <a class="nav-link me-lg-3 ${category eq 'faq' ? 'active' : ''}" href="<c:url value='/faq/list'/>">Community</a>
					<c:choose>

						<c:when test="${loginId eq 'admin'}">
							<li class="nav-item"><a class="nav-link me-lg-3"
								href="<c:url value='/lingmember'/>">관리자 화면</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link me-lg-3"
								href="#download">download</a></li>
						</c:otherwise>


					</c:choose>

                    </ul>
                    
                </div>
            </div>
        </nav> 
        <div id="container" class="container-fluid my-0">
                    <tiles:insertAttribute name="container"/>
                </div>
       
        <!-- Footer-->
        <footer class="bg-black text-center py-5">
            <div class="container px-5">
                <div class="text-white-50 small">
                    <div class="mb-2">&copy; Your Website 2023. All Rights Reserved.</div>
                    <a href="<c:url value= '/login'/>">Privacy</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">Terms</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">FAQ</a>
                </div>
            </div>
        </footer>  
      
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="<c:url value='/js/scripts.js'/>"></script>
       
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
                       