package Servlet;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/khachhang")
public class KhachHangServlet extends HttpServlet {

    KhachHangBUS bus = new KhachHangBUS();
    Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("xoa".equals(action)) {
            String ma = request.getParameter("ma");
            bus.xoa(ma);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"ok\"}");
            return; // ❗ rất quan trọng
        }
        bus.docDS();
        request.setAttribute("ds", bus.getDS());
        request.getRequestDispatcher("khachhang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String ma = request.getParameter("ma");
        String hoten = request.getParameter("hoten");
        String sdt = request.getParameter("sdt");
        String diachi = request.getParameter("diachi");

        KhachHangDTO kh = new KhachHangDTO();
        kh.setMa(ma);
        kh.setHoten(hoten);
        kh.setSdt(sdt);
        kh.setDiachi(diachi);

        // ===== THÊM =====
        if ("them".equals(action)) {
             if (bus.tonTaiMa(ma)) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\":\"trung_ma\"}");
                return;
            }
            bus.them(kh);
        }

        // ===== SỬA =====
        else if ("sua".equals(action)) {
            bus.sua(kh);
        }

        // trả JSON
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(kh));
    }
}
