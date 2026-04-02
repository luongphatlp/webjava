package GUI;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "NhaCungCapUI", urlPatterns = {"/NhaCungCapUI"})
public class NhaCungCapUI extends HttpServlet {

    NhaCungCapBUS bus = new NhaCungCapBUS();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NhaCungCapUI</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NhaCungCapUI at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("txtSearch");
        List<NhaCungCapDTO> list;

        if (search != null && !search.isEmpty()) {
            list = bus.search(search);
        } else {
            list = bus.getAll();
        }

        request.setAttribute("ds", list);
        request.getRequestDispatcher("HOME.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
