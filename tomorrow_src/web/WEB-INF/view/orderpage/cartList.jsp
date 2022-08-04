
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/reset.css"
	rel="stylesheet" type="text/css">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartlist</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
#allrevoke:hover {
	color: blue;
}

#goPay {
	background-color: rgba(72, 188, 246, 0.897);
}

#goPay:hover {
	background-color: rgb(52, 152, 219);
}
</style>


<style>
* {
	text-decoration: none;
}

#cartDelBtn {
	color: rgb(77, 77, 255);
	cursor: pointer;
}

#cartDelBtn:hover {
	color: rgba(72, 188, 246, 0.897);
}
</style>

<!-- 모두체크하기  -->
<script>
	$(allcheck);
	function allcheck() {
		$("#allcheck").click(function() {
			var CheckedAll = $("#allcheck").prop("checked");
			$(".checkbox").prop("checked", CheckedAll);
		});
	}
</script>


<script>
	function goDelete() {
		var strChk = [];

		$("input:checkbox[name=selectPno]:checked").each(function() {
			strChk.push($(this).val());
		});

		var cnf = confirm("상품을 삭제하시겠습니까?");
		if (cnf) {
			del_container.submit();
		} else {
			location.href = "cartlist";
		}
	}
</script>

<!-- 모두 해제하기 -->
<!-- <script>
	$(allrevoke);
	function allrevoke() {
		$("#allrevoke").click(function() {
			$(".checkbox").prop("checked", function(index, value) {
				$(this).prop("checked", !value);
			});
		});
	}
</script> -->
<script>
	$(document).ready(function() {
		$(".tprice").hide();
	});
</script>

</head>
<body>
	<div><jsp:include page="/WEB-INF/view/template_header.jsp"></jsp:include></div>
	<div id="cartWrap" style="display: flex; margin: 0 10% 0 10%;">
		<div id="orderWrap"
			style="width: 60%; padding: 100px 50px 100px 50px; min-width: 350px;">

			<div id="allSelect" style="width: 100%; display: flex;">
				<span style="width: 75%; margin-left: 45px"> <label><input
						type="checkbox" id="allcheck"> 모두 선택</span></label> <span id="cartDelBtn"
					onclick="goDelete()"
					style="width: 25%; cursor: pointer; text-align: left;"> 선택한
					상품삭제</span>
			</div>

			<div class="orderitems"
				style="border: 0px solid black; border-radius: 15px; width: 100%; padding: 10px;">

				<form action="cartdel" method="post" id="del_container">
					<c:forEach items="${cartVoList}" var="vo">
						<div
							style="width: 92%; padding: 25px; border-radius: 15px; text-align: right; border: 1px solid black; margin: 15px; display: flex;">

							<label style="font-size: small;"><input type="checkbox"
								id="check" class="checkbox" name="selectPno" value="${vo.cNo }"
								style="margin-rigth: 20px;"> 주문번호: ${vo.cNo } </label>

							<div style="width: 40%;">
								<img src="<%=request.getContextPath() %>/${vo.productImgName }"
									style="width: 150px; heigth: 150px; margin-right: 70px; margin-left: 20px; border-radius: 15px;">
							</div>


							<div
								style="width: 40%; text-align: left; font-size: medium; margin-top: 3px;">
								<p style="margin-top: 8px;">상품번호:${vo.pNo}</p>
								<p style="margin-top: 8px;">주문갯수:${vo.cCnt}</p>
								<p style="margin-top: 8px;">주문옵션:${vo.pSeq}</p>
								<p style="color: blue; margin-top: 8px;">
									<b>상품이름:<a href="productDetail?p_no=${vo.pNo }">${vo.pName}</a></b>
								</p>
								<p style="margin-top: 8px;">브랜드명:${vo.pBrand}</p>
								<p style="margin-top: 8px;">가격:${vo.pPrice * vo.cCnt}</p>

							</div>
						</div>
					</c:forEach>
				</form>
				<c:if test="${empty cartVoList }">
					<div
						style="width: 88%; padding: 25px; border-radius: 15px; text-align: center; margin: auto 0; border: 1px solid black; margin: 15px; height: 200px;">
						<br> <br> <br> <br> <br> <br>선택된 상품이
						없습니다.
					</div>
				</c:if>



			</div>
		</div>


		<div id="finalPriceWrap" style="width: 40%; padding: 100px 0 0 50px;">
			<div
				style="border: 2px solid rgb(110, 110, 110); border-radius: 15px; width: 70%; padding: 30px; min-width: 300px;">


				<div class="tprice">
					<c:set var="tPrice" value="0" />
					<c:forEach items="${cartVoList}" var="vo">
						${vo.pPrice}
						${vo.cCnt }
						<c:set var="tPrice" value="${( tPrice + vo.pPrice ) * vo.cCnt }"></c:set>
					</c:forEach>
				</div>


				<div style="display: flex; margin-top: 40px;">
					<span style="width: 30%; padding-top: 10px;">결제금액 : </span> <span
						style="width: 40%; text-align: center; font-size: 40px; font-weight: 700; color: rgb(52, 152, 219);">${tPrice }</span>
					<span
						style="width: 20%; padding-top: 10px; text-align: right; font-size: medium;"><b>(원)</b></span><br>
				</div>

				<button onclick="location.href='orderpage'" id="goPay"
					style="margin: 40px 0 0 0; width: 100%; height: 40px; border: 0px; border-radius: 13px; color: white; font-size: 15px; cursor: pointer;">


					<div class="tprice">
						<c:set var="tCnt" value="0" />
						<c:forEach items="${cartVoList}" var="vo"> ${vo.cCnt }
						<c:set var="tCnt" value="${tCnt + vo.cCnt }"></c:set>
						</c:forEach>
					</div>
					<b>${tCnt }개 상품 결제하기</b>
				</button>
			</div>
			<br>



		</div>


	</div>
	<div style="display: block"><jsp:include
			page="/WEB-INF/view/template_footer.jsp"></jsp:include></div>




</body>
</html>