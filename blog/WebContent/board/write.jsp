<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<%@ include file="../include/authentication.jsp"%>
<head>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head> 

<body>
 	 <div class="container">
   	
		<form action="/blog/board?cmd=writeProc" method="POST">

  		<div class="form-group">	
  			<label for="title">Title:</label>
    		<input type="text" class="form-control" placeholder="title" id="title" name="title">
  		</div>
  		
  		<div class="form-group">
  			<label for="content">Content:</label>
  			<textarea id="summernote" name="content"></textarea><br/>
  			<button type="submit" class="btn btn-primary">글쓰기 등록</button>
  		</div>
  		
		</form>
	 </div>
</body>

<script>
      $('#summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 400
      });
</script>

<%@ include file="../include/footer.jsp" %>


