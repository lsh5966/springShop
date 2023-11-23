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
<link rel="stylesheet" href="/resources/css/shopView.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	function replyList() {
		console.log('댓글')

		var gdsNum = ${view.gdsNum};

		// 비동기식 데이터 요청
		$
				.getJSON(
						"/shop/view/replyList" + "?n=" + gdsNum,
						function(data) {
							var str = "";

							$(data)
									.each(
											function() {

												console.log(data);

												// 날짜 데이터를 보기 쉽게 변환
												var repDate = new Date(
														this.repDate);
												repDate = repDate
														.toLocaleDateString("ko-US")

												// HTML코드 조립
												str += "<li data-repNum='" + this.repNum + "'>"
														+ "<div class='userInfo'>"
														+ "<span class='userName'>"
														+ this.userName
														+ "</span>"
														+ "<span class='date'>"
														+ repDate
														+ "</span>"
														+ "</div>"
														+ "<div class='replyContent'>"
														+ this.repCon
														+ "</div>"
														+ "<c:if test='${member != null}'>"
														+ "<div class='replyFooter'>"
														+ "<button type='button' class='modify' data-repNum='" + this.repNum + "'>수정</button>"
														+ "<button type='button' class='delete' data-repNum='" + this.repNum + "'>삭제</button>"
														+ "</div>"
														+ "</c:if>"
														+ "<div class='replyCommentContainer'></div>"
														+ "</li>";
											});

							// 조립한 HTML코드를 추가
							$("section.replyList ol").html(str);
						});
	}
</script>

</head>
<body onload="replyList();">
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
				<div class="goods">
					<div class="info">
						<div class="goodsImg">
							<img src="${view.gdsImg}">
						</div>
						<div class="goodsInfo">
							<p class="gdsName">
								<span>상품명</span>${view.gdsName}</p>

							<p class="cateName">
								<span>카테고리</span>${view.cateName}</p>

							<p class="gdsPrice">
								<span>가격 </span>
								<fmt:formatNumber pattern="###,###,###" value="${view.gdsPrice}" />
								원
							</p>

							<p class="gdsStock">
								<span>재고 </span>
								<fmt:formatNumber pattern="###,###,###" value="${view.gdsStock}" />
								EA
							</p>

							<c:if test="${view.gdsStock != 0}">

								<p class="cartStock">
									<span>구입 수량</span>
									<button type="button" id="plus_1" class="plus">+</button>
									<input type="number" class="numBox" min="1" max="${view.gdsStock}" value="1" readonly="readonly" />
									<button type="button" class="minus">-</button>
									<script type="text/javascript">
										$(document)
												.ready(
														function() {
															var maxQuantity = parseInt("${view.gdsStock}");

															$(".plus")
																	.click(
																			function() {
																				var num = parseInt($(
																						".numBox")
																						.val());
																				var plusNum = num + 1;

																				if (plusNum <= maxQuantity) {
																					$(
																							".numBox")
																							.val(
																									plusNum);
																				}
																			});

															$(".minus")
																	.click(
																			function() {
																				var num = parseInt($(
																						".numBox")
																						.val());
																				var minusNum = num - 1;

																				if (minusNum >= 1) {
																					$(
																							".numBox")
																							.val(
																									minusNum);
																				}
																			});
														});
									</script>
								</p>
								<p class="addToCart">
									<button type="button" class="addCart_btn">카트에 담기</button>
									<script>
										$(".addCart_btn")
												.click(
														function() {

															var gdsNum = $(
																	"#gdsNum")
																	.val();
															var cartStock = $(
																	".numBox")
																	.val();

															var data = {
																gdsNum : gdsNum,
																cartStock : cartStock
															};

															$
																	.ajax({
																		url : "/shop/view/addCart",
																		type : "post",
																		data : data,
																		success : function(
																				result) {

																			if (result == 1) {
																				alert("카트 담기 성공");
																				$(
																						".numBox")
																						.val(
																								"1");
																			} else {
																				alert("회원만 사용할 수 있습니다.")
																				$(
																						".numBox")
																						.val(
																								"1");
																			}
																		},
																		error : function() {
																			alert("카트 담기 실패");
																		}
																	});
														});
									</script>
								</p>
							</c:if>

							<c:if test="${view.gdsStock == 0}">
								<p>상품 수량이 부족합니다.</p>
							</c:if>
						</div>
					</div>
					<div class="gdsDes">${view.gdsDes}</div>
				</div>

				<!-- 리플리스트 -->

				<div id="reply">

					<c:if test="${member == null }">
						<p>
							소감을 남기시려면 <a href="/member/signin">로그인</a>해주세요
						</p>
					</c:if>

					<c:if test="${member != null}">
						<section class="replyForm">
							<form role="form" method="post" autocomplete="off">
								<input type="hidden" name="gdsNum" id="gdsNum" value="${view.gdsNum}">
								<div class="input_area">
									<textarea name="repCon" id="repCon"></textarea>
								</div>
								<div class="input_area">
									<button type="button" id="reply_btn">댓글 남기기</button>
									<script>
										$("#reply_btn")
												.click(
														function() {
															var formObj = $(".replyForm form[role='form']");
															var gdsNum = $(
																	"#gdsNum")
																	.val();
															var repCon = $(
																	"#repCon")
																	.val();

															var data = {
																gdsNum : gdsNum,
																repCon : repCon
															};

															$
																	.ajax({
																		url : "/shop/view/registReply",
																		type : "post",
																		data : data,
																		success : function() {
																			replyList();
																			$(
																					"#repCon")
																					.val(
																							"");
																		}
																	});
														});
									</script>
								</div>
							</form>
						</section>
					</c:if>

					<section class="replyList">
						<ol>
							<!-- 여기에 댓글 리스트 출력 -->
						</ol>
						<script>
							replyList();
						</script>
						<script>
							$(document).on(
									"click",
									".modify",
									function() {
										$(".replyModal").fadeIn(200);

										var repNum = $(this)
												.attr("data-repNum");
										var repCon = $(this).parent().parent()
												.children(".replyContent")
												.text();

										$(".modal_repCon").val(repCon);
										$(".modal_modify_btn").attr(
												"data-repNum", repNum);

									});

							// 스크립트로 인해 생성된 HTML의 이벤트는 .click() 메서드를 사용할 수 없음
							$(document).on("click", ".delete", function() {

								// 사용자에게 삭제 여부를 확인
								var deletConfirm = confirm("정말로 삭제하시겠습니까?");

								if (deletConfirm) {

									var data = {
										repNum : $(this).attr("data-repNum")
									}; // ReplyVO 형태로 데이터 생성

									$.ajax({
										url : "/shop/view/deleteReply",
										type : "post",
										data : data,
										success : function(result) {

											// result의 값에 따라 동작
											if (result == 1) {
												replyList(); // 리스트 새로고침
											} else {
												alert("작성자 본인만 할 수 있습니다.") // 본인이 아닌 경우										
											}
										},
										error : function() {
											// 로그인하지 않아서 에러가 발생한 경우
											alert("로그인하셔야합니다.")
										}
									});
								}
							});
						</script>
					</section>
				</div>
			</div>
		</section>
		<div class="replyModal">

			<div class="modalContent">

				<div>
					<textarea class="modal_repCon" name="modal_repCon"></textarea>
				</div>

				<div>
					<button type="button" class="modal_modify_btn">수정</button>
					<button type="button" class="modal_cancel">취소</button>
				</div>

			</div>

			<div class="modalBackground"></div>

		</div>

		<script>
			$(".modal_modify_btn").click(function() {
				var modifyConfirm = confirm("정말로 수정하시겠습니까?");

				if (modifyConfirm) {
					var data = {
						repNum : $(this).attr("data-repNum"),
						repCon : $(".modal_repCon").val()
					}; // ReplyVO 형태로 데이터 생성

					$.ajax({
						url : "/shop/view/modifyReply",
						type : "post",
						data : data,
						success : function(result) {

							if (result == 1) {
								replyList();
								$(".replyModal").fadeOut(200);
							} else {
								alert("작성자 본인만 할 수 있습니다.");
							}
						},
						error : function() {
							alert("로그인하셔야합니다.")
						}
					});
				}

			});

			$(".modal_cancel").click(function() {
				//$(".replyModal").attr("style", "display:none;");
				$(".replyModal").fadeOut(200);
			});
		</script>


		<div id="footer">
			<%@ include file="../include/footer.jsp"%>
		</div>
	</div>


</body>
</html>