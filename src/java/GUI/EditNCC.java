package GUI;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditNCC", urlPatterns = {"/EditNCC"})
public class EditNCC extends HttpServlet {

    private NhaCungCapBUS bus = new NhaCungCapBUS();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Lấy dữ liệu từ form modal sửa
        String ma = request.getParameter("maNCC");
        String ten = request.getParameter("tenNCC");
        String dc = request.getParameter("diaChi");
        String sdt = request.getParameter("soDienThoai");

        // 2. Tạo đối tượng DTO
        NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, dc, sdt);
        HttpSession session = request.getSession();

        try {
            // 3. Gọi lớp BUS để xử lý (Thay vì gọi thẳng DAO)
            bus.update(ncc);
            
            // 4. Lưu thông báo thành công vào Session
            session.setAttribute("message", "Cập nhật thông tin thành công!");
        } catch (Exception e) {
            // Lưu thông báo lỗi nếu có
            session.setAttribute("error", "Cập nhật thất bại: " + e.getMessage());
        }

        // 5. Quay về trang danh sách chính
        response.sendRedirect("NhaCungCapUI");
    }
}