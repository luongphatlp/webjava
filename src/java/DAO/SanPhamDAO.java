
package DAO;

import DATABASE.MyConnection;
import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    public List<SanPhamDTO> docDS(){
        List<SanPhamDTO> ds=new ArrayList<>();
        String sql="SELECT masp,tensp,dongia,soluong FROM sanpham";
        try(
            Connection conn=MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
        ){
            while(rs.next()){
                SanPhamDTO sp=new SanPhamDTO();
                sp.setMa(rs.getString("masp"));
                sp.setTen(rs.getString("tensp"));
                sp.setSoluong(rs.getInt("soluong"));
                sp.setGia(rs.getInt("dongia"));
                ds.add(sp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ds;
    }
    public int them(SanPhamDTO sp){
        String sql="INSERT INTO sanpham(masp,tensp,dongia,soluong) VALUES (?,?,?,?)";
        int result=0;
        try(
            Connection conn=MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
        ){
            ps.setString(1, sp.getMa());
            ps.setString(2,sp.getTen());
            ps.setInt(3, sp.getGia());
            ps.setInt(4, sp.getSoluong());
            result=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int sua(SanPhamDTO sp){
        String sql="UPDATE sanpham SET tensp=?,dongia=?,soluong=? WHERE masp=?";
        int result=0;
        try(
            Connection conn=MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
        ){
            ps.setString(1,sp.getTen());
            ps.setInt(2, sp.getGia());
            ps.setInt(3, sp.getSoluong());
            ps.setString(4, sp.getMa());
            result=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int xoa(String ma){
        String sql="Delete from sanpham where masp=?";
        int result=0;
        try(
            Connection conn=MyConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
        ){
            ps.setString(1,ma);
            result=ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
