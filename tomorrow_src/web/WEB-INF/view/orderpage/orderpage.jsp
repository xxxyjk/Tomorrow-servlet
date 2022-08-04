<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 / 결제 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
* {
	text-decoration: none;
}

.textbox:hover {
	background-color: rgba(0, 0, 0, 0.04);
	border: 2px solid rgb(0, 0, 0);
}

#goPay {
	background-color: rgba(72, 188, 246, 0.897);
}

#goPay:hover {
	background-color: rgb(52, 152, 219);
}

#fillSame {
	color: rgb(77, 77, 255);
	cursor: pointer;
}

#fillSame:hover {
	color: rgba(72, 188, 246, 0.897);
}

ul {
	white-space: nowrap;
	position: relative;
	right: 40px;
}

li {
	display: inline-block;
	border: 1px solid rgba(0, 0, 0, 0.359);
	text-align: center;
}

#card {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_card.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#bank {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_vbank.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#kakao {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_kakaopay.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#toss {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_toss.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#naver {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_naver.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#payco {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_payco.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}

#chai {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/img_chai.png");
	width: 80px;
	height: 100px;
	background-position: top;
	background-repeat: no-repeat;
	background-size: contain;
	position: relative;
	top: 10px;
	cursor: pointer;
}
</style>

<!-- 결제 동의 -->
<script>
	$(document).ready(function() {
		$("#agree01").click(function() {
			$("#agree02").slideToggle();
		});
	});
	$(document).ready(function() {
		$("#agree03").click(function() {
			$("#agree04").slideToggle();
		});
	});
</script>

<!-- 위와 똑같이 채우기 -->
<script>
	function FillSame() {
		document.getElementById("receivePerson").value = document
				.getElementById("orderName").value;

		document.getElementById("phoneRest2").value = document
				.getElementById("phoneRest").value;
	}
</script>

<!-- 모두 체크하기 -->
<script>
	$(allcheck);
	function allcheck() {
		$("#allcheck").click(function() {
			var CheckedAll = $("#allcheck").prop("checked");
			$(".check").prop("checked", CheckedAll);
		});
	}
</script>

<!-- 결제수단 -->
<script>
	$(document).ready(function() {
		$("#card").click(function() {
			$(".card").show(100);
			$(".bank").hide();
			$(".kakao").hide();
			$(".toss").hide();
			$(".naver").hide();
			$(".payco").hide();
			$(".chai").hide();
			$("#card").css("border-color", "yellow");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});

	$(document).ready(function() {
		$("#bank").click(function() {
			$(".card").hide();
			$(".bank").show(100);
			$(".kakao").hide();
			$(".toss").hide();
			$(".naver").hide();
			$(".payco").hide();
			$(".chai").hide();
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "yellow");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});
	$(document).ready(function() {
		$("#kakao").click(function() {
			$(".card").hide();
			$(".bank").hide();
			$(".kakao").show(100);
			$(".toss").hide();
			$(".naver").hide();
			$(".payco").hide();
			$(".chai").hide();
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "yellow");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});
	$(document).ready(function() {
		$("#toss").click(function() {
			$(".card").hide();
			$(".bank").hide();
			$(".kakao").hide();
			$(".toss").show(100);
			$(".naver").hide();
			$(".payco").hide();
			$(".chai").hide();
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "yellow");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});
	$(document).ready(function() {
		$("#naver").click(function() {
			$(".card").hide();
			$(".bank").hide();
			$(".kakao").hide();
			$(".toss").hide();
			$(".naver").show(100);
			$(".payco").hide();
			$(".chai").hide();
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "yellow");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});
	$(document).ready(function() {
		$("#payco").click(function() {
			$(".card").hide();
			$(".bank").hide();
			$(".kakao").hide();
			$(".toss").hide();
			$(".naver").hide();
			$(".payco").show(100);
			$(".chai").hide();
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "yellow");
			$("#chai").css("border-color", "rgba(0, 0, 0, 0.359)");
		});
	});
	$(document).ready(function() {
		$("#chai").click(function() {
			$(".card").hide();
			$(".bank").hide();
			$(".kakao").hide();
			$(".toss").hide();
			$(".naver").hide();
			$(".payco").hide();
			$(".chai").show(100);
			$("#card").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#bank").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#kakao").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#toss").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#naver").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#payco").css("border-color", "rgba(0, 0, 0, 0.359)");
			$("#chai").css("border-color", "yellow");
		});
	});
</script>

<script>
	$(document).ready(function() {
		$(".tprice").hide();
	});
</script>

</head>
<body>
	<div>
		<div><jsp:include page="/WEB-INF/view/template_header.jsp"></jsp:include></div>


		<form name="oderPay" id="orderPay" onsubmit="return validate();">
			<c:set var="vo" value="${memberVo }"></c:set>
			<div id="cartWrap" style="display: flex; margin: 0 10% 0 10%">
				<div id="orderWrap"
					style="width: 60%; padding: 100px 0 200px 50px; min-width: 550px;">

					<h2>주문/결제</h2>
					<h4>주문자</h4>
					<hr style="color: black;">
					<br> <br>

					<table border=0 style="text-align: left;">
						<tr>
							<td>이름</td>
							<td colspan="2"><input type="text" id="orderName"
								name="oName" class="textbox" placeholder="이름"
								value="${vo.mName }"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>
						</tr>

						<tr>
							<td>이메일</td>
							<td colspan="2"><input type="email" placeholder="email"
								name="oEmail" class="textbox"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>

						</tr>
						<tr>
							<td id="orderPhone">연락처</td>
							<td colspan="2"><input type="text" placeholder="핸드폰 번호 입력"
								name="oPhone" id="phoneRest" class="textbox"
								value="${vo.mPhone}"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>

						</tr>
					</table>

					<br> <br> <br>
					<div style="display: flex;">
						<span style="width: 70%;"> <b>배송지</b>
						</span> <span id="fillSame" onclick="FillSame()" style="width: 30%;">
							위와 동일하게 채우기 </span>
					</div>
					<br>
					<hr style="color: black;">
					<br> <br>
					<table border=0 style="text-align: left;">
						<tr>
							<td>배송지명</td>
							<td colspan="2"><input type="text" class="textbox"
								id="oRecieverAddress2"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>
						</tr>
						<tr>
							<td>받는사람</td>
							<td colspan="2"><input type="text" id="receivePerson"
								name="oReciever" class="textbox"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>
						</tr>
						<tr>
							<td>연락처</td>
							<td colspan="2"><input type="text" placeholder="핸드폰 번호 입력"
								name="oRecieverPhone" id="phoneRest2" class="textbox"
								style="height: 25px; width: 200px; margin-left: 20px;"></td>

						</tr>
						<tr>
							<td>주소</td>
							<td colspan="3"><input type="text" class="textbox"
								name="oRecieverAddress"
								style="height: 25px; width: 400px; margin-left: 20px;"></td>
						</tr>

						<tr>
							<td id="orderRequest" colspan="4"><select size="1"
								class="textbox" style="height: 40px; width: 500px;">
									<option value="c1" selected>배송시 요청사항을 선택해주세요</option>
									<option value="c2">경비실에 맡겨주세요.</option>
									<option value="c3">도착하기 전에 연락주세요.</option>
									<option value="c4">현관문 앞에 놓아주세요.</option>
									<option value="c5">안전하게 운전해주세요.</option>
									<option value="c6">벨을 누르지 말아주세요.</option>
							</select></td>

						</tr>
					</table>
					<br> <br> <br>
					<h4>주문상품</h4>
					<hr>
					<br> <br>

					<div id="orderProducts">

						<c:forEach items="${cartVoList}" var="vo">

							<div
								style="width: 90%; padding: 25px;; border-radius: 15px; text-align: right; margin-top: 10px; border: 1px solid black; display: flex;">
								<div style="width: 50%;">
									<img src="<%=request.getContextPath() %>/${vo.productImgName }"
										style="width: 180px; heigth: 180px; margin-right: 100px; border-radius: 15px;">
								</div>

								<div style="width: 50%; text-align: left; font-size: small;">
									<p>
										상품번호:<a href="productDetail?p_no=${vo.pNo }">${vo.pNo } </a>
									</p>
									<p>주문갯수:${vo.cCnt}</p>
									<p>주문옵션:${vo.pSeq}</p>
									<p style="color: blue;">
										<b>상품이름:<a href="productDetail?p_no=${vo.pNo }">${vo.pName}</b>
										</a>
									</p>
									<p>브랜드명:${vo.pBrand}</p>

								</div>
							</div>
						</c:forEach>
						<c:if test="${empty cartVoList }">
							<div
								style="width: 88%; padding: 25px; border-radius: 15px; text-align: center; margin: auto 0; border: 1px solid black; height: 200px;">
								<br> <br> <br> <br>구매하실 상품이 상품이 없습니다. <br>
								<a href=storeproduct?pageCateId=0 style="text-decoration: none"><<구매하러
										가기>></a>
							</div>
						</c:if>
					</div>
					<br> <br> <br>
					<h4>결제수단</h4>
					<hr>

					<div id="payWrap" style="width: 60%;">
						<ul>
							<li id="card">
								<p style="position: relative; top: 50px;">카드</p>
							</li>
							<li id="bank">
								<p style="position: relative; top: 50px;">무통장입금</p>
							</li>
							<li id="kakao">
								<p style="position: relative; top: 50px;">카카오페이</p>
							</li>
							<li id="toss">
								<p style="position: relative; top: 50px;">토스</p>
							</li>
							<li id="naver">
								<p style="position: relative; top: 50px;">네이버페이</p>
							</li>
							<li id="payco">
								<p style="position: relative; top: 50px;">페이코</p>
							</li>
							<li id="chai">
								<p style="position: relative; top: 50px;">차이</p>
							</li>



						</ul>
					</div>
					<div class="card" style="display: none;">
						<select size="1"
							style="height: 40px; width: 610px; margin-top: 10px;">
							<option value="b1" selected>카드를 선택해주세요</option>
							<option value="b2">우리카드</option>
							<option value="b3">KB국민카드</option>
							<option value="b4">롯데카드</option>
							<option value="b5">농협카드</option>
							<option value="b6">현대카드</option>
							<option value="b7">신한카드</option>
							<option value="b8">시티카드</option>
							<option value="b9">하나카드</option>
							<option value="b10">삼성카드</option>
							<option value="b11">카카오페이카드</option>
						</select> <select size="1"
							style="height: 40px; width: 610px; margin-top: 10px;">
							<option value="m1" selected>일시불</option>
							<option value="m2" selected>3개월</option>
							<option value="m3" selected>6개월</option>
							<option value="m4" selected>12개월</option>
						</select>
					</div>

					<div class="card"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); margin-top: 20px; padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>카드 결제 혜택</h3>
						<p>-페이북 마이태그 결제혜택, 5만원 이상 결제 시 3천원 결제일 할인, 5/1~31 (기간 내 1인 1회)</p>
						<p>-마이태그 혜택은 결제 전, 페이북에서 오늘의집 할인 혜택을 태그해야 적용 가능</p>
					</div>
					<div class="kakao"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>카카오페이 결제 혜택</h3>
						<p>-카카오페이 내 NH농협카드로 10만원 이상 결제 시 5% 즉시 할인 (최대 3만원 할인)</p>
						<p>-5/1~15 (기간 내 1회 사용가능)</p>
					</div>
					<div class="toss"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>토스 결제 혜택</h3>
						<p>-토스페이 결제 시 1.5% 캐시백 (최대 3만원), 기간 내 1인 10회, 4/18~5/8</p>
						<p>-삼성, 신한카드 3만원 이상 결제 시, 7% 즉시할인(최대 3,500원), 카드사별 기간 내 1인 1회,
							5/1~31</p>
					</div>
					<div class="naver"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>네이버페이 결제 혜택</h3>
						<p>-네이버페이 결제 기본 1%적립, 포인트 결제 1.5%적립 + 소득공제</p>
						<p>-네이버쇼핑 유입 +1%, 그 외 +0.2%</p>
					</div>
					<div class="payco"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>페이코 결제 혜택</h3>
						<p>-페이코포인트로 결제 시 3% 적립 (한도/횟수 무제한)</p>
						<p>-5/1~31</p>
					</div>
					<div class="chai"
						style="width: 100%; background-color: rgba(0, 0, 0, 0.137); padding: 10px; font-size: small; border-radius: 20px; width: 610px; display: none;">
						<h3>차이결제 혜택</h3>
						<p>-차이 신용카드 전용 부스트 : 5만원 이상 결제 시 15,000원 캐시백, 5/1~31</p>
						<p>-차이 부스트 혜택 : 10만원 이상 결제 시 10,000원 캐시백, 5/1~31</p>
					</div>
				</div>





				<div id="finalPriceWrap"
					style="width: 40%; padding: 70px 100px 0 50px;">
					<div <c:set var="setOpt" value="${pdOpt.optNo }"></c:set>
						style="width: 400px; padding: 20px; border: 2px solid rgb(117, 117, 117); border-radius: 15px;">
						<h3>결제금액</h3>
						
						<div class="tprice">
							<c:set var="tPrice" value="0" />
							<c:forEach items="${cartVoList}" var="vo">
						${vo.pPrice}
						${vo.cCnt }
						<c:set var="tPrice" value="${( tPrice + vo.pPrice ) * vo.cCnt }"></c:set>
							</c:forEach>
						</div>
						
						<br>
						<div style="display: flex; margin-top: 40px;">
							<span style="width: 30%; padding-top: 10px;">결제금액 : </span> <span
								style="width: 40%; text-align: center; font-size: 40px; font-weight: 700; color: rgb(52, 152, 219);">${tPrice }</span>
							<span
								style="width: 20%; padding-top: 10px; text-align: right; font-size: medium;"><b>(원)</b></span><br>
						</div>
						<br>
						<hr>


						<div style="width: 100%;">
							<input type="checkbox" id="allcheck"
								style="height: 15px; width: 15px;"> 아래 내용에 모두 동의합니다.
							(필수) <br> <br> <input type="checkbox" class="check"
								required style="height: 15px; width: 15px;"><span
								id="agree01" style="cursor: pointer;"> 개인정보 수집 허용 및 제 3자
								제공 동의 &nbsp;&nbsp;▼</span><br>
							<p id="agree02" style="display: none;">개인정보 수집 허용 및 제 3자 제공
								동의합니다.개인정보 수집 허용 및 제 3자 제공 동의합니다.개인정보 수집 허용 및 제 3자 제공 동의합니다.개인정보
								수집 허용 및 제 3자 제공 동의합니다.</p>
							<input type="checkbox" class="check" required
								style="height: 15px; width: 15px;"><span id="agree03"
								style="cursor: pointer;"> 결제대행 서비스 이용약관 동의&nbsp;&nbsp;▼</span><br>
							<p id="agree04" style="display: none;">개인정보 수집 허용 및 제 3자 제공
								동의합니다.개인정보 수집 허용 및 제 3자 제공 동의합니다.개인정보 수집 허용 및 제 3자 제공 동의합니다.개인정보
								수집 허용 및 제 3자 제공 동의합니다.</p>
							<input type="checkbox" class="check" required
								style="height: 15px; width: 15px;"><span> 본인은 만
								14세 이상이며, 주문 내용을 확인하였습니다.</span><br> <br>

							<button id="goPay"
								style="margin: 0 auto; width: 100%; height: 40px; border: 0px; border-radius: 13px; color: white; font-size: 15px; cursor: pointer;">

								<div class="tprice">
									<c:set var="tCnt" value="0" />
									<c:forEach items="${cartVoList}" var="vo"> ${vo.cCnt }
						<c:set var="tCnt" value="${tCnt + vo.cCnt }"></c:set>
									</c:forEach>
								</div>
								<b>${tCnt }개 상품 결제하기</b>
							</button>

						</div>


					</div>
				</div>
			</div>
		</form>
	</div>

	<div style="box-sizing: border-box;"><jsp:include
			page="/WEB-INF/view/template_footer.jsp"></jsp:include></div>
</body>



<script>
	function validate() {
		var oEmail = $("input[name='oEmail']").val();
		if (!oEmail) {
			alert("이메일을 입력하세요");
			$("input[name='oEmail']").focus();
			return false;
		}
		var oName = $("input[name='oName']").val();
		if (!oName) {
			alert("성함을 입력하세요");
			$("input[name='oName']").focus();
			return false;
		}
		var oPhone = $("input[name='oPhone']").val();
		if (!oPhone) {
			alert("핸드폰 번호를 입력하세요");
			$("input[name='oPhone']").focus();
			return false;
		}
		var oRecieverAddress = $("input[id='oRecieverAddress2']").val();
		if (!oRecieverAddress) {
			alert("배송지명을 입력하세요");
			$("input[id='oRecieverAddress2']").focus();
			return false;
		}
		var oReciever = $("input[name='oReciever']").val();
		if (!oReciever) {
			alert("받으실 분의 성함을 입력하세요");
			$("input[name='oReciever']").focus();
			return false;
		}
		var oRecieverPhone = $("input[name='oRecieverPhone']").val();
		if (!oRecieverPhone) {
			alert("핸드폰 번호를 입력하세요");
			$("input[name='oRecieverPhone']").focus();
			return false;
		}
		var oRecieverAddress = $("input[name='oRecieverAddress']").val();
		if (!oRecieverAddress) {
			alert("주소를 입력하세요");
			$("input[name='oRecieverAddress']").focus();
			return false;
		}
		return confirm("결제하시겠습니까?");
	}
</script>




</html>