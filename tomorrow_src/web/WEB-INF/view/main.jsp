<link href="<%= request.getContextPath() %>/resources/css/reset.css" rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" ></script>  	  	
  	<style>       
    @import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');
    #container {           
      position: relative;
      background: url('resources/images/main_bg.jpg')  no-repeat;
      background-size: cover;
      height: 1000px;
    }
    .btn_group {
      position: absolute;
      /* border: 1px solid red; */
      width: 800px;
      bottom: 35%;
      left: 35%;
    }    
    #cmty_btn, #store_btn {      
      margin: 0 50px;         
      width: 220px;
      height: 150px;
      border: 1px solid white;
      border-radius: 220px;     
      font-family: 'Nanum Pen Script', cursive;
      font-size: 1.5em;       
    }
  </style>
</head>
<body>
	<div id="container">
      <div class="btn_group">
        <button type="button" id="cmty_btn" onclick="location.href='storylist';">커뮤니티 페이지 바로가기</button>
        <button type="button" id="store_btn" onclick="location.href='store';">스토어 페이지 바로가기</button>      
      </div>
    </div> 
</body>
</html>