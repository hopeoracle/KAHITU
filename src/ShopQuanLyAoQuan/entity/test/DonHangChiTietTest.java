package ShopQuanLyAoQuan.entity.test;

import static org.testng.Assert.assertEquals;


import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;
import org.testng.Assert;


import ShopQuanLyAoQuan.entity.DonHangChiTiet;

public class DonHangChiTietTest {

  @Test
  public void getDonGiaTest() {
    DonHangChiTiet dhct = new DonHangChiTiet();
    float expResult = 0;
    float result = dhct.getDonGia();
    assertEquals(result, expResult);
  }

  @Test
  public void getGhiChuTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    String expResult = null;
	    String result = dhct.getGhiChu();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaDHTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    String expResult = null;
	    String result = dhct.getMaDH();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaDHCTTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    String expResult = null;
	    String result = dhct.getMaDHCT();
	    assertEquals(expResult, result);
  }

  @Test
  public void getMaSPTest() {
    DonHangChiTiet dhct = new DonHangChiTiet();
    String expResult = null;
    String result = dhct.getMaSP();
    assertEquals(expResult, result);
  }
  @Rule
  ExpectedException setSoLuongTestWithNegative  = ExpectedException.none();
  @Test
  public void setSoLuongTestWithNegative() {
    int soLuong = -10;
    DonHangChiTiet dhct = new DonHangChiTiet();
    dhct.setSoLuong(soLuong);
  }
  @Rule
  ExpectedException setSoLuongTestWithZero = ExpectedException.none();
  @Test
  public void setSoLuongTestWithZero() {
    int soLuong = 0;
    DonHangChiTiet dhct = new DonHangChiTiet();
    dhct.setSoLuong(soLuong);
  }
  
  @Test()
  public void setSoLuongTestWithValid() {
    int soLuong = 10;
    DonHangChiTiet dhct = new DonHangChiTiet();
    dhct.setSoLuong(soLuong);
    int actual = dhct.getSoLuong();
    assertEquals(actual, soLuong);
    }
  
  @Test
  public void getTotalPriceTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    Double expResult = (double) 0;
	    Double result = dhct.getTotalPrice();
	    assertEquals(expResult, result);
  }
  @Rule
  ExpectedException setDonGiaTestWithNegative = ExpectedException.none();
  @Test
  public void setDonGiaTestWithNegative() {
	  float donGia = -10;
	    DonHangChiTiet dhct = new DonHangChiTiet();
	    dhct.setDonGia(donGia);
  }
  @Rule
  ExpectedException setDonGiaTestWithZero = ExpectedException.none();
  @Test
  public void setDonGiaTestWithZero() {
	  float donGia = 0;
	    DonHangChiTiet dhct = new DonHangChiTiet();
	    dhct.setDonGia(donGia);
  }
  
  @Test
  public void setDonGiaTestWithValid() {
	  float donGia = 10;
	    DonHangChiTiet dhct = new DonHangChiTiet();
	    dhct.setDonGia(donGia);
	    float actual = dhct.getDonGia();
	    assertEquals(donGia, actual);
  }

  @Test
  public void setGhiChuTest() {
	  String ghiChu = "Kiem Thu";
	    DonHangChiTiet dhct = new DonHangChiTiet();
	    dhct.setGhiChu(ghiChu);
	    String expResult = "Kiem Thu";
	    assertEquals(expResult, dhct.getGhiChu());
  }

  @Test
  public void setMaDHTest() {
    String maDH = "DH02";
    DonHangChiTiet dhct = new DonHangChiTiet();
    dhct.setMaDH(maDH);
    String expResult = "DH02";
    assertEquals(expResult, dhct.getMaDH());
  }
  @Test
  public void setMaDHCTTest() {
    DonHangChiTiet dhct = new DonHangChiTiet();
    String maDHCT = "2";
    dhct.setMaDHCT(maDHCT);
    String expResult = "2";
    assertEquals(expResult, dhct.getMaDHCT());
  }

  @Test
  public void setMaSPTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    String maSP = "SP02";
	    dhct.setMaSP(maSP);
	    String expResult = "SP02";
	    assertEquals(expResult, dhct.getMaSP());
  }

  @Test
  public void getSoLuongTest() {
	  DonHangChiTiet dhct = new DonHangChiTiet();
	    int expResult = 0;
	    int result = dhct.getSoLuong();
	    assertEquals(result, expResult);
  }
}
