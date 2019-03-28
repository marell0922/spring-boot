<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>죄송합니다. ${url }</h1>
 <p>
 	사용자 요청이 갑자기 많아져서
 	서비스에 일시적 장애가 발생하였습니다.
 	잠시후, 다시 시도해 주세요..
 </p>
 <p> 
 	사실 거짓말이에요.. 터졌는데 언제 고칠지 몰라요 
 	그냥 이거 쓰지 마세요. RuntimeException이에요. -Controller
 </p>
 
 <p>
 	=====================================================================
 </p>
 <p style="color:red">
 	 ${exception }
 </p>
</body>
</html>