
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="resources/css/common/reset.css">
<link rel="stylesheet" href="resources/css/footsell.css">
</head>
<body>

	<header>
		<div class="inner_header">
			<div class="logo">
				<a href="http://localhost:8080/"></a>
			</div>
			<div class="h_nav">
				<ul>
					<li><a href="/shop/list?c=100&l=1">STORE</a></li>
					<li><a href="/footsell/listPageSearch?num=1">COMMUNITY</a></li>
				</ul>
			</div>
		<ul class="h_icon">
    <a href="/shop/cartList"><div class="cart"></div></a>
    <c:if test="${member == null}">
        <div class="rogin">
            <a href="/member/signin">LOGIN</a>
        </div>
        <div class="rogin">
            <a href="/member/signup">회원가입</a>
        </div>
    </c:if>
    <div class="m_nav">
        <c:if test="${member != null}">
            <c:if test="${member.verify == 9}">
                <li><a href="/admin/index">관리자 화면</a></li>
            </c:if>
            <li><a>${member.userName}님 환영합니다.</a></li>
            <li><a href="/member/signout">로그아웃</a></li>
            <!-- 주문 목록 메뉴 -->
            <li><a href="/shop/orderList">주문 리스트</a></li>
        </c:if>
    </div>
</ul>


		</div>
	</header>