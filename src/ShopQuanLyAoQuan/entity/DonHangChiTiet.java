package ShopQuanLyAoQuan.entity;

/**
 *
 * @author Admin
 */
public class DonHangChiTiet {
    private String MaDHCT;
    private String MaDH;
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private float DonGia;
    private String GhiChu;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(String MaDHCT, String MaDH, String MaSP,  int SoLuong, float DonGia, String GhiChu) {
        this.MaDHCT = MaDHCT;
        this.MaSP = MaSP;
        this.MaDH = MaDH;
        this.SoLuong = SoLuong;
        this.DonGia= DonGia;
        this.GhiChu = GhiChu;
    }

    public String getMaDHCT() {
        return MaDHCT;
    }

    public void setMaDHCT(String MaDHCT) {
        this.MaDHCT = MaDHCT;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }
    

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String MaDH) {
        this.MaDH = MaDH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float ThanhTien) {
        this.DonGia = ThanhTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    public double getTotalPrice() {
        return getDonGia() * getSoLuong();
    }
}
