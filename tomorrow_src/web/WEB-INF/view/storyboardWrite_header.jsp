<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');
        a {
        text-decoration: none;
        } 
        #logo {
        font-family: 'Nanum Pen Script', cursive;
        font-size: 2.5rem;
        color: black;
        }
        #navigation_content {      
        position: relative;
        border-bottom: 1px solid #ccc;      
        height: 90px;  
        /* max-width: ; */
        } 
        #navigation_primary_menu {
        position: absolute;
        width: 500px;
        height: 80px;
        /* left: 300px; */
        left:17%;
        }
        #navigation_bar_logo {  
        position: absolute;  
        box-sizing: border-box;
        width: 200px;              
        font-size: 30px;
        font-weight: bold;
        color: black;
        text-align: center;     
        top: 25px;     
        }
        @media screen and (max-width: 1500px) {
            #navigation_primary_menu { 
                left: 12%;
            }
        }
        @media screen and (max-width: 1200px) {
            #navigation_primary_menu { 
                left:100px;
            }
        }
    </style>
</head>
<header>
	<div id="navigation_content">
        <div id="navigation_primary_menu">
            <div id="navigation_bar_logo">
                <a href="#" id="logo" onclick="location.href='main';">내일의 집</a>
            </div>
        </div>
    </div>
</header>
</html>