<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>msg</title>
</head>
<body>

<script>
var msg = '${msg}';	
if(msg != ''){		
	alert(msg);
	location.replace("cartlist");
}
	</script>
</body>
</html>