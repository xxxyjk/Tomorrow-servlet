<link href="<%=request.getContextPath()%>/resources/css/reset.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
section {
	width: 80%;
	float: right;
}

article {
	border: 1px solid black;
	padding: 20px;
	margin-bottom: 10px;
}

nav {
	width: 20%;
	float: left;
	padding-left: 10px;
	box-sizing: border-box;
}

table tr td {
	border: 1px solid black;
}

footer {
	clear: both;
}
</style>
<style>
.wrap_content{
	margin: 30px 15%;
}
nav div{
	font-size: 18px;
}
nav button {
	display:flex;
	flex-direction:column;
	font-size: 20px;
	font-weight: 900;
	margin: 15px;
	border: none;
	background-color: white;
}
nav button:hover{
	color: #35c5f0;
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
<script>
	$(function() {
		console.log("페이지 로딩");
		$("body").show();
		$("nav>button").click(furnitureSelect);
	});

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
			url : "CategoryServlet.ax",
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

			html += '<h3 id="categoryName">' + cateName + '</h3>';

			html += '<hr>';
			html += '<div class="product_wrapper">';
			for (var i = 0; i < result.selectAllProduct.length; i++) {
				var vo = result.selectAllProduct[i];
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
				html += '		<a href="storeproduct?pageCateId='+pageCateId+'&page=' + result.startPage-1 + '">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;';
			}

			for (var p = result.startPage; p <= result.endPage; p++) {
				html += '		<a href="storeproduct?pageCateId='+pageCateId+'&page=' + p + '">' + p + '</a>&nbsp;&nbsp;&nbsp;&nbsp;';
				
				
			}

			if (result.endPage < result.totalPageCnt) {
				html += '		<a href="storeproduct?&pageCateId='+pageCateId+'&page=' + result.endPage+1 + '">다음</a>';
			}
			html += '</p>';

			$("#productList").html(html); //

		}
	}
</script>
</head>
<body>
	<div class="main_wrap">
		<div class="wrap_header">
			<jsp:include page="../template_header.jsp" />
		</div>
		<div class="wrap_content">
			<div class="content">
				<nav>
					<button type="button" id="all">전체 상품</button>
					<button type="button" id="furniture">가구</button>
					<button type="button" id="pabric">페브릭</button>
					<button type="button" id="light">조명</button>


				</nav>
				<section>
					<article id="productList">
						<h3 id="categoryName">
							<c:set var="cateIdx" value="${pageCateId }"></c:set>
							<c:choose>
								<c:when test="${cateIdx==1 }">가구</c:when>
								<c:when test="${cateIdx==2 }">페브릭</c:when>
								<c:when test="${cateIdx==3 }">조명</c:when>
								<c:otherwise>전체 상품</c:otherwise>
							</c:choose>

						</h3>

						<hr>
						<div class="product_wrapper">
							<c:forEach items="${selectAllProduct }" var="vo">
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
								<a href="storeproduct?pageCateId=${pageCateId }&page=${startPage-1 }">이전</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:forEach begin="${startPage }" end="${endPage }" var="p">
								<a href="storeproduct?pageCateId=${pageCateId }&page=${p }">${p }</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${endPage < totalPageCnt }">
								<a href="storeproduct?pageCateId=${pageCateId }&page=${endPage+1 }">다음</a>
							</c:if>
						</p>
					</article>

				</section>


			</div>
			<div class="wrap_footer">
				<jsp:include page="../template_footer.jsp" />
			</div>
		</div>

	</div>
</body>
</html>