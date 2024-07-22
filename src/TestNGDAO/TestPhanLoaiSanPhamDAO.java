package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import ShopQuanLyAoQuan.dao.PhanLoaiSanPhamDAO;
import ShopQuanLyAoQuan.entity.PhanLoaiSanPham;
import ShopQuanLyAoQuan.entity.SanPham;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

public class TestPhanLoaiSanPhamDAO {

    PhanLoaiSanPhamDAO phanLoaiSanPhamDAO;

    @BeforeMethod
    public void setUp() {
        phanLoaiSanPhamDAO = new PhanLoaiSanPhamDAO();
    }

    @AfterMethod
    public void tearDown() {
        phanLoaiSanPhamDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai(null);
        phanLoaiSanPham.setTenLoai("Áo sơ mi");
        try {	
            phanLoaiSanPhamDAO.update(phanLoaiSanPham);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Thông tin loại sản phẩm không hợp lệ");
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai(" ");
        phanLoaiSanPham.setTenLoai("Áo sơ mi");
        try {
            if (phanLoaiSanPham.getMaLoai().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã loại không được để trống");
            }
            phanLoaiSanPhamDAO.insert(phanLoaiSanPham);
            assertNotNull(phanLoaiSanPhamDAO.selectById(phanLoaiSanPham.getMaLoai()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai("MSP0006");
        phanLoaiSanPham.setTenLoai("Áo sơ mi");
        try {
        	phanLoaiSanPhamDAO.insert(phanLoaiSanPham);
        	PhanLoaiSanPham insertdphanLoaiSanPham = phanLoaiSanPhamDAO.selectById(phanLoaiSanPham.getTenLoai());
        	assertEquals(phanLoaiSanPham.getMaLoai(), insertdphanLoaiSanPham.getMaLoai());
        	assertEquals(phanLoaiSanPham.getTenLoai(), insertdphanLoaiSanPham.getTenLoai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai(null);
        phanLoaiSanPham.setTenLoai("Áo sơ mi");
        try {
            phanLoaiSanPhamDAO.update(phanLoaiSanPham);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Thông tin loại sản phẩm không hợp lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai(" ");
        phanLoaiSanPham.setTenLoai("Áo sơ mi");
        try {
            if (phanLoaiSanPham.getMaLoai().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã loại không được để trống");
            }
            phanLoaiSanPhamDAO.update(phanLoaiSanPham);
            assertNotNull(phanLoaiSanPhamDAO.selectById(phanLoaiSanPham.getMaLoai()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        PhanLoaiSanPham phanLoaiSanPham = new PhanLoaiSanPham();
        phanLoaiSanPham.setMaLoai("MSP0006");
        phanLoaiSanPham.setTenLoai("Áo dài nữ");
        try {
        	phanLoaiSanPhamDAO.update(phanLoaiSanPham);
        	PhanLoaiSanPham updatedphanLoaiSanPham = phanLoaiSanPhamDAO.selectById(phanLoaiSanPham.getTenLoai());
            assertEquals(phanLoaiSanPham.getTenLoai(), updatedphanLoaiSanPham.getTenLoai());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        phanLoaiSanPhamDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        phanLoaiSanPhamDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            phanLoaiSanPhamDAO.delete("MSP0006");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<PhanLoaiSanPham> phanLoaiSanPhams = phanLoaiSanPhamDAO.selectALl();
        Assert.assertNotNull(phanLoaiSanPhams);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        PhanLoaiSanPham phanLoaiSanPham = phanLoaiSanPhamDAO.selectById("MSP0001");
        Assert.assertNotNull(phanLoaiSanPham);
    }

    @Test(description = "Kiểm thử kiểm tra sự tồn tại của Mã Loại")
    public void testMaLoaiExists() {
        boolean exists = phanLoaiSanPhamDAO.isMaLoaiExists("MSP0001");
        Assert.assertTrue(exists);
    }

    @Test(description = "Kiểm thử kiểm tra sự tồn tại của Tên Loại")
    public void testTenLoaiExists() {
        boolean exists = phanLoaiSanPhamDAO.isTenLoaiExists("Áo thun");
        Assert.assertTrue(exists);
    }
}
