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
<link rel="stylesheet" href="/resources/css/footsellModify.css" />
</head>
<body>
    <div id="header"><%@ include file="../include/header.jsp"%></div>
    <div class="modify">
        <form method="post">
            <label class="title"><span>제목</span> <input type="text" name="title" value="${view.title}" /></label> <br />
            <label class="writer"><span>작성자</span> <input type="text" name="writer" value="${view.writer}" /></label> <br />
            <label class="content"><textarea cols="50" rows="5" name="content">${view.content}</textarea></label> <br />
            <button type="submit">완료</button>
        </form>
    </div>
    <div id="footer"><%@ include file="../include/footer.jsp"%></div>
</body>
</html>
