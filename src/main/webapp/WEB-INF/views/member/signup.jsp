<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="/resources/css/footsell.css">
<link rel="stylesheet" href="/resources/css/signup.css">
<style type="text/css">
</style>
</head>
<body>
	<div class="signup">
		<section id="content">
			<form role="form" method="post" autocomplete="off">
				<div class="input_area">
					<label for="userId">아이디</label> <input type="email" id="userId" name="userId" placeholder="example@email.com" required="required" />
				</div>

				<div class="input_area">
					<label for="userPass">패스워드</label> <input type="password" id="userPass" name="userPass" required="required" />
				</div>

				<div class="input_area">
					<label for="userName">닉네임</label> <input type="text" id="userName" name="userName" placeholder="닉네임을 입력해주세요" required="required" />
				</div>

				<div class="input_area">
					<label for="userPhon">연락처</label> <input type="text" id="userPhon" name="userPhon" placeholder="연락처를 입력해주세요" required="required" />
				</div>

				<button type="submit" id="signup_btn" name="signup_btn">회원가입</button>
			</form>
		</section>
	</div>
</body>
</html>

