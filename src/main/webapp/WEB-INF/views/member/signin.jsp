<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="/resources/css/footsell.css">
<link rel="stylesheet" href="/resources/css/signin.css">
<style>

</style>
</head>
<body>
<section id="content">
    <form role="form" method="post" autocomplete="off" id="loginBox">
        <div id="inputBox">
            <div class="input_area">
                <label for="userId">아이디</label>
                <input type="email" id="userId" name="userId" required="required" />
            </div>

            <div class="input_area">
                <label for="userPass">패스워드</label>
                <input type="password" id="userPass" name="userPass" required="required" />
            </div>

            <button type="submit" id="signin_btn" name="signin_btn">로그인</button>

            <c:if test="${msg == false}">
                <p class="error_message">로그인에 실패했습니다.</p>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="alert alert-danger">${msg}</div>
            </c:if>
        </div>
    </form>
</section>
</body>
</html>

