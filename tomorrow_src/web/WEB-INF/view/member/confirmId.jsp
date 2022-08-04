<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kh.semi.tomorrow.member.model.dao.MemberDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	// 사용자가 작성해본 id 값을 받아서 DB에 존재하는지 여부 체크 
	
	// 주소 뒤에 붙어온 파라미터 꺼내기 
	String mId = request.getParameter("mId");
	// dao에 해당 id가 존재하는지 체크 
	MemberDao dao = new MemberDao();
	boolean result = dao.confirmId(mId);
%>
	<% if(result){  // id가 존재할 경우 %>
		<table>
			<tr>
				<td> <%= mId %>는(은) 이미 사용중입니다. </td>
			</tr>
		
		<form action="confirmId.jsp" method="post">
			<tr>
				<td> 다른아이디를 입력하세요. <br/>
					<input type="text" name="mId"/>
					<input type="submit" value="ID중복확인" />
				</td>
			</tr>
		</form>
		</table>
	<%}else{ // 아이디가 존재하지 않는 경우 즉 사용 가능한 경우 %>
		<table>
			<tr>
				<td> 입력하신 <%= mId %>는(은) 사용 가능한 아이디 입니다. 
					<input type="button" value="닫기" onclick="setId()"  />
				</td>
			</tr>
		</table>
	<% } %>
	<script>
		function setId(){
			// 팝업에서 opener는 팝업창을 호출시켜준 페이지를 의미한다. 
			// signupForm 페이지의id 태그값 변경해주기 
			opener.document.inputForm.id.value= "<%=mId%>"; 
			self.close(); //팝업창 닫기 
		}
	
	</script>

</body>
</html>