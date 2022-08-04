<%@page import="kh.semi.tomorrow.product.model.vo.ProductVo"%>
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
<title>Tomorrow : 관리자 상품 관리</title>
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
</head>
<style>
    #container {      
      margin: 0 auto;
      width: 1300px;
      height: 1400px;
    }
    #nav_menu {
      float: left;
      width: 20%;
      height: 100%;
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
    
    #product_content {
      float: right;
      position: relative;      
      width: 80%;
      height: 100%;
      /* background-color: burlywood; */
    }    

    #category_group {
      position: absolute;      
      top: 100px;
      left: 4%;
    }
    .store_btn {
      width: 100px;
      height: 40px;
      background: rgb(158, 158, 158);
      color: white;

      border: 1px solid rgb(158, 158, 158);
      border-radius: 50px;
    }
    
    #funiture, #fabric, #light{
      margin: 0 10px;
    }

    #total {
      /* border: 1px solid black; */
      position: absolute;
      top: 110px;
      left: 4%;
      font-size: 15px;
      font-weight: bold;
      width: 100px;
      height: 40px;
    }

    #prod_mange {
      /* border: 1px solid black; */
      position: absolute;
      width: 300px;
      height: 40px;
      top: 110px;
      left: 76%;
    }

    #prod_container {
      border: 1px solid #ccc;
      border-radius: 5px;
      position: absolute;
      width: 900px;
      height: 1200px;

      top: 12%;
      left: 4%;      
    }
    
    .productImg {
    	width: 200px;
		height: 200px;
		border-radius: 6px;
    }
    
    .product_wrapper .proDetail {
    	width: 220px;
		padding: 15px;
		margin: 15px;
    }
    
    .product_wrapper {
		display: flex;
		flex-wrap: wrap;		
	}
    
    #p_no {
		font-size: 12px;
		font-family: bold;
		color: black;
	}
        
    #prev_next {    
    	clear: both;  
      	width: 800px;
      	height: 50px;
      	text-align: center; 
      	margin-left: 350px;
      	padding-top: 30px;       	
    }
    
    .pname {
		padding: 10px 20px 10px 0;
		font-size: 17px;
		font-weight: 500;
	}

	.pbrand {
		font-size: 13px;
		font-weight: 900;
		color: #656e75;
	}

	.pprice {
		float: right;
		padding-right: 20px;
		color: black;
		font-size: 20px;
		font-weight: 900;
	}
	
    #prev_next a {
   		color: black;   		
   	}
  </style>
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
    <section id="product_content">
      <div id="total">전체 상품</div> 
      <div id="prod_mange">
        <button type="button" id="prod_alt" style="width:70px; height:30px;">수정</button>
        <button type="button" id="prod_del" style="width:70px; height:30px;">삭제</button>
      </div>  
<%
	ArrayList<ProductVo> productList = (ArrayList<ProductVo>)request.getAttribute("productlist");
%> 
      <form action="adProductDel" method="post" id="prod_container">
      	<div class="product_wrapper">
<c:forEach items="${productList}" var="vo">      	
			<div class="proDetail">
				
					<div id="p_no"><input type="checkbox" name="selectPno" value="${vo.pNo }">상품번호 ${vo.pNo }</div>
				
				<div>
					<a href="productDetail?p_no=${vo.pNo }">
						<img src="${vo.productImgName }" class="productImg">
					</a>
				</div>
				<div class="pname">${vo.pName}</div>
				<div class="pbrand">${vo.pBrand }</div>
				<div class="pprice">${vo.pPrice }원</div><br>				
			</div>
</c:forEach>   
		</div>	   
      </form>
    </section>
    	<p id="prev_next">
    		<c:if test="${ startPage > 1 }">
				<a href="adProductManage?page=${startPage-1}">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="p">
				<a href="adProductManage?page=${p}">${ p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${endPage < totalPageCnt }">
				<a href="adProductManage?page=${endPage+1}">다음</a>
			</c:if>	
    	</p>
  </div>
  
  
<script>
$(colorChangeHandler);
$(productHandler);

function colorChangeHandler() {
    $("#furniture").on("click", function() {              
      $("#furniture").css({"color":"black"});
      $("#fabric").css({"color":"white"});
      $("#light").css({"color":"white"});
    });
    $("#fabric").on("click", function() {        
      $("#fabric").css({"color":"black"});
      $("#furniture").css({"color":"white"});
      $("#light").css({"color":"white"});
    });
    $("#light").on("click", function() {        
      $("#light").css({"color":"black"});
      $("#furniture").css({"color":"white"});
      $("#fabric").css({"color":"white"});
    });
} 
 
 function productHandler() {
	$("#prod_alt").click(function() {
	    console.log("수정 클릭 후 상품 수정 페이지로 이동");
	    location.href="adProductEdit.do";
	});

	$("#prod_del").click(function() {
		var strChk = [];
		 
		 $("input:checkbox[name=selectPno]:checked").each(function() {
				strChk.push($(this).val());			
		 });
		 
		 var cnf = confirm("게시글을 삭제하시겠습니까?");
			if(cnf) {
				prod_container.submit();		
			} else {			
				location.href="adProductManage";
			}
	});	

}
</script>
</body>
</html>