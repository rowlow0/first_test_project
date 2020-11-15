<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@page import="java.sql.Timestamp" %>
    <%@page import="com.dao.BbsDao" %>
    <%@page import="java.text.SimpleDateFormat" %>
    <%@page import="java.util.List" %>
    <%@page import="com.sql.BbsDto" %>
    <%@page import="javax.servlet.http.*" %>
    <%@page import="java.net.*" %>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#"> <!-- check chrome console -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
*{margin:0;padding:0;}
    .controller1{
        padding: 25px 0;margin: auto;width: 840px;text-align: center; height:auto;
    }
            .controller2{
        padding: 25px 0;margin: auto;width: 840px;text-align: center; height:auto;}
    
    table{width: 840px;padding: 10px 0;border-collapse: collapse; }th{background-color: rgb(100, 100, 100);color:white;}button{margin: 4px 0;padding: 10px 0;width: 840px;background-color: rgb(255, 80, 80);color:white;border:none;}a{text-decoration: none;color:black;}a:hover{text-decoration: underline;}.active{text-decoration: underline;}
    
</style>
</head>
<body>
<div class="controller1">
    <form action="bbs.com" method="get">
        <select name="tNumber" onchange="this.form.submit();">
            <option value="ten"<c:if test="${tNumber eq'ten'}">selected</c:if>>10개</option>
            <option value="fit"<c:if test="${tNumber eq'fit'}">selected</c:if>>50개</option>
            <option value="hund"<c:if test="${tNumber eq'hund'}">selected</c:if>>100개</option>
        </select>
    </form>
    </div>
<div class="controller1">
    <table>
        <tr>
            <th width="100px">카테고리</th>
            <th width="40px">번호</th>
            <th width="150px">제목</th>
            <th>내용</th>
            <th width="100px">작성자</th>
            <th width="150px">날짜</th>
            <th width="40px">조회</th>
        </tr>
        
                    <c:forEach var="b" items="${list}">
            <fmt:formatDate value="${b.getBbsDate()}" var="strDate" type="date" pattern="yyyy-MM-dd hh-mm-ss"></fmt:formatDate>
        <tr>
            <td>${b.getBbsCategory()}</td>
            <td>${b.getBbsId()}</td>
            <td><b><a href="bbsview.com?bbsId=${b.getBbsId()}">${b.getBbsTitle()}</a></b></td>
            <td>${b.getBbsContent()}</td>
            <td>${b.getId()}</td>
			<td>${strDate}</td>
            <td>${b.getBbsHit()}</td>
        </tr>
</c:forEach>
    </table>
    <div id="resultDIV"></div>
              <c:if test="${empty list}"><p>글이 존재하지 않습니다.<p></c:if>
    </div>
          <div class="controller1">
                        <c:if test="${empty list}"><a href="javascript:history.back();">뒤로가기</a></c:if>
                        <c:if test="${!empty list}">
<table>
<tr>
                <td><a href="javascript:PageMove(${paging.prevPageNo10})">&lt;&lt;</a></td>
        <td><a href="javascript:PageMove(${paging.prevPageNo})">&lt;</a></td>
        <c:if test="${paging.pageNo>10 }">
        <td><a href="javascript:PageMove(${paging.firstPageNo})">${paging.firstPageNo }..</a></td>
        </c:if>
        <c:if test="${paging.pageNo ne paging.finalPageNo }">
              <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <td class="active"><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:when>
                      <c:otherwise>
                        <td><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              </c:if>
                      <c:if test="${paging.pageNo eq paging.finalPageNo && paging.pageNo >10}">
              <c:forEach var="i" begin="${paging.startPageNo-9}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <td class="active"><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:when>
                      <c:otherwise>
                        <td><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              </c:if>
                                    <c:if test="${paging.pageNo < 10}">
              <c:forEach var="i" begin="1" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <td class="active"><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:when>
                      <c:otherwise>
                        <td><a href="javascript:PageMove(${i})">${i}</a></td>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              </c:if>
                      <td><a href="javascript:PageMove(${paging.finalPageNo})">..${paging.finalPageNo }</a></td>
        <td><a href="javascript:PageMove(${paging.nextPageNo})">&gt;</a></td>
                <td><a href="javascript:PageMove(${paging.nextPageNo10})">&gt;&gt;</a></td>
        </tr>
      </table>
      </c:if>
                        </div>
                        
            
          <div class="controller1">
    <p>
        <a href="write.com"><button>글쓰기</button></a><br>
        <a href="home.com"><button>HOME</button></a>
    </p>
    <form action="bbs.com" method="get">
        <select name="select">
            <option value="category">카테고리</option>
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="id">작성자</option>
        </select>
        <input type="search" name="search">
       <input type="submit" value="보내기">
    </form>
    </div>
    <script src="/asasTest3/ajaxUtil.js"></script>
    <script>
    function PageMove(page){
        <c:url value="/bbs.com" var="url">
        <c:param name="tNumber" value="${tNumber}"/>
        <c:param name="select" value="${select}" />
        <c:param name="search" value="${search}" />
        <c:param name="page"/>
      </c:url>
        location.href = "${url}"+page}

</script>
</body>
</html>