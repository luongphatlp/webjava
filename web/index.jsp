<%-- 
    Document   : index
    Created on : Apr 1, 2026, 1:49:07 PM
    Author     : Latitude E7470
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nhập tên bạn vô đây!!!</h1>
        <form action="${pageContext.request.contextPath}/sanpham" method="get">
            <button type="submit">Gửi</button>
        </form>
    </body>
</html>
