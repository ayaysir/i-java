<%@page import="prj.ijava.dao.BoardDAO"%>
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
	
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

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


.roundDiv{
	border: 1px solid white;
	padding: 10px;
	border-radius: 10px;
}

.codingArea{
	width: 100%;
	height: 200px;
	display: block;
	background-color: black;
	color: #30a35f;
}



</style>

<script>
	$(document).ready(function(){
		
		$(document).ready(function(){
			$("textarea").keydown(function(e) {

			    if(e.keyCode === 9) { // tab was pressed

				     // get caret position/selection
				     var start = this.selectionStart;
				     var end = this.selectionEnd;

				     var $this = $(this);
				     var value = $this.val();

				     // set textarea value to: text before caret + tab + text after caret
				     $this.val(value.substring(0, start)
				                    + "\t"
				                    + value.substring(end));

				     // put caret at right position again (add one for the tab)
				     this.selectionStart = this.selectionEnd = start + 1;

				     // prevent the focus lose
				     e.preventDefault();
				 }
			
			});
			
			function refresh(){
				var codeMainArea = $('#codeMainArea').val();
				var codeFunctionArea = $('#codeFunctionArea').val();
				
				
				$.ajax({
					url : "CodingAjaxController",
					type : "post",
					data : {
						codeMainArea : codeMainArea,
						codeFunctionArea : codeFunctionArea
					}, // 리퀘스트 parameter 보내기 {키값, 변수명(value)}
					success : function(response) {
						console.log("AJAX Request 성공: ");
						console.log(response);
						$('#resultArea').text(response);
						
					},
					error : function() {
						console.log("error");
					},
					complete : function() {
						console.log("AJAX 종료");
					}
				})
			}
			
			
			
			$('.codingArea').keyup(function(e) { 
				
				 if(e.keyCode === 13 || e.keyCode === 9  || keyCode === 37 || keyCode === 38 || keyCode === 39 || keyCode === 40){
					 refresh()
				 }
			})
			
			$('.codingArea').focusout(function() { 			
				
				refresh()
			})
			
			$('.codingArea').mouseenter(function() { 			
				
				refresh()
			})
			
			$('.codingArea').click(function() { 			
		
				refresh()
			})
			
			$('#nextBtn').click(function() { 			
				$('#stgResult').val($('#resultArea').text());
				$('#frm').submit();
			})
		})
	})
		
	
		
		
	
		
		
		

</script>

</head>
<body class=container>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="stage" />
	</jsp:include>

	

	<table class="table">
		
		<tbody>
		
			<tr>
				<th scope="row" style="font-size: 25px"><span class='btn btn-success'>${ st.absOrder }</span> ${ st.title }</th>
				<td  style="text-align: right; padding-top:25px">나의 진행상황: ${ st.absOrder }/24</td>				
		
			</tr>
			
			<tr>
				<td colspan=2>
				<label>설명</label>
				<div class='roundDiv'>
				${ st.description }
				</div>
				
				<hr>
				
				<c:choose>
					<c:when test="${ st.ansType == 1 }">
														<div class=row id=type1>
													<div class=col-6>
														<label class="col-form-label">메인 코드</label>
														<pre class=roundDiv>${ st.ansMain }</pre>
													</div>
													<c:if test="${ st.ansFunction ne null }">
														<div class=col-6>
															<label class="col-form-label">함수(메소드)</label>
															<pre class=roundDiv>${ st.ansFunction }</pre>
														</div>
													</c:if>
												</div>	
					</c:when>
					<c:when test="${ st.ansType == 3 }">
													<div class=col-6>
														<label class="col-form-label">결과</label>
														<pre class=roundDiv>${ st.ansResult }</pre>
													</div>
					</c:when>
				</c:choose>
				
				
			
					
				
				<hr>
				
			<form method=post action="check.stage" id=frm>
				<c:choose>
					<c:when test="${ st.ansField == 1 }">
						<div class=row id=field1>
							<div class=col-8>
								<label class="col-form-label">코드 입력</label>
								<textarea class=codingArea id=codeMainArea name=stgMain>${ param.extext }</textarea>
							</div>
							<div class=col-4>
								<label class="col-form-label">코딩 결과</label>
								<pre id=resultArea class=roundDiv>실시간으로 결과를 확인하세요.</pre>
								<input type=hidden value='' id=codeFunctionArea>
								<input id=stgResult type=hidden name=stgResult value=''>
							</div>
						</div>					
					</c:when>
					
					<c:when test="${ st.ansField == 2 }">
						<div class=row id=field2>
							<div class=col-8>
								<label class="col-form-label">함수(메소드) 입력</label>
								<textarea class=codingArea id=codeFunctionArea name=stgFunc>${ param.extext }</textarea>
								<input type=hidden value='' id=codeMainArea>
							</div>
							<div class=col-4>
								<label class="col-form-label">코딩 결과</label>
								<pre id=resultArea class=roundDiv>실시간으로 결과를 확인하세요.</pre>
								<input id=stgResult type=hidden name=stgResult value=''>
							</div>
						</div>
					</c:when>
					
					<c:when test="${ st.ansField == 3 }">
						<div class=row id=field3>
							<div class=col-4>
								<label class="col-form-label">메인 코드 (필수)</label>
								<textarea class=codingArea id=codeMainArea name=stgMain>${ param.extext }</textarea>
							</div>
							<div class=col-4>
								<label class="col-form-label">함수(메소드) 선언 코드 (선택)</label>
								<textarea class=codingArea id=codeFunctionArea name=stgFunc>${ param.extext }</textarea>
							</div>
							<div class=col-4>
								<label class="col-form-label">코딩 결과</label>
								<pre id=resultArea class=roundDiv>실시간으로 결과를 확인하세요.</pre>
								<input id=stgResult type=hidden name=stgResult value=''>
							</div>
						</div>
					</c:when>
					
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
				
				
				<input type=hidden name=stn value=${ st.absOrder }>
				
			</form>

				<hr>
				
				<div class=row>
					<div class=col-4>
						<c:if test="${ st.absOrder != 1 }">
							<button type="button" class="btn btn-secondary btn-lg btn-block">《 이전 스테이지에</button>
						</c:if>
					</div>
													<div class=col-4></div>
					<div class=col-4>
						<button type="button" class="btn btn-primary btn-lg btn-block" id=nextBtn>결과를 확인하고 다음 스테이지에 》</button>
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
	
	
	
		
			
					
					
				</td>
				
				
			</tr>
			<tr>
				<td colspan=2 style="text-align: right">
						
						
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