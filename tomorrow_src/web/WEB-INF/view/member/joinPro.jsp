<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kh.semi.tomorrow.member.model.dao.MemberDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 요청 파라미터의 캐릭터 인코딩을 한글로 하기 위함.
// 이 문장이 없다면 form을 통하여 넘어온 한글은 모두 깨져서 출력 된다.
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="kh.semi.tomorrow.member.model.dao.MemberDao" />
<jsp:setProperty property="*" name="member"/>


<body>
<h1> 회원가입 완료 !! </h1>
<button onclick ="window.location.href='main.jsp'">메인으로</button>
</body>
</html>