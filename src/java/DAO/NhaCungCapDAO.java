package DAO;

import database.Connect;
import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author THANH NHAN
 */
public class NhaCungCapDAO {

    public ArrayList<NhaCungCapDTO> getAll() {
        ArrayList<NhaCungCapDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";

        try (Connection con = Connect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO();
                int index = 1;
                ncc.setMaNCC(rs.getString(index++));
                ncc.setTenNCC(rs.getString(index++));
                ncc.setDiaChi(rs.getString(index++));
                ncc.setSoDienThoai(rs.getString(index++));

                ds.add(ncc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public int insert(NhaCungCapDTO ncc) {
        int result = 0;
        String qry = "INSERT INTO nhacungcap (Ma, Ten, DiaChi, SDT) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connect.getConnection(); PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, ncc.getMaNCC());
            st.setString(2, ncc.getTenNCC());
            st.setString(3, ncc.getDiaChi());
            st.setString(4, ncc.getSoDienThoai());
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int delete(String maNCC) {
        int result = 0;

        String qry = "DELETE FROM nhacungcap WHERE Ma = ?";
        try (Connection conn = Connect.getConnection(); PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, maNCC);
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int update(NhaCungCapDTO ncc) {
        int result = 0;
        // Đảm bảo tên các cột Ma, Ten, DiaChi, SDT khớp với DB của bạn
        String qry = "UPDATE nhacungcap SET Ten = ?, DiaChi = ?, SDT = ? WHERE Ma = ?";
        try (Connection conn = Connect.getConnection(); PreparedStatement st = conn.prepareStatement(qry)) {
            st.setString(1, ncc.getTenNCC());
            st.setString(2, ncc.getDiaChi());
            st.setString(3, ncc.getSoDienThoai());
            st.setString(4, ncc.getMaNCC());
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<NhaCungCapDTO> search(String keyword) {
        ArrayList<NhaCungCapDTO> ds = new ArrayList<>();
        // Tìm kiếm không phân biệt hoa thường trên tất cả các trường
        String sql = "SELECT * FROM nhacungcap WHERE Ma LIKE ? OR Ten LIKE ? OR DiaChi LIKE ? OR SDT LIKE ?";

        try (Connection con = Connect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, searchPattern);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhaCungCapDTO ncc = new NhaCungCapDTO();
                    ncc.setMaNCC(rs.getString(1));
                    ncc.setTenNCC(rs.getString(2));
                    ncc.setDiaChi(rs.getString(3));
                    ncc.setSoDienThoai(rs.getString(4));
                    ds.add(ncc);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }
}
