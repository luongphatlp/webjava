package GUI;

import BUS.NhaCungCapBUS;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteNCC", urlPatterns = {"/DeleteNCC"})
public class DeleteNCC extends HttpServlet {

    private NhaCungCapBUS bus = new NhaCungCapBUS();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maNCC = request.getParameter("id");
        HttpSession session = request.getSession();

        if (maNCC != null) {
            try {
                bus.delete(maNCC); // Dùng BUS thay vì DAO
                session.setAttribute("message", "Xóa nhà cung cấp thành công!");
            } catch (Exception e) {
                session.setAttribute("error", "Không thể xóa nhà cung cấp này!");
            }
        }
        response.sendRedirect("NhaCungCapUI");
    }
}