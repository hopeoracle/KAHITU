package TestNGDAO;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ShopQuanLyAoQuan.dao.NhanVienDAO;
import ShopQuanLyAoQuan.entity.NhanVien;

public class TestNhanVienDAO {

    NhanVienDAO nhanVienDAO;

    @BeforeMethod
    public void setUp() {
        nhanVienDAO = new NhanVienDAO();
    }

    @AfterMethod
    public void tearDown() {
        nhanVienDAO = null;
    }

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(null);
        nhanVien.setHoTen("Nguyễn Văn i");
        nhanVien.setDiaChi("Huế");
        nhanVien.setSDT("0886986559");
        nhanVien.setEmail("Nguyeni@gmail.com");
        nhanVien.setLuong(6000000);
        nhanVien.setNgaySinh(null);
        nhanVien.setGhiChu(null);

        try {
            nhanVienDAO.insert(nhanVien);
            // Kiểm tra xem nhanVien đã được chèn thành công hay không
            assertNotNull(nhanVienDAO.selectById(nhanVien.getMaNV()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(" ");
        nhanVien.setHoTen("Nguyễn Văn a");
        nhanVien.setDiaChi("Huế");
        nhanVien.setSDT("090352179");
        nhanVien.setEmail("Nguyenqa@gmail.com");
        nhanVien.setLuong(6000000);
        nhanVien.setNgaySinh(null);
        nhanVien.setGhiChu(null);

        try {
            if (nhanVien.getMaNV().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã nhân viên không được để trống");
            }
            nhanVienDAO.insert(nhanVien);
            assertNotNull(nhanVienDAO.selectById(nhanVien.getMaNV()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV00022");
        nhanVien.setHoTen("Nguyễn Te");
        nhanVien.setDiaChi("Huế");
        nhanVien.setSDT("0663000323");
        nhanVien.setEmail("Nguyente@gmail.com");
        nhanVien.setLuong(9000000);
        nhanVien.setNgaySinh(null);
        nhanVien.setGhiChu(null);

        try {
            nhanVienDAO.insert(nhanVien);
            NhanVien retrievedNhanVien = nhanVienDAO.selectById(nhanVien.getMaNV());

            // Kiểm tra xem nhanVien đã được chèn thành công và trùng khớp với dữ liệu ban đầu hay không
            assertEquals(nhanVien.getHoTen(), retrievedNhanVien.getHoTen());
            assertEquals(nhanVien.getDiaChi(), retrievedNhanVien.getDiaChi());
            assertEquals(nhanVien.getSDT(), retrievedNhanVien.getSDT());
            assertEquals(nhanVien.getEmail(), retrievedNhanVien.getEmail());
            assertEquals(nhanVien.getLuong(), retrievedNhanVien.getLuong());
            assertEquals(nhanVien.getGhiChu(), retrievedNhanVien.getGhiChu());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(null);
        try {
            nhanVienDAO.update(nhanVien);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Thông tin nhân viên không hợp lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(" ");
        nhanVien.setHoTen("Nguyễn Văn a");
        nhanVien.setDiaChi("Huế");
        nhanVien.setSDT("090352179");
        nhanVien.setEmail("Nguyenqa@gmail.com");
        nhanVien.setLuong(6000000);
        nhanVien.setNgaySinh(null);
        nhanVien.setGhiChu(null);

        try {
            if (nhanVien.getMaNV().trim().isEmpty()) {
                throw new IllegalArgumentException("Mã nhân viên không được để trống");
            }
            nhanVienDAO.update(nhanVien);
            assertNotNull(nhanVienDAO.selectById(nhanVien.getMaNV()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV00003");
        nhanVien.setHoTen("Nguyễn Văn B");
        try {
            nhanVienDAO.update(nhanVien);
            NhanVien updatedNhanVien = nhanVienDAO.selectById(nhanVien.getMaNV());
            assertEquals(nhanVien.getHoTen(), updatedNhanVien.getHoTen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        try {
            nhanVienDAO.delete(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ID không được để trống");
        }
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        try {
            nhanVienDAO.delete(" ");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ID không được để trống");
        }
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            nhanVienDAO.delete("NV00003");
            // Kiểm tra xem đã xóa thành công hay không
            assertNull(nhanVienDAO.selectById("NV00003"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<NhanVien> nhanViens = nhanVienDAO.selectALl();
        assertNotNull(nhanViens);
        assertTrue(nhanViens.size() > 0);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        NhanVien nhanVien = nhanVienDAO.selectById("NV00001");
        assertNotNull(nhanVien);
    }

    @Test(description = "Kiểm thử tìm kiếm theo Mã NV hoặc Tên NV")
    public void testTimkiemByMaNVOrTenNV() {
        List<NhanVien> nhanViens = nhanVienDAO.timkiemByMaNVOrTenNV("NV00001", true);
        assertNotNull(nhanViens);
    }

    @Test(description = "Kiểm thử lấy theo SDT")
    public void testSelectBySDT() {
        NhanVien nhanVien = nhanVienDAO.selectBySDT("0886314559");
        assertNotNull(nhanVien);
    }

    @Test(description = "Kiểm tra sự tồn tại của Mã NV")
    public void testMaNVExists() {
        boolean exists = nhanVienDAO.maNVExists("NV00001");
        assertTrue(exists);
    }

    @Test(description = "Kiểm thử lấy theo Email")
    public void testSelectByEmail() {
        NhanVien nhanVien = nhanVienDAO.selectByEmail("tranb@gmail.com");
        assertNotNull(nhanVien);
    }
}
