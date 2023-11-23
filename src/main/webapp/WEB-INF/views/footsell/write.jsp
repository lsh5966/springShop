<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>글 작성</title>
    <link rel="stylesheet" href="/resources/css/common/reset.css" />
    <link rel="stylesheet" href="/resources/css/footsell.css" />
    <link rel="stylesheet" href="/resources/css/footsellWrite.css" />
</head>

<body>
    <div id="header">
        <%@ include file="../include/header.jsp"%>
    </div>
    

    <form method="post">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" />

        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" />

        <label for="content">내용</label>
        <textarea id="content" name="content" rows="5" cols="50"></textarea>

        <button type="submit">작성</button>
	</form>
    <div id="footer">
        <%@ include file="../include/footer.jsp"%>
    </div>
</body>

</html>
