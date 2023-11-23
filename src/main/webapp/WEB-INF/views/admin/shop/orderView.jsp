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
<link rel="stylesheet" href="/resources/css/admin/orderView.css">
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
				<div class="orderInfo">
					<c:forEach items="${orderView}" var="orderView" varStatus="status">

						<c:if test="${status.first}">
							   <p><span>주문자</span> ${orderView.userId}</p>
							   <p><span>수령인</span> ${orderView.orderRec}</p>
							   <p><span>주소</span> (${orderView.userAddr1}) ${orderView.userAddr2} ${orderView.userAddr3}</p>
							   <p><span>가격</span> <fmt:formatNumber pattern="###,###,###" value="${orderView.amount}" /> 원</p>
							   <p><span>상태</span> ${orderView.delivery}</p>
							   
							   <div class="deliveryChange">
										   <form role="form" method="post" class="deliveryForm">
										   
										    <input type="hidden" name="orderId" value="${orderView.orderId}" />
										    <input type="hidden" name="delivery" class="delivery" value="" />
										    
										    <button type="button" class="delivery1_btn">배송 중</button>
										    <button type="button" class="delivery2_btn">배송 완료</button>
										    
										    <script>
										     $(".delivery1_btn").click(function(){
										      $(".delivery").val("배송 중");
										      run();
										     });
										     
										     $(".delivery2_btn").click(function(){
										      $(".delivery").val("배송 완료");
										      run();
										      
										     });
										     
										     function run(){
										      $(".deliveryForm").submit();
										     }
										    
										    </script>
										   </form>
										</div>
							</c:if>

					</c:forEach>
				</div>

				<ul class="orderView">
					<c:forEach items="${orderView}" var="orderView">
						<li>
							<div class="thumb">
								<img src="${orderView.gdsThumbImg}" />
							</div>
							<div class="gdsInfo">
								<p>
									<span>상품명</span> ${orderView.gdsName}<br /> <span>개당 가격</span>
									<fmt:formatNumber pattern="###,###,###" value="${orderView.gdsPrice}" />
									원<br /> <span>구입 수량 </span>${orderView.cartStock} 개<br /> <span>최종 가격</span>
									<fmt:formatNumber pattern="###,###,###" value="${orderView.gdsPrice * orderView.cartStock}" />
									원
								</p>
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
