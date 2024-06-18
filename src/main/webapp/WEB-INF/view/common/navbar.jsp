<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${sessionScope.authUser == null }">
					로그인하세요.
				</c:when>
	<c:otherwise>
					로그인중입니다. 
					${sessionScope.authUser.name } (${sessionScope.authUser.id })				
				</c:otherwise>
</c:choose>

<div style="text-align: right">
<c:choose>
	<c:when test="${sessionScope.authUser == null }">
<a href="${pageContext.servletContext.contextPath }/login">로그인</a>
<a href="${pageContext.servletContext.contextPath }/signup">회원가입</a>	
	</c:when>
	<c:otherwise>
<a href="${pageContext.servletContext.contextPath }/logout">${authUser.name }님 로그아웃</a>
	</c:otherwise>
</c:choose>
</div>