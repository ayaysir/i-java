<%@page import="prj.ijava.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: ${boardInfo.boardTitle} 〉 ${ outputArticle.title }</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

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
#commentArea{
	background-color: #4E5D6C;
	margin: 10px;
}

#commentTable > tr > td {
	border: none;
}


pre{
	border: 1px solid white;
	padding: 10px;
	border-radius: 10px;
}
</style>

<script>
	$(document).ready(function(){
		$('#searchBtn').click(searchPopup);
		
		function searchPopup() {
			alert('서치팝업')
		}
		
		$('#btnList').click(function(){
			location.href = "${pageContext.servletContext.contextPath}/list.board?id=${param.id}&page=${param.page}";
		})
		
		$('#writeBtn').click(function(){
			location.href = "${pageContext.servletContext.contextPath}/write.page?id=${param.id}";
		})
		
		$('#subBtn').click(function(){
			var length = $('#exampleTextarea').val().length;
			// alert(length);
			if( length < 5 ){
				alert('5자 이상 입력해야 합니다.')
			} else {
				$('#frm').submit();
			}
		})
		
		$('#deleteBtn').click(function(){
			var select = confirm("정말로 삭제하시겠습니까? 삭제하시면 글을 복구할 수 없습니다.")
			if(select){
				location.href = "deleteProc.board?seq=${ outputArticle.seq }&id=${ param.id }" ;
			}
			
		})
		
		
		
	});
</script>

</head>
<body class=container>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="board" />
	</jsp:include>

	

	<table class="table">
		
		<tbody>
		
			<tr>
				<th scope="row">${ outputArticle.title }</th>
				<td  style="text-align: right"><span class="text-muted"> ${ outputArticle.writedDate } | 조회수: ${ outputArticle.readCount } </td>				
			</tr>
			<tr>
				<td colspan=2>
					<img src=${ photo64 } style="width:30px; height:30px" class="rounded-circle" > 
					<strong>${ outputArticle.nickname }</strong> <span class=text-muted>${ gradeName } </span> ${ levelMarkup2 }
					<div class=row>
						<div class=col style="margin-top: 10px">
							<p>${ outputArticle.contents }</p>
						</div>
						
					</div>
					
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<!-- ads3 -->
	<ins class="adsbygoogle"
	     style="display:block"
	     data-ad-client="ca-pub-6364767349592629"
	     data-ad-slot="3802096603"
	     data-ad-format="auto"></ins>
	<script>
	(adsbygoogle = window.adsbygoogle || []).push({});
	</script>
	
	
	
		
					<div class=row id=commentArea>	
						<div class=col>
							<table id=commentTable class=table-hover style="width:100%; text-align:center;">	
								<tbody>
								
								<c:forEach var="c" items="${ outputComments }" varStatus="status">
									
											<tr>
												<td style="width: 20%">${ outputNicks[status.index] }
													<br>${ levelList[status.index] }
														<span class=text-muted style="font-size: 12px">${ gradeNameList[status.index] }</span></td>
												<td style="text-align:left">${ c.contents }</td>
												<td style="width: 20%">${ c.writedDate } <c:if test="${c.writerEmail eq sessionScope.currentLoginUser }">
													<br><span class="badge badge-danger">삭제</span></c:if></td>
											</tr>								
								
								</c:forEach>
								
								<tr>
									<td colspan=3 style="text-align:left">
										<!-- 로그인 전용 페이지 -->
	
	<c:choose>
		<c:when test="${ not empty sessionScope.currentLoginUser }">
		
			
				<form id=frm method=post action='${pageContext.servletContext.contextPath}/commentWriteProc.board?boardId=${ param.id }&page=${ param.page }&no=${ outputArticle.seq }'>
				<div>
					<label for="exampleTextarea" class=text-left>코멘트 작성</label>
					<textarea class="form-control" id="exampleTextarea"  name=comment rows="2"></textarea>
					<button type="button" id=subBtn class="btn btn-primary">등록</button>
				</div>
				
				
				</form>
				
			
		</c:when>
		<c:otherwise>
			<div class=row style="padding-top: 20px">
				<div class='col text-center'><p> 로그인한 사용자만 코멘트를 작성할 수 있습니다. <a class="text-warning" href="login.page"> [로그인하러 가기]</a></p></div>				
			</div>
		</c:otherwise>
	</c:choose>
									</td>
								</tr>
							
							
							
								</tbody>							
								
							</table>
						</div>				

					</div>
					
					
				</td>
				
				
			</tr>
			<tr>
				<td colspan=2 style="text-align: right">
							<button type="button" class="btn btn-primary" id="writeBtn">글쓰기</button>
							
							<c:if test="${ sessionScope.currentLoginUser eq outputArticle.writerEmail }">
								<button type="button" class="btn btn-secondary" onclick="alert('준비중입니다.')">수정</button>
								<button type="button" id=deleteBtn class="btn btn-secondary">삭제</button>
							</c:if>
							
								<button type="button" class="btn btn-secondary" id="btnList">목록</button>
						
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