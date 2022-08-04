<%@ page import="kh.semi.tomorrow.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	MemberVo ssMV = (MemberVo)session.getAttribute("ssMV");
%>
<!DOCTYPE html>
<header>
    <div id="navigation_content">   
<c:if test="${ssMV.mNickname == '관리자'}">
   	  <div id="modal_content">
        <div class="admin_modal">
          <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" fill="currentColor" id="admin_img" class="bi bi-gear" viewBox="0 0 16 16">
            <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
            <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
          </svg>
          <a href="admain" id="admin_modal_btn">관리자 페이지로 가기</a>
        </div>
      </div>
</c:if>
      <div id="navigation_primary_menu">
        <div id="navigation_bar_logo">        
            <a href="#" id="logo" onclick="location.href='main';">내일의 집</a>
            <div id="navigation_bar_menu">
              <a href="storylist"id="cmty_btn" >커뮤니티</a>
              <a href="store"id="store_btn" >스토어</a>
            </div>      
        </div>
      </div>        
      <div id="navigation_primary_right">
        <div id="navigation_bar_right">
          <a href="#" style="color: black;" id="cart" onclick="location.href='cartlist'; ">
            <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
          </a>         
<c:if test="${empty ssMV}">
          <a href="login" class="navigation_bar_right_btn" id="login_btn" style="left:50px; bottom:5px;">로그인</a>
</c:if>
<c:if test="${not empty ssMV}">
          <a href="logout" class="navigation_bar_right_btn" id="logout_btn" style="left:50px; bottom:5px;">로그아웃</a>
</c:if>
<c:if test="${empty ssMV}">
          <a href="join" class="navigation_bar_right_btn" id="join_btn">회원가입</a>
</c:if>          
<c:if test="${not empty ssMV}">  
          <a href="memberinfo" style="color: black;" id="my_info">
            <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
              <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
              <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
            </svg>  
          </a>  
</c:if>
          <button type="button" id="write_btn" onclick="location.href='storyenroll'; ">글쓰기</button>
        </div>      
      </div> 
    </div>    
    <script>
    { /* 로그인 & 로그아웃 jQuery */
    	/* $("#login_btn").click(function() {
    		location.href="login";
    	});
		$("#logout_btn").click(function() {
			location.href="logout";
    	}); */
		
		// header jquery
        $("#my_info").hover( function() { 
          // console.log("hover 실행");
          $(".admin_modal").show();
        });      
        $(window).on("click", function() {
          // console.log("window 클릭");
          $(".admin_modal").hide();
        });
    }
    
    
    </script>
 </header>
