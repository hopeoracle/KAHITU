package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.DonHang;
import com.fsm.utils.jdbcHelper;
import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DonHangDAO extends ShopAoQuanDAO<DonHang,String> {
    final String INSERT_SQL ="insert into DonHang(MaDH, MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE DonHang SET MaKH = ?, MaNV= ?, NgayLap = ?, TongTien= ?, GhiChu=? where MaDH = ?";
    final String DELETE_SQL ="DELETE from DonHang WHERE MaDH = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM DonHang";
    final String SELECT_BY_ID_SQL ="SELECT * FROM DonHang WHERE MaDH= ?";
    final String SELECT_TENBYID = "SELECT HOTEN FROM DONHANG INNER JOIN NHANVIEN \n" +
                                  "ON NHANVIEN.MANV = DonHang.MANV\n" +
                                  "WHERE DONHANG.MANV = ?";
    final String UPDATE_TongTien ="UPDATE DonHang SET TongTien= ? where MaDH = ?";
    final String SELECT_GetTongTien ="select TongTien from DonHang where MaDH = ?";
    

    @Override
    public void insert(DonHang entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaDH(),entity.getMaKH(),entity.getMaNV(),entity.getNgayLap(),entity.getTongTien(),entity.getGhiChu());
    }

    @Override
    public void update(DonHang entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMaKH(),entity.getMaNV(),entity.getNgayLap(),entity.getTongTien(),entity.getGhiChu(),entity.getMaDH());
    }
    
    public void updateTongTien(DonHang entity) {
        jdbcHelper.Update(UPDATE_TongTien,entity.getTongTien(),entity.getMaDH());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<DonHang> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHang selectById(String id) {
        List<DonHang> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                DonHang entity = new DonHang();
                entity.setMaDH(rs.getString("MaDH"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<DonHang> selectNhanVienByMaNV(String maNV) {
         return selectBySql(SELECT_TENBYID,maNV);
    }
    
    public List<DonHang> selectNhanVienByMaDH(String maDH) {
         return selectBySql(SELECT_GetTongTien,maDH);
    }
    
    public List<Date> selectDay(){
        String sql = "select distinct NgayLap from DonHang order by NgayLap desc";
        List<Date> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getDate(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<String> selectMonth() {
        String sql = "SELECT \n"
                + "    CONCAT(YEAR(NgayLap), '-', MONTH(NgayLap)) \n"
                + "FROM DonHang\n"
                + "GROUP BY YEAR(NgayLap), MONTH(NgayLap);";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Integer> selectYear() {
        String sql = "SELECT YEAR(NgayLap) FROM DonHang GROUP BY YEAR(NgayLap);";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
}
