package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ShopQuanLyAoQuan.dao.SanPhamDAO;
import ShopQuanLyAoQuan.entity.NhanVien;
import ShopQuanLyAoQuan.entity.SanPham;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

public class TestSanPhamDAO {

    SanPhamDAO sanPhamDAO;

    @BeforeMethod
    public void setUp() {
        sanPhamDAO = new SanPhamDAO();
    }

    @AfterMethod
    public void tearDown() {
        sanPhamDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(null);
        sanPham.setMaLoai("MSP0001");
        sanPham.setTenSP("Áo thun nam");
        sanPham.setGiaNhap(250000);
        sanPham.setSoLuongNhap(100);
        sanPham.setGhiChu(null);
        sanPham.setHinhAnh("image.jpg");
        try {
            sanPhamDAO.insert(sanPham);
            // Kiểm tra xem nhanVien đã được chèn thành công hay không
            assertNotNull(sanPhamDAO.selectById(sanPham.getMaSP()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(" ");
        sanPham.setMaLoai("MSP0001");
        sanPham.setTenSP("Áo thun nam");
        sanPham.setGiaNhap(250000);
        sanPham.setSoLuongNhap(100);
        sanPham.setGhiChu(null);
        sanPham.setHinhAnh("image.jpg");
        try {
            if (sanPham.getMaLoai().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sản phẩm không được để trống");
            }
            sanPhamDAO.insert(sanPham);
            assertNotNull(sanPhamDAO.selectById(sanPham.getMaSP()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP00007");
        sanPham.setMaLoai("MSP0001");
        sanPham.setTenSP("Áo thun nam");
        sanPham.setGiaNhap(250000);
        sanPham.setSoLuongNhap(100);
        sanPham.setGhiChu(null);
        sanPham.setHinhAnh("image.jpg");
        try {
            sanPhamDAO.insert(sanPham);
            SanPham retrievedSanPham = sanPhamDAO.selectById(sanPham.getMaSP());

            // Kiểm tra xem nhanVien đã được chèn thành công và trùng khớp với dữ liệu ban đầu hay không
            assertEquals(sanPham.getMaSP(), retrievedSanPham.getMaSP());
            assertEquals(sanPham.getMaLoai(), retrievedSanPham.getMaLoai());
            assertEquals(sanPham.getTenSP(), retrievedSanPham.getTenSP());
            assertEquals(sanPham.getGiaNhap(), retrievedSanPham.getGiaNhap());
            assertEquals(sanPham.getSoLuongNhap(), retrievedSanPham.getSoLuongNhap());
            assertEquals(sanPham.getGhiChu(), retrievedSanPham.getGhiChu());
            assertEquals(sanPham.getHinhAnh(), retrievedSanPham.getHinhAnh());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(null);
        exceptionRule.expect(Exception.class);
        sanPhamDAO.update(sanPham);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(" ");
        sanPham.setMaLoai("MSP0002");
        sanPham.setTenSP("Áo thun nam");
        sanPham.setGiaNhap(250000);
        sanPham.setSoLuongNhap(100);
        sanPham.setGhiChu(null);
        sanPham.setHinhAnh("image.jpg");
        try {
            if (sanPham.getMaLoai().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sản phẩm không được để trống");
            }
            sanPhamDAO.update(sanPham);
            assertNotNull(sanPhamDAO.selectById(sanPham.getMaSP()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP03");
        sanPham.setMaLoai("LOAI02");
        sanPham.setTenSP("Áo khoác nữ");
        try {
            sanPhamDAO.update(sanPham);
            SanPham updatedSanPham = sanPhamDAO.selectById(sanPham.getMaSP());
            assertEquals(sanPham.getMaLoai(), updatedSanPham.getMaLoai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        sanPhamDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        sanPhamDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            sanPhamDAO.delete("SP03");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<SanPham> sanPhams = sanPhamDAO.selectALl();
        Assert.assertNotNull(sanPhams);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        SanPham sanPham = sanPhamDAO.selectById("SP00001");
        Assert.assertNotNull(sanPham);
    }

    @Test(description = "Kiểm thử tìm kiếm theo Mã SP hoặc Tên SP")
    public void testTimKiemByMaSPOrTenSP() {
        List<SanPham> sanPhams = sanPhamDAO.timKiem("SP00001", true);
        Assert.assertNotNull(sanPhams);
    }

    @Test(description = "Kiểm thử lấy theo Tên SP")
    public void testSelectByTenSP() {
        SanPham sanPham = sanPhamDAO.selectByTenSP("Áo thun Puma");
        Assert.assertNotNull(sanPham);
    }
}
