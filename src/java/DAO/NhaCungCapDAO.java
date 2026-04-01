/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

        // Sử dụng try-with-resources để tự động đóng connection/statement
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
    public static void main(String[] args) {
        NhaCungCapDAO ncc = new NhaCungCapDAO();
        ArrayList<NhaCungCapDTO> ds = ncc.getAll();
        for(NhaCungCapDTO a : ds) {
            System.out.println(a);
        }
    }
}
