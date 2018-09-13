<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: 코딩하기</title>

<!-- Bootstrap core CSS -->


<link href="${pageContext.servletContext.contextPath}/css/normalizer.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">



<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<!-- include summernote css/js -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-bs4.js"></script>
  
  <!-- include summernote-ko-KR -->
<script src="${pageContext.servletContext.contextPath}/summernote/lang/summernote-ko-KR.js"></script>

  
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

.codingArea{
	width: 100%;
	height: 500px;
	display: block;
	background-color: black;
	color: #30a35f;
}

#resultArea{
	border: 1px solid white;
	padding: 10px;
	border-radius: 10px;
}


</style>



<script>
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
		
		$('#sendToFree').click(function() { 
			$('#outputStr').val("<p>코드" + "<pre>" 
					+ $('#codeMainArea').val() + "</pre>" 
					+ "</p>" 
					+ "<p>함수" + "<pre>" 
					+ $('#codeFunctionArea').val() 
					+ "</pre></p>"
					+ "<p>결과" + "<pre>" 
					+ $('#resultArea').html() 
					+ "</pre></p><br><br>"
					);
			var str1 = '[코드] ' + $('#inputTitle').val();
			$('#outputTitle').val(str1);
			frm1.submit();
		})
		
		$('#sendToQna').click(function() { 
			var str1 = '[질문] ' + $('#inputTitle').val();
			$('#qnaTitle').val(str1);
			$('#qnaMain').val($('#codeMainArea').val());
			$('#qnaFunction').val($('#codeFunctionArea').val());
			$('#qnaResult').val($('#resultArea').text())
			frm2.submit();
		})
		
		$('#sendToRepo').click(function() { 
			var str1 = '[코드] ' + $('#inputTitle').val();
			$('#shTitle').val(str1);
			$('#shMain').val($('#codeMainArea').val());
			$('#shFunction').val($('#codeFunctionArea').val());
			$('#shResult').val($('#resultArea').text())
			frm3.submit();
		})
	});
</script>

</head>
<body class=container>

	


	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="coding" />
	</jsp:include>


	
	

		<div class="form-group">
			<label class="col-form-label" for="inputTitle">제목</label> <input
				type="text" class="form-control" placeholder="제목을 입력해주세요."
				id="inputTitle" name=title>

		</div>
		
			<div class=row>
				<div class=col-6>
					<label class="col-form-label">메인 코드 (필수)</label>
					<c:choose>
						<c:when test="${ param.extMain eq null or param.extMain eq '' }">
<textarea class=codingArea id=codeMainArea>
/* 코드 작성자: ${ sessionScope.cluNickname } */
 
 </textarea>
 						</c:when>
 						<c:otherwise>
<textarea class=codingArea id=codeMainArea>
${ param.extMain }
 </textarea>	
 						</c:otherwise>
					</c:choose>
					
					
					 <div class="collapse" id="collapseExample">
					 	<div class="">
					 	<c:choose>
					 		<c:when test="${ param.extFunc eq null or param.extFunc eq '' }">
<label class="col-form-label">함수(메소드) 선언 코드 (선택)</label>
<textarea class=codingArea id=codeFunctionArea>

 /* 주의: 함수명은 현재 사용중인 모든 회원과 함수명을 공유합니다.
 * 예를 들어 다른 사람이 'cafe'라는 함수를 이미 사용하고 있다면 
 * 'cafe'라는 함수명을 사용하실 수 없습니다.
 * 이로 인해 에러가 발생할 수 있으니 함수명 중복을 피하기 위해
 * 중복 우려가 없는 고유한 패턴의 함수명을 사용해주세요. 예) ds1_cafe
 * 이 주석을 지워주세요. */

/* 함수(메소드) 작성자: ${sessionScope.cluNickname } */
 
</textarea>
					 		</c:when>
					 		<c:otherwise>
<label class="col-form-label">함수(메소드) 선언 코드 (선택)</label>
<textarea class=codingArea id=codeFunctionArea>
${ param.extFunc } 

</textarea>
					 		</c:otherwise>
					 		
					 	</c:choose>
					 		

					  	</div>
					</div>
				</div>
				<div class=col-6>
					<label class="col-form-label">코딩 결과</label>
					<pre id=resultArea>코딩 문법에 오류가 없다면 이곳에 결과가 표시됩니다.</pre>
				</div>
				
				
			</div>
		
		
		
		
		 <a class="btn btn-secondary btn-block" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    	함수(메소드) 선언 메뉴 
  		</a>
  		
  		<hr>
  
 
		
		<div class=right-block>
			<button type="button" class="btn btn-primary" id=sendToRepo>저장소에 보내기</button>
			<button type="button" class="btn btn-secondary" id=sendToQna>질문게시판에 보내기</button>
			<button type="button" class="btn btn-secondary" id=sendToFree>자유게시판에 보내기</button>
		</div>
		
		
		<form method=post action="write.page?id=free" id=frm1>
			<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>
			<input id=outputTitle type="hidden" value='' name=extitle>	
			<input id=outputStr type="hidden" value='' name=extext>			
		</form>
		
		<form method=post action="question.page" id=frm2>
			<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>
			<input id=qnaTitle type="hidden" value='' name=qtitle>	
			<input id=qnaMain type="hidden" value='' name=qmain>
			<input id=qnaFunction type="hidden" value='' name=qfunc>
			<input id=qnaResult type="hidden" value='' name=qresult>				
		</form>
		
		<form method=post action="shareCode.page" id=frm3>
			<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>
			<input id=shTitle type="hidden" value='' name=shtitle>	
			<input id=shMain type="hidden" value='' name=shmain>
			<input id=shFunction type="hidden" value='' name=shfunc>
			<input id=shResult type="hidden" value='' name=shresult>				
		</form>


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