<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA: 회원 가입 (2단계)</title>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<!-- Bootstrap core CSS -->
<link href="../css/normalizer.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">

<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<style>
body {
	margin: 0px auto;
	padding: 20px;
}

article {
	width: 93%;
	margin: 0px auto;
}

input[readonly] {
	color: white;
}

#agreementTextarea {
	color: white;
}

.ira {
	background-color: grey;
	text-align: center;
	padding-top: 10px;
	font-size: 12px;
}
</style>

<script>

	var photo64 = "";

	function readImage(input) {
		if ( input.files && input.files[0] ) {
			var FR= new FileReader();
			FR.onload = function(e) {
				 $('#img').attr( "src", e.target.result );
				 $('#source').text( e.target.result );
				 photo64 = e.target.result;
				 $('#photoString').val(photo64);
			};       
			FR.readAsDataURL( input.files[0] );
		}
	}
	window.onload = function() {			
		
		$("#profilePhoto").change(function(){
			readImage( this );
		});

		$("#profilePhoto").trigger("change");
		
	
		
		$('#testbtn').click(function(){		
			
			alert(photo64);
			
		})

		// 중복 확인
		
		$('#inputNickname').focusout(function() { 
			var nickname = $('#inputNickname').val();
			
			
			$.ajax({
				url : "isNicknameDuplicated.ajax",
				type : "get",
				data : {
					nickname : nickname
				}, // 리퀘스트 parameter 보내기 {키값, 변수명(value)}
				success : function(response) {
					console.log("AJAX Request 성공: ");
					console.log(response)
					if(response.indexOf('true')>-1){
						$('#duplResultArea').html("<span class='badge badge-pill badge-danger'>❌</span> 이미 존재하는 별명입니다.");
						$('#inputNickname').val("");
						$('#inputNickname').focus();
					} else if ( nickname != "" ) {
						$('#duplResultArea').html("<span class='badge badge-pill badge-success'>✔</span> 사용 가능한 별명입니다.");
					} else if ( nickname == "" ) {
						$('#duplResultArea').text("");
					}

				},
				error : function() {
					console.log("에러 발생");
				},
				complete : function() {
					console.log("AJAX 종료");
				}
			})
		})
		
		
		
		// submit
		document.getElementById('sendInfo').onclick = function() {
			
			// 최종 점검
			if($('#inputNickname').val() == ""){
				modalOpen('오류', '닉네임을 입력해주세요.', '#inputNickname');
			} 		
			
			else{				
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
		
		
		

	
	}
</script>

</head>
<body class=container>

	<c:if test="${ not empty sessionScope.currentLoginUser }">
		<jsp:forward page="/error.htm"></jsp:forward>
	</c:if>


	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	<section style="margin-bottom: 10px;">
		<div class="alert alert-dismissible alert-secondary" >
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>회원 가입!</strong> 추가 정보를 입력하세요! 건너뛰시면 임의의 별명과 사진이 입력됩니다.			

		</div>

	</section>
	<article>

		<form id=frm action='${pageContext.servletContext.contextPath}/insertAdvInfoProcess.member' method=post>
			<input type=hidden value="${ param.currentEmail }" name=currentEmail> 
			<fieldset>
				<legend>추가 정보 입력란 </legend>
				<div class="form-group row"></div>
				
				
				<div class="form-group">
					<label for="inputNickname">닉네임</label> 
					
					<div class="row">
						<div class="col-8"><input type="text"
							class="form-control" id="inputNickname"
							aria-describedby="emailHelp" placeholder="닉네임을 입력해주세요." name=nickname autofocus>
						</div>
						<div class="col-4">
							<div class="ira" id=duplResultArea style="width:100%; height:100%;"></div>
						</div>
					</div>				

					<small id="emailHelp" class="form-text text-muted">닉네임은 15자 이내로 중복될 수 없습니다.</small>
				</div>

				<div class="form-group">
					<label for="inputPhoto">사진 입력</label> 
					<input type="file"
						class="form-control-file" id="profilePhoto"
						aria-describedby="fileHelp" name=profilePhoto accept="image/*"  >
						<p><img id="img" src="" style="max-width:500px; max-height:500px;"/></p>
						<small id="fileHelp" class="form-text text-muted" >200KB 내의 프로필 사진을 추가하실 수 있습니다.</small>
				</div>

				<div>
					<button id=sendInfo class="btn btn-primary" type=button>회원가입</button>
					<button class="btn btn-secondary" type=reset>다시 입력</button>
					<!-- <button class="btn btn-secondary" type=button id=testbtn>테스트</button> -->
				</div>
				
				<input type=hidden value="" id=photoString name=photoString>
				
				


			</fieldset>
		</form>

	
				<!-- <div id="source" style="width:100%; height:600px; word-break:break-all;"></div> -->

	</article>

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