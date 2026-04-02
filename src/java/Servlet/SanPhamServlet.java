package Servlet;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/sanpham")
public class SanPhamServlet extends HttpServlet{
    SanPhamBUS bussp=new SanPhamBUS();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        int gia = Integer.parseInt(request.getParameter("gia"));
        int soluong = Integer.parseInt(request.getParameter("soluong"));

        SanPhamDTO sp = new SanPhamDTO();
        sp.setMa(ma);
        sp.setTen(ten);
        sp.setGia(gia);
        sp.setSoluong(soluong);

        // ===== THÊM =====
        if ("them".equals(action)) {
            bussp.themSP(sp);
        }

        // ===== SỬA =====
        else if ("sua".equals(action)) {
            bussp.suaSP(sp);
        }

        // trả JSON
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(gson.toJson(sp));
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if("xoa".equals(action)){
            String ma=request.getParameter("ma");
            System.out.println(ma);
            bussp.xoaSP(ma);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\":\"ok\"}");
            return;
        }
        bussp.docDS();
        request.setAttribute("ds", bussp.getDS());
        request.getRequestDispatcher("sanpham.jsp").forward(request, response);
     }
}
