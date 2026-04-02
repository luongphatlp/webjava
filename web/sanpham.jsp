<%-- 
    Document   : sanpham
    Created on : Apr 1, 2026, 3:55:32 PM
    Author     : Latitude E7470
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sanpham.css">
</head>
<body>

<h1>Danh sách sản phẩm</h1>


<form  id="themsanphamform">
            Mã SP: <input type="text" name="ma"><br>
            Tên SP: <input type="text" name="ten"><br>
            Giá: <input type="number" name="gia"><br>
            Số lượng: <input type="number" name="soluong"><br>
            <input type="submit" value="Thêm sản phẩm">
</form>
<table id="bangSanPham">
    <thead>
        <tr>
            <th>Mã SP</th>
            <th>Tên sản phẩm</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="sp" items="${ds}">
            <tr>
                <td>${sp.ma}</td>
                <td>${sp.ten}</td>
                <td>${sp.gia}</td>
                <td>${sp.soluong}</td>
                <td>
                    <button onclick="xoa('${sp.ma}', this)">Xóa</button>
                    <button onclick="suaRow(this)">Sửa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/JS/sanphamjs.js">
    
</script>
</body>
</html>
