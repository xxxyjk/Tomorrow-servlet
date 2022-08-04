<%@page import="kh.semi.tomorrow.member.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdn.ckeditor.com/4.18.0/full/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<!--
	private String bNo;
	private String bTitle;
	private String bContent;
	private int bCnt;
	private Timestamp bDate;
	private String bNy;
	private String mId;
	private String mNickname;
	private int pNo;
-->
<body>
	<form name="frm_sbWrite" action="enroll.do" method="post">
	<div>
		id : <%= session.getAttribute("id") %><br>
	    <input type="text" name="bTitle" placeholder="제목을 입력해주세요">
	</div>
	<div>
		<textarea id="ckeditor" name="bContent"></textarea>
	    <script>
	    	CKEDITOR.replace('ckeditor', {
	    		filebrowserUploadUrl: '${pageContext.request.contextPath}/ckeditorImageUpload.do'
//				enterMode:CKEDITOR.ENTER_BR,
//				shiftEnterMode:CKEDITOR.ENTER_P
	    	});
	    	CKEDITOR.on('dialogDefinition', function(ev){
		           var dialogName = ev.data.name;
		           var dialogDefinition = ev.data.definition;
		         
		           switch (dialogName) {
		               case 'image': //Image Properties dialog
		                   //dialogDefinition.removeContents('info');
		                   dialogDefinition.removeContents('Link');
		                   dialogDefinition.removeContents('advanced');
		                   break;
		           }
		       });
	    </script>
	</div>
	<div>
		<input type="hidden" name="mId" value="<%= session.getAttribute("id") %>">
        <input type="text" name="pNo" placeholder="상품번호">
	</div>
        <button type="submit">게시물 등록</button>
    </form>
</body>
</html>