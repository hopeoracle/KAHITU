package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.DonHangChiTiet;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DonHangChiTietDAO extends ShopAoQuanDAO<DonHangChiTiet,String> {
    final String INSERT_SQL ="INSERT INTO DonHangChiTiet( MaSP, MaDH, SoLuong, GiaBan, GhiChu)  VALUES(?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE DonHangChiTiet SET MaSP = ?, MaDH= ?, SoLuong = ?, GiaBan= ?, GhiChu= ? where MaDHCT = ?";
    final String DELETE_SQL ="DELETE from DonHangChiTiet WHERE MaDHCT = ?";
    final String SELECT_ALL_SQL ="SELECT MaDHCT, MaDH, MaSP, SoLuong, GiaBan, GhiChu FROM DonHangChiTiet ";
    final String SELECT_BY_ID_SQL ="SELECT * FROM DonHangChiTiet WHERE MaDHCT= ?";
    final String SELECT_BY_ID_MaDH ="SELECT * FROM DonHangChiTiet where MaDH = ?";

    @Override
    public void insert(DonHangChiTiet entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaSP(),entity.getMaDH(),entity.getSoLuong(),entity.getDonGia(),entity.getGhiChu());
    }

    @Override
    public void update(DonHangChiTiet entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMaSP(),entity.getMaDH(),entity.getSoLuong(),entity.getDonGia(),entity.getGhiChu(),entity.getMaDHCT());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<DonHangChiTiet> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHangChiTiet selectById(String id) {
        List<DonHangChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    public List<DonHangChiTiet> selectByIdDH(String id) {
          List<DonHangChiTiet> list = new ArrayList<>();
          String sql="SELECT * FROM DonHangChiTiet where MaDH = ?";
        try {
            ResultSet rs = jdbcHelper.query(sql, id);
            while(rs.next()){
                DonHangChiTiet entity = new DonHangChiTiet();
                entity.setMaDHCT(rs.getString("MaDHCT"));
                entity.setMaDH(rs.getString("MaDH"));
                entity.setMaSP(rs.getString("MaSP"));              
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setDonGia(rs.getFloat("GiaBan"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<DonHangChiTiet> selectBySql(String sql, Object... args) {
        List<DonHangChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                DonHangChiTiet entity = new DonHangChiTiet();
                entity.setMaDHCT(rs.getString("MaDHCT"));
                entity.setMaDH(rs.getString("MaDH"));
                entity.setMaSP(rs.getString("MaSP"));              
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setDonGia(rs.getFloat("GiaBan"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
