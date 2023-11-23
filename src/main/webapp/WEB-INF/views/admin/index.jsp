<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="/resources/css/footsell.css">
<link rel="stylesheet" href="/resources/css/aside.css">
</head>
<body>
	<div>
		<header>
			<div>
				<%@ include file="../include/header.jsp"%>
			</div>
		</header>
		<section id="container">
			<aside>
				<%@ include file="/WEB-INF/views/admin/goods/aside.jsp"%>

			</aside>
			<div id="container_box">
					본문영역		
			</div>
		</section>


		<div id="footer">
			<%@ include file="../include/footer.jsp"%>
		</div>
	</div>
</body>
</html>