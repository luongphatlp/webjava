package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Latitude E7470
 */
public class SanPhamDTO {
    private String ma,ten;
    private int soluong,gia;
    public SanPhamDTO(){}
    public SanPhamDTO(String ma,String ten,int soluong,int dongia){
        this.ma=ma;
        this.ten=ten;
        this.soluong=soluong;
        this.gia=dongia;
    }
    public void setMa(String ma){this.ma=ma;}
    public String getMa(){return ma;}
    public void setTen(String ten){this.ten=ten;}
    public String getTen(){return ten;}
    public void setSoluong(int soluong){this.soluong=soluong;}
    public int getSoluong(){return soluong;}
    public void setGia(int dongia){this.gia=dongia;}
    public int getGia(){return gia;}
}
