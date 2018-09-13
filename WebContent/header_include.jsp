<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>I.JAVA: Header</title>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>
#head_right{
	text-align: right;
	padding-top: 18px;
	font-size: 12px;
}

</style>

</head>
<body class=container>

	<header>
		<div class="row">
			<div class="col-lg-8">
				<h1>
					<a href=main.page style="color:white">I.JAVA</a> <small class="text-muted">coding education service for beginners</small>
				</h1>
			</div>
			<div  id="head_right" class="col-lg-4">
			<!-- 로그인 분기 -->
			
				 <c:if test="${ not empty sessionScope.currentLoginUser }">
				 	${ sessionScope.cluNickname } (${ sessionScope.currentLoginUser })님, 환영합니다. <a href='${pageContext.servletContext.contextPath}/logoutProcess.member' class="text-muted">로그아웃</a>
				 </c:if>
				 <c:if test="${ empty sessionScope.currentLoginUser }">
				 	<a href='${pageContext.servletContext.contextPath}/login.page' class="text-muted">로그인</a>
				 </c:if>
			
		
			
			</div>
		</div>
	</header>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation" style="">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav mr-auto">
				<li id=menu_home class="${ param.currentPage == 'home' ? 'nav-item active' : 'nav-item' }"><a class="nav-link"
					href="main.page">처음으로 <!-- <span class="sr-only">(current)</span> -->
				</a></li>
				<li id=menu_tutorial class="${ param.currentPage == 'stage' ? 'nav-item active' : 'nav-item' }"><a class="nav-link"
					href="view.stage">따라하기</a></li>
				<li id=menu_coding class="${ param.currentPage == 'coding' ? 'nav-item active' : 'nav-item' }"><a class="nav-link"
					href="${pageContext.servletContext.contextPath}/coding.page">코딩하기</a></li>
				<li id=menu_share class="${ param.currentPage == 'share' ? 'nav-item active' : 'nav-item' }"><a class="nav-link" href="list.share">공유하기</a></li>
				<li id=menu_qna class="${ param.currentPage == 'qna' ? 'nav-item active' : 'nav-item' }"><a class="nav-link" href="list.qna">질문하기</a></li>
				<li id=menu_freeboard class="${ param.currentPage == 'ranking' ? 'nav-item active' : 'nav-item' }"><a class="nav-link"
					href="ranking.member">랭킹</a></li>
				<li id=menu_freeboard class="${ param.currentPage == 'board' ? 'nav-item active' : 'nav-item' }"><a class="nav-link"
					href="list.board?id=free">자유게시판</a></li>
					
				<c:if test="${ not empty sessionScope.currentLoginUser }">
					  <li class="nav-item dropdown">
  						  <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">내 정보</a>
					    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 40px, 0px);">
					      <a class="dropdown-item"><img src=${ sessionScope.cluPhoto } class=rounded-circle style="width: 30px; height: 30px"> 　${sessionScope.cluNickname }</a>
					      <a class="dropdown-item" href="#">${ sessionScope.currentLoginUser }</a>
					      <a class="dropdown-item" href="#">LV. ${ sessionScope.cluLevel } (${ sessionScope.cluGrade })</a>
					      <a class="dropdown-item" href="#">포인트: ${ sessionScope.cluPoint }</a>
					      												<div class="dropdown-divider"></div>
					      <a class="dropdown-item" href="privateList.share">내 저장소</a>
					      <a class="dropdown-item" onclick="alert('준비중입니다.')">업적 달성 보기</a>
					      <a class="dropdown-item" onclick="alert('준비중입니다.')">회원정보 수정/탈퇴</a>
					    </div>
					  </li>
				</c:if>	
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="button" onclick="alert('준비중입니다.')">Search</button>
			</form>
		</div>
	</nav>


	
</body>
</html>