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

.ranktable{
	font-size: 20px;
}



</style>
</head>
<body class=container>
	<jsp:include page="/header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="ranking"/>
	</jsp:include> 
	<section>
		<div>
			<div class="jumbotron">
				<h3 class="display-3">포인트 랭킹</h3>
				<hr>
				<p class="lead">포인트를 쌓아 1등에 도전해보자! (상위 100명)</p>
				<hr>
				<table class="table table-hover" >
			
					
					<tbody>

					<c:forEach var="m" items="${ranklist}" varStatus="st">
							<tr class=ranktable>
							
								<c:choose>
										<c:when test="${st.index+1 == 1}">
											<td style="width: 10%; text-align:center">
												<img src="img/rank1.svg" style="width: 60px; height: 60px"><br>
												1등
											</td>
										</c:when>
										<c:when test="${st.index+1 == 2}">
											<td style="width: 10%; text-align:center">
												<img src="img/rank2.png" style="width: 50px; height: 50px"><br>
												2등
											</td>
										</c:when>
										<c:when test="${st.index+1 == 3}">
											<td style="width: 10%; text-align:center">
												<img src="img/rank3.png" style="width: 40px; height: 40px"><br>
												3등
											</td>
										</c:when>
										<c:otherwise>
											<td style="width: 10%; text-align:center">${st.index+1}</td>
										</c:otherwise>
								</c:choose>
							
							
								
								<td style="width: 15%"><img class=rounded-circle src=${m.photoUrl} style="width: 80px; height: 80px">　</td>
								
								
								
								<td style="width: 58%">
									<c:choose>
										<c:when test="${st.index+1 == 1}">
											<h1 class=text-warning>${ m.nickname } ${ lvmu[st.index] }	</h1>
										</c:when>
										<c:when test="${st.index+1 == 2}">
											<h2 class=text-primary>${ m.nickname } ${ lvmu[st.index] }	</h2>
										</c:when>
										<c:when test="${st.index+1 == 3}">
											<h4 class=text>${ m.nickname } ${ lvmu[st.index] }	</h4>
										</c:when>
										<c:otherwise>
											${ m.nickname } ${ lvmu[st.index] }	
										</c:otherwise>
									</c:choose>
																
								</td>
								
								<td style="width: 12%">${m.point} pt</td>
							</tr>
					</c:forEach>


					</tbody>
				</table>


			</div>
		</div>
	</section>
	<article>
		<jsp:include page="/floater/quotes.htm" flush="false" />
	</article>

	<jsp:include page="/footer_include.jsp" flush="false" />

</body>
</html>