<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        *{margin: 4px 0;}
        .controller{padding: 25px 0;margin: auto;width: 840px;}#wriTitle{text-align: center;background-color: rgb(100, 100, 100);width: 800px;height: 20px;padding: 12px 0;color:white;}table{width: 840px;margin: 25px0;padding: 20px;border-collapse: collapse;}#category{width: 100px;height: 30px;}#title{width: 700px;height: 24px;}textarea{width:800px;height: 400px;}.button{width: 100px;padding: 5px 12px;border: none;background-color: rgb(150, 60, 60);color:white;}button{padding: 5px 12px;background-color: white;border-color: rgb(180, 180, 180);border-width: 1px;}textarea{resize: none;}
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onLoad="focusIt()">
<div class="controller">
    <div id="WriTitle"><b>게시판 수정</b></div>
    <form action="BbsUpdateController.com" method="post" name="inform">
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="bbsTitle" required id="title" value="${bbsupdate.bbsTitle}"></td>
            </tr>
                        <tr>
                <td width="100px">카테고리</td>
                <td><select name="bbsCategory" id="category">
                    <c:if test="${bbsupdate.bbsCategory.equals('잡담')}"><option selected value="잡담">잡담</option></c:if>
                    <c:if test="${bbsupdate.bbsCategory.equals('java')}"><option selected value="java">java</option></c:if>
                    <c:if test="${bbsupdate.bbsCategory.equals('c++')}"><option selected value="c++">c++</option></c:if>
                    <c:if test="${bbsupdate.bbsCategory.equals('python')}"><option selected value="python">python</option></c:if>
                    <c:if test="${bbsupdate.bbsCategory.equals('idl')}"><option selected value="idl">idl</option></c:if>
                    <c:if test="${bbsupdate.bbsCategory.equals('fortran')}"><option selected value="fortran">fortran</option></c:if>
					<c:if test="${bbsupdate.bbsCategory.equals('기타')}"><option selected value="기타">기타</option></c:if>

					<c:if test="${!bbsupdate.bbsCategory.equals('잡담')}"><option value="잡담">잡담</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('java')}"><option value="java">java</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('c++')}"><option value="c++">c++</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('python')}"><option value="python">python</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('idl')}"><option value="idl">idl</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('fortran')}"><option value="fortran">fortran</option></c:if>
                    <c:if test="${!bbsupdate.bbsCategory.equals('기타')}"><option value="기타">기타</option></c:if>
                </select></td>
            </tr>
            <tr>
            <td colspan="2"><textarea name="bbsContent" id="" cols="50" rows="12" required>${bbsupdate.bbsContent}</textarea></td>
        </tr>
        <tr>
            <td align="center"><input type="submit" value="작성" class="button"></td>
            <td align="center"><input type="reset" name="초기화" class="button"></td>
        </tr>
		</table>
		<div style="display:none;">
		<input type="text" name="bbsId" id="" value="${bbsupdate.bbsId}">
		</div>
    </form>
    <div>
        <a href="bbs.com"><button>게시판</button></a>
    </div>
    <div><a href="home.com"><button>홈으로</button></a></div>
</div>
</body>
<script>
function focusIt(){
	document.inform.title.focus();
}
</script>
</html>