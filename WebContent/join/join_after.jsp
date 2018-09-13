<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<!-- servlet 에서 넘어오는 경우 경로가 이상하다 -->
<!-- servlet은 루트에 있고 루트 외의 페이지로 포워딩 시키는 경우 -->
<!-- 페이지는 루트 외에 있더라도 경로가 루트에 있는 것으로 가정됨 -->
<!-- Bootstrap core CSS -->
<link href="css/normalizer.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<title>Join After</title>

<script>
	
		function modalOpen(title, text, redirect) {
			$('.modal-title').text(title);
			$('#modalText').text(text);
			$("#myModal").modal();
			$('#myModal').on('hidden.bs.modal', function () {
				if(redirect == "back"){
					history.back();
				}
				else	location.href = redirect;
			})

		}
		
		

</script>


</head>
<body>

	

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

	<c:choose>
		<c:when test="${ result == 1 }">
			<script>
				modalOpen('성공', '회원가입에 성공했습니다.', 'join/join_steptwo.jsp?currentEmail=${ currentEmail }');
				// location.href = '../coding.jsp';
			</script>
		</c:when>
		<c:otherwise>
		<script>
				modalOpen('실패', '서버 오류로 회원가입에 실패했습니다. 잠시 후 다시 시도해주세요.', 'back');
				// location.href = '../coding.jsp';
			</script>
		</c:otherwise>
	</c:choose>

	

	
	
	
</body>
</html>