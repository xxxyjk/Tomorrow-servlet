<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
#productdetail {
	margin: 15px 15%;
	width: 75%;
}

#detail_img {
	padding: 0 10px;
	width: 45%;
	height: 650px;
	float: left;
}

#detail_img img {
	width: 100%;
	height: 100%;
	border-radius: 6px;
}

#detailcontent {
	padding: 0 10px;
	width: 45%;
	height: 700px;
	float: left;
}

.pbrand {
	margin-top: 30px;
	font-size: 15px;
	color: #656e75;
	font-weight: 700;
}

.pname {
	font-size: 22px;
	line-height: 33px;
	min-height: 43px;
	margin: 30px 92px 0 0;
}

.pprice {
	color: #35c5f0;
	font-size: 30px;
	font-weight: 900;
	margin: 30px 0;
}

#optselect {
	font-weight: 700;
}

.optName {
	font-size: 14px;
	line-height: 21px;
	color: #828c94;
	margin: 30px 0 0;
}

.optName {
	margin-top: 15px;
	width: 100px;
}

#price {
	margin-top: 15px;
	font-size: 30px;
	font-weight: 700;
	color: #000;
}

.pp {
	margin: 30px 0;
	text-align: right;
	flex: 0 0 auto;
	font-weight: 700;
	color: #000;
	text-align: right;
}

.orderBtn {
	width: 48%;
	float: right;
	display: inline-block;
	box-sizing: border-box;
	border: 1px solid #35c5f0;
	background: none;
	font-weight: 700;
	text-align: center;
	border-radius: 4px;
	background-color: #35c5f0;
	border-color: #35c5f0;
	color: #fff;
	padding: 13px 10px 14px;
	font-size: 17px;
	line-height: 26px;
	display: inline-block;
}

.cartBtn {
	width: 48%;
	display: inline-block;
	box-sizing: border-box;
	border: 1px solid #35c5f0;
	background: none;
	font-weight: 700;
	text-align: center;
	border-radius: 4px;
	background-color: #fff;
	border-color: #35c5f0;
	color: #35c5f0;
	padding: 13px 10px 14px;
	font-size: 17px;
	line-height: 26px;
	display: inline-block;
}
</style>
<style>
.up img {
	width: 70px;
	position: fixed;
	bottom: 20px;
	right: 200px;
	z-index: 999;
}

#productInfoBtns {
	clear: both;
	padding: 3px 0;
	width: 100%;
	height: 35px;
	background-color: rgb(217, 209, 209);
	text-align: center;
}

#productInfoBtns button {
	color: black;
	background-color: rgb(217, 209, 209);
	border: none;
	padding: 0 30px;
	font-size: 20px;
	font-weight: 700;
	line-height: 30px;
	padding: 0 30px;
}

#productInform {
	width: 100%;
}

#information {
	margin: 10px 25%;
	width: 70%;
	font-weight: 700;
}

#ship, #refund {
	margin: 50px 25%;
	width: 60%;
	font-size: 20px;
	font-weight: 600;
	line-height: 50px;
	margin-bottom: 20px;
}

#shipInfo {
	margin: 10px 25%;
	width: 60%;
	line-height: 30px;
}
</style>
<style>
#story {
	font-size: 20px;
	font-weight: 600;
	margin-bottom: 20px;
}

.wrap_content {
	margin: 10px 25%;
	position: relative;
	width: 60%;
}

.container_content {
	display: flex;
	flex-wrap: wrap;
}

.story_one {
	position: relative;
	padding-left: 25px;
	padding-right: 25px;
	padding-bottom: 40px;
	box-sizing: border-box;
	flex: 0 0 30%;
	width: 100%;
}

.story_one_header {
	margin: 0 0 15px;
}

.story_writer {
	font-size: 15px;
	line-height: 19px;
	font-weight: 600;
}

.story_image {
	width: 100%;
}

.wrap_story_thumbnail {
	padding-bottom: 100%;
	border-radius: 6px;
	position: relative;
	overflow: hidden;
}

.story_thumbnail {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 100%;
	transform: translate(-50%, -50%);
	transition: transform .2s;
}

.story_bcnt {
	position: absolute;
	bottom: 15px;
	right: 15px;
	font-size: 13px;
	color: #fff;
	z-index: 10;
}

.story_title {
	margin: 15px 0 0;
	font-size: 15px;
	line-height: 22px;
}
</style>
</head>
<body>
	<div class="main_wrap">
		<div class="wrap_header" id="top">
			<jsp:include page="../template_header.jsp" />
		</div>
		<section>
			<section id="productdetail">
				<c:set var="vo" value="${selectProduct }"></c:set>
				<section id="detail_img">
					<img src="${vo.productImgName }">
				</section>
				<section id="detailcontent">
					<form id="frmInf">
						<div class="pbrand">${vo.pBrand }</div>
						<div class="pname">${vo.pName }</div>
						<div class="pprice">${vo.pPrice }<a>(원)</a>
						</div>

						<div>
							<p id="optselect">옵션 선택</p>
						</div>
						<c:if test="${not empty vo.pdvo }">
							<div>
								<c:forEach items="${vo.pdvo }" var="pdOpt" varStatus="status">
									<c:choose>
										<c:when test="${status.index eq 0}">
											<c:set var="setOpt" value="${pdOpt.optNo }"></c:set>
											<div class="optName">${pdOpt.optName }</div>
											<select name="option_${setOpt }" class="form-select">
												<option selected value="${pdOpt.pSeq }">${pdOpt.optVal }</option>
										</c:when>
										<c:when test="${status.index ne 0 and setOpt ne pdOpt.optNo}">
											<c:set var="setOpt" value="${pdOpt.optNo }"></c:set>
											</select>
											<div class="optName">${pdOpt.optName }</div>
											<select name="option_${setOpt }" class="form-select">
												<option selected value="${pdOpt.pSeq }">${pdOpt.optVal }</option>
										</c:when>
										<c:otherwise>
											<option value="${pdOpt.pSeq }">${pdOpt.optVal }</option>
										</c:otherwise>
									</c:choose>
									<option disabled hidden class="optPrice"
										value="${pdOpt.optPrice }">${pdOpt.optPrice }</option>
									<option disabled hidden class="pSeq" value="${pdOpt.pSeq }">${pdOpt.pSeq }</option>
								</c:forEach>


								</select>
							</div>
						</c:if>
						<p class="pp">
							<span>주문금액 </span><span id="price"></span><span>(원)</span>
						</p>
						<input type="hidden" name="pNo" value="${vo.pNo}">
						<button type="button" class="cartBtn">장바구니</button>
						<button type="button" class="orderBtn">바로구매</button>
					</form>
				</section>
			</section>



		</section>

		<section id="productInfoBtns">
			<button type="button" onclick="location.replace('#productInform')">상품정보</button>
			<button type="button" onclick="location.replace('#story')">리뷰</button>
			<button type="button" onclick="location.replace('#ship')">배송/환불</button>
		</section>

		<section id="productInform">
			<div id="information">${vo.pContent }</div>
			<div class="wrap_content">
				<section>
					<p id="story">리뷰 (오늘의 스토리)</p>
					<div class="container_content">
						<c:forEach items="${listStoryBoard }" var="voi">
							<div class="story_one">
								<div class="story_one_header">
									<div class="story_writer">${voi.bWriter }</div>
									<div class="story_writer_intro">${voi.mIntro }</div>
								</div>
								<div class="story_image">
									<div class="wrap_story_thumbnail">
										<a class="story_link" href="storyread?bno=${voi.bNo }"> <img
											class="story_thumbnail"
											src="${pageContext.request.contextPath }/${voi.bImgPath }">
											<span class="story_bcnt"> 조회수 ${voi.bCnt } </span>
										</a>
									</div>
								</div>
								<div class="story_title">${voi.bTitle }</div>
							</div>
						</c:forEach>
					</div>
			</div>


			<table id="shipInfo">
				<tr>
					<td id="ship">배송관련 안내</td>
				</tr>
				<tr>
					<td>배송</td>
					<td>일반택배</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td>3,500원(70,000원 이상 구매시 무료 배송)</td>
				</tr>

				<tr>
					<td>도서산간 추가 배송비</td>
					<td>5,000원</td>
				</tr>
				<tr>
					<td>배송불가 지역</td>
					<td>배송불가 지역이 없습니다.</td>
				</tr>
				<tr>
					<td id="refund">교환/환불 안내</td>
				</tr>
				<tr>
					<td>반품 배송비</td>
					<td>5,000원 (최초 배송비가 무료인 경우 10,000원 부과)</td>
				</tr>
				<tr>
					<td>교환배송비</td>
					<td>10,000원</td>
				</tr>

				<tr>
					<td>보내실 곳</td>
					<td>서울특별시 중구 남대문로 120 대일빌딩 2F, 3F</td>
				</tr>

			</table>

		</section>
		<section class="up">
			<img src="resources/images/product_up.png"
				onclick="location.replace('#top')">
		</section>
		<div class="wrap_footer">
			<jsp:include page="../template_footer.jsp" />
		</div>
	</div>


	<script>
		$(".cartBtn").click(function() {
			console.log(this);
			frmInf.action = 'cartEnroll';
			frmInf.method = 'post';
			frmInf.submit();
		});
	</script>
	<script>
		$(".orderBtn").click(function() {
			console.log(this);
			frmInf.action = 'orderEnroll';
			frmInf.method = 'post';
			frmInf.submit();
		});
	</script>




	<script>
		calPrice();
		function calPrice() {
			var basicPrice = Number('${vo.pPrice }');
			if (isNaN(basicPrice)) {
				basicPrice = 0;
			}
			var optPrice = 0;
			$("select").each(function(index, element) {
				var checkedEle = $(element).children(":checked");
				console.log(checkedEle);
				var optOnePrice = Number(checkedEle.next().text());
				console.log(optOnePrice);
				if (isNaN(optOnePrice)) {
					optOnePrice = 0;
				}
				optPrice += optOnePrice;
			});
			var totalPrice = basicPrice + optPrice;
			console.log(totalPrice);
			$("#price").html(totalPrice);
		}
	</script>
	<script>
		$("select").change(function() {
			console.log(this);
			calPrice();
		});
	</script>



</body>
</html>