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
<link rel="stylesheet" href="/resources/css/store.css">
<link rel="stylesheet" href="/resources/css/shopList.css">
<style>
</style>

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
				<%@ include file="../include/aside.jsp"%>

			</aside>
			<div id="container_box">
				<section id="content">
					<ul>
						<c:forEach items="${list}" var="list">
							<li>
								<div class="goodsThumb">
									<img src="${list.gdsThumbImg}">
								</div>
								<div class="goodsName">
									<a href="/shop/view?n=${list.gdsNum}">${list.gdsName}</a>
								</div>
								<div class="price">
									<fmt:formatNumber pattern="###,###,###" value="${list.gdsPrice}" /><span>원</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</section>
			</div>
		</section>


		<div id="footer">
			<%@ include file="../include/footer.jsp"%>
		</div>
	</div>
</body>
</html>