<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: 게시판(일반)</title>

<!-- Bootstrap core CSS -->


<link href="${pageContext.servletContext.contextPath}/css/normalizer.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


  
<style>
body {
	margin: 0px auto;
	padding: 20px;
}

#wrapper{
	padding: 20px;
}
table{
	margin-top: 20px;
}

.minicol{
	width: 6%;
	text-align: center;
}
.centeral{
	text-align: center;
}
#commentArea{
	background-color: #4E5D6C;
	margin: 10px;
}

#commentTable > tr > td {
	border: none;
}


  #sortable { list-style-type: none; margin: 0 auto; padding-left: 80px; }
  #sortable li { margin: 3px 3px 3px 0; float: left; width: 300px; height: 270px; text-align: center; }
 

</style>



<script>
	
	
	

	$(document).ready(function(){
		$( function() {
		    $( "#sortable" ).sortable();
		    $( "#sortable" ).disableSelection();
		  } );
		
		
	
		
		$('#sortable').mouseup(function(){
			
			setTimeout(function(){var arr = $( "#sortable" ).sortable('toArray');
			console.log(arr)}, 200)
			
		})
		
		
		
	});
	
	
</script>

</head>
<body class=container>
	

	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="home" />
	</jsp:include>
	
	<h2 style="padding: 20px 0px;">스테이지 디자이너</h2> <a href=# class="btn btn-success">스테이지 만들기</a>

<div class='row center-block'>
	<div class=col-12>
	
		<ul id="sortable">			
			<c:forEach var="item" items="${ outputList }" varStatus="st">
				<li class="ui-state-default" id='list${st.index+1}'>
									<div class="${colorMarkups[st.index]}" style="max-width: 100%; height: 100%">
									  <div class="card-header">스테이지 ${ item.absOrder }</div>
									  <div class="card-body">
									    <h6 class="card-title">${ item.title }</h6>
									    <p class="card-text" style="font-size: 14px">
									    따라하기 필드: ${ item.ansField }<br>
									    따라하기 유형: ${ item.ansType }<br>
									    포인트: ${ item.givePoint }<br><br>
									 	<span class=text-muted style="font-size: 12px"><c:out value='${fn:substring(item.description.replaceAll("\\\<.*?\\\>",""),0, 70)}' />...</span> 
										</p>
									  </div>
									</div>  
				</li>
			</c:forEach>
		</ul>
	
	</div>
</div>



<hr>

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