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
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>




<!-- include summernote css/js -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.js"></script>
  
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

h3{
	padding: 20px 0px;
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
		
		
		
		$('.codingArea').keyup(function() { 
			
			refresh()
		})
		
		$('.codingArea').focusout(function() { 			
			
			refresh()
		})
		
		$('.codingArea').mouseenter(function() { 			
			
			refresh()
		})
			
		
		//	<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>	
		//	<input id=qnaMain type="hidden" value='' name=qmain>
		//	<input id=qnaFunction type="hidden" value='' name=qfunc>
		//	<input id=qnaResult type="hidden" value='' name=qresult>	
		//	<input id=qnaEtc type="hidden" value='' name=qetc>	
		
		$('#writeBtn').click(function() {			
			
			if ($('#summernote').summernote('isEmpty')) {
				  alert('내용을 입력해주세요.');
				}
			else if(!$('#inputTitle').val()) {
				alert('제목을 입력해주세요.');
			}
			else if(/(<([^>]+)>)/gi.test($('#inputTitle').val())){
				alert('태그는 사용하실 수 없습니다.');
			}
			else if($("#selectColor option:selected").val() == '0'){
				alert('색상을 선택해주세요.');
			}			
			else{
				$('#contentsHiddenField').val($('#summernote').summernote('code'));
				$('#stgResult').val($('#resultArea').text());
				$('#frm').submit();				
			}
			
			
		})
		
		 $('#summernote').summernote({ 
			  height: 300,                 // set editor height
			  minHeight: null,             // set minimum height of editor
			  maxHeight: null,             // set maximum height of editor
			  focus: true,                 // set focus to editable area after initializing summernote
			  lang: 'ko-KR', 				// default: 'en-US'
			
	  });
	});
</script>

</head>
<body class=container>

	


	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="stage" />
	</jsp:include>


	<form method=post action='isWriteRight.stage' id=frm>
			<div id=row>
				<div id=col><h3>스테이지 만들기</h3></div>				
			</div>
			
			<div class="form-group">
			<label class="col-form-label" for="inputTitle">제목</label> <input
				type="text" class="form-control" placeholder="제목을 입력해주세요."
				id="inputTitle" name=title>

			</div>
			
			<div class="form-group">
					<label class="col-form-label">설명 작성</label> 
					<div id="summernote"></div>
				</div>
  			<hr>
			
			<div class=row>
				<div class=col-4>
						<fieldset>
						  <legend>따라하기 필드</legend>
						  <div class="form-group">
						    <div class="custom-control custom-radio">
						      <input type="radio" id="fieldRadio1" name="fieldRadio" class="custom-control-input" value=1 checked>
						      <label class="custom-control-label" for="fieldRadio1">메인 필드만 이용</label>
						    </div>
						    <div class="custom-control custom-radio">
						      <input type="radio" id="fieldRadio2" name="fieldRadio" class="custom-control-input" value=2>
						      <label class="custom-control-label" for="fieldRadio2">함수(메소드) 필드만 이용</label>
						    </div>
						    <div class="custom-control custom-radio">
						      <input type="radio" id="fieldRadio3" name="fieldRadio" class="custom-control-input" value=3>
						      <label class="custom-control-label" for="fieldRadio3">두 필드 모두 이용</label>
						    </div>
						     <div class="custom-control custom-radio">
						      <input type="radio" id="fieldRadio4" name="fieldRadio" class="custom-control-input" value=4>
						      <label class="custom-control-label" for="fieldRadio4">없음(설명만 제공)</label>
						    </div>
						  </div>						 
						</fieldset>
				</div>
				<div class=col-4>
					<fieldset>
						  <legend>따라하기 유형</legend>
						  <div class="form-group">
						    <div class="custom-control custom-radio">
						      <input type="radio" id="typeRadio1" name="typeRadio" class="custom-control-input" value=1 checked>
						      <label class="custom-control-label" for="typeRadio1">코드를 그대로 따라적기</label>
						    </div>
						    <div class="custom-control custom-radio">
						      <input type="radio" id="typeRadio2" name="typeRadio" class="custom-control-input" value=2>
						      <label class="custom-control-label" for="typeRadio2">코드를 유사하게 따라적기 (Beta)</label>
						    </div>
						    <div class="custom-control custom-radio">
						      <input type="radio" id="typeRadio3" name="typeRadio" class="custom-control-input" value=3>
						      <label class="custom-control-label" for="typeRadio3">결과만 똑같게 따라적기 (문제 답 맞추기)</label>
						    </div>
						     <div class="custom-control custom-radio">
						      <input type="radio" id="typeRadio4" name="typeRadio" class="custom-control-input" value=4>
						      <label class="custom-control-label" for="typeRadio4">메소드, 명령어만 따라적기 (Beta)</label>
						    </div>
						  </div>						 
						</fieldset>
				</div>
				<div class=col-4>
					<fieldset>
						  <legend>기타 설정</legend>
							<div class="form-group">
							  	<label class="col-form-label" for="inputPoint">포인트 설정</span></label>
							  	<input type="text" class="form-control" placeholder="포인트 입력" id="inputPoint" name=point>
							</div>
							  <div class="form-group">
							  <label class="col-form-label" for="inputPoint">카드 색상 설정</span></label>
								    <select class="custom-select" id=selectColor style="background-color: black; color:#f4f4de" name=color>
								    	<option selected>색상을 선택하세요.</option>
										<option value="1" class='text-primary'>Primary</option>
										<option value="2" class='text-secondary'>Secondary</option>
										<option value="3" class='text-success'>Success</option>
										<option value="4" class='text-danger'>Danger</option>
										<option value="5" class='text-warning'>Warning</option>
										<option value="6" class='text-info'>Info</option>
										<option value="7" class='text-light'>Light</option>
										<option value="8" class='text-black'>Black</option>
								      
								    </select>
								  </div>
					</fieldset>
				</div>
			</div>
		
					
			<hr>
				
			<div class="collapse" id="collapseExample">
				<div class=row>
					<div class=col-6>
					 	<label class="col-form-label">메인 코드 (필수)</label>
						<textarea class=codingArea id=codeMainArea name=stgMain></textarea>
				 		<label class="col-form-label">함수(메소드) 선언 코드 (선택)</label>
						<textarea class=codingArea id=codeFunctionArea name=stgFunc></textarea>

					</div>
					<div class=col-6>
						<label class="col-form-label">코딩 결과</label>
						<pre id=resultArea>코딩 문법에 오류가 없다면 이곳에 결과가 표시됩니다.</pre>
					</div>
				</div>						
			</div>
		
		
		
		
		 <a class="btn btn-warning btn-block" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    	프로그래밍 메뉴 펼치기/접기
  		</a>
  		
  		
 
		
		<div class=right-block>
			<button type="button" class="btn btn-primary" id=writeBtn>답변 작성하기</button>
			<button type="button" class="btn btn-secondary">뒤로가기</button>
		</div>
		
		<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>	
		<input id=contentsHiddenField type="hidden" value='' name=stgDesc>
		<input id=stgResult type="hidden" value='' name=stgResult>
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