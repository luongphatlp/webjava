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
<script>
    document.getElementById("themsanphamform").addEventListener("submit", function(e) {
        e.preventDefault();;
        
        let data = new URLSearchParams();
        data.append("action", "them");  
        data.append("ma", document.querySelector('input[name="ma"]').value);
        data.append("ten", document.querySelector('input[name="ten"]').value);
        data.append("gia", document.querySelector('input[name="gia"]').value);
        data.append("soluong", document.querySelector('input[name="soluong"]').value);

        fetch("${pageContext.request.contextPath}/themsanpham", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(response => response.json()) // Servlet trả JSON
        .then(data => {
            // data = {ten: "...", gia: 123}
            let tbody = document.querySelector("#bangSanPham tbody");

            // Tạo 1 row mới
            let row = tbody.insertRow(); // insert vào cuối table
            let cellMa = row.insertCell(0);
            let cellTen = row.insertCell(1);
            let cellGia = row.insertCell(2);
            let cellSL  = row.insertCell(3);
            let cellAction = row.insertCell(4);
            // STT = số row hiện tại
            cellMa.innerText = data.ma;
            cellTen.innerText = data.ten;
            cellGia.innerText = data.gia;
            cellSL.innerText = data.soluong;
            cellAction.innerHTML = `
                <button onclick="xoa('${data.ma}', this)">Xóa</button>
                <button onclick="suaRow(this)">Sửa</button>
            `;
            this.reset(); // reset form
        });
    });
    function xoa(ma, btn) {
        if (!confirm("Xóa?")) return;

        fetch("sanpham?action=xoa&ma=" + ma)
        .then(res => res.json())
        .then(() => {
            btn.closest("tr").remove();
        });
    }
    function suaRow(btn) {
        let row = btn.closest("tr");

        let ma = row.cells[0].innerText;
        let ten = prompt("Tên mới:", row.cells[1].innerText);
        let gia = prompt("Giá mới:", row.cells[2].innerText);
        let sl = prompt("SL mới:", row.cells[3].innerText);

        let data = new URLSearchParams();
        data.append("action", "sua");
        data.append("ma", ma);
        data.append("ten", ten);
        data.append("gia", gia);
        data.append("soluong", sl);

        fetch("sanpham", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: data
        })
        .then(res => res.json())
        .then(sp => {
            row.cells[1].innerText = sp.ten;
            row.cells[2].innerText = sp.gia;
            row.cells[3].innerText = sp.soluong;
        });
    }
</script>
</body>
</html>
