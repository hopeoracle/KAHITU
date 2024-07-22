package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.TaiKhoan;
import com.fsm.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TaiKhoanDAO extends ShopAoQuanDAO<TaiKhoan,String> {
    final String INSERT_SQL ="insert into TaiKhoan(MaNV,MatKhau, HoTen, VaiTro) VALUES(?,?,?,?)";
    final String UPDATE_SQL ="UPDATE TaiKhoan SET MatKhau = ?, HoTen= ?, VaiTro = ? where MaNV = ?";
    final String DELETE_SQL ="DELETE from TaiKhoan WHERE MaNV = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM TaiKhoan";
    final String SELECT_BY_ID_SQL ="SELECT * FROM TaiKhoan WHERE MaNV= ?";
    
    @Override
    public void insert(TaiKhoan entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro());
    }

    @Override
    public void update(TaiKhoan entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro(),entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<TaiKhoan> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TaiKhoan selectById(String id) {
        List<TaiKhoan> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectBySql(String sql, Object... args) {
         List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                TaiKhoan entity = new TaiKhoan();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<TaiKhoan> timkiemByMaNVOrTenNV(String keyword, boolean isSearchByMaNV) {
    String sql;
    List<TaiKhoan> list = new ArrayList<>();
    try {
        if (isSearchByMaNV) {
            sql = "SELECT * FROM TaiKhoan WHERE MaNV LIKE ?";
        } else {
            sql = "SELECT * FROM TaiKhoan WHERE HoTen LIKE ?";
        }
        ResultSet rs = jdbcHelper.query(sql, "%" + keyword + "%");
        while (rs.next()) {
            TaiKhoan entity = new TaiKhoan();
            entity.setMaNV(rs.getString("MaNV"));
            entity.setMatKhau(rs.getString("MatKhau"));
            entity.setHoTen(rs.getString("HoTen"));
            entity.setVaiTro(rs.getBoolean("VaiTro"));
            list.add(entity);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
    }
    
    public boolean isMaNVTaken(String maNV) {
        String sql = "SELECT COUNT(*) AS count FROM TaiKhoan WHERE MaNV = ?";
        try {
            ResultSet rs = jdbcHelper.query(sql, maNV);
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
   

}
