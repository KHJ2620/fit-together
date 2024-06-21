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
	<div class="container px-1">
		<div class="my-2">
			<%@ include file="/WEB-INF/view/common/navbar.jsp" %>
		</div>
		<div style="width: 840px; margin: 0 auto;">
		<h2>자유게시판</h2>
		<p style="text-align: right">
			자유게시판 &gt; <span style="color: hotpink;">의견들</span>
		</p>
		<div>
			총 <span style="color:orchid;">${ feeds.size()}</span>건의 의견이 등록되어 있습니다.		
		</div>
		<table style="width:800px; margin: auto; border-collapse: collapse">
			<thead>
				<tr style="border-bottom: 1px solid #ddd; height: 30px;">
					<th style="width: 10%">번호</th>
					<th>제목</th>
					<th style="width: 10%">카테고리</th>
					<th style="width: 10%">글쓴이</th>
					<th style="width: 15%">등록일</th>
					<th style="width: 10%">조회</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${feeds }" var="one">
				<tr style="border-bottom: 1px solid #ddd; height: 30px;">
					<td class="text-center">${ one.no} </td>
					<td>
						<a href="${pageContext.servletContext.contextPath }/view?no=${one.no}">
							${ one.title}
						</a>
					</td>
					<td class="text-center">${one.category } </td>
					<td class="text-center">${one.writerId} </td>
					<td class="text-center">${one.writedAt } </td>
					<td class="text-center">${one.readCnt } </td>
				</tr>
			</c:forEach>
			</tbody>				
		</table>
		<div style="margin-top:10px; text-align: right;">
			<a href="${pageContext.servletContext.contextPath }/write">
			<button>의견쓰기</button>
			</a>
		
		</div>
	</div>
	</div>
</body>
</html>