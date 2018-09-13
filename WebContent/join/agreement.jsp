<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA: 약관 동의하기</title>

<!-- Bootstrap core CSS -->
<link href="../css/normalizer.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<style>
body {
	margin: 0px auto;
	padding: 20px;
}

#agreementTextarea {
	color: white;
}
</style>

</head>
<body>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	<section>
		<div class="alert alert-dismissible alert-secondary">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>회원 가입!</strong> 회원 가입을 해보세요!
		</div>

	</section>
	<article>

		<div class="form-group">
			<label for="exampleTextarea">회원가입 약관</label>
			<textarea class="form-control" id="agreementTextarea" rows="10"
				disabled>
      ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
        ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
          ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
            ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
              ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                  ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                    ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                      ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                         ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
        ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
          ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
            ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
              ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                  ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                    ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
                      ㅁㄴ라ㅣㅇㅁㄴ렆ㅋ카ㅣㅊ퍼ㅗㅑㅐㅋ려햐ㅐ뎌랴ㅐㄻㄹㅇㄴㄹㅇㄹ
      </textarea>
		</div>
		<div class="row" style="text-align: center;">
			<div class="col-lg-12">
				<div class="form-check">
					<label class="form-check-label"> <input
						class="form-check-input" id="isAgree" type="checkbox" value=""> 약관을 모두
						읽었으며, 이에 동의합니다.
					</label>
					<button type="button" id=clickAction class="btn btn-primary">회원
						가입하기</button>
				</div>
			</div>

		</div>



	</article>

	<jsp:include page="../footer_include.jsp" flush="false" />

	<script>
		document.getElementById("clickAction").onclick = function(){
			var chk = document.getElementById("isAgree").checked;
			if(!chk) {
				alert("약관을 읽고 동의란에 체크해주세요.");
			} else {
				location.href = "join.jsp";
			}
		}
	</script>

</body>
</html>