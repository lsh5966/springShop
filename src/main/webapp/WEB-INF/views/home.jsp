<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="resources/css/common/reset.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="resources/css/footsell.css">

<style type="text/css">
.sec_1 {
 width: 100%;
 max-width: 1600px;
 margin: 0 auto;
}
.sec_1 table {
	width: 50%;
}
<style>
  /* 스타일링 테이블 */
  table {
    width: 100%;
    border-collapse: collapse;
  }

  th, td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
  }

  th {
    background-color: #f2f2f2;
  }

  /* 링크 스타일 */
  a {
    text-decoration: none;
    color: #0070C0;
  }

  /* TOPIC 섹션 스타일 */
  .topic {
    margin-top: 20px;
    padding: 20px;
    border: 1px solid #ccc;
  }

  .topic h1 {
    font-size: 24px;
    margin: 0;
    padding: 0;
  }

  .topic a {
    display: block;
    margin-top: 10px;
    text-decoration: none;
    color: #0070C0;
  }

  .topic div {
    margin-top: 10px;
  }

  .topic p {
    margin: 0;
    padding: 0;
  }

  .topic ul {
    list-style-type

</style>

</head>

<body>

	<header>
			<%@ include file="/WEB-INF/views/include/header.jsp"%>
	</header>
	<main>

		<div class="main_wrap">
			<div class="swiper mySwiper">
				<section class="top_banner swiper-wrapper">
					<div class="swiper-slide">
						<a href=""><img src="resources/images/optimize1.png" alt=""> <span></span>
							<h1></h1> </a>
					</div>
					<div class="swiper-slide">
						<a href=""><img src="resources/images/optimize.jpg" alt=""> <span></span>
							<h1></h1> </a>
					</div>
					<div class="swiper-slide">
						<a href=""><img src="resources/images/optimize (2).jpg" alt=""> <span></span>
							<h1></h1> </a>
					</div>
				</section>
				<!-- <div class="swiper-button-prev"></div> -->
				<div class="swiper-pagination"></div>
				<!-- <div class="swiper-button-next"></div> -->
			</div>
			<section class="sec_1">
				<div class="info">
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach items="${list}" var="list">
								<tr>
									<td>${list.bno}</td>
									<td><a href="/footsell/view?bno=${list.bno}">${list.title}</a></td>
									<td>${list.writer}</td>
									<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></td>
									<td>${list.viewCnt}</td>
								</tr>
							</c:forEach>


						</tbody>

					</table>

					<div class="topic">
						<h1>TOPIC</h1>
						<a href=""></a>
						<div>
							<h1>살로몬 스포츠스타일 XT-6 인기상품 재입고 기획전</h1>
							<p>살로몬 스포츠스타일의 인기 상품 XT-6을 만나보세요.</p>
							<p>08.30 ~ 12.31</p>
						</div>
						<ul>
							<li><a href="">건지올른스 22FW 1차 발매 기획전</a></li>
							<li><a href="">하이드아웃 22FW 경량패딩&니트 단독 할인전</a></li>
							<li><a href="">하루타 22AW 메인 캐리오버 재입고 15%할인+5%쿠폰전</a></li>
							<li><a href="">가을 아우터와 어울리는 가방 추천</a></li>
						</ul>
					</div>
				</div>
			</section>

		</div>
	</main>
	<footer>
		<div id="footer">
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>

	</footer>




</body>
</html>

<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<script src="resources/js/footsell.js"></script>

