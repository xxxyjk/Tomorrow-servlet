<link href="<%=request.getContextPath()%>/resources/css/reset.css" rel="stylesheet" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdn.ckeditor.com/4.18.0/full/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery-animate-css-rotate-scale.js"></script>
<script src="https://ucarecdn.com/libs/widget/3.x/uploadcare.min.js"></script>
<script src="https://ucarecdn.com/libs/widget-tab-effects/1.x/uploadcare.tab-effects.min.js"></script>
	<style>
        button {
            cursor: pointer;
        }
        .wrap_content {
            margin: 0 auto;
            width: 800px;
        }
        .story_guide {
            margin: 20px 0;
            border-radius: 5px;
            border: 1px solid #dadce0;
            box-shadow: 0 2px 4px 0 rgb(234 235 239 / 80%);
        }
        .story_guide_button {
            padding: 25px 30px;
            display: flex;
            align-items: center;
            width: 100%;
            border: none;
            background: none;
            box-sizing: border-box;
            text-align: left;
            border-radius: 5px;
        }
        .story_guide_button:hover {
            background-color: #f7f8fa;
        }
        .guide_button_text {
            flex: 1;
        }
        .guide_title {
            margin-left: 10px;
            font-size: 15px;
            font-weight: 700;
            line-height: 26px;
        }
        .guide_sub_title {
            margin-left: 8px;
            font-size: 13px;
            font-weight: 400;
            line-height: 26px;
            color: #a4acb3;
        }
        .button_icon_svg2 {
            font-size: 18px;
            transition: transform .2s;
        }
        .open_guide {
            display: none;
            padding: 15px 30px 30px;
            font-size: 15px;
        }
        .open_guide ul {
            list-style: inside;
            font-size: 22px;
        }
        .open_guide li {
            margin: 12px 0 12px 10px;
            font-size: 15px;
            line-height: 25px;
        }
        .story_thumbnail {
            text-align: center;
        }
        #before_file_preview {
            width: 500px;
            height: 500px;
            border-radius: 10px;
        }
        #file_preview {
            height: 500px;
            display: none;
        }
        .preview_img {
            width: 500px;
            height: 500px;
            border-radius: 10px;
        }
        .file_upload {
            margin-top: 10px;
        }
        .file_upload .file_name {
            display: inline-block;
            height: 40px;
            padding: 0 10px;
            vertical-align: middle;
            font-size: 15px;
            border: 1px solid #eaebef;
            width: 600px;
            color: #828c94;
        }
        .file_upload .file_label {
            display: inline-block;
            padding: 10px;
            color: white;
            vertical-align: middle;
            background-color: #828c94;
            cursor: pointer;
            font-size: 15px;
            font-weight: 700;
            width: 100px;
            height: 20px;
            margin-left: 10px;
            border-radius: 8px;
        }
        .file_upload input[type="file"] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }
        .story_title {
            margin: 45px 0 0;
        }
        .input_title {
            padding: 15px 0;
            font-size: 35px;
            line-height: 30px;
            width: 100%;
            border: none;
            border-bottom: 1px solid #eaebef;
        }
        .story_content {
            margin: 40px 0 0;
        }
        .story_pno {
            margin: 40px 0 0;
        }
        .story_submit {
            margin: 40px 0 20px;
            text-align: center;
        }
        .story_submit_button {
            width: 300px;
            height: 50px;
            font-size: 15px;
            background: #35c5f0;
            border: 1px solid #35c5f0;
            border-radius: 5px;
            line-height: 20px;
            font-weight: 700;
            color: white;
        }
        .story_submit_button:hover {
            background: #72D6F4;
            border: #72D6F4;
        }
        input[type=text]:focus {
            outline: 0;
        }
    </style>
    <style>
        .uploadcare--widget__button.uploadcare--widget__button_type_open {
            background-color: #f8f8f8;
            color: #484848;
            border: solid 1px #d1d1d1;
        }
    </style>
    <script>
        uploadcare.registerTab('preview', uploadcareTabEffects)
        UPLOADCARE_LOCALE = "ko"
        UPLOADCARE_LOCALE_TRANSLATIONS = {
            buttons: {
                choose: {
                    files: {
                        one: '파일 선택'
                    }
                }
            }
        }
        UPLOADCARE_PUBLIC_KEY = "183400fad159d76bdf53"
        UPLOADCARE_TABS = "file url gdrive gphotos"
        UPLOADCARE_EFFECTS = "crop"
    </script>
    <script>
        $(function(){
            console.log("글쓰기");
            $("body").show();
            $(".story_guide_button").click(openGuide);
        });

        function openGuide() {
            if ($(".open_guide").is(":hidden")) {
                $(".open_guide").slideDown();
                $(".button_icon_svg2").rotate('180deg');
            } else {
                $(".open_guide").slideUp();
                $(".button_icon_svg2").rotate('360deg');
            }
        }
    </script>
</head>
<body>
	<div class="main_wrap">
		<div class="wrap_header">
			<jsp:include page="../storyboardWrite_header.jsp"/>
		</div>
	</div>
	<div class="wrap_content">
        <div class="story_guide">
            <button type="button" class="story_guide_button">
                <div class="guide_button_icon1">
                    <svg class="button_icon_svg1" width="25" height="25" viewBox="0 0 25 25" preserveAspectRatio="xMidYMid meet">
                        <rect width="25" height="25" fill="#6ADFC4" rx="10"></rect>
                        <g fill="#FFF" transform="translate(7 8)">
                            <rect width="7" height="1.5" rx=".8"></rect>
                            <rect width="11" height="1.5" y="4" rx=".8"></rect>
                            <rect width="11" height="1.5" y="8" rx=".8"></rect>
                        </g>
                    </svg>
                </div>
                <div class="guide_button_text">
                    <span class="guide_title">
                        스토리 작성 가이드
                    </span>
                    <span class="guide_sub_title">
                        원활한 스토리 작성을 위해 꼭 읽어주세요
                    </span>
                </div>
                <div class="guide_button_icon2">
                    <svg class="button_icon_svg2" width="1em" height="1em" viewBox="0 0 16 16" preserveAspectRatio="xMidYMid meet">
                        <path fill="currentColor" fill-rule="evenodd" d="M2.87 4L1.33 5.5 8 12l6.67-6.5L13.13 4 8 9z">
                        </path>
                    </svg>
                </div>
            </button>
            <div class="open_guide">
                <ul>
                    <li>간단한 자기 소개 후 집에 관한 이야기를 들려주세요.</li>
                    <li>사진이 있으면 좋아요. (손그림도 OK)</li>
                    <li>사진 속 제품 정보를 본문에 최대한 적어주세요. (제품분류/브랜드/제품명 등)</li>
                    <li>사진 첨부 시 용량은 장당 <b>최대 10MB</b>까지 업로드할 수 있고, jpg, png 포맷을 지원합니다.</li>
                    <li>대표사진 권장 사이즈 : 1920 x 1920, 최소 1400 x 1400 (1:1 비율)</li>
                    <li>정보를 많이 입력할수록 검색 결과에 많이 노출되어 조회수가 올라갑니다.</li>
                    <li>게시글은 관리자에 의해 <b>변경/삭제</b>될 수 있습니다.</li>
                    <li>게시글 작성과 이미지 업로드 시, 타인의 지식재산권을 침해하지 않도록 유의해주세요.</li>
                </ul>
            </div>
        </div>
        <form name="frm_sbWrite" action="storyenroll.do" method="post" enctype="multipart/form-data">
            <hr>
            <input type="hidden" name="bNo" value="${bvo.bNo }">
            <div class="story_thumbnail">
	            <c:if test="${not empty bvo.bImgPath }">
					<img id="before_file_preview" src="${pageContext.request.contextPath }/${bvo.bImgPath }" width="300"><br>
				</c:if>
				<input type="hidden" name="bFilePath" value="${bvo.bImgPath }">
				<div id="file_preview"></div>
                <div class="file_upload">
                    <input class="file_name" value="대표사진 첨부" placeholder="대표사진 첨부">
                    <lable class="file_label" for="input_file" onclick="$('#input_file').trigger('click');">사진찾기</lable>
                    <input type="file" id="input_file" name="upload"><br>
                </div>
                <script>
                function readInputFile(input) {
                    if(input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('#file_preview').html("<img src="+ e.target.result +" class='preview_img'>");
                        }
                        reader.readAsDataURL(input.files[0]);
                    }
                }
                $("#input_file").on('change', function(){
                    readInputFile(this);
                    var fileName = $("#input_file").val();
                    $(".file_name").val(fileName);
                    $("#before_file_preview").hide();
                    $("#file_preview").show();
                });
            	</script>
            </div>
            <div class="story_title">
                <input type="text" class="input_title" name="bTitle" value="${bvo.bTitle }" required>
            </div>
            <div class="story_content">
                <textarea id="ckeditor" name="bContent" required>${bvo.bContent }</textarea>
                <script>
                    CKEDITOR.replace('ckeditor', {
                    	height: 500,
                        filebrowserUploadUrl: '${pageContext.request.contextPath}/ckeditorImageUpload.do'
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
            </div>
            <div>
                <input type="hidden" name="mId" value="${ssMV.mId }">
            </div>
            <div class="story_pno">
                <input type="hidden" name="pNo" value="${bvo.pNo }">
            </div>
            <hr>
            <div class="story_submit">
                <button type="submit" class="story_submit_button">게시물 수정</button>
            </div>
        </form>
    </div>
</body>
</html>