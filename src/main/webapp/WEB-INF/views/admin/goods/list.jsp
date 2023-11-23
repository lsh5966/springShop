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
<link rel="stylesheet" href="/resources/css/admin/gList.css">
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
				<!-- <h2>상품목록</h2> -->
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>카테고리</th>
							<th>가격</th>
							<th>수량</th>
							<th>등록날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td><img src="${list.gdsThumbImg}"></td>
								<td><a href="/admin/goods/view?n=${list.gdsNum}">${list.gdsName}</a></td>
								<td>
									${list.cateName}
								</td>
								<td><fmt:formatNumber value="${list.gdsPrice}" pattern="###,###,###" /></td>
								<td>${list.gdsStock}</td>
								<td><fmt:formatDate value="${list.gdsDate}" pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</section>


		<div id="footer">
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>



</body>
</html>
