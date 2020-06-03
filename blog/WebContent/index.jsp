<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
	//방법1 (지향)
	//response.sendRedirect("/blog/board?cmd=home");
%>
<!--방법 2 (지양)-->
<c:redirect url="/board?cmd=home" />