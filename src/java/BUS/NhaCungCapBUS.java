package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.List;

public class NhaCungCapBUS {
    private NhaCungCapDAO dao = new NhaCungCapDAO();

    public List<NhaCungCapDTO> getAll() {
        return dao.getAll();
    }

    public void add(NhaCungCapDTO ncc) throws Exception {
        if (ncc.getMaNCC() == null || ncc.getMaNCC().trim().isEmpty()) {
            throw new Exception("Mã nhà cung cấp không được để trống!");
        }
        if (ncc.getSoDienThoai() == null || ncc.getSoDienThoai().length() < 10) {
            throw new Exception("Số điện thoại không hợp lệ!");
        }
        dao.insert(ncc);
    }

    public void update(NhaCungCapDTO ncc) {
        dao.update(ncc);
    }

    public void delete(String id) {
        dao.delete(id);
    }

    public List<NhaCungCapDTO> search(String keyword) {
        if (keyword == null) keyword = "";
        return dao.search(keyword.trim());
    }
}