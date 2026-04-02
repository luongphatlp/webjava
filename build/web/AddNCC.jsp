<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="form-container">
            <h2>THÊM NHÀ CUNG CẤP MỚI</h2>

            <form action="AddUI" method="POST">
                <table>
                    <tr>
                        <td class="label">Mã NCC <span class="required">*</span></td>
                        <td>
                            <input type="text" name="maNCC" placeholder="Ví dụ: NCC001" required>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Tên Nhà Cung Cấp <span class="required">*</span></td>
                        <td>
                            <input type="text" name="tenNCC" placeholder="Nhập tên nhà cung cấp" required>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Địa Chỉ</td>
                        <td>
                            <input type="text" name="diaChi" placeholder="Nhập địa chỉ đầy đủ">
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Số Điện Thoại <span class="required">*</span></td>
                        <td>
                            <input type="text" name="soDienThoai" placeholder="Ví dụ: 0912345678" required>
                        </td>
                    </tr>
                </table>

                <div class="button-group">
                    <button type="submit" class="btn-save">Lưu Nhà Cung Cấp</button>
                    <button type="reset" class="btn-reset">Nhập Lại</button>
                </div>
            </form> <hr> <h3>DANH SÁCH NHÀ CUNG CẤP HIỆN TẠI</h3>

            <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
                <thead>
                    <tr style="background-color: #f2f2f2;">
                        <th>Mã NCC</th>
                        <th>Tên Nhà Cung Cấp</th>
                        <th>Địa Chỉ</th>
                        <th>Số Điện Thoại</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ncc" items="${ds}">
                        <tr>
                            <td>${ncc.maNCC}</td>
                            <td>${ncc.tenNCC}</td>
                            <td>${ncc.diaChi}</td>
                            <td>${ncc.soDienThoai}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>