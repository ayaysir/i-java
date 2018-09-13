<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA: 코딩하기</title>

<!-- Bootstrap core CSS -->
<link href="../css/normalizer.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<style>
body {
	margin: 0px auto;
	padding: 20px;
}
article {
	padding: 20px;
}
</style>
</head>
<body>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	<section></section>
	<article>
		<form action='../CodingHandleController' method=post>
			
				<div class="form-group">
					<label for="exampleTextarea">I.JAVA 코드를 입력하세요.</label>
					<textarea name=inputArea class="form-control" id="exampleTextarea" rows="3"></textarea>
				</div>
			
				<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</article>

	<jsp:include page="../footer_include.jsp" flush="false" />

</body>
</html>