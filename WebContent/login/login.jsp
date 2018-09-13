<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: 로그인</title>
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
</style>
<script>
 $(document).ready(function(){
	 $('#inputEmail1').focus();
	 
	 $("#inputEmail1").keydown(function (key) {
		 
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	        	$('#inputPassword1').focus();
	        }
	 
	    });
	 
	 $("#inputPassword1").keydown(function (key) {
		 
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	        	submit();
	        }
	 
	    })
	 
	 $('#submitAction').click(submit);
	 
	 function submit(){
		// 최종 점검
			if($('#inputEmail1').val() == ""){
				modalOpen('오류', '이메일을 입력해주세요.', '#inputEmail1');
				$('#inputPassword1').val("");
			} else if ($('#inputPassword1').val() == ""){
				modalOpen('오류', '패스워드를 입력해주세요.', '#inputPassword1');				
			} else {
				document.getElementById('frm').submit();
			}
	 }
	 
	 function modalOpen(title, text, focusElement){
			$('.modal-title').text(title);
			$('#modalText').text(text);
			$("#myModal").modal();
			$('#myModal').on('hidden.bs.modal', function () {
			    $(focusElement).focus();
			})
		}
 });
</script>
</head>
<body class=container>
<jsp:include page="/header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	
	<form id=frm action="${pageContext.servletContext.contextPath}/loginProcess.member" method=post>
	<div class="jumbotron">
				<h1 class="display-3">Hello, world!</h1>
				<p class="lead">I.JAVA는 어린이 및 코딩 초보자를 위한 코딩 학습 사이트입니다.</p>
				<hr class="my-4">
				  <div class="form-group">
				      <label for="inputEmail1">이메일 주소</label>
				      <input type="email" class="form-control" id="inputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name=email>
				      <small id="emailHelp" class="form-text text-muted">아이디는
						이메일로 사용합니다. 우리는 당신의 이메일을 절대 외부에 유출시키지 않습니다.</small>
				    </div>
				    <div class="form-group">
				      <label for="inputPassword1">비밀번호</label>
				      <input type="password" class="form-control" id="inputPassword1" placeholder="Password" name=password>
				    </div>
				    <input type=hidden value='${ redir  }' name=redir>
				    <div class="form-group">
					    <button id=submitAction type="button" class="btn btn-primary">로그인</button>
					    <button type="button" class="btn btn-secondary" onclick="location.href='join.page'">회원 가입</button>
					    <button type="button" class="btn btn-secondary" onclick="alert('준비중입니다.')">아이디/비밀번호 찾기</button>
				    </div>
				    
			</div>
			
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
	
	<jsp:include page="/footer_include.jsp" flush="false" />

</body>
</html>