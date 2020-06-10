<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/nav.jsp" %>

<div class="container">

	<div class="col-md-12 m-2">
		<form class="form-inline justify-content-end" action="/blog/board" method="GET">
			<input type="hidden" name="cmd" value="search"/>
			<input type="hidden" name="page" value="0"/>
    		<input name="keyword" class="form-control mr-sm-2" type="text" placeholder="Search">
    		<button class="btn btn-primary" type="submit">검색</button>
  		</form>
	</div>

<c:forEach var="board" items="${boards}">
	<div class="card m-2" style="width:100%">
	  <div class="card-body">
	    <h4 class="card-title">${board.title}</h4>
	    <p class="card-text">${board.content}</p>
	    <a href="/blog/board?cmd=detail&page=${param.page}&id=${board.id}" class="btn btn-primary">상세보기</a>
	    <p class="card-text">조회수 : ${board.readCount}</p>
	  </div>
	</div>
</c:forEach>

<br/>


<ul class="pagination justify-content-center">

<c:choose>
  <c:when test="${param.page == 0}">	
  	<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page-1}">Previous</a></li>
  </c:when>
  <c:otherwise>
  	<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page-1}">Previous</a></li>
  </c:otherwise>
</c:choose>

<c:choose>
  <c:when test="${isLast}">
  	<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page+1}">Next</a></li>
  </c:when>
  <c:otherwise>
  	<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page+1}">Next</a></li>
  </c:otherwise>
</c:choose>  
</ul>

</div>

<%@ include file="include/footer.jsp" %>
