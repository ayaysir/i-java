<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join After</title>
</head>
<body>
	<c:choose>
		<c:when test="${ !isEmailDuplicated }">
			<script>
				alert('아이디가 중복되지 않았습니다.');
				location.href = history.back();
			</script>
		</c:when>
		<c:otherwise>
			<script>
			alert('이미 가입된 아이디입니다.');
			location.href = history.back();
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>