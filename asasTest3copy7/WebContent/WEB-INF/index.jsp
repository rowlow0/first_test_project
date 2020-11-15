<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>home1</title>
</head>
<body>
<P>INDEX 출력! and</P><hr>
<a href="bbs.com?page=1"><button>게시판</button></a>
<c:if test="${sessionID==null}">
	<a href="login.com"><button>로그인</button></a><br>
</c:if>
<c:if test="${sessionID!=null}">${sessionID} 로그인 중 <br>
<a href="logout.com"><button>로그아웃</button></a><br>
    
</c:if><br>
</body>
</html>