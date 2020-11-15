<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-8"); 
    String cp = request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<input type="text" id="su1">
 
<select id="oper">
    <option value="hap">더하기</option>
    <option value="sub">빼기</option>
    <option value="mul">곱하기</option>
    <option value="div">나누기</option>
</select>
 
<input type="text" id="su2">
 
<input type="button" value=" = " onclick="sendIt();"/><br/>
 
<!-- <div id="resultDIV" style="display: none;"></div> -->
<!-- 결과를 = 아래 하얀부분에 띄움.  id는 사용자 정의 -->
 
<!-- 이렇게 테이블 형태 안에 결과값을 출력할 수도 있음 -->
<table border="1" width="600">
<tr height="30">
<td></td>
<td></td>
</tr>
<tr height="30">
<td></td>
<td><div id="resultDIV" style="display: none;"></div></td>
</tr>
</table>
 
</body>

<script type="text/javascript" src="<%=cp%>/ajaxUtil.js"></script>
 
<script type="text/javascript">
 
    function sendIt() {
 
        //XMLHttpRequest 객체를 생성
        xmlHttp = createXMLHttpRequest();
        // 1.브라우저 검색 2. 그에 맞게 xmlReq 객체 생성 후 여기에 반환
 
        var query = "";
        var su1 = document.getElementById("su1").value;
        var su2 = document.getElementById("su2").value;
        var oper = document.getElementById("oper").value;
 
        // get방식 데이터 전송
        query = "test1_ok.jsp?su1="+su1+"&su2="+
                su2 + "&oper="+oper; 
 
 
        xmlHttp.onreadystatechange = callback;
        // onreadystatechange라는 프로퍼티(메소드)는
        // 서버가 작업을 마치고 클라이언트한테 
        // 정보나 데이터를 되돌려 줄 때 자동으로 실행되는 메소드
        // 콜백메소드(자동으로 실행되는 함수)명은 사용자 정의
 
        //서버한테 보내기
        xmlHttp.open("GET",query,true);
        //위에 함수가 get방식이므로
        //true: 비동기방식으로 보낸다 (서버에 a작업던져놓고,  나는 딴 짓하고 있다. 대신 받아주는 함수 필요 => callback)
        //ajax방식은 대부분 true
        xmlHttp.send(null);
        // POST 방식으로 보낼때는 uri에 줄줄이 데이터를 못붙이므로
        // null자리에다가 써주면 됨
 
        //서버에서 처리작업 맡긴거임.
 
    }
 
    function callback(){
 
        if(xmlHttp.readyState==4) { // ppt 참고 
 
            if(xmlHttp.status==200) {
 
                printData();
 
            }
 
        }
 
    }
 
    function printData() {
 
        //받아낸 XML(responseXML)에 root라는 애가 있음
        var result = 
            xmlHttp.responseXML.getElementsByTagName("root")[0];
        //Tag오면 Element's'
        //Id오면 Element
 
        var out = document.getElementById("resultDIV");
 
 
        out.innerHTML = "";
        out.style.display = "";
        // 초기화 작업
        // 처음 실행 시 : resultDIV에 10이 들어가고
        // 두번 째 실행 시 : 20이 들어가려면 그 전의 10이 지워져 있어야 함
 
        var data = result.firstChild.nodeValue; // root의 첫번째 value값
 
        out.innerHTML = data; // html 형식으로 데이터를 out에다가 뿌려라
 
    }
 
 
</script>
</html>
