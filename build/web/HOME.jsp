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
                       placeholder="Tìm mã, tên, địa chỉ hoặc SĐT..." autocomplete="off">
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
                            <td style="font-weight: 700; color: var(--primary);">${ncc.maNCC}</td>
                            <td>${ncc.tenNCC}</td>
                            <td style="color: #6b7280;">${ncc.diaChi}</td>
                            <td>${ncc.soDienThoai}</td>
                            <td style="text-align: center;">
                                <button type="button" class="btn-action" title="Sửa"
                                        onclick="openEditModal('${ncc.maNCC}', '${ncc.tenNCC}', '${ncc.diaChi}', '${ncc.soDienThoai}')">
                                    <img src="icon/edit.png" alt="Edit">
                                </button>
                                <a href="DeleteNCC?id=${ncc.maNCC}" onclick="return confirm('...')"></a>
                                <a href="javascript:void(0)" class="btn-action" 
                                   onclick="return confirmDelete('DeleteNCC?id=${ncc.maNCC}')">
                                    <img src="icon/delete (1).png" alt="Delete">
                                </a>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="addModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close-modern" onclick="closeModal()">&times;</span>
                    <h2>Thêm đối tác mới</h2>
                </div>
                <form action="AddUI" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Mã Nhà Cung Cấp</label>
                            <input type="text" name="maNCC" placeholder="Ví dụ: NCC001" required>
                        </div>
                        <div class="form-group">
                            <label>Tên Nhà Cung Cấp</label>
                            <input type="text" name="tenNCC" placeholder="Nhập tên công ty..." required>
                        </div>
                        <div class="form-group">
                            <label>Địa Chỉ</label>
                            <input type="text" name="diaChi" placeholder="Trụ sở chính...">
                        </div>
                        <div class="form-group" style="margin-bottom: 0;">
                            <label>Số Điện Thoại</label>
                            <input type="text" name="soDienThoai" placeholder="Ví dụ: 090xxxxxxx" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn-save-modern">Xác nhận thêm</button>
                        <button type="button" class="btn-cancel-modern" onclick="closeModal()">Hủy</button>
                    </div>
                </form>
            </div>
        </div>

        <div id="editModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close-modern" onclick="closeEditModal()">&times;</span>
                    <h2>Cập nhật thông tin</h2>
                </div>
                <form action="EditNCC" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Mã NCC (Không thể sửa)</label>
                            <input type="text" id="edit_ma" name="maNCC" readonly>
                        </div>
                        <div class="form-group">
                            <label>Tên Nhà Cung Cấp</label>
                            <input type="text" id="edit_ten" name="tenNCC" required>
                        </div>
                        <div class="form-group">
                            <label>Địa Chỉ</label>
                            <input type="text" id="edit_dc" name="diaChi">
                        </div>
                        <div class="form-group" style="margin-bottom: 0;">
                            <label>Số Điện Thoạ i</label>
                            <input type="text" id="edit_sdt" name="soDienThoai" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn-save-modern">Lưu thay đổi</button>
                        <button type="button" class="btn-cancel-modern" onclick="closeEditModal()">Hủy</button>
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
        <div id="confirmModal" class="modal">
            <div class="modal-content" style="width: 380px; text-align: center; padding: 40px 30px;">
                <div style="font-size: 50px; color: #ef4444; margin-bottom: 20px;">⚠️</div>
                <h2 style="margin-bottom: 10px; font-size: 1.4rem;">Xác nhận xóa?</h2>
                <p style="color: #64748b; margin-bottom: 30px; line-height: 1.5;">
                    Hành động này không thể hoàn tác. Bạn có chắc chắn muốn xóa đối tác này không?
                </p>
                <div style="display: flex; gap: 12px;">
                    <button type="button" id="btnConfirmDelete" class="btn-save" style="background: #ef4444; flex: 1; border: none; cursor: pointer;">Xóa ngay</button>
                    <button onclick="closeConfirmModal()" class="btn-cancel" style="flex: 1;">Hủy bỏ</button>
                </div>
            </div>
        </div>
    </body>
</html>