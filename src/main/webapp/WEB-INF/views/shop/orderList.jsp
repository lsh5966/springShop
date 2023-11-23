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
<link rel="stylesheet" href="/resources/css/store.css">
<link rel="stylesheet" href="/resources/css/orderList.css">
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

					<ul class="orderList">
						<c:forEach items="${orderList}" var="orderList">
							<li>
								<div>
									<p>
										<span>주문번호</span> <a href="/shop/orderView?n=${orderList.orderId}">${orderList.orderId}</a>
									</p>
									<p>
										<span>수령인</span> ${orderList.orderRec}
									</p>
									<p>
										<span>주소 </span>(${orderList.userAddr1}) ${orderList.userAddr2} ${orderList.userAddr3}
									</p>
									<p>
										<span>가격</span>
										<fmt:formatNumber pattern="###,###,###" value="${orderList.amount}" />
										원
									</p>
									<div>
										<p class="orderCancel">
											<button><a href="/shop/cancelOrder?orderId=${orderList.orderId}" class="cancel-button">주문 취소</a></button>
										</p>
										<p>
											<span>상태</span> ${orderList.delivery}
										</p>
									</div>
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