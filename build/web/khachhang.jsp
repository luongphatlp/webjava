<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/khachhang.css">
</head>
<body>

<h1>Danh sách khách hàng</h1>

<form id="formKH">
    Mã KH: <input type="text" name="ma"><br>
    <label id="errorMa" style="color: red"></label><br>
    Họ tên: <input type="text" name="hoten"><br>
    SĐT: <input type="text" name="sdt"><br>
    Địa chỉ: <input type="text" name="diachi"><br>
    <input type="submit" id="btnThem" value="Thêm khách hàng">
    <button type="button" id="btnSua" disabled>Cập nhật</button>
</form>
<input type="text" id="search" placeholder="Tìm kiếm...">
<button id="reset">Làm mới</button>

<table id="bangKH">
    <thead>
        <tr>
            <th>Mã</th>
            <th>Họ tên</th>
            <th>SĐT</th>
            <th>Địa chỉ</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="kh" items="${ds}">
            <tr>
                <td>${kh.ma}</td>
                <td>${kh.hoten}</td>
                <td>${kh.sdt}</td>
                <td>${kh.diachi}</td>
                <td>
                    <button type="button" onclick="xoa('${kh.ma}', this)">Xóa</button>
                    <button type="button" onclick="suaRow(this)">Sửa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/JS/khachhang.js"></script>
</body>
</html>