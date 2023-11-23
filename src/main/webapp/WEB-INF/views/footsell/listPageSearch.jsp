<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/resources/css/common/reset.css" />
<link rel="stylesheet" href="/resources/css/list.css" />
<link rel="stylesheet" href="/resources/css/footsell.css" />
</head>
<body>
	<div id="header"><%@ include file="../include/header.jsp"%></div>
	<div class="list">
		<div class="list_wrap">
			<table>
				<thead>
					<tr>
						<th class="tr_bno">번호</th>
						<th>제목</th>
						<th class="tr_writer">작성자</th>
						<th class="tr_regDate">작성일</th>
						<th class="tr_viewCnt">조회수</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${list}" var="list">
						<tr>
							<td class="tr_bno">${list.bno}</td>
							<td><a href="/footsell/view?bno=${list.bno}">${list.title}<c:if test="${list.reply_Cnt ne 0}">
										<b>[ <c:out value="${list.reply_Cnt}" /> ]
										</b>
									</c:if></a></td>
							<td class="tr_writer">${list.writer}</td>
							<td class="tr_regDate"><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd" /></td>
							<td class="tr_viewCnt">${list.viewCnt}</td>
						</tr>
					</c:forEach>


				</tbody>
			</table>


			<div>
				<c:if test="${page.prev}">
					<span>[ <a href="/footsell/listPageSearch?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]
					</span>
				</c:if>

				<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
					<span> <c:if test="${select != num}">
							<a href="/footsell/listPageSearch?num=${num}${page.searchTypeKeyword}">${num}</a>
						</c:if> <c:if test="${select == num}">
							<b style="color: black; font-weight: bold">${num}</b>
						</c:if>

					</span>
				</c:forEach>

				<c:if test="${page.next}">
					<span>[ <a href="/footsell/listPageSearch?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]
					</span>
				</c:if>

				<div id="nav"><%@ include file="../include/nav.jsp"%></div>
			</div>
			<div>
				<select name="searchType">
					<option value="title" <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
					<option value="content" <c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
					<option value="title_content" <c:if test="${page.searchType eq 'title_content'}">selected</c:if>>제목+내용</option>
					<option value="writer" <c:if test="${page.searchType eq 'writer'}">selected</c:if>>작성자</option>
				</select> <input type="text" name="keyword" value="${page.keyword}" />

				<button type="button" id="searchBtn">검색</button>
			</div>
		</div>
	</div>



	<div id="footer">
		<%@ include file="../include/footer.jsp"%>
	</div>

</body>
</html>
<script>
	document.getElementById("searchBtn").onclick = function() {

		let searchType = document.getElementsByName("searchType")[0].value;
		let keyword = document.getElementsByName("keyword")[0].value;

		location.href = "/footsell/listPageSearch?num=1" + "&searchType="
				+ searchType + "&keyword=" + keyword;
	};
</script>