<%@page import="kh.semi.tomorrow.member.model.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<link href="<%=request.getContextPath()%>/resources/css/reset.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/css/memberList.css"
	rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomorrow : 회원 관리 페이지</title>
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

		$("#member_del").on("click", function() {
			console.log("탈퇴 버튼 실행");
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
				<li><a href="adProductManage" style="font-size: 16px">-상품
						수정/삭제</a></li>
				<li><a href="adMemberOrderList">주문 내역 조회</a></li>
				<li><a href="adMemberList" style="color: #35C5F0;">회원 관리</a></li>
				<li><a href="adArticleManage">게시물 관리</a></li>
			</ul>
		</nav>
		<section id="member_content">
			<p id="member_info_title">회원 정보 조회</p>
			<div id="membe_title_grp">
				<p id="member_cnt" style="font-size: 12px;">
					전체 회원
					<%=request.getAttribute("memberCnt")%>명
				</p>
				<button type="button" id="member_del"
					style="width: 70px; height: 30px;">탈퇴</button>
			</div>
			<form action="adMemberWithDraw" method="post" id="memberListFrm">
				<table id="member_list">
					<tr>
						<td colspan="2"></td>
						<td>회원 ID</td>
						<td>회원 이름</td>
						<td>닉네임</td>
						<td>생년월일</td>
						<td>연락처</td>
						<td>가입날짜</td>
						<td>탈퇴여부</td>
					</tr>
					<%
						ArrayList<MemberVo> memberlist = (ArrayList<MemberVo>) request.getAttribute("memberlist");
					%>
					<c:forEach items="${memberlist}" var="member">
						<tr>
							<td colspan="2"><input type="checkbox" name="chk_box"
								value="${member.mId}"></td>
							<td>${member.mId }</td>
							<td>${member.mName }</td>
							<td>${member.mNickname }</td>
							<td>${member.mBrith }</td>
							<td>${member.mPhone }</td>
							<td>${member.mDate }</td>
							<td>${member.mNy }</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</section>
		<p id="prev_next">
			<c:if test="${ startPage > 1 }">
				<a href="adMemberList?page=${ startPage-1}">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="p">
				<a href="adMemberList?page=${ p}">${ p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:forEach>
			<c:if test="${endPage < totalPageCnt }">
				<a href="adMemberList?page=${ endPage+1}">다음</a>
			</c:if>
		</p>
	</div>

	<!-- 체크박스 클릭시 클릭한 값들을 배열로 만든 후 서블릿에 값 전달 -->
	<script>
		$("#member_del").on("click", function() {
			/* 
			var strChk = [];
			
			$("input:checkbox[name=chk_box]:checked").each(function() {
				strChk.push($(this).val());			
			});
			
			console.log(strChk); 
			 */
			var cnf = confirm("해당 회원의 탈퇴 상태여부를 변경하겠습니까?");
			if (cnf) {
				memberListFrm.submit();
			} else {
				location.href = "adMemberList";
			}

		});
	</script>

</body>
</html>