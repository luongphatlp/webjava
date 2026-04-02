
package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.List;


public class SanPhamBUS {
    List<SanPhamDTO> ds=new ArrayList<>();
    SanPhamDAO dao=new SanPhamDAO();
    public List<SanPhamDTO> getDS(){return ds;}
    public void docDS(){ds=dao.docDS();}
    public int themSP(SanPhamDTO sp){
        ds.add(sp);
        return dao.them(sp);
    }
    public int suaSP(SanPhamDTO sp){
        docDS();
        for(int i=0;i<ds.size();i++)
            if(ds.get(i).getMa().equals(sp.getMa())){
                ds.set(i,sp);
                return dao.sua(sp);
            }
        return 0;
    }
    public int xoaSP(String ma){
        docDS();
        for(int i=0;i<ds.size();i++)
            if(ds.get(i).getMa().equals(ma)){
                ds.remove(i);
                return dao.xoa(ma);
            }
        return 0;
    }
}
