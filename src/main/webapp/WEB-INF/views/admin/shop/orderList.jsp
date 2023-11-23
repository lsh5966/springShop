<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="/resources/css/footsell.css">
<link rel="stylesheet" href="/resources/css/aside.css">
<link rel="stylesheet" href="/resources/css/admin/orderList.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div>
		<header>
			<div>
				<%@ include file="/WEB-INF/views/include/header.jsp"%>
			</div>
		</header>
		<section id="container">
			<aside>
				<%@ include file="/WEB-INF/views/admin/goods/aside.jsp"%>

			</aside>
			<div id="container_box">
				<ul class="orderList">
					<c:forEach items="${orderList}" var="orderList">
						<li>
							<div>
								<p>
									<span>주문번호</span> <a href="/admin/shop/orderView?n=${orderList.orderId}">${orderList.orderId}</a>
								</p>
								<p>
									<span>주문자 </span>${orderList.userId}
								</p>
								<p>
									<span>수령인 </span>${orderList.orderRec}
								</p>
								<p>
									<span>주소</span> (${orderList.userAddr1}) ${orderList.userAddr2} ${orderList.userAddr3}
								</p>
								<p>
									<span>가격</span>
									<fmt:formatNumber pattern="###,###,###" value="${orderList.amount}" />
									원
								</p>
								<p><span>상태</span> ${orderList.delivery}</p>
							</div>
						</li>
					</c:forEach>
				</ul>

			</div>
		</section>


		<div id="footer">
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>



</body>
</html>
