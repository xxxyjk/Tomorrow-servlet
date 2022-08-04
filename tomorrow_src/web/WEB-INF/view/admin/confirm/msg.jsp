<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomorrow : msg page</title>
</head>
<body>
<%
	String msg = (String)request.getAttribute("msg");
%>
	<script>
	var msg = '${msg}';	 
/*	
 	if(msg != ''){		
		alert(msg);
		location.replace("admain");
	}
*/	
 	
 	
	if(msg == '오류가 발생했습니다.' || msg == '상품 등록에 성공하였습니다.'){
		alert(msg);
		location.replace("admain");
	}

	if(msg == '정수로 변환 중 오류가 발생했습니다.') {
		alert(msg);
		location.replace("admain");
	}
	
	if(msg == '상품 등록에 실패하였습니다.') {
		alert(msg);
		location.replace("adProductEnroll");
	}
	
	if(msg == '상품번호가 선택되지 않았습니다. 다시 입력해주세요' || msg == '관리자가 상품을 삭제했습니다.'){
		alert(msg);
		location.replace("adProductManage");
	}
	
	if(msg == '상품 수정에 실패하였습니다.' || msg == '상품 수정에 성공하였습니다.') {
		alert(msg);
		location.replace("admain");
	}
	if(msg == '회원을 선택하지 않았습니다.' || msg == '회원 탈퇴 여부를 변경했습니다.'){
		alert(msg);
		location.replace("adMemberList");
	}
	
	if(msg == '게시물을 선택하지 않았습니다.' || msg == '관리자가 게시글을 삭제했습니다.'){
		alert(msg);
		location.replace("adArticleManage");
	}
	

	
	
	</script>
</body>
</html>