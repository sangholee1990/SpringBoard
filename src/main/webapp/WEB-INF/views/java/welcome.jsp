<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>어서오세요</title>
</head>
<body>
<%=session.getAttribute("id")%>님 환영합니다
<input type="button" value="로그아웃" onclick="location.href='/board/logout.jsp'"/><br/>
</body>
</html>