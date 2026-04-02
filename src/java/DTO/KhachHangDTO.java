package DTO;

public class KhachHangDTO {
    private String ma;
    private String hoten;
    private String sdt;
    private String diachi;

    public KhachHangDTO() {}

    public KhachHangDTO(String ma, String hoten, String sdt, String diachi) {
        this.ma = ma;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getMa() { return ma; }
    public void setMa(String ma) { this.ma = ma; }

    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi = diachi; }
}