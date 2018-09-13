<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: ${ boardInfo.boardTitle }</title>
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
#wrapper{
	padding: 20px;
}
table{
	margin-top: 20px;
}

.minicol{
	width: 6%;
	text-align: center;
}
.centeral{
	text-align: center;
}
table a{
	text-decoration: none;
}
.nounder:hover{
	text-decoration: underline;
}

</style>
<script>
	$(document).ready(function(){
		$('#searchBtn').click(searchPopup);
		
		function searchPopup() {
			alert('서치팝업')
		}
		
		$('#writeBtn').click(function(){
			location.href = "${pageContext.servletContext.contextPath}/coding.page";
		})
		
		
	});
</script>

</head>
<body class=container>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="share" />
	</jsp:include>

	<div class=container id=wrapper>
		<h3>${ boardInfo.boardTitle }</h3>
		<table class="table table-hover">
			<thead>
				<tr class=table-success>
					<th scope="col" class="minicol">번호</th>
					<th scope="col" id="titlecol">제목</th>
					<th scope="col" class="centeral" id="writercol" style="width:14%;">공개 여부</th>
					<th scope="col" class="centeral" id="datecol" style="width:14%">작성일</th>
					<th scope="col" class="minicol">조회</th>
					
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="item" items="${ outputList }" varStatus="st">
					<tr>
						<td class="minicol">${ item.seq }</td>
						<td id="titlecol"><a style="text-decoration:none" 
		href="${pageContext.servletContext.contextPath}/read.share?seq=${ item.seq }&page=${ (param.page != null) ? param.page : 1  }">
		${ item.title } 
		<span class=text-warning style="font-size: 10px">${ commentCount[st.index] != 0 ? [commentCount[st.index]] : "" }</span>
		<span class=text-danger style="font-size: 10px">
		<c:if test="${ item.likeCount != 0 }">♥${ item.likeCount }</c:if>
			</span>
		</a>
		<span class=text-muted style="font-size: 10px">${ splitedTags[st.index] }</span>
		
		
		</td>
						<td class="centeral" id="writercol"> ${ item.isPublished }</td>
						<td class="centeral" id="datecol">${ item.writedDate }</td>
						<td class="minicol">${ item.readCount }</td>
					</tr>
				</c:forEach>
				
			
			</tbody>
		</table>
		
		<table style="width: 100%; text-align: right">
			<tr>
				<td style="width: 33%;"></td>
				<td style="width: 33%;">
					<ul class="pagination pagination-sm">		
					
						<!-- navi 반복 시작 -->
						<c:forEach var="item" items="${ navi }">
							<c:choose>
								<c:when test="${ item.isActive }"><li class="page-item active"></c:when>
								<c:when test="${ item.isDisabled }"><li class="page-item disabled"></c:when>
								<c:otherwise><li class="page-item"></c:otherwise>
							</c:choose>
							<a class="page-link" href="list.share?page=${item.link}">${item.symbol}</a>
						
						</c:forEach>
						<!-- navi 반복 끝 -->					
						
						
						<!-- 돋보기(검색버튼) -->
						<li class="page-item"><a class="page-link" href="#"
							id=searchBtn>&#128269;</a></li>
					</ul>
				</td>
				<td style="width: 33%;">
					<div class="btn-group" role="group" aria-label="Basic example">
						<button type="button" class="btn btn-primary" id=writeBtn>코딩하기</button>
					</div>
				</td>
			</tr>
		</table>

		<div class=row>
			<div class=col-4></div>
			<div class=col-4>
				
			</div>
			<div class="col-1"></div>
			<div class="col-3">
				
			</div>
		</div>

		

	</div>
	
	



	<!-- modal -->
	<div class="modal" id=myModal>
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p id=modalText>Modal body text goes here.</p>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../footer_include.jsp" flush="false" />

</body>
</html>