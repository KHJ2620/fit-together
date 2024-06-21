<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<div class="container px-1">
		<div class="my-2">
			<%@ include file="/WEB-INF/view/common/navbar.jsp" %>
		</div>
		<div class="border-rounded p-2 text-center">
			<p class="fs-3">
				<span class="warning">${e.hostId }</span> 의 체육행사 <small>✍${e.registerAt }</small>
			</p>
			<h1>
				<span class="warning">[${e.tag } ]</span>
				<c:out value="${e.title }" />
			</h1>
			<p class="fs-3">
				<c:out value="${e.description }" />
			</p>
			<p class="fs-3">
				<span>📆 ${e.openDate }</span> <span>🚩 ${g.name }
					(${g.location })</span> <span>😊 ${e.attendee } / ${e.capacity }</span>
			</p>
		</div>
		<div class="my-2 text-right">
			<a href="${pageContext.servletContext.contextPath }/events/join/${e.id}"><button class="p-2 fs-4 border-rounded">참가신청</button></a>
		</div>
		<h3 class="my-2">전체댓글 | 참가자들</h3>
		<div>
			

		</div>


	</div>
</body>
</html>