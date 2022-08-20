<%@page import="kh.semi.tomorrow.product.model.vo.ProductVo"%>
<link href="<%=request.getContextPath()%>/resources/css/reset.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/header.css"
	rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tomorrow : 상품 수정 페이지</title>
<script src="//cdn.ckeditor.com/4.18.0/full/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
#container {
	margin: 70px auto;
	width: 1000px;
	height: 100%;
}
#content_enroll {
	margin: 0 auto;
	display: flex;
	width: 950px;
}
#product_img {
	flex-flow: row wrap;
	width: 450px;
	height: 450px;
	border: 1px dotted #ccc;
}

#product_enroll_grp {
	flex-flow: row wrap;
	margin-left: 150px;
	width: 450px;
	height: 500px;
}

.enroll_content:not(:first-of-type) {
	margin-top: 25px;
}

.enroll_content>label {
	font-weight: bold;
	font-size: 15px;
}

input[type="text"], select {
	margin-top: 10px;
}
#category, #prod_brand, #prod_name, #prod_price, 
#prod_option, #opt_no, #opt_val, #opt_price, #prod_no,
#opt_name, #opt_value, #opt_cash, #pno, #pSeq {
	width: 300px;
	height: 30px;
	border-radius: 5px;
	border : 2px solid black;
}

#file_btn {
	/* margin: 5px 0 0 150px; */
	/*  
	margin-left: 50px; 
	*/
	/* width: 150px; */
	/* height: 40px; */
	/* border: 1px solid #ccc; */	
}

#product_explanation {
	/* border: 1px solid black; */
	margin-top: 100px;
	width: 150px;
	margin-top: 100px;
    	margin-bottom: 30px;
	font-weight: bold;
	font-size: 18px;
}

#product_content {
	margin-top: 20px;
	width: 1000px;
	height: 950px;
	/* height: 1300px; */
	border: 1px solid #ccc;
	border-radius: 20px;
}

.prod_box {
	display: block;
	width: 650px;
	height: 400px;
	border: 1px dotted #ccc;
	margin: 20px auto;
}

#file_btn_grp {	
	height: 80px;
	margin-top: 170px;	
}

#opt_addition_content {
	position: relative;
	width: 600px;
	height: 50px;
	margin-top: 40px;
}

#opt_explanation {
	position: absolute;
	bottom: 0px;
	width: 100px;
	height: 30px;
	font-weight: bold;
	font-size: 18px;
}

#add_btn {
	position: absolute;
	bottom: 6px;
	left: 18%;
	width: 45px;
	height: 28px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

#option_container {
	border: 1px solid #ccc;
	border-radius: 20px;
	margin: 10px 0 30px 0;
	height: 450px;
}

#option_content {
	margin: 30px 0 0 60px;
	width: 500px;
	height: 400px;
}

#edit_btn {
	position: absolute;
	margin-top: 20px;
	left: 45%;
	width: 200px;
	height: 40px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.underbar {
	margin-left: 0;
	width: 310px;
}

#product_img_info {
	margin-bottom: 15px;
}

#product_img_info tr td {		
	display: table-cell;
	vertical-align:middle;
	height: 30px;
}

#product_img_info input {
	margin-bottom: 10px;
}

.pName {
	font-size: 13px;
	font-weight: bold;
}

#imgEditBtn_content {
	margin-top: 50px;
	margin-left: 38%;
}

#imgEditBtn {
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 200px;
	height: 40px;	
}
</style>
</head>
<body>
	<jsp:include page="../template_header.jsp" />


	<div id="container">
		<form method="post" enctype="multipart/form-data" id="edfrm">
			<section id="content_enroll">
				<img id="product_img">
				<div id="product_enroll_grp">
					<div class="enroll_content">
						<!-- 상품 번호 -->
						<label for="pno" style="font-size: 15px;">조회할 상품 번호를	입력해주세요.</label>
						<button type="button" id="search_btn">조회</button><br> 
						<input type="text" name="pno" id="pno" required placeholder="상품 번호를 입력해주세요.">
						<!-- <input type="hidden" name="pSeq" id="pSeq" > --> 
							<!-- <datalist id="aaa">
								option
							</datalist> -->
					</div>
					
					<div class="enroll_content">
						<label for="pSeq" style="font-size: 15px;">상품 상세번호를 입력하세요.</label><br> 
						<input type="text" name="pSeq" id="pSeq" placeholder="상품 상세번호를 입력해주세요.">
					</div>
					
					
						<div class="enroll_content">
							<!-- 카테고리 종류 -->
							<label for="category" style="font-size: 15px">카테고리 종류를
								입력해주세요.</label><br> 
							<select name="category" id="category" required>
								<option value="0" selected>카테고리 종류</option>								
								<option value="1">가구</option>
								<option value="2">패브릭</option>
								<option value="3">조명</option>
							</select>
							<input type="hidden" name="ctgry" id="ctgry">
							<input type="hidden" name="pFilePath" id="pFilePath">
						</div>
						<div class="enroll_content">
							<label for="prod_brand" style="font-size: 15px;">브랜드명을
								입력해주세요. </label><br> 
							<input type="text" name="prod_brand" id="prod_brand" 	placeholder="브랜드명을 입력해주세요.">
						</div>
						<div class="enroll_content">
							<!-- 상품 이름 -->
							<label for="prod_name" style="font-size: 15px">상품명을
								입력해주세요.</label><br> 
							<input type="text" name="prod_name" id="prod_name"  placeholder="상품명을 입력해주세요.">
						</div>
						<div class="enroll_content">
							<!-- 상품 가격 -->
							<label for="prod_price" style="font-size: 15px">가격을
								입력해주세요.</label><br> 
							<input type="text" name="prod_price" id="prod_price"  placeholder="금액(원)을 입력해주세요.">
						</div>
						<div class="enroll_content">
							<!-- 옵션 번호 -->
							<label for="opt_no" style="font-size: 15px">옵션을 입력해주세요.</label><br>
							<select name="opt_no" id="opt_no">									
								<option value="0">옵션을 선택해주세요.</option>
								<option value="1">색상</option>
								<option value="2">사이즈</option>
								<option value="3">추가상품</option>  								
							</select><br>
							
							<!-- 옵션값 -->
<!--  					<select name="opt_val" id="opt_val">	</select>   -->
							<input type="text" name="opt_val" id="opt_val" placeholder="옵션값을 입력해주세요."> 
						</div>
						<div class="enroll_content">
							<!-- 옵션 추가가격 -->
							<label for="opt_price" style="font-size: 15px">옵션 가격을
								입력해주세요.</label><br> 
							<input type="text" name="opt_price"	id="opt_price" value=0>
						</div>
					
				</div>
			</section>
			<div class="enroll_content">파일 첨부 &nbsp;<input type="file" name="upload" id="file_btn"></div>			
			<div id="file_btn_grp">
				<button type="button" id="edit_btn">상품 수정</button>
			</div>
		</form>			
		<section>
			<div id="opt_addition_content">
				<p id="opt_explanation">옵션 정보</p>					
				<button type="button" id="add_btn">추가</button>
			</div>
			<div id="option_container">
				<div id="option_content">
					<div class="enroll_content">
						<label for="prod_no">상품번호</label>
						<hr class="underbar">
						<input type="text" name="prod_no" id="prod_no"	placeholder="상품번호를 입력해주세요."> <br>
					</div>
					<div class="enroll_content">
						<label for="opt_name">옵션명</label>
						<hr class="underbar">
						<select name="opt_name" id="opt_name" required>			
							<option value="0" selected >옵션을 선택해주세요.</option>
							<option value="1">색상</option>
							<option value="2">사이즈</option>
							<option value="3">추가상품</option> 
						</select> <br>
					</div>
					<div class="enroll_content">
						<label for="opt_value">옵션값</label>
						<hr class="underbar">
						<input type="text" name="opt_value" id="opt_value"	placeholder="옵션값을 입력해주세요."> <br>
					</div>
					<div class="enroll_content">
						<label for="opt_cash">옵션 추가가격</label>
						<hr class="underbar">
						<input type="text" name="opt_cash" id="opt_cash" value="0">
					</div>
				</div>
			</div>
		</section>

		<section>
			<form action="adContentImageEdit.do" method="post" enctype="multipart/form-data" id="imgfrm" >
				<p id="product_explanation">상품 정보</p>
				<hr style="border:1px solid #ccc;">
				<table id="product_img_info">
					<tr>
						<td><label for="pnum" class="pName">상품 번호</label></td>
					</tr>
					<tr>
						<td><input type="text" name="pnum" id="pnum" placeholder="상품 번호를 입력하세요" required></td>
					</tr>
					<input type="hidden" name="pContentPath" id="pContentPath">					
				</table>
				<textarea id="ckeditor" name="pContent" ></textarea>
	    		<script>
	    			CKEDITOR.replace('ckeditor', {
	    				filebrowserUploadUrl: '${pageContext.request.contextPath}/ContentImageUpload.do'
		//				filebrowserUploadUrl: '${pageContext.request.contextPath}/ckeditorImageUpload.do'
		//				enterMode:CKEDITOR.ENTER_BR,
		//				shiftEnterMode:CKEDITOR.ENTER_P
	    			});
	    			CKEDITOR.on('dialogDefinition', function(ev){
		           		var dialogName = ev.data.name;
		           		var dialogDefinition = ev.data.definition;
			         
		           		switch (dialogName) {
		               		case 'image': //Image Properties dialog
		                   		//dialogDefinition.removeContents('info');
		                   		dialogDefinition.removeContents('Link');
		                   		dialogDefinition.removeContents('advanced');
		                   break;
		           		}
		       		});
	    		</script>	
	    		<div id="imgEditBtn_content">
	    			<button type="button" id="imgEditBtn">이미지 수정</button>	
	    		</div>
			</form>
		</section>		
	</div>

	<script>
		$("#search_btn").on("click", searchHandler);
		$("#edit_btn").on("click", editHandler);
		$("#add_btn").on("click", optAddtionHandler);
		$("#imgEditBtn").on("click", productInfoImgHandler);
		
		function searchHandler() {			
			var a = prompt("상품 상세번호를 입력하세요.");			
			console.log("조회한 상품번호: " + $("#pno").val());		
			console.log("상품 상세번호: " + a);
			$("#pSeq").val(a);			

			$.ajax({
				url : "adPdSearch.aj",
				type : "post",
				data : {
					pNo : $("#pno").val()		
					,pSeq : $("#pSeq").val()
				},
				dataType:"json",
				success : function(result) {		
					if(result == null || result.pdt == null) {
						alert("상품 조회에 실패했습니다. 상품번호와 상세번호를 다시 확인해주세요");
						location.reload();
					} else {
						alert("상품이 조회되었습니다.");
						console.log(result);
						console.log("result.productImgName: " + result.productImgName);
						console.log("result.pdt.pSeq: " + result.pdt.pSeq);
						console.log("result.cateId: " + result.cateId);
						console.log("result.pdt.optVal: " + result.pdt.optVal);
						console.log("result.pdt.optName: " + result.pdt.optName);
					
						// 조회한 상품의 값을 입력
						$("#product_img").prop("src", result.productImgName);						
						$("#pFilePath").prop("value", result.productImgName);
						$("#pContentPath").prop("value", result.pContent);
						$("#prod_brand").val(result.pBrand);
						$("#prod_name").val(result.pName);
						$("#prod_price").val(result.pPrice);					
						$("#opt_price").val(result.pdt.optPrice);
						$("#opt_val").val(result.pdt.optVal);	
						$("#pSeq").val(result.pdt.pSeq);
											
						if(result.cateId == 1){
							$("#category").val("1").prop("selected", true);	
						}
						if(result.cateId == 2){
							$("#category").val("2").prop("selected", true);	
						}
						if(result.cateId == 3){
							$("#category").val("3").prop("selected", true);	
						}		
										
						if(result.pdt.optNo == 1){
							$("#opt_no").val("1").prop("selected", true);	
						}
						if(result.pdt.optNo == 2){
							$("#opt_no").val("2").prop("selected", true);	
						}
						if(result.pdt.optNo == 3){
							$("#opt_no").val("3").prop("selected", true);	
						}		
					}

				},
				error : function(request, status, error) {
					console.log(request);
					console.log(status);
					console.log(error);
					//에러페이지 이동					
				}
			});
		}

		function editHandler() {
			var msg = confirm("상품을 수정하시겠습니까?");
			console.log("상품번호: " + $("#pno").val());
			console.log("상품 상세번호: " + $("#pSeq").val());
			console.log("카테고리(가구, 패브릭, 조명)id: " + $("#category").val());
			console.log("브랜드명: " + $("#prod_brand").val());
			console.log("상품명: " + $("#prod_name").val());
			console.log("상풍 가격: " + $("#prod_price").val());
			console.log("옵션번호: " + $("#opt_no").val());
			console.log("옵션값: " + $("#opt_val").val());
			console.log("추가가격: " + $("#opt_price").val());
			
			var str = $("#category").val();
			
			if(msg) {
				console.log("category: " + str);
				$("#edfrm").prop("action", "adProductEdit.do");
				edfrm.submit();				
			} else {
				console.log("수정 취소");
			}
/* 
			if (msg) {
				$.ajax({
					url : "adProductEdit",
					type : "post",
					data : {
						pNo : $("#pno").val(),
						pSeq : $("#pSeq").val(),
						pContent : '상품상세 이미지 경로',
						cateId : $("#category").val(),
						pBrand : $("#prod_brand").val(),
						pName : $("#prod_name").val(),
						pPrice : $("#prod_price").val(),
						optNo : $("#opt_no").val(),
						optVal : $("#opt_val").val(),
						optPrice : $("#opt_price").val()
					},
					success : function(result) {
						console.log(result);
						if (result > 0) {
							alert("상품이 성공적으로 수정되었습니다.");
							location.reload();
						} else {
							console.log(result);
							alert("상품 수정에 실패했습니다. 다시 입력해주세요");
							location.reload();
						}
					},
					error : function(request, status, error) {
						console.log(request);
						console.log(status);
						console.log(error);
						//에러페이지 이동
					}
				});
			} else {
				alert("상품 수정이 취소되었습니다.");
				location.reload();
			} 
*/
		}

		function optAddtionHandler() {
			var msg = confirm("옵션을 추가하시겠습니까?");
			console.log("상품번호: " + $("#prod_no").val());
			console.log("옵션번호: " + $("#opt_name").val());
			console.log("옵션값: " + $("#opt_value").val());
			console.log("옵션가격: " + $("#opt_cash").val());

			if (msg) {
				$.ajax({
					url : "adOptionAddtion.aj",
					type : "post",
					data : {
						pNo : $("#prod_no").val(),
						optNo : $("#opt_name").val(),
						optVal : $("#opt_value").val(),
						optPrice : $("#opt_cash").val()
					},
					success : function(result) {
						if (result > 0) {
							alert("상품이 성공적으로 등록되었습니다.");
							location.href = "admain";
						} else {
							console.log(result);
							alert("상품 등록에 실패했습니다. 다시 입력해주세요");
							location.reload();
						}
					},
					error : function(request, status, error) {
						console.log(request);
						console.log(status);
						console.log(error);
						confirm.log("error발생");
						//에러페이지 이동		
						//location.href="adProductEnroll";
					}
				});
			} else {
				alert("옵션 추가를 취소하였습니다.");
				location.reload();
			}
		}
		
		function productInfoImgHandler() {
			var msg = confirm("이미지를 수정하시겠습니까?");			
			if(msg){				
				imgfrm.submit();
			}
			else {
				console.log("이미지 수정 취소");
			}
		}
		
	</script>
</body>
</html>