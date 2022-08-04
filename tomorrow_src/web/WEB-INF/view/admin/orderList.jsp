<%@page import="kh.semi.tomorrow.admin.model.vo.MemberOrderListVo"%>
<%@page import="java.util.ArrayList"%>
<link href="<%= request.getContextPath() %>/resources/css/reset.css" rel="stylesheet" type="text/css">
<link href="<%= request.getContextPath() %>/resources/css/header.css" rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomorrow : 관리자 주문 내역</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" ></script>
<script>
    $(function() {
      // header jquery
      $("#my_info").hover( function() { 
        // console.log("hover 실행");
        $(".admin_modal").show();
      });      
      
      $(window).on("click", function() {
        // console.log("window 클릭");
        $(".admin_modal").hide();
      });
      
      
    });  
  </script>
<style>
    @import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@400;600&family=Nanum+Pen+Script&display=swap');
    table,  td, th{
	  border-top: 1px solid black;            
      font-family: 'IBM Plex Sans KR', sans-serif;
      /* font-family: 'Nanum Pen Script', cursive; */
	  }
	td {
		display: table-cell;
		vertical-align:middle;
	}	 
    #container {
     margin: 0 auto;
      width: 1300px;
      height: 100%;
    }
    #nav_menu {
      float: left;
      width: 20%;
      height: 850px;
      /* background-color: #ccc; */
    }

    #nav_menu ul {
      margin-top: 100px;      
    }

    #nav_menu li a {
      display: inline-block;
      color: black;
      font-size: 20px;
      font-weight: bold;
      margin: 10px 5px 5px 30px;            
    }   
    
    #order_content {
      float: right;
      position: relative;      
      width: 80%;
      height: 850px;
    }

    #order_info_content {      
      position: absolute;
      top: 13%;      
      left: 10px;
      font-size: 16px;
      font-weight: bold;
    }

    #total_order {
      position: absolute;
      top: 13%;
      left: 90%;
    }

    #order_list {                  
      position: absolute;  
      top: 18%;
      left: 10px;
      width: 900px;
          
      font-size: 13px;
      height: 25px;
      line-height: 25px;      
    }   

    #order_list tr th {      
      font-size: 14px;      
    }   

    #order_list td:not(:first-of-type) {
      text-align: center;    
      line-height: 1rem;     
    }

    .order_box {
      /* border: 1px solid #ccc; */
      display: flex;
      margin: 15px 15px 5px 15px;      
      height: 90px;
    }

    .sumnail {
      flex-flow: row nowrap;
      margin: 6px;
      width: 65px; height: 65px;
      background-color: #ccc;
    }

    .order_info{
      /* border: 1px solid black; */
      flex-flow: row nowrap;
      width: 300px;      
    }
	
    #brand{
      font-size: 12px;
    }   
    #prod_name{
      font-weight: bold;
      font-size: 14px;
    }    
    #prod_opt{
      color: #ccc;
      font-size: 11px;
    } 
	
	#prev_next {    
    	clear: both;  
      	width: 800px;
      	height: 100px;
      	text-align: center; 
      	margin-left: 350px;
      	padding-top: 30px;       	
    }
    
    #prev_next a {
   		color: black;
   		
   	}
  </style>
</head>
<body>
	<jsp:include page="../template_header.jsp"/>
	
	<div id="container">
    <nav id="nav_menu">
      <ul>
        <li><a href="admain">상품 관리</a></li>
        <li><a href="admain" style="font-size:16px">-상품 목록</a></li>
        <li><a href="adProductEnroll" style="font-size:16px">-상품 등록</a></li>
        <li><a href="adProductManage" style="font-size:16px">-상품 수정/삭제</a></li>
        <li><a href="adMemberOrderList">주문 내역 조회</a></li>
        <li><a href="adMemberList">회원 관리</a></li>
        <li><a href="adArticleManage">게시물 관리</a></li>
      </ul>
    </nav>    
    <section id="order_content">
      <p id="order_info_content">주문 내역 조회</p>                    
      <!-- <button type="button" id="total_order" style="width:70px; height:30px;">전체</button>        -->
      <table id="order_list">        
        <tr>
          <th width="300">상품 정보</th>          
          <th width="150">주문일자</th>
          <th width="150">주문 번호</th>
          <th width="150">상품 금액(수량)</th>
          <th width="150">주문한 고객</th>
        </tr>
<%
	ArrayList<MemberOrderListVo> orderlist = (ArrayList<MemberOrderListVo>)request.getAttribute("orderlist");
%>                
<c:forEach items = "${orderlist }" var="vo"> 
        <tr>
          <td>
            <div class="order_box">                                   
              <img src="${vo.productImgName}" alt="img" class="sumnail">
              <ul class="order_info">
                <li id="brand">${vo.pBrand }</li>
                <li id="prod_name">${vo.pName }</li>
              </ul>      
            </div>
          </td>
          <td>${vo.oDate }</td>
          <td>${vo.oNo }</td>
          <td>          	
          	${vo.oTotalPrice}(원)<br>          	
            <span>${vo.oDcnt }개</span>  
          </td>
          <td>${vo.oName }</td>
        </tr>
</c:forEach>            
      </table>
    </section>
        
    <p id="prev_next">
    	<c:if test="${ startPage > 1 }">
			<a href="adMemberOrderList?page=${ startPage-1}">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:forEach begin="${startPage }" end="${endPage }" var="p">
			<a href="adMemberOrderList?page=${ p}">${ p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:forEach>
		<c:if test="${endPage < totalPageCnt }">
			<a href="adMemberOrderList?page=${ endPage+1}">다음</a>
		</c:if>	
    </p>
  </div>
</body>
</html>