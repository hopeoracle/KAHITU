package ShopQuanLyAoQuan.entity.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;

import ShopQuanLyAoQuan.entity.DonHang;
import ShopQuanLyAoQuan.entity.DonHangChiTiet;

public class DonHangTest {

  @Test
  public void getGhiChuTest() {
	  DonHang dh = new DonHang();
	    String expResult = null;
	    String result = dh.getGhiChu();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaDHTest() {
	  DonHang dh = new DonHang();
	    String expResult = null;
	    String result = dh.getGhiChu();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaKHTest() {
	  DonHang dh = new DonHang();
	    String expResult = null;
	    String result = dh.getMaKH();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaNVTest() {
	  DonHang dh = new DonHang();
	    String expResult = null;
	    String result = dh.getMaNV();
	    assertEquals(expResult, result);
  }

  @Test
  public void getNgayLapTest() {
	  DonHang dh = new DonHang();
	    Date expResult = null;
	    Date result = dh.getNgayLap();
	    assertEquals(expResult, result);
  }

  @Test
  public void getTongTienTest() {
	  DonHang dh = new DonHang();
	    float expResult = 0;
	    float result = dh.getTongTien();
	    assertEquals(result, expResult);
  }

  @Test
  public void setGhiChuTest() {
	  String ghiChu = "Kiem Thu";
	    DonHang dh = new DonHang();
	    dh.setGhiChu(ghiChu);
	    String expResult = "Kiem Thu";
	    assertEquals(expResult, dh.getGhiChu());
  }

  @Test
  public void setMaDHTest() {
	  String maDH = "DH02";
	    DonHang dh = new DonHang();
	    dh.setMaDH(maDH);
	    String expResult = "DH02";
	    assertEquals(expResult, dh.getMaDH());
  }

  @Test
  public void setMaKHTest() {
	  String maKH = "KH02";
	    DonHang dh = new DonHang();
	    dh.setMaKH(maKH);
	    String expResult = "KH02";
	    assertEquals(expResult, dh.getMaKH());
  }

  @Test
  public void setMaNVTest() {
	  String maNV = "NV02";
	    DonHang dh = new DonHang();
	    dh.setMaNV(maNV);
	    String expResult = "NV02";
	    assertEquals(expResult, dh.getMaNV());
  }

  @Test
  public void setNgayLapTest() {
	  Calendar calendar = Calendar.getInstance();
	    calendar.set(2024, 04, 06);
	    Date validDate = calendar.getTime();
	    DonHang dh = new DonHang();

	    // Act
	    dh.setNgayLap(validDate);

	    // Assert
	    assertEquals(validDate, dh.getNgayLap());
  }
  @Rule
  ExpectedException setNgayLap_withInvalidDate = ExpectedException.none();
  @Test
  public void setNgayLap_withInvalidDate() {
      // Arrange
      Calendar calendar = Calendar.getInstance();
      calendar.set(2022, 13, 31);
      Date invalidDate = calendar.getTime();
      DonHang dh = new DonHang();
      // Act and Assert
      dh.setNgayLap(invalidDate);
  }

  @Test
  public void setTongTienTest() {
	  float tongTien = 10;
	    DonHang dh = new DonHang();
	    dh.setTongTien(tongTien);
	    float actual = dh.getTongTien();
	    assertEquals(tongTien, actual);
  }
}
