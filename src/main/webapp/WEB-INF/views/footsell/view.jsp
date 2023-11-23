<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" href="/resources/css/common/reset.css" />
<link rel="stylesheet" href="/resources/css/footsell.css" />
<link rel="stylesheet" href="/resources/css/footsellView.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
function showPasswordPrompt(bno, rno) {
    var password = prompt('댓글을 삭제하려면 비밀번호를 입력하세요:');

    if (password !== null) {
        fetch('/reply/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                vo: {
                    bno: bno,
                    rno: rno,
                },
                password: password,
            }),
        })
        .then(response => response.json())
        .then(data => {
            if (data.result === 1) {
                alert('댓글이 성공적으로 삭제되었습니다.');
                // 페이지 새로고침
                location.reload();
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        })
        .catch(error => {
            console.error('댓글 삭제 중 오류 발생:', error);
            alert('댓글 삭제 중 오류가 발생했습니다.');
        });
    }
}
</script>
</head>
<body>
	<div id="header"><%@ include file="../include/header.jsp"%></div>

	<div class="sec1">
		<div class="primaryContent">
			<label class="title">제목 ${view.title}</label> <br> <label class="writer">작성자${view.writer}</label> <br> <label class="content"><p>${view.content}</p></label> <br>
			<div class="c_nav">
				<a href="/footsell/modify?bno=${view.bno}">수정</a> <a href="/footsell/delete?bno=${view.bno}">삭제</a>
				<div id="nav"><%@ include file="../include/nav.jsp"%></div>
			</div>
			<hr>
			<div>

				<form method="post" action="/reply/write">
					<p>
						<label>작성자</label> <input type="text" name="writer">
					</p>
					<p>
						<label>비밀번호</label> <input type="password" name="password">
					</p>
					<p>
						<textarea rows="5" cols="50" name="content"></textarea>
					</p>
					<p>
						<input type="hidden" name="bno" value="${view.bno}">
						<button type="submit">등록</button>
					</p>
				</form>


			</div>
		</div>
		<!--댓글 시작  -->


		<ul class="reply">
			<c:forEach items="${reply}" var="reply">
				<li>
					<div>
						<span class="userName">${reply.writer}/</span><span class="date"><fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" /></span>

						<p class="replyContent">${reply.content }</p>
						<p>
							<button>
								<a href="/reply/modify?bno=${view.bno}&rno=${reply.rno}">수정</a>
							</button>
							<button>
								<a href="javascript:void(0);" onclick="showPasswordPrompt('${view.bno}', '${reply.rno}', '${view.password}');">삭제</a>
							</button>

						</p>

						<hr />
					</div>
				</li>
			</c:forEach>

		</ul>



		<!--댓글 끝  -->
	</div>
	<div id="footer">
		<%@ include file="../include/footer.jsp"%>
	</div>
</body>
</html>