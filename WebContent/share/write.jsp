<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I.JAVA: 게시판(일반)</title>

<!-- Bootstrap core CSS -->


<link href="${pageContext.servletContext.contextPath}/css/normalizer.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<!-- include summernote css/js -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-lite.js"></script>
  
  <!-- include summernote-ko-KR -->
<script src="${pageContext.servletContext.contextPath}/summernote/lang/summernote-ko-KR.js"></script>

  
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

pre{
	border: 1px solid white;
	padding: 10px;
	border-radius: 10px;
}

</style>



<script>
	$(document).ready(function(){
	
		  $('#summernote').summernote({ 
				  height: 300,                 // set editor height
				  minHeight: null,             // set minimum height of editor
				  maxHeight: null,             // set maximum height of editor
				  focus: true,                 // set focus to editable area after initializing summernote
				  lang: 'ko-KR', 				// default: 'en-US'
				
		  });
		  
		$('#subBtn').click(function(){
			if ($('#summernote').summernote('isEmpty')) {
				  alert('내용을 입력해주세요.');
				}
			else if(!$('#inputTitle').val()) {
				alert('제목을 입력해주세요.');
			}
			else if(/(<([^>]+)>)/gi.test($('#inputTitle').val())){
				alert('태그는 사용하실 수 없습니다.');
			}
			else{
				$('#contentsHiddenField').val($('#summernote').summernote('code'));
				$('#ctMain').val($('#imain').text());
				$('#ctFunc').val($('#ifunc').text());
				$('#ctResult').val($('#iresult').text());
				if ($("#customRadio1:radio[value='Y']").is(":checked")) {
					$('#isPublish').val('Y')
				}
				else  {
					$('#isPublish').val('N')
				}
				$('#frm').submit();
				
			}
		})
		
		$('.note-popover').css({
			display : 'none'
		});

		$.ajax({
			url : 'https://api.github.com/emojis',
			async : false
		}).then(function(data) {
			window.emojis = Object.keys(data);
			window.emojiUrls = data;
		});
		;

		$(".hint2emoji")
				.summernote(
						{
							height : 100,
							toolbar : false,
							placeholder : 'type starting with : and any alphabet',
							hint : {
								match : /:([\-+\w]+)$/,
								search : function(keyword,
										callback) {
									callback($
											.grep(
													emojis,
													function(
															item) {
														return item
																.indexOf(keyword) === 0;
													}));
								},
								template : function(item) {
									var content = emojiUrls[item];
									return '<img src="' + content + '" width="20" /> :'
											+ item + ':';
								},
								content : function(item) {
									var url = emojiUrls[item];
									if (url) {
										return $('<img />')
												.attr('src',
														url)
												.css('width',
														20)[0];
									}
									return '';
								}
							}
						});
	});
</script>

</head>
<body class=container>

	<!-- 로그인 전용 페이지 -->
		<c:if test="${ empty sessionScope.currentLoginUser }">
			<jsp:forward page="/login.member">
				<jsp:param  name="redir" value="${pageContext.servletContext.contextPath}/write.board?id=${ param.id }" />
			</jsp:forward>
		</c:if>


	<jsp:include page="../header_include.jsp" flush="false">
		<jsp:param name="currentPage" value="share" />
	</jsp:include>


	<form action='${pageContext.servletContext.contextPath}/writeProc.share' method=post id=frm>

		<div class="form-group">
			<label class="col-form-label" for="inputTitle">제목</label> <input
				type="text" class="form-control" placeholder="제목을 입력해주세요."
				id="inputTitle" name=title value='${ param.shtitle }'>

		</div>
		
		<label class="col-form-label">메인</label> 
		<pre id=imain>${ param.shmain }</pre>
		<label class="col-form-label">함수</label> 
		<pre id=ifunc>${ param.shfunc }</pre>
		<label class="col-form-label">결과</label> 
		<pre id=iresult>${ param.shresult }</pre>
		
		<label class="col-form-label">기타 할 말</label> 
		<textarea id="summernote" style="width:1px; height:1px"></textarea>
		
		<hr>
		
		<div class="form-group">
  			<label class="col-form-label col-form-label-sm" for="inputTags">태그 입력 (태그는 쉼표로 구분해주세요.)</label>
  			<input class="form-control form-control-sm" type="text" placeholder="예) 김밥,떢볶이,순대" id="inputTags" name=tags>
		</div>
		
		<hr>
		
		 <div class="form-group">
		 <label class="col-form-label">공유 여부 선택</label> 
    <div class="custom-control custom-radio">
      <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input" checked value='Y'>
      <label class="custom-control-label" for="customRadio1" >'공유하기' 게시판에 모두가 볼 수 있도록 공유합니다. 동시에 내 저장소에서도 확인 가능합니다.</label>
    </div>
    <div class="custom-control custom-radio">
      <input type="radio" id="customRadio2" name="customRadio" class="custom-control-input" value='N'>
      <label class="custom-control-label" for="customRadio2">'내 저장소' 메뉴에서 나만 볼 수 있도록 합니다.</label>
    </div>
  
  </div>
		
		<div class=center-block>
			<button type="button" id=subBtn class="btn btn-primary">글쓰기</button>
			<button type="button" class="btn btn-primary">뒤로가기</button>
		</div>
		
		<input type=hidden value=${ sessionScope.currentLoginUser } name=writerEmail>
		<input type=hidden id=contentsHiddenField name=contents>
		
		<input type=hidden id=ctMain name=shmain>
		<input type=hidden id=ctFunc name=shfunc>
		<input type=hidden id=ctResult name=shresult>
		<input type=hidden id=isPublish name=isPublish>
		

	</form>


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