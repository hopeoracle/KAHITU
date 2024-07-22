/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.entity;

/**
 *
 * @author Admin
 */
public class SanPham {
    private String MaSP;
    private String MaLoai;
    private String TenSP;
    private float GiaNhap;
    private int SoLuongNhap;
    private String GhiChu;
    private String HinhAnh;

    public SanPham() {
    }

    public SanPham(String MaSP, String MaLoai, String TenSP, float GiaNhap, int SoLuongNhap, String GhiChu, String HinhAnh) {
        this.MaSP = MaSP;
        this.MaLoai = MaLoai;
        this.TenSP = TenSP;
        this.GiaNhap = GiaNhap;
        this.SoLuongNhap = SoLuongNhap;
        this.GhiChu = GhiChu;
        this.HinhAnh = HinhAnh;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public float getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(float GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.SoLuongNhap = SoLuongNhap;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
    
    
}
