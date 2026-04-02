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

@WebServlet(name = "AddUI", urlPatterns = {"/AddUI"})
public class AddUI extends HttpServlet {

    private NhaCungCapBUS bus = new NhaCungCapBUS();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("AddNCC.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ma = request.getParameter("maNCC");
        String ten = request.getParameter("tenNCC");
        String dc = request.getParameter("diaChi");
        String sdt = request.getParameter("soDienThoai");

        NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, dc, sdt);
        HttpSession session = request.getSession();
        
        try {
            bus.add(ncc); // Dùng BUS thay vì DAO
            session.setAttribute("message", "Thêm nhà cung cấp thành công!");
            response.sendRedirect("NhaCungCapUI");
        } catch (Exception e) {
            session.setAttribute("error", "Lỗi: " + e.getMessage());
            response.sendRedirect("NhaCungCapUI");
        }
    }
}