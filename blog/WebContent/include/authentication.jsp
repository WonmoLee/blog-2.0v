<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    <!-- 인증이 필요할때 넣는 코드 -->
<c:if test="${empty sessionScope.principal}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>