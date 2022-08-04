--MEMBER
desc member; 
select * from member;
select M_ID, M_NAME, M_NICKNAME, M_BIRTH, M_PHONE, M_DATE, M_NY from member;
select M_ID, M_NAME, M_NICKNAME, M_BIRTH, M_PHONE, M_DATE from member where M_NY = 'Y';

desc orders;
desc order_detail;
select * from orders;
select * from order_detail;
select * from product_detail;

-- 주문 내역 조회 
select P.P_CONTENT, P.P_BRAND, P.P_NAME, D.OPT_VAL  
from PRODUCT P JOIN PRODUCT_DETAIL D ON P.P_NO = D.P_NO;    


-- 고객의 주문번호
select p.p_content, p.p_brand, p.p_name, o.o_no, od.O_detail_cnt,
o.o_date, o.O_TOTAL_PRICE, o.O_NAME  
    from orders o join order_detail od on o.O_NO= od.O_NO
    join product p on p.p_no = od.P_NO                   
    order by o.o_no desc    
;
-- 페이징 처리 한 고객 주문 내역 조회
select * 
    from( select rownum r, t1.* 
        from (select i.product_img_name, p.p_brand, p.p_name, o.o_no, od.O_detail_cnt,
                o.o_date, o.O_TOTAL_PRICE, o.O_NAME  
                from orders o join order_detail od on o.O_NO= od.O_NO
                join product p on p.p_no = od.P_NO
                join product_img i on i.p_no = p.p_no
                order by o.o_no desc  
        ) t1 
    )
;
-- 주문 내역 조회
select i.product_img_name, p.p_brand, p.p_name, o.o_no, od.O_detail_cnt,
                o.o_date, o.O_TOTAL_PRICE, o.O_NAME  
                from orders o join order_detail od on o.O_NO= od.O_NO
                join product p on p.p_no = od.P_NO
                join product_img i on i.p_no = p.p_no
                order by o.o_no desc
;
select * from product_img;



-- 총 주문 개수 
select  
    count(o_name) 
    from orders o join order_detail od using(o_no)         
    group by o_name
;
select * from orders o join order_detail od using(o_no);
    
select * from orders;

-- MEMBER INSERT문 
INSERT INTO MEMBER VALUES ('admin001', 'admin001', '관리자', '관리자', '991231', '작업 테스트 중', sysdate, '010-0000-0000', '0', 'Y');
INSERT INTO MEMBER VALUES ('test001', 'test123', '김민준', '민준쓰', '970714', null, sysdate, '010-4528-4792', '1', 'Y');
INSERT INTO MEMBER VALUES ('test002', 'test123', '이서연', 'tjdus123', '941109', '내꿈은내집마련', sysdate, '010-6298-1087', '1', default);
INSERT INTO MEMBER VALUES ('test003', 'test123', '박도윤', '도개걸윤', '000726', '초보인테리어', sysdate, '010-7028-2153', '1', default);
INSERT INTO MEMBER VALUES ('test004', 'test123', '정지우', '지우123', '020103', '중급인테리어', sysdate, '010-1853-7767', '1', default);
INSERT INTO MEMBER VALUES ('test005', 'test123', '최민서', '민서123', '921018', '고수인테리어', sysdate, '011-8103-3924', '1', default);
INSERT INTO MEMBER VALUES ('test006', 'test123', '유석재', 'sjy5384', '840105', null, sysdate, '010-9910-5384', '1', default);
INSERT INTO MEMBER VALUES ('test007', 'test123', '안지영', '귀빨간사춘기', '960610', null, sysdate, '010-1515-1818', '1', default);
INSERT INTO MEMBER VALUES ('test008', 'test123', '이수영', '수영국대', '871106', '자취12년고수', sysdate, '011-5810-1090', '1', default);
INSERT INTO MEMBER VALUES ('test009', 'test123', '김다미', '다리미', '950417', null, sysdate, '010-9552-5384', '1', default);
INSERT INTO MEMBER VALUES ('test010', 'test123', '최우식', '우식한남자', '900326', '최우식입니다.', sysdate, '010-4219-0591', '1', 'Y');
INSERT INTO MEMBER VALUES ('test011', 'test123', '김고은', '그립다도깨비', '941203', '인테리어 독학중', sysdate, '010-3583-1902', '1', default);
INSERT INTO MEMBER VALUES ('test012', 'test123', '남주혁', 'namju', '920805', null, sysdate, '010-5211-9284', '1', default);
INSERT INTO MEMBER VALUES ('test013', 'test123', '김태리', '태리태리', '960415', null, sysdate, '010-4512-3488', '1', default);
INSERT INTO MEMBER VALUES ('test014', 'test123', '박민영', 'min0', '910328', 'null', sysdate, '010-9386-1257', '1', default);
desc member;

select * from member;
update member set m_nickname = ?, m_birth= ?, m_phone= ?, m_intro=? where m_id = ?;
update member set m_nickname ='장첸님' where m_id = 'cu1234';
update member set m_ny= 'Y' where m_id = 'test009';
update member set m_ny= 'Y' where m_id ='test010';

--CART
-- 장바구니 구입 전
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test001', 1, '3, 6', 1 , default);
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test001', 2, '8, 10', 3,  default);
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test001', 3, '15, 20', 2, default);
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test001', 4, '22, 25', 5, default);

-- 장바구니 구입 후
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test003', 1, '3, 6', 1, 'Y');
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test003', 2, '8, 10', 3, 'Y');
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test003', 3, '15, 20', 2, 'Y');
INSERT INTO CART VALUES (SEQUENCE_CART_C_NO.nextval, 'test003', 4, '22, 25', 5, 'Y');

--ORDERS
select * from member;
select * from orders;
select * from order_detail;
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test002', sysdate, '이서연', 'test001@naver.com', '010-6298-1087', '이서연', '경기도 군포시 오금동 871-1 삼익소월아파트 373-1803', '010-6298-1087', 92900+30000 +39800+50000);
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test004', sysdate, '정지우', 'test004@gmail.com', '010-1853-7767', '신하준', '전주시 덕진구 송천동 신일ⓐ 104/1507', '010-8049-1783', 92900+30000 +39800+50000 +173900+40000);
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test005', sysdate, '최민서', 'test005@yahoo.com', '011-8103-3924', '최민서', '고양시 일산구 백석동 1316번지 현대밀라트1차 B동515호', '011-8103-3924', 39800+50000 +173900+40000 +19900);
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test001', sysdate, '김민준', 'test001@nate.com', '010-4528-4792', '주채원', '서울시 송파구 방이동212-8 코오롱아파트105-405', '010-3319-7841', 19900);
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test007', sysdate, '안지영', 'test007@daym.net', '010-1515-1818', '안지영', '서울특별시 마포구 독막로6길 15 2F 쇼파르엔터테인먼트', '010-1515-1818', 19900);
INSERT INTO ORDERS VALUES(SEQUENCE_ORDERS_O_NO.nextval, 'test013', sysdate, '김태리', 'test013@naym.net', '010-4512-3488', '김태리', '서울특별시 용산구 독서당로 122-1 포트힐빌딩 ', '010-4512-3488', 119000);

       
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 1, 1, '3, 6', 1);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 1, 2, '8, 10', 3);

INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 2, 1, '3, 6', 1);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 2, 2, '8, 10', 3);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 2, 3, '15, 20', 2);

INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 3, 2, '8, 10', 3);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 3, 3, '15, 20', 2);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 3, 4, '22, 25', 5);

INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 4, 4, '22, 25', 5);
--
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 5, 9, '25, 30', 5);
INSERT INTO ORDER_DETAIL VALUES(SEQUENCE_ORDER_DETAIL_O_D_SEQ.nextval, 6, 8, '25, 30', 1);

commit;



--SELECT CART
SELECT * FROM CART WHERE C_NY = 'N' AND M_ID = 'test001';

--SELECT ORDER_DETAIL