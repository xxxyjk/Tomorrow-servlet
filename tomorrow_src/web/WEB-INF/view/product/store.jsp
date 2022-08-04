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
<title>store</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<style>
.content_keyword p, .content_hit p, .content_cate p {
	font-size: 20px;
	font-weight: bold;
}

.content_keyword, .content_hit, .content_cate {
	margin: 30px 15%;
}

.content_cate button {
	background-color: white;
	border: 0px;
	margin: 0 6%
}

.cate_buttons {
	margin: auto;
	width: 100%
}

.cate_buttons button:hover {
	color: #35c5f0;
}

.cate_buttons p {
	font-size: 15px;
}

.pcontent {
	width: 200px;
	height: 200px;
	border-radius: 6px;
}

.product_wrapper .proDetail {
	width: 220px;
	margin: 20px;
}

.product_wrapper {
	display: flex;
	flex-wrap: wrap;
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
</style>
<style>
/* 배너 슬라이드 */
* {
	box-sizing: border-box
}

.bannerSlide {
	max-width: 1920px;
	position: relative;
	margin: auto;
}

.bannerSlides {
	display: none;
}

.prev, .next {
	cursor: pointer;
	position: absolute;
	top: 50%;
	width: auto;
	margin-top: -22px;
	padding: 16px;
	color: white;
	font-weight: bold;
	font-size: 18px;
	transition: 0.6s ease;
	border-radius: 0 3px 3px 0;
	user-select: none;
}

.next {
	right: 0;
	border-radius: 3px 0 0 3px;
}

.prev:hover, .next:hover {
	background-color: rgba(0, 0, 0, 0.8);
}

.dot {
	cursor: pointer;
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbb;
	border-radius: 50%;
	display: inline-block;
	transition: background-color 0.6s ease;
}

.active, .dot:hover {
	background-color: #717171;
}

.fade {
	-webkit-animation-name: fade;
	-webkit-animation-duration: 1.5s;
	animation-name: fade;
	animation-duration: 1.5s;
}

@
-webkit-keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}

}
@
keyframes fade {
	from {opacity: .4
}

to {
	opacity: 1
}
</style>
<script>
	function clickproDetail(thisEle) {
		console.log(thisEle);
		console.log($(thisEle).parent());
		var frmEle = $(thisEle).parent().get(0);
		console.log(frmEle);
		frmEle.action = "./productDetail";
		frmEle.method = "get";
		frmEle.submit();
	};
</script>

</head>
<body>
	<div class="main_wrap">
		<div class="wrap_header">
			<jsp:include page="../template_header.jsp" />
		</div>
		<div class="wrap_content">
			<section class="content_ad">
				<div class="bannerSlide">
					<div class="bannerSlides fade">
						<img src="./upload/images/banner1.jpg" alt="" style="width: 100%;">
					</div>

					<div class="bannerSlides fade">
						<img src="./upload/images/banner2.jpg" alt="" style="width: 100%;">
					</div>

					<div class="bannerSlides fade">
						<img src="./upload/images/banner3.jpg" alt="" style="width: 100%;">
					</div>

					<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
						class="next" onclick="plusSlides(1)">&#10095;</a>
				</div>
			
			</section>


			<section class="content_hit">
				<p>인기상품</p>
				<div class="product_wrapper">
					<c:forEach items="${selectAllProduct }" var="vo">
						<form class="prdt" action="./productDetail" method="get">
							<input type="hidden" name="p_no" value="${vo.pNo }">
							<div class="proDetail" onclick="clickproDetail(this);">
								<div>
									<img src="${vo.productImgName }" class="pcontent">
								</div>
								<div class="pname">${vo.pName }</div>
								<div class="pbrand">${vo.pBrand }</div>
								<div class="pprice">${vo.pPrice }</div>
							</div>
						</form>
					</c:forEach>
				</div>
			</section>

			<section class="content_cate">
				<p>카테고리</p>
				<div class="cate_buttons">
					<button type="button" id="store_btn"
						onclick="location.href='storeproduct?pageCateId=0';">
						<img src="./resources/images/cate_all.jpg">
						<p>전체</p>
					</button>

					<button type="button" id="furni_btn"
						onclick="location.href='storeproduct?pageCateId=1';">
						<img src="./resources/images/cate_furniture.jpg">
						<p>가구</p>
					</button>

					<button type="button" id="pabric_btn"
						onclick="location.href='storeproduct?pageCateId=2';">
						<img src="./resources/images/cate_pabric.jpg">
						<p>페브릭</p>
					</button>

					<button type="button" id="light_btn"
						onclick="location.href='storeproduct?pageCateId=3';">
						<img src="./resources/images/cate_light.jpg">
						<p>조명</p>
					</button>
				</div>
			</section>

		</div>
		<div class="wrap_footer">
			<jsp:include page="../template_footer.jsp" />
		</div>
	</div>
	<script>
		var slideIndex = 1;
		showSlides(slideIndex);

		function plusSlides(n) {
			showSlides(slideIndex += n);
		}

		function currentSlide(n) {
			showSlides(slideIndex = n);
		}

		function showSlides(n) {
			var i;
			var slides = document.getElementsByClassName("bannerSlides");
			var dots = document.getElementsByClassName("dot");
			if (n > slides.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = slides.length
			}
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
		}
	</script>
	<script>
	var myIndex = 0;
	carousel();

	function carousel() {
		var i;
		var x = document.getElementsByClassName("bannerSlides");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		myIndex++;
		if (myIndex > x.length) {
			myIndex = 1
		}
		x[myIndex - 1].style.display = "block";
		setTimeout(carousel, 2500); 
	}
</script>

</body>
</html>
