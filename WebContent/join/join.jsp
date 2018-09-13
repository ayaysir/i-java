<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA: 회원 가입</title>

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
	window.onload = function() {
		changeDay();
		document.getElementById('exampleSelect1').onchange = changeDay;
		document.getElementById('exampleSelect2').onchange = changeDay;

		function changeDay() {

			var selectedMonth = document.getElementById('exampleSelect2').value;

			var endDay = 31;

			if (selectedMonth == "4" || selectedMonth == "6"
					|| selectedMonth == "9" || selectedMonth == "11") {
				endDay = 30;
			} else if (selectedMonth == "2") {
				var selectedYear = parseInt(document
						.getElementById('exampleSelect1').value);
				if (selectedYear % 4 == 0) {
					endDay = 29;
				} else {
					endDay = 28;
				}
			}

			var str = "";
			for (i = 1; i <= endDay; i++) {
				str += "<option>" + i + "</option>";
			}
			document.getElementById('daySelectArea').innerHTML = str;

		}

		

		// 중복 확인
		
		$('#inputEmail1').focusout(function() { 
			var email = $('#inputEmail1').val();
			
			
			$.ajax({
				url : "isEmailDuplicated.ajax",
				type : "get",
				data : {
					email : email
				}, // 리퀘스트 parameter 보내기 {키값, 변수명(value)}
				success : function(response) {
					console.log("AJAX Request 성공: ");
					console.log(response)
					if(response.indexOf('true')>-1){
						$('#duplResultArea').html("<span class='badge badge-pill badge-danger'>❌</span> 이미 가입된 아이디입니다.");
						$('#inputEmail1').val("");
						$('#inputEmail1').focus();
					} else if ( email != "" ) {
						$('#duplResultArea').html("<span class='badge badge-pill badge-success'>✔</span> 사용 가능한 아이디입니다.");
					} else if ( email == "" ) {
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
		
		// 비밀번호 형식 확인
		
		$('#inputPassword1').keyup(function() { 
			
			var regex = /^[a-zA-Z0-9!@()_-|]{6,20}$/  
			var pwd = $('#inputPassword1').val();
			// console.log(pwd);
			if(pwd == ""){
				
			}
			else if(regex.test(pwd)){
				// console.log(true)
				$('#pwdCheckArea').html("<span class='badge badge-pill badge-success'>✔</span> 사용하실 수 있습니다.");
			} else {
				// console.log(false)
				$('#pwdCheckArea').html("<span class='badge badge-pill badge-danger'>❌</span> 사용하실 수 없는 비밀번호입니다.");

			}			
		})
		
		// 비밀번호 재입력 확인
	
		$('#inputPassword2').keyup(function() { 
			
			var pwd1 = $('#inputPassword1').val();
			var pwd2 = $('#inputPassword2').val();
	
			if(pwd1 == ""){
				
			}
			else if(pwd1==pwd2){
				$('#pwdRevalArea').html("<span class='badge badge-pill badge-success'>✔</span> 동일한 비밀번호입니다.");
			} else {
				console.log(false)
				$('#pwdRevalArea').html("<span class='badge badge-pill badge-danger'>❌</span> 비밀번호가 다릅니다.");

			}			
		})
		
			// 전화번호 하이픈 블락
	
		$('#inputTel').keyup(function() { 
			
			var regex = /[^0-9]/ 
			if(regex.test($('#inputTel').val())) {
				$('#inputTel').val($('#inputTel').val().replace(regex, ''))
			} else if ($('#inputTel').val() > 20){
				$('#inputTel').val($('#inputTel').val().substring(0, 19))
			}	
			
			
		})
		
		// submit
		document.getElementById('sendInfo').onclick = function() {
			
			// 최종 점검
			if($('#inputEmail1').val() == ""){
				modalOpen('오류', '이메일을 입력해주세요.', '#inputEmail1');
			} else if ($('#inputPassword1').val() == ""){
				modalOpen('오류', '패스워드를 입력해주세요.', '#inputPassword1');
				$('#pwdCheckArea').html('');
				$('#pwdRevalArea').html('');
			} else if ($('#inputPassword2').val() == ""){
				modalOpen('오류', '패스워드를 입력해주세요.', '#inputPassword2');
				$('#pwdCheckArea').html('');
				$('#pwdRevalArea').html('');
			} else if ($('#inputPassword1').val() != $('#inputPassword2').val())	{
				modalOpen('오류', '입력한 패스워드가 서로 다릅니다.', '#inputPassword1')
				$('#inputPassword1').val('');
				$('#inputPassword2').val('');
				$('#pwdCheckArea').html('');
				$('#pwdRevalArea').html('');
				
			}
			
			else{
				var birthStr = document.getElementById('exampleSelect1').value
				+ "/" + document.getElementById('exampleSelect2').value
				+ "/" + document.getElementById('daySelectArea').value;
				document.getElementById('birthdayField').value = birthStr;
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
	
	<jsp:include page="/header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	<section style="margin-bottom: 10px;">
		<div class="alert alert-dismissible alert-secondary" >
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>회원 가입!</strong> 회원 가입을 해보세요! 


		</div>

	</section>
	<article>

		<form id=frm action='joinProcess.member' method=post>
			<fieldset>
				<legend>회원 가입 입력란 </legend>
				<div class="form-group row"></div>
				
				
				<div class="form-group">
					<label for="exampleInputEmail1">이메일 주소</label> 
					
					<div class="row">
						<div class="col-8"><input type="email"
							class="form-control" id="inputEmail1"
							aria-describedby="emailHelp" placeholder="이메일을 입력해주세요." name=email autofocus>
						</div>
						<div class="col-4">
							<div class="ira" id=duplResultArea style="width:100%; height:100%;"></div>
						</div>
					</div>				

					<small id="emailHelp" class="form-text text-muted">아이디는
						이메일로 사용합니다. 우리는 당신의 이메일을 절대 외부에 유출시키지 않습니다.</small>
				</div>
				<div class="form-group">
					<label for="inputPassword1">비밀번호</label> 
					
					<div class="row">
						<div class="col-8"><input type="password"
						class="form-control" id="inputPassword1" placeholder="Password"
						name=password>
						</div>
						<div class="col-4">
							<div class="ira" id=pwdCheckArea style="width:100%; height:100%;"></div>
						</div>
					</div>
					
					 <small id="password1lHelp"
						class="form-text text-muted">비밀번호는 최소 6자리 이상이어야 합니다. 영문자, 숫자, 일부 특수기호 !@()_-| 만 사용하실 수 있습니다.</small>
				</div>
				<div class="form-group">
					<label for="inputPassword2">비밀번호 다시입력</label>
					
						<div class="row">
						<div class="col-8"><input
						type="password" class="form-control" id="inputPassword2"
						placeholder="Password" name=password2>
						</div>
						<div class="col-4">
							<div class="ira" id=pwdRevalArea style="width:100%; height:100%;"></div>
						</div>
					</div>		
					  <small
						id="password2Help" class="form-text text-muted">비밀번호를 다시
						입력해주세요.</small>
				</div>
				<div class="form-group">
					<label for="exampleInputTel">전화번호</label>
					
					
					 <input type="tel"
						class="form-control" id="inputTel" placeholder="전화번호를 입력해주세요."
						name=tel> <small id="telHelp" class="form-text text-muted">전화번호(핸드폰번호
						포함)을 하이픈(-)을 제외하고 입력해주세요.</small>
				</div>
				<!-- 우편번호 필드 -->
				<div class="form-group">
					<label for="exampleInputTel">우편번호</label>
					<div class="row">
						<div class="col-8">
							<input type="text" class="form-control" id="sample2_postcode"
								placeholder="우편번호" name=zipcode readonly>
						</div>
						<div class="col-4">
							<button type="button" class="btn btn-primary"
								onclick="sample2_execDaumPostcode()" style="width:100%;">우편번호 찾기</button>
						</div>
					</div>

					<input type="text" class="form-control" id="sample2_address"
						placeholder="주소" name=address1 readonly> <input
						type="text" class="form-control" id="sample2_address2"
						placeholder="주소 상세" name=address2>




				</div>

				<!-- 생년월일 -->
				<label>생년월일</label>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="exampleSelect1">출생연도</label> <select
								class="form-control" id="exampleSelect1">

								<script>
									for (i = 2018; i >= 1900; i--) {
										if (i == 2000) {
											document
													.write("<option selected='selected'>");
										} else {
											document.write("<option>");
										}

										document.write(i);
										document.write("</option>");
									}
								</script>

							</select>
						</div>
						<!-- form area 끝 -->
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label for="exampleSelect2">월</label> <select
								class="form-control" id="exampleSelect2">

								<script>
									for (i = 1; i <= 12; i++) {
										document.write("<option>");
										document.write(i);
										document.write("</option>");
									}
								</script>

							</select>
						</div>
						<!-- form area 끝 -->
					</div>
					<div class="col-lg-3">
						<!-- 히든 필드 -->
						<span id=hiddenArea><input id=birthdayField type=hidden
							name=birthday value=''></span>
						<div class="form-group">

							<label for="exampleSelect3">일</label> <select id=daySelectArea
								class="form-control">
								<option>월을 선택해주세요.</option>
							</select>

						</div>
						<!-- form area 끝 -->
					</div>

				</div>



				<div>
					<button id=sendInfo class="btn btn-primary" type=button>회원가입</button>
					<button class="btn btn-secondary" type=reset>다시 입력</button>
				</div>


			</fieldset>
		</form>



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

	<jsp:include page="/footer_include.jsp" flush="false" />



	<!-- 다음 우편번호 -->
	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
	<div id="layer"
		style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
		<img
			src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
			id="btnCloseLayer"
			style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
			onclick="closeDaumPostcode()" alt="닫기 버튼">
	</div>

	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	<script>
		// 우편번호 찾기 화면을 넣을 element
		var element_layer = document.getElementById('layer');

		function closeDaumPostcode() {

			// iframe을 넣은 element를 안보이게 한다.
			element_layer.style.display = 'none';
		}

		function sample2_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var fullAddr = data.address; // 최종 주소 변수
							var extraAddr = ''; // 조합형 주소 변수

							// 기본 주소가 도로명 타입일때 조합한다.
							if (data.addressType === 'R') {
								//법정동명이 있을 경우 추가한다.
								if (data.bname !== '') {
									extraAddr += data.bname;
								}
								// 건물명이 있을 경우 추가한다.
								if (data.buildingName !== '') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
								fullAddr += (extraAddr !== '' ? ' ('
										+ extraAddr + ')' : '');
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample2_postcode').value = data.zonecode; //5자리 새우편번호 사용
							document.getElementById('sample2_address').value = fullAddr;
							// document.getElementById('sample2_addressEnglish').value = data.addressEnglish;

							// 커서를 상세주소 필드로 이동한다.
							document.getElementById('sample2_address2').focus();

							// iframe을 넣은 element를 안보이게 한다.
							// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
							element_layer.style.display = 'none';
						},
						width : '100%',
						height : '100%',
						maxSuggestItems : 5
					}).embed(element_layer);

			// iframe을 넣은 element를 보이게 한다.
			element_layer.style.display = 'block';

			// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
			initLayerPosition();
		}

		// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
		// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
		// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
		function initLayerPosition() {
			var width = 300; //우편번호서비스가 들어갈 element의 width
			var height = 400; //우편번호서비스가 들어갈 element의 height
			var borderWidth = 5; //샘플에서 사용하는 border의 두께

			// 위에서 선언한 값들을 실제 element에 넣는다.
			element_layer.style.width = width + 'px';
			element_layer.style.height = height + 'px';
			element_layer.style.border = borderWidth + 'px solid';
			// 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
			element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth)
					+ 'px';
			element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth)
					+ 'px';
		}
	</script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>