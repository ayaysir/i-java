<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="i" begin="1" end="10" step="1">
		
		<input id=btn${ i } type=button value=${ i }>
		<input id=input${ i } type=text value=${ i } disabled><br>
		
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="10" step="1">
	
		<script>		
		
				document.getElementById('btn${ i }').onclick = function(){
					alert('${ i }');
					document.getElementById('input${ i }').removeAttribute("disabled");
					document.getElementById('input${ i }').focus();
				};
	
		</script>
		
	</c:forEach>
	
</body>
</html>