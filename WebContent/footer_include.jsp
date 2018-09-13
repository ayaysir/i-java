<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I.JAVA: Footer</title>

<style>
</style>
<script>
	setInterval(function(){
		$.ajax({
			url : "updateCluSession.ajax",
			type : "get",
			data : "", // 리퀘스트 parameter 보내기 {키값, 변수명(value)}
			success : function(response) {
				console.log("AJAX updateCluSession Request 성공: ");
			
			},
			error : function() {
				console.log("에러 발생");
			},
			complete : function() {
				
			}
		})
	}, 5000)
	
	
</script>
</head>
<body>
<footer class=container>
	
<div class=center-block style="width:100%; margin-top: 10px; text-align:center;">
<p class="text-muted">Copyright ⓒ 2007 - 2013 Ponkot, All rights reserved. Theme by Bootswatch: MIT Licenses.</p>
</div>
</footer>
</body>
</html>