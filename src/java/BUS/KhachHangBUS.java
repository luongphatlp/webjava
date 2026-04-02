package BUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    ArrayList<KhachHangDTO> ds = new ArrayList<>();
    KhachHangDAO dao = new KhachHangDAO();

    public ArrayList<KhachHangDTO> getDS() {
        return ds;
    }

    public void docDS() {
        ds = dao.docDS();
    }

    public int them(KhachHangDTO kh) {
        int result = dao.them(kh);
        if(result > 0){
            ds.add(kh);
        }
        return result;
    }

    public boolean xoa(String ma) {
        int result = dao.xoa(ma);
        if (result > 0) {
            ds.removeIf(kh -> kh.getMa().equals(ma));
            return true;
        }
        return false;
    }

    public boolean sua(KhachHangDTO khMoi) {
        int result = dao.sua(khMoi);

        if (result > 0) {
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getMa().equals(khMoi.getMa())) {
                    ds.set(i, khMoi);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<KhachHangDTO> tim(String key) {
        if (key == null || key.isEmpty()) return ds;

        ArrayList<KhachHangDTO> kq = new ArrayList<>();

        for (KhachHangDTO kh : ds) {
            if (kh.getMa().contains(key) ||
                kh.getHoten().toLowerCase().contains(key.toLowerCase())) {
                kq.add(kh);
            }
        }
        return kq;
    }
    public boolean tonTaiMa(String ma){
        return dao.tonTaiMa(ma);
    }
}