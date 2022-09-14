<%@page import="kh.semi.tomorrow.storyboard.model.vo.StoryBoardVo"%>
<%@page import="java.util.ArrayList"%>
<link href="<%=request.getContextPath()%>/resources/css/reset.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/articleManage.css"
	rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomorrow : 관리자 게시글 관리</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {
		// header jquery
		$("#my_info").hover(function() {
			// console.log("hover 실행");
			$(".admin_modal").show();
		});
		$(window).on("click", function() {
			// console.log("window 클릭");
			$(".admin_modal").hide();
		});
	});
</script>
</head>
<body>
	<jsp:include page="../template_header.jsp" />

	<div id="container">
		<nav id="nav_menu">
			<ul>
				<li><a href="admain">상품 관리</a></li>
				<li><a href="admain" style="font-size: 16px">-상품 목록</a></li>
				<li><a href="adProductEnroll" style="font-size: 16px">-상품
						등록</a></li>
				<li><a href="adProductManage" style="font-size: 16px;">-상품
						수정/삭제</a></li>
				<li><a href="adMemberOrderList">주문 내역 조회</a></li>
				<li><a href="adMemberList">회원 관리</a></li>
				<li><a href="adArticleManage" style="color: #35C5F0;">게시물
						관리</a></li>
			</ul>
		</nav>
		<section id="story_content">
			<p id="story_info_title">오늘의 스토리</p>
			<div id="story_manage_grp">
				<button type="button" id="story_ins"
					onclick="location.href='storyenroll';"
					style="width: 70px; height: 30px;">작성</button>
				<button type="button" id="story_del"
					style="width: 70px; height: 30px;">삭제</button>
			</div>
			<form action="adAritcleDelete" method="post" id="boardFrm">
				<table id="story_list">
					<tr>
						<td colspan="2"></td>
						<td width="65">글번호</td>
						<td width="500">제목</td>
						<td width="120">작성자</td>
						<td width="120">작성일</td>
						<td width="50">조회수</td>
					</tr>
					<%
						ArrayList<StoryBoardVo> boardlist = (ArrayList<StoryBoardVo>) request.getAttribute("boardlist");
					%>
					<c:forEach items="${boardlist }" var="vo">
						<tr>
							<td colspan="2"><input type="checkbox" name="checkBoard"
								value="${vo.bNo }"></td>
							<td><a href="storyread?bno=${vo.bNo }">${vo.bNo }</a></td>
							<td><a href="storyread?bno=${vo.bNo }">${vo.bTitle }</a></td>
							<td>${vo.bWriter }</td>
							<td>${vo.bDate }</td>
							<td>${vo.bCnt }</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</section>
		<p id="prev_next">
			<c:if test="${ startPage > 1 }">
				<a href="adArticleManage?page=${ startPage-1}">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="p">
				<a href="adArticleManage?page=${ p}">${ p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${endPage < totalPageCnt }">
				<a href="adArticleManage?page=${ endPage+1}">다음</a>
			</c:if>
		</p>
	</div>

	<%
		String msg = (String) request.getAttribute("msg");
	%>
	<script>
		var msg = '${msg}';
		if (msg != '') {
			alert(msg);
		}
	</script>
	<script>
		$("#story_del").on("click", storyDelete);

		function storyDelete() {
			//		console.log("storyDelete()");		
			var strChk = [];

			$("input:checkbox[name=checkBoard]:checked").each(function() {
				strChk.push($(this).val());
			});
			//		console.log(strChk);		
			var cnf = confirm("게시글을 삭제하시겠습니까?");
			if (cnf) {
				boardFrm.submit();
			} else {
				location.href = "adArticleManage";
			}

		}
	</script>
</body>
</html>