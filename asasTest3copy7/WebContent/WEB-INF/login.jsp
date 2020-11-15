<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onLoad="focusIt()">
<c:if test="${sessionID!=null}">
    <script>
        alert("이미 로그인 중입니다");
        location.href="home.com";
    </script>
</c:if>
<c:if test="${loginResult==-1||loginResult==0}">
    <script>
        alert("아이디 혹은 비밀번호가 틀렸습니다.");
    </script>
</c:if>
<div class="container">
    <h5>로그인 페이지</h5>
    <hr>
    <form action="login.com" method="post" name="inform">
        <input type="text" name="id">
        <input type="password" name="pw">
        <input type="submit" value="login">
            </form>
        <button onclick="location.href='home.com';">Home</button>
    <hr>
    <a href="signUp.com"><input type="button" value="회원가입"></a>
    </div>
    <script>
    function focusIt(){
        document.inform.id.focus();
    }
    </script>
</body>
</html>