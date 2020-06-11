<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/nav.jsp"%>

<div class="container">
	<div class="d-flex justify-content-center">
	<form action="/blog/user?cmd=profileUploadProc" method="POST" enctype="multipart/form-data">
		<div class="form-group">
			<img onerror="this.src='/blog/img/userProfile.png'" src="${sessionScope.principal.userProfile}" width="350px" height="300px" />
		</div>
		<div class="form-group bg-light">
			<input type="file" name="userProfile" id="img__preview"/>
		</div>
		<input type="hidden" name="id" value="${sessionScope.principal.id}"/>
		<div class="form-group">
			<button class="btn btn-primary w-100">사진전송</button>
		</div>
	</form>
	</div>
</div>

<script>
	$("#img__preview").on("change", function(e){
		console.log(e);
	});
</script>

<%@ include file="../include/footer.jsp"%>