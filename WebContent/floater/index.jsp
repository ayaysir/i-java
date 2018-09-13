<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA</title>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<!-- Bootstrap core CSS -->
<link href="${pageContext.servletContext.contextPath}/css/normalizer.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">

<style>
body {
	margin: 0px auto;
	padding: 20px;

}

.circleProfile {
    width: 80px; height: 80px;
    object-fit: cover;
    border-radius: 50%;
}
</style>
</head>
<body class=container>
	<jsp:include page="/header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home"/>
	</jsp:include> 
	<section>
		<div>
			<div class="jumbotron">
				<h1 class="display-3">Hello, world!</h1>
				<p class="lead">I.JAVA는 어린이 및 코딩 초보자를 위한 코딩 학습 사이트입니다.</p>
				<hr class="my-4">
				
				<!-- 로그인 분기 -->
				<c:if test="${ empty sessionScope.currentLoginUser }">
					<p>회원 가입을 하고 수많은 코딩 예제와 웹 컴파일러를 사용해보자!</p>
					<p class="lead">
						<a class="btn btn-primary btn-lg" href="join.page" role="button">회원 가입</a>
						<a class="btn btn-secondary btn-lg" href="login.page" role="button">로그인</a>
					</p>
				</c:if>
				
				<c:if test="${ not empty sessionScope.currentLoginUser }">
					<div class='row'>
						<div class='col-1'>
							<img src=${ sessionScope.cluPhoto } class=circleProfile>
						</div>
						<div class=col-11 style="padding-left: 25px">
							<h3>${sessionScope.cluNickname } (${ sessionScope.currentLoginUser })</h3>
							<table style="width: 300px">
							<tr>
								<td>LV. ${ sessionScope.cluLevel } (${ sessionScope.cluGrade })</td>							 	
							</tr>
							<tr>								
							 	<td>포인트: ${ sessionScope.cluPoint }</td>
							</tr>
							</table>
						
						</div>
					</div>
					
					
					
				</c:if>
				

			</div>
		</div>
	</section>
	<article>
		<jsp:include page="/floater/quotes.htm" flush="false" />
	</article>

	<jsp:include page="/footer_include.jsp" flush="false" />

</body>
</html>