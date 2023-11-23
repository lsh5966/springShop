<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/resources/css/common/reset.css" />
<link rel="stylesheet" href="/resources/css/footsell.css" />
<link rel="stylesheet" href="/resources/css/r_modify.css" />
<style type="text/css">

</style>
</head>
<body>
	<div id="header">
		<%@ include file="../include/header.jsp"%>
	</div>
	<div>
		<form method="post" action="/reply/modify">
			<label>댓글 작성자</label>
			<input type="text" name="writer" readonly="readonly" value="${reply.writer}">
			<label>댓글 내용</label>
			<textarea rows="5" cols="50" name="content">${reply.content}</textarea>

			<!-- 추가: 비밀번호 입력 필드 -->
			<label>비밀번호</label>
			<input type="password" name="password">
			<input type="hidden" name="bno" value="${reply.bno}">
			<input type="hidden" name="rno" value="${reply.rno}">
			 <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
			<button type="submit">댓글 수정</button>
		</form>
	</div>
	<div id="footer">
		<%@ include file="../include/footer.jsp"%>
	</div>
</body>
</html>

