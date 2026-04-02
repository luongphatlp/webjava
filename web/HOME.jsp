<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý Nhà Cung Cấp</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
    </head>
    <body>
        <h1>Quản lý Nhà Cung Cấp</h1>

        <div class="btn-container">
            <form action="NhaCungCapUI" method="GET" class="search-container">
                <input type="text" name="txtSearch" value="${param.txtSearch}" 
                       placeholder="Tìm mã, tên, địa chỉ hoặc SĐT...">
                <button type="submit" class="btn-search">Tìm kiếm</button>
            </form>

            <button type="button" class="btn-add" onclick="openModal()">
                <span>+</span> Thêm đối tác
            </button>
        </div>

        <div class="table-card">
            <table>
                <thead>
                    <tr>
                        <th>Mã NCC</th>
                        <th>Tên Nhà Cung Cấp</th>
                        <th>Địa Chỉ</th>
                        <th>Số Điện Thoại</th>
                        <th style="text-align: center;">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ncc" items="${ds}">
                        <tr>
                            <td style="font-weight: 600; color: #4f46e5;">${ncc.maNCC}</td>
                            <td>${ncc.tenNCC}</td>
                            <td style="color: #6b7280;">${ncc.diaChi}</td>
                            <td>${ncc.soDienThoai}</td>
                            <td style="text-align: center;">
                                <button type="button" class="btn-action" title="Sửa"
                                        onclick="openEditModal('${ncc.maNCC}', '${ncc.tenNCC}', '${ncc.diaChi}', '${ncc.soDienThoai}')">
                                    <img src="icon/edit.png" alt="Edit">
                                </button>
                                <a href="DeleteNCC?id=${ncc.maNCC}" class="btn-action" 
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa nhà cung cấp này?')">
                                    <img src="icon/delete (1).png" alt="Delete">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="addModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <h2>THÊM NHÀ CUNG CẤP MỚI</h2>
                <hr style="border: 0.5px solid #eee; margin: 15px 0;">
                <form action="AddUI" method="POST">
                    <table class="form-table">
                        <tr><td>Mã NCC *</td><td><input type="text" name="maNCC" placeholder="VD: NCC001" required></td></tr>
                        <tr><td>Tên NCC *</td><td><input type="text" name="tenNCC" placeholder="Tên công ty/đại lý" required></td></tr>
                        <tr><td>Địa Chỉ</td><td><input type="text" name="diaChi" placeholder="Địa chỉ trụ sở"></td></tr>
                        <tr><td>SĐT *</td><td><input type="text" name="soDienThoai" placeholder="Số điện thoại liên hệ" required></td></tr>
                    </table>
                    <div style="text-align: center; margin-top: 25px; display: flex; gap: 10px;">
                        <button type="submit" class="btn-save" style="flex: 2;">Lưu thông tin</button>
                        <button type="button" class="btn-cancel" onclick="closeModal()" style="flex: 1;">Hủy</button>
                    </div>
                </form>
            </div>
        </div>

        <div id="editModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeEditModal()">&times;</span>
                <h2>SỬA THÔNG TIN ĐỐI TÁC</h2>
                <hr style="border: 0.5px solid #eee; margin: 15px 0;">
                <form action="EditNCC" method="POST">
                    <table class="form-table">
                        <tr><td>Mã NCC</td><td><input type="text" id="edit_ma" name="maNCC" readonly style="background: #f3f4f6; color: #6b7280;"></td></tr>
                        <tr><td>Tên NCC *</td><td><input type="text" id="edit_ten" name="tenNCC" required></td></tr>
                        <tr><td>Địa Chỉ</td><td><input type="text" id="edit_dc" name="diaChi"></td></tr>
                        <tr><td>SĐT *</td><td><input type="text" id="edit_sdt" name="soDienThoai" required></td></tr>
                    </table>
                    <div style="text-align: center; margin-top: 25px; display: flex; gap: 10px;">
                        <button type="submit" class="btn-save" style="flex: 2;">Cập nhật dữ liệu</button>
                        <button type="button" class="btn-cancel" onclick="closeEditModal()" style="flex: 1;">Hủy</button>
                    </div>
                </form>
            </div>
        </div>

        <div id="toast"></div>
        <script src="${pageContext.request.contextPath}/JS/script.js"></script>
        <script>
                            window.onload = function () {
            <%
            String msg = (String) session.getAttribute("message");
            String err = (String) session.getAttribute("error");
            if (msg != null) {
            %>
                                showToast("<%= msg%>", "success");
            <% session.removeAttribute("message"); %>
            <% } else if (err != null) {%>
                                showToast("<%= err%>", "error");
            <% session.removeAttribute("error"); %>
            <% }%>
                            };
        </script>
    </body>
</html>