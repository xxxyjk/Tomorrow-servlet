<link href="<%= request.getContextPath() %>/resources/css/header.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/resources/css/join.css" rel="stylesheet" type="text/css">    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내일의 집</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" ></script>

    
    <style>
        .text {
            border-radius:9px; width: 250px; height: 30px;
        }
        *{text-decoration: none;}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/template_header.jsp"></jsp:include>	
   <section>
    <form action="join" method="post" id="regfrm">
    	<div class="con">
        	<label class="bold1">회원가입</label>
        	<hr>
        	<div class="div">
	        	<label class="bold2">아이디</label><br>
        		<label class="sp">영문, 숫자를 포함한 3~6자 이하로 입력하세요.</label><br>
			<form action="joinPro.jsp" method="post" onsubmit="return check()">
        		<input type="text" name="mId" class="id" id="mId" required placeholder="아이디">
        		<button type="button" class="btn" onclick="confirmId(this.form)">중복확인</button>
			</form>
        	</div>        
        	<div class="div">
	        	<label class="bold2">비밀번호</label><br>
        		<span class="sp">영문, 숫자를 포함한 3~6자 이하로 입력하세요.</span><br>        
        		<input type="password" name="password" id="password" class="input" onkeyup="passwordCheckFunction();" required placeholder="비밀번호"><br>
        	</div>
        	<div class="div">
	        	<label class="bold2">비밀번호 확인</label><br>
        		<input type="password" name="passwordRe" id="passwordRe" class="input"  onkeyup="passwordCheckFunction();" required placeholder="비밀번호 확인"><br>
        	</div>       

        	<div class="div">
		        <label class="bold2">닉네임</label><br>
        		<span class="sp">다른 유저와 겹치지 않는 별명을 입력해주세요. (3~6자)</span><br>
        		<input type="text" name="mNickname" id="mNickname" class="input" required placeholder="별명 (3~6자)"><br>
        	</div>
        	<div class="div">
		        <label class="bold2">이름</label><br>        		
        		<input type="text" name="mNname" id="mName" class="input" required placeholder="이름을 입력해주세요."><br>
        	<div class="div">
	            <label class="bold2">생년월일</label><br>
            	<input type="text" name="mBirth" id="mBirth" class="input" required placeholder="YY/MM/DD"><br>
        	</div>
        	<div class="div">
	            <label class="bold2">연락처</label><br>
            	<input type="text" name="phone" id="phone" class="input" required placeholder="'-'을 포함하여 넣어주세요."><br>
        	</div>
        	<div class="div">
	            <label class="bold2">한줄소개</label><br>
            	<span class="sp">한글 10자 이하로 입력하세요.</span><br>
            	<input type="text" name="intro" id="intro" class="input" required placeholder="한줄소개를 입력하세요."><br>
        	</div>
        	<div class="div">       
        	<button type="submit" id="enrollBtn" class = "btn2">회원가입하기</button>

        		<div class="bottom">
	        		<label class="label2">이미 아이디가 있으신가요?</label>
        			<a href="<%=request.getContextPath() %>/login" class="login">로그인</a>
        		</div>
    		</div>
    	</div>
    </form>	
   </section>
    
    <div style="display: block"><jsp:include page="/WEB-INF/view/template_footer.jsp"></jsp:include></div>
    
<script>
function confirmId(inputForm){ // <--inputForm <- this.form 객체 받음
	if(inputForm.id.value =="" || ! inputForm.id.value){
		alert("아이디를 입력하세요");
		return;
	}
	// 팝업 
	var url = "confirmId.jsp?id="+inputForm.id.value; //ex) confirmId.jsp?id=져니박 
	open(url, "confirmId", "toolbar=no, location=no, status=no, menubar=no, resizable=no, width=300, height=200");		
}
</script>

 
<script>
	$("#enrollBtn").on("click", EnrollHandler);

	function EnrollHandler() {
		var msg = confirm("회원 가입을 진행하시겠습니까?");
		if(msg) {
			console.log("회원 가입을 진행.");
			regfrm.submit();
		} else {
			console.log("회원 가입을 취소.");
		}
	}
</script>    

</body>
</html>