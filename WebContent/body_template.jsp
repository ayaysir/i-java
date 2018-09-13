<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA</title>

<!-- Bootstrap core CSS -->
<link href="css/normalizer.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<style>
body {
	margin: 0px auto;
	padding: 20px;

}
</style>
</head>
<body>
	<jsp:include page="header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home"/>
	</jsp:include> 
	<section>
		<div>
			<div class="jumbotron">
				<h1 class="display-3">Hello, world!</h1>
				<p class="lead">This is a simple hero unit, a simple
					jumbotron-style component for calling extra attention to featured
					content or information.</p>
				<hr class="my-4">
				<p>It uses utility classes for typography and spacing to space
					content out within the larger container.</p>
				<p class="lead">
					<a class="btn btn-primary btn-lg" href="#" role="button">Learn
						more</a>
				</p>
			</div>
		</div>
	</section>
	<article>
		<div class="row">
			<div class="col-lg-4">
				<div class="bs-component">
					<blockquote class="blockquote">
						<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Integer posuere erat a ante.</p>
						<footer class="blockquote-footer">
							Someone famous in <cite title="Source Title">Source Title</cite>
						</footer>
					</blockquote>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="bs-component">
					<blockquote class="blockquote">
						<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Integer posuere erat a ante.</p>
						<footer class="blockquote-footer">
							Someone famous in <cite title="Source Title">Source Title</cite>
						</footer>
					</blockquote>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="bs-component">
					<blockquote class="blockquote">
						<p class="mb-0">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Integer posuere erat a ante.</p>
						<footer class="blockquote-footer">
							Someone famous in <cite title="Source Title">Source Title</cite>
						</footer>
					</blockquote>
				</div>
			</div>
		</div>
	</article>

	<jsp:include page="footer_include.jsp" flush="false" />

</body>
</html>