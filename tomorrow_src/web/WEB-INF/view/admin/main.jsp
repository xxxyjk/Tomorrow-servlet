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
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tomorrow : 관리자 상품 페이지</title>
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
  <script>
    $(colorChangeHandler);
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
    
      
  </script>
  <style>
    #container {      
    	margin: 0 auto;
      	width: 1300px;
      	height: 1500px;
    }
    
    #nav_menu {
      float: left;
      width: 20%;
      height: 100%;      
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
      	width: 500px;      
      	top: 150px;
      	left: 4%;
    }
    .store_btn {
    	width: 100px;
      	height: 40px;
      	background: rgb(158, 158, 158);
      	color: white;	
   		border: 1px solid rgb(158, 158, 158);
      	border-radius: 50px; 
      	margin-right: 10px;     
    }

    #funiture, #fabric, #light{
      	margin: 0 10px;
    }

    #guide {
    	/* border: 1px solid black; */
      	position: absolute;
      	top: 110px;
      	left: 4%;
      	font-size: 15px;
      	font-weight: bold;
      	width: 400px;
      	height: 40px;
    }
    
    #prod_container {
    	border: 1px solid #ccc;
    	border-radius: 5px;
    	position: absolute;
      	width: 1000px;
      	height: 700px;

      	top: 15%;
      	left: 4%;
    }   	
  </style>
  <style>

.productImgName {
	width: 200px;
	height: 200px;
	border-radius: 6px;
}

.product_wrapper .proDetail {
	width: 220px;
	padding: 15px 0;
	padding-right: 15%;
	padding-left: 15%;
	margin: 0 40px;
	
	
}

.product_wrapper {
	display: flex;
	flex-wrap: wrap;
	box-sizing: border-box;
	
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

#categoryName{
	font-size: 20px;
	font-weight: 900;
	color: black;
}
.pasing{
	text-align: center;
	margin: 20px 0 5px;
}
.pasing a{
	border: 1px solid #f5f5f5;
	background-color: #f5f5f5;
	padding: 0 5px;
	color: black;
	font-weight: 700;
	border-radius: 3px;
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
    <div id="product_content">
      <div id="category_group">
		<button type="button" id="all" class="store_btn">전체</button>
      	<button type="button" id="furniture" class="store_btn">가구</button>
      	<button type="button" id="fabric" class="store_btn">페브릭</button>
      	<button type="button" id="light" class="store_btn" >조명</button>
      	<input type="hidden" name="ctgry" id="ctgry">                
        
      </div>
      <div id="guide">카테고리를 선택하세요</div>
     <div id="prod_container"> 

<!--      	
      	<div class="product_wrapper">
      	
<c:forEach items="${productList}" var="vo">      	
			<div class="proDetail">
				<div id="p_no">상품번호 ${vo.pNo }</div>
				<div>
					<a href="productDetail?p_no=${vo.pNo }">
						<img src="${vo.productImgName }" class="productImg">
					</a>
				</div>
				<div class="pname">${vo.pName}</div>
				<div class="pbrand">${vo.pBrand }</div>
				<div class="pprice">${vo.pPrice }원</div>				
			</div>
</c:forEach>						
      	</div>   
-->   	    
		<section>
					<article id="productList">						
							<c:set var="cateIdx" value="${pageCateId }"></c:set>
							
						<div class="product_wrapper">
							<c:forEach items="${productList }" var="vo">
								<form class="prdt" action="./productDetail" method="get">
									<input type="hidden" name="p_no" value="${vo.pNo }">

									<div class="proDetail" onclick="clickproDetail(this);">
										<div>
											<img src="${vo.productImgName }" class="productImgName">
										</div>
										<div class="pname">${vo.pName }</div>
										<div class="pbrand">${vo.pBrand }</div>
										<div class="pprice">${vo.pPrice }</div>
									</div>
								</form>
							</c:forEach>
						</div>
						
						<p class="pasing">
							<c:if test="${startPage > 1 }">
								<a href="admain?pageCateId=${pageCateId }&page=${startPage-1 }">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:forEach begin="${startPage }" end="${endPage }" var="p">
								<a href="admain?pageCateId=${pageCateId }&page=${p }">${p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${endPage < totalPageCnt }">
								<a href="admain?pageCateId=${pageCateId }&page=${endPage+1 }">다음</a>
							</c:if>
						</p>
					</article>
		</section>	
      </div>
  	</div>         	     
  </div>
<script>
/*
	$(".store_btn").click(btnHandler);
*/

	console.log("페이지 로딩");
	$("body").show();
	$("button").click(furnitureSelect);
	
	
	function clickproDetail(thisEle) {
		console.log(thisEle);
		console.log($(thisEle).parent());
		var frmEle = $(thisEle).parent().get(0);
		console.log(frmEle);
		frmEle.action = "./productDetail";
		frmEle.method = "get";
		frmEle.submit();
	};
	
	function furnitureSelect() {
		console.log(this);
		var btnIdx = $(this).index();
		console.log(btnIdx);
		$.ajax({
			url : "adProductCtgry",
			type : "post",
			data : {
				pageCateId : btnIdx
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				displayProductList(result, btnIdx);
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
		
		function displayProductList(result, btnIdx) {
			console.log(this);
			var html = "";
			$("#productList").html(""); //

			var pageCateId = result.pageCateId;

			if (pageCateId == 1) {
				cateName = "가구";
			} else if (pageCateId == 2) {
				cateName = "페브릭";
			} else if (pageCateId == 3) {
				cateName = "조명";
			} else {
				cateName = "전체 상품";
			}

	
			html += '<div class="product_wrapper">';
			for (var i = 0; i < result.productList.length; i++) {
				var vo = result.productList[i];
				html += '		<form class="prdt"><input type="hidden" name="p_no" value="'+vo.pNo+'">';
				html += '			<div class="proDetail" onclick="clickproDetail(this);">';
				html += '				<div><img src="'+vo.productImgName+'" class="productImgName"></div>';
				html += '			';
				html += '			';
				html += '				<div class="pname">' + vo.pName + '</div>';
				html += '			';
				html += '			';
				html += '				<div class="pbrand">' + vo.pBrand + '</div>';
				html += '			';
				html += '			';
				html += '				<div class="pprice">' + vo.pPrice + '</div>';
				html += '			</div>';
				html += '		 </form>';
			}

			html += '</div>';
			html += '<p class="pasing">';
			if (result.startPage > 1) {
				html += '		<a href="admain?pageCateId='+pageCateId+'&page=' + result.startPage-1 + '">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			}

			for (var p = result.startPage; p <= result.endPage; p++) {
				html += '		<a href="admain?pageCateId='+pageCateId+'&page=' + p + '">' + p + '</a>&nbsp;&nbsp;&nbsp;&nbsp;';
				
				
			}

			if (result.endPage < result.totalPageCnt) {
				html += '		<a href="admain?&pageCateId='+pageCateId+'&page=' + result.endPage+1 + '">다음</a>';
			}
			html += '</p>';

			$("#productList").html(html); //

		}
/*
	function btnHandler() {
		console.log("btnHandler() 실행");
		var ctgry = $(this).val();
		console.log(ctgry);					
		$("#ctgry").val(ctgry);		
		
		btnFrm.submit();
		/* 
		$.ajax({
			url : "adProductCtgry",
			type : "post",
			data : {
				ctgry : $(this).val()
			},
			success : function(result) {
				console.log(result);
				
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		}); 
		*/

	}

</script>
  
</body>
</html>