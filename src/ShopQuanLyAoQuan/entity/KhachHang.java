/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.entity;

/**
 *
 * @author Admin
 */
public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String SDT;
    private String DiaChi;
    private String Email;
    private String GhiChu;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String HoTen, String SDT, String DiaChi, String Email, String GhiChu) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.GhiChu = GhiChu;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getGhiChu(){
        return GhiChu;
    }
    
    public void setGhiChu(String GhiChu){
        this.GhiChu = GhiChu;
    }
    
    
}
