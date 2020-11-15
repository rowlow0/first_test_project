<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-8"); 
    String cp = request.getContextPath(); 
 
    // 서버 역할
    int su1 = Integer.parseInt(request.getParameter("su1"));
    int su2 = Integer.parseInt(request.getParameter("su2"));
    String oper = request.getParameter("oper");
 
    int sum = 0;
 
    if(oper.equals("hap")) 
        sum = su1 + su2;
    else if(oper.equals("sub"))
        sum = su1 - su2;
    else if(oper.equals("mul"))
        sum = su1 * su2;
    else if(oper.equals("div"))
        sum = su1 / su2;
 
    // 아래부분은 xml 말고도 text 로도 내보내줄 수 있음 (완벽한모양도 가능 -> 코드가 길어질것임)
 
    // 나온 결과를 클라이언트한테 돌려줄 차례
    // 모양을 갖춰서 돌려줘야함
    StringBuffer sb = new StringBuffer(); // StringBuffer를 꼭 이용해야하는건 아님
 
    // 돌려주는 데이터를 xml 형태로 돌려줌
    sb.append("<?xml version='1.0' encoding='utf-8'?>\n");
    sb.append("<root>" + sum + "</root>"); // root는 xml 이 들어있음
 
    //되돌려준다
    response.setContentType("text/xml;charset=utf-8");
    response.getWriter().write(sb.toString());
    // 클라이언트의 콜백함수로 돌아감
 
    // 여기서 xml형태로 짜줬으니깐, 아래 html코드는 필요 없음
    // 표형태로 서버쪽에서 미리 짜서 반환해줄 수도 있음 디자인은 자유롭게 구성 가능
 
%>
