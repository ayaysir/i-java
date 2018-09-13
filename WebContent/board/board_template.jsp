<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: 게시판(일반)</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="../css/normalizer.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
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
</style>

<script>
	$(document).ready(function(){
		$('#searchBtn').click(searchPopup);
		
		function searchPopup() {
			alert('서치팝업')
		}
	});
</script>

</head>
<body class=container>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>

	<div class=container id=wrapper>
		<h3>게시판 (일반)</h3>
		<table class="table table-hover">
			<thead>
				<tr class=table-dark>
					<th scope="col" class="minicol">번호</th>
					<th scope="col" id="titlecol">제목</th>
					<th scope="col" class="centeral" id="writercol" style="width:20%;">작성자</th>
					<th scope="col" class="centeral" id="datecol" style="width:12%">작성일</th>
					<th scope="col" class="minicol">조회</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="i" begin="2001" end="2012" step="1">
					<tr>
						<td class="minicol">${ i }</td>
						<td id="titlecol">적당한 길이의 제목입니다만......</td>
						<td class="centeral" id="writercol">김길동</td>
						<td class="centeral" id="datecol">2014-02-10</td>
						<td class="minicol">24</td>
					</tr>
				</c:forEach>
				
			
			</tbody>
		</table>

		<div class=row>
			<div class=col-4></div>
			<div class=col-4>
				<ul class="pagination pagination-sm">
					<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a>
					</li>
					<li class="page-item active"><a class="page-link" href="#">1</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#">&raquo;</a>
					<li class="page-item"><a class="page-link" href="#" id=searchBtn>&#128269;</a>
					</li>
				</ul>
			</div>
			<div class=col-4></div>
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