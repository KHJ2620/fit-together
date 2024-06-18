<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${empty title }">
		<title>핏투게더</title>
	</c:when>
	<c:otherwise>
		<title>${title }::핏투게더</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css?<%=System.currentTimeMillis() %>" />
</head>
<body>
	<div class="my-2">
			<%@ include file="/WEB-INF/view/common/navbar.jsp" %>
		</div>
<a href="${pageContext.servletContext.contextPath }/events/design">체육 행사 만들기</a>
<a href="${pageContext.servletContext.contextPath }/events">체육 행사 리스트</a>
	<div class="container px-1">
		<div>
			<h1 class="text-center">핏투게더</h1>
		</div>
	</div>
</body>
</html>