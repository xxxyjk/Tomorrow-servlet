# :pushpin: tomorrow
## 1. 프로젝트 정보
>#### 1-1. 프로젝트 명: 내일의 집(Tomorrow)
>- 오늘의 집 사이트를 벤치마킹하여 카테고리와 게시판 종류를 축소시킴으로써 전문적이고 편의성 확대를 목표로 설계했습니다.   
>- 사용자간 소통할 수 있는 커뮤니케이션 컨텐츠를 제공함으로써 적극적인 리뷰 활동을 할 수 있습니다.   
>- 스토어 컨텐츠를 통해 다양한 카테고리별 상품 정보들을 한눈에 볼 수 있습니다.   

</br>

>#### 1-2. 기한 및 인원     
>- 제작 기간: 2022년 03월 29일 ~ 2022년 05월 16일    
>- 참여인원 : 총 5명

</br>

## 2. 사용 기술
>#### 2-1. 개발 환경   
 >- **Apache Tomcat Web Server 9.0**   
 Download-url: https://tomcat.apache.org/download-90.cgi   
 ![Aphache-tomcat9 0](https://user-images.githubusercontent.com/98321110/200492022-6f0c5983-ee2b-48f1-8106-29bc249bf85e.PNG)   
 
 >- Visual Studio Code 1.66   
 >- Eclipse IDE for Enterprise Java Developers, Version: 2020-09 (4.17.0)    
 >- Get Started With Oracle Database 11g Express Edition   
  /Oracle SQL Developer, Version:Oracle IDE 17.2.0, Oracle IDE 21.2.1    
 >- listly  
 >- StarUML, Version: 5.0.1   
 >- Operating System: Windows 10 Pro/Home x64
 
>#### 2-2. `Back-end`
  >- Java 11
  >- JSP
  >- JSTL-1.2
  >- gson-2.8.6
  >- cos
  >- ojdbc6
  >- Oracle
  
>#### 2-3. `Front-end`
  >- HTML5
  >- CSS3
  >- JavaScript
  >- jQuery
  >- Ajax
  >- BootStrap

</br>

## 3. ERD 설계
![Tomorrow-ERD](https://user-images.githubusercontent.com/98321110/194283481-271d56cb-414c-4dfb-8e9f-7a7a6b8f7a6c.png)

</br>   

## 4. 핵심 기능
>- 내일의 집 핵심 기능은 게시판에서 다른 사용자들의 상품 리뷰를 통해 
  정보를 얻고 자연스러운 구매로 이어지게 스토어 페이지를 구성한 것입니다.

</br>   

### 4-1. 프로젝트 흐름
![mvc_structure](https://user-images.githubusercontent.com/98321110/194736019-87effc58-9d15-454c-a86b-33e8afa7b331.PNG)

</br>   

### 4-2. 사용자 요청
![user_request_code(1)](https://user-images.githubusercontent.com/98321110/194801075-0e54eeb5-b1b5-4230-bc9c-556a19261928.PNG)   
- **Ajax를 통한 비동기 요청 또는 javascript를 이용한 특정 url로 양식 값 전달**   
   - 사용자 화면에서 발생한 데이터 값들은 Ajax의 비동기방식으로 특정 url에 전달하거나,    
 javascript의 submit함수를 통해 입력한 form 값을 전송방식에 따라 get/post 요청으로 특정 url에 전달합니다.

</br>   

### 4-3. Controller  
![controller_code(1)](https://user-images.githubusercontent.com/98321110/195057862-4259f944-7735-441e-8a96-8b5e8c5b3848.PNG)   
- **사용자 요청 처리와 응답 전송**
  - 사용자가 화면에서 요청 시 Controller에서 그 값을 받고, Service에서 로직 처리한 응답 결과를 다시 View에 응답합니다.
  
</br>

### 4-4. Service
![jdbcTemp_code(1)](https://user-images.githubusercontent.com/98321110/194803035-254d35c2-6412-4fd9-a779-382712b38abf.PNG)
- **Connection Pool**
  - 프로젝트 DB서버와 연결하기 위해 JAVA에서 제공하는 Connection 객체를 싱글톤 패턴으로 생성하여 연결된 커넥션을 나타냈습니다.
  
</br>

### 4-5. Model
![model_code(1)](https://user-images.githubusercontent.com/98321110/194801138-b1df0362-6a67-4af4-bf1a-510f68d4b3a6.PNG)   
- **로직 처리**
  - 예외 처리 없이 사용자의 요청을 정상적으로 수행했다면 DB에 저장 후 결과 값을 다시 Dao - Service - Controller를 거쳐 사용자 화면에 보여줍니다.
  
</br>

## 5. 프로젝트에서 맡은 기능
>- 메인 페이지 및 공통 헤더 및 푸터
>- 관리자 페이지  
  >상품 관리: 상품 조회/등록/수정/삭제   
  >회원 관리: 회원 조회/탈퇴 상태 변경   
  >주문 관리: 회원들의 상품 주문 내역 조회   
  >게시판 관리: 게시판 조회/삭제    
  
</br>

 ### 5-1. 프로젝트 내용
 ![image](https://user-images.githubusercontent.com/98321110/194977471-299f24ae-5d77-4f2b-bd73-16cdfc506bb2.png)   
 - **화면설명:** 관리자 페이지의 상품 수정 컨텐츠에서 수정할 상품의 번호와 상품 상세 번호를 입력한 후 조회 버튼을 클릭하여 상품의 정보를 확인   
 
 ---
 
 ![상품수정_코드구현](https://user-images.githubusercontent.com/98321110/194978696-39a04de6-a355-42c8-af0e-e1765054d3f3.PNG)    
 - **화면설명:** 조회 버튼을 클릭하면 javascript를 통해 이벤트 함수 searchHandler를 실행하여 사용자의 조회 요청으로 전달받은 상품 번호와 상품 상세번호를 지정  한 서블릿에 전달  
 
 ---
 
 ![상품수정구현(2)](https://user-images.githubusercontent.com/98321110/194979887-970d0c62-6b56-4ec9-bbd6-dd0cd571ac2b.PNG)   
 - **화면설명:** 전달받은 상품번호와 상품 상세번호를 DB조회한 처리 결과값을 다시 화면에 전달

 ---

</br>

## 6. 프로젝트 과정에서 생긴 issue
>- MultipartRequest 객체를 사용해 파일 업로드 기능을 구현했는데 다른 팀원들의 pc로 이미지 화면이 보이지 않은 issue가 생김. 여러 번 팀원들과 코드 리뷰를 해본 결과 MultipartRequest를 통해 파일 업로드 시 파일 경로가 각자 로컬 저장소에만 저장되어 공유되지 않기 때문에 다른 ip에서 업로드 한 이미지가 보이지 않았음. 학습목적으로 MultipartRequest 클래스를 적용했기 때문에 파일 서버를 사용하지 않아 이미지 관리 측면에서 다른 사용자에게 상품 이미지가 보이지 않은 issue 발생

</br>

## 7. 참고 사이트
> - 오늘의집: https://ohou.se/
> - oven app: https://ovenapp.io/
> - Bootstrap: https://getbootstrap.com/
> - WYSIWYG - ckeditor5: https://ckeditor.com/ckeditor-5/demo/
> - W3Schools: https://www.w3schools.com/
> - DynamicDummyImageGenerator: https://dummyimage.com/

</br>

## 8. 느낀점
>- 첫 팀 프로젝트 산출물을 통해 웹 설계의 대한 요구사항 분석부터 코드 구현까지 직접 참여함으로써 부족함도 많았고 배운점도 많은 좋은 경험이었습니다.

