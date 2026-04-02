package DAO;

import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;

public class KhachHangDAO {

    private Connection conn;

    public KhachHangDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3309/store", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 đọc danh sách
    public ArrayList<KhachHangDTO> docDS() {
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        try {
            String sql = "SELECT * FROM khachhang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO(
                        rs.getString("ma"),
                        rs.getString("hoten"),
                        rs.getString("sdt"),
                        rs.getString("diachi")
                );
                ds.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    // 🔹 thêm
    public int them(KhachHangDTO kh) {
        try {
            String sql = "INSERT INTO khachhang(ma, hoten, sdt, diachi) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getHoten());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getDiachi());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 🔹 xóa
    public int xoa(String ma) {
        try {
            String sql = "DELETE FROM khachhang WHERE ma=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 🔹 sửa
    public int sua(KhachHangDTO kh) {
        try {
            String sql = "UPDATE khachhang SET hoten=?, sdt=?, diachi=? WHERE ma=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, kh.getHoten());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiachi());
            ps.setString(4, kh.getMa());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 🔹 tìm theo mã
    public KhachHangDTO getByMa(String ma) {
        try {
            String sql = "SELECT * FROM khachhang WHERE ma=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new KhachHangDTO(
                        rs.getString("ma"),
                        rs.getString("hoten"),
                        rs.getString("sdt"),
                        rs.getString("diachi")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean tonTaiMa(String ma) {
    boolean exists = false;
    try {
        String sql = "SELECT 1 FROM khachhang WHERE Ma = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            exists = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return exists;
}
}