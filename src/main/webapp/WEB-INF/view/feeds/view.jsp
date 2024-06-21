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
<c:choose>
<c:when test="${found == null}">
		<script>
			window.alert("존재하지 않는 글입니다.");
			history.go(-1);
		</script>
</c:when>
<c:otherwise>
	<div class="container px-1">
		<div class="my-2">
			<%@ include file="/WEB-INF/view/common/navbar.jsp" %>
		</div>
		<div style="width: 840px; margin: 0 auto;">
		<h2>자유게시판</h2>
		<p style="text-align: right">
			자유게시판 &gt; <span style="color: hotpink;">의견 상세보기</span>
		</p>
		
		<div>
			
			<h3 style="text-align: center; margin-bottom:8px; border-bottom: 1px solid #ccc; padding : 8px;">${found.title }</h3>		
			<div style="padding : 2px; text-align: right;">
				<span style="color : #777">작성일</span> <span>${found.writedAt }</span> |
				<span style="color : #777">작성자</span>  <span>${found.writerId }</span> |
				<span style="color : #777">카테고리</span>  <span>${found.category}</span> |
				<span style="color : #777">조회</span> <span>${found.readCnt } </span>
			</div>
			<p>
				${found.body  }
			</p>
		
		
		</div>
		</div>
</div>
</c:otherwise>
</c:choose>
</body>
</html>
