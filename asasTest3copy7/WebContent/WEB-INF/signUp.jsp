<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onLoad="focusIt()">
<c:if test="${joinResult==0}">
    <script>
        alert("아이디 중복");
    </script>
</c:if>
<h5>회원가입 페이지</h5>
<hr>
<form action="signUp.com" method="post" name="inform">
<input type="text" name="id">
<input type="password" name="pw">
<input type="email" name="email">
<input type="text" name="name">
<input type="submit" value="post">
</form>
<script>
    function focusIt(){
        document.inform.id.focus();
    }
    </script>
</body>
</html>