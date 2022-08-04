<%@page import="kh.semi.tomorrow.member.model.vo.MemberVo"%>
<link href="<%= request.getContextPath() %>/resources/css/header.css" rel="stylesheet" type="text/css">    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <style>
        .input_format {
            border-radius:9px; width: 250px; height: 30px;
        }
        *{text-decoration: none;}
    </style>
    <style>
    	#editInfo {
    		width : 150px;
    		height : 35px;
    		border-radius: 5px;
    	}
    
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/template_header.jsp"/>

       <div style="width: 70%; min-width:400px; margin:150px auto;">
        <div  style="display: flex; width: 100%;">
            <span style=" width: 80%;"><b style="font-size: larger ;">회원정보수정</b></span>
            <span style="width: 20%;"><a href="deleteAccount">탈퇴하기</a></span>
        </div>
  
    
    	<div id="updateWrap" style="width:100%; margin-top: 50px;">
	    	<form action="update" method="post" id="editFrm">
        		<table>  
	            	<tr>	
	                	<td>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>	
                		<td>${ssMV.mId}</td>
                		<td><input type="hidden" name="mId" id="mId" value="${ssMV.mId }"></td>
            		</tr>
            		<tr>
		                <td>&nbsp;</td>
                		<td></td>
            		</tr>
            		<tr>
	                	<td><label for="mNickname">닉네임</label></td>
                		<td><input type="text" class="input_format" name="mNickname" id="mNickname"></td>
            		</tr>
	            	<tr><td>&nbsp;</td></tr>
            		<tr>
		                <td><label for="mBirth">생년월일</label></td>
	                	<td><input type="text" class="input_format" name="mBrith" id="mBrith" placeholder="YYMMDD"></td>
            		</tr>
            		<tr><td>&nbsp;</td></tr>
	            	<tr>
	                	<td><label for="phone">연락처</label></td>
                		<td><input type="text" class="input_format" name="phone" id="phone" placeholder="'-'을 포함하여 입력해주세요"></td>
            		</tr>
            		<tr><td>&nbsp;</td></tr>
            		<tr>
		                <td><label for="intro">한줄소개</label></td>
                		<td><input type="text" class="input_format" name="intro" id="intro"></td>
            		</tr>
            		<tr><td>&nbsp;</td></tr>
		
            		<tr>
                	<td></td>
                	<td><button type="button" id="editInfo">회원정보수정</button></td>
            		</tr>
        		</table>
			</form>        
   		</div>
    </div>

    <div style="display: block"><jsp:include page="/WEB-INF/view/template_footer.jsp"></jsp:include></div>
    
<script>
	$(function() {
		console.log("내 정보");
		console.log("mId: " + $("#mId").val());		
	});
	
	$("#editInfo").on("click", memberEditHandler);
		
	function memberEditHandler(){
		var msg = confirm("회원정보를 수정하시겠습니까? ");
		console.log("회원 정보 수정하기");
		if(msg){
			editFrm.submit();
		} else {
			location.reload();
		}			
	}
</script>
    
    
    
</body>
</html>