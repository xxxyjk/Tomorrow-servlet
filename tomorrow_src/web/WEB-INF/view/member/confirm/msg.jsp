<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomorrow : msg page</title>
</head>
<body>
	<script>
	var msg = '${msg}';	
	if(msg != '' && msg == '아이디 또는 비밀번호가 일치하지 않습니다.'){		
		alert(msg);
		location.replace("login");
	}
	if(msg != '' && msg == '회원가입에 성공했습니다.'){
		alert(msg);
		location.replace("storylist");
	}		
	if(msg != '' && msg == '회원가입에 실패했습니다. 회원 가입 양식을 다시 확인해주세요.'){
		alert(msg);
		location.replace("join");
	}
	if(msg != '' && msg == '회원 정보 수정에 실패하였습니다.'){
		alert(msg);
		location.replace("update");
	}
	if(msg != '' && msg == '회원 정보 수정에 성공하였습니다.'){
		alert(msg);
		location.replace("storylist");
	}
	</script>
</body>
</html>