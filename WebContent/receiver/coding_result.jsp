<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.servletContext.contextPath}/css/normalizer.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">


<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script>
	
</script>

<style>
body {
	margin: 0px auto;
	padding: 20px;

}

section{
	padding-top: 10px;
}

article {
	padding: 10px 10px;
}

pre{
	border: 1px solid white;
	padding: 10px;
	border-radius: 10px;
}

</style>

<script>
</script>

</head>
<body>
	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home"/>
	</jsp:include> 
	<section>
		<div class="alert alert-dismissible alert-warning">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <h4 class="alert-heading">Warning!</h4>
  <p class="mb-0">Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, <a href="#" class="alert-link">vel scelerisque nisl consectetur et</a>.</p>
</div>
	</section>
	<article>
				<div>
				<h6>내가 입력한 코드</h6>
				<pre><%
					request.setCharacterEncoding("UTF8");
					System.out.println(request.getParameter("inputArea"));
					out.print(request.getParameter("inputArea"));
				%></pre>
				</div>
				
				<div>
				<h6>결과 출력</h6>
				<pre><%
					System.out.println((String)request.getAttribute("outdisp"));
					out.print((String)request.getAttribute("outdisp"));%></pre>
				</div>
				
				<form method=post action="write.board?id=free">
					<input type="hidden" value='' name=extext><div>
				<h6>내가 입력한 코드</h6>
				<pre><%
					request.setCharacterEncoding("UTF8");
					System.out.println(request.getParameter("inputArea"));
					out.print(request.getParameter("inputArea"));
				%></pre>
				</div>
				
				<div>
				<h6>결과 출력</h6>
				<pre><%
					System.out.println((String)request.getAttribute("outdisp"));
					out.print((String)request.getAttribute("outdisp"));%></pre>
				</div>
					<button id=sendToFreeboard>보내기</button>
				</form>
				
	</article>

	<jsp:include page="../footer_include.jsp" flush="false" />

</body>
</html>