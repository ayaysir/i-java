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
#commentArea{
	background-color: #4E5D6C;
	margin: 10px;
}

#commentTable > tr > td {
	border: none;
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


	<table class="table">
		
		<tbody>
		
			<tr>
				<th scope="row">새벽 세시 반이네요...</th>
				<td  style="text-align: right"><span class="text-muted"> 2001.01.01 12:23:35 | 조회수: 666 | </span>수정 <span class="text-muted">|</span> 삭제</td>				
			</tr>
			<tr>
				<td colspan=2>
					<img src=https://dummyimage.com/30x30/995a99/2c35b8&text=dummy class="rounded-circle"> <strong>닉네임</strong> <span class=text-muted>레벨명 [레벨]</span> 
					<div class=row>
						<div class=col style="margin-top: 10px"><p>Trancing Pulse</p></div>
						
					</div>
					<div class=row id=commentArea>

						<div class=col>
							<table id=commentTable class=table-hover style="width:100%; text-align:center;">	
								<tbody>
								<tr>
									<td>닉네임</td>
									<td>내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... </td>
									<td>기타 내용</td>
								</tr>
								<tr>
									<td>닉네임</td>
									<td>내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... </td>
									<td>기타 내용</td>
								</tr>
								<tr>
									<td>닉네임</td>
									<td>내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... </td>
									<td>기타 내용</td>	
								</tr>
								<tr>
									<td>닉네임</td>
									<td>내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... 내용입니다... </td>
									<td>기타 내용</td>
								</tr>
								</tbody>							
								
							</table>
						</div>				

					</div>
					
					
				</td>
				
				
			</tr>
			<tr>
				<td colspan=2 style="text-align: right">
							<button type="button" class="btn btn-primary">글쓰기</button>
							<button type="button" class="btn btn-secondary">수정</button>
								<button type="button" class="btn btn-secondary">삭제</button>
								<button type="button" class="btn btn-secondary">목록</button>
						
					</td>
				</tr>
				
			
		</tbody>
	</table>

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