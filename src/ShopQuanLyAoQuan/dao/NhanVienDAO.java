package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.NhanVien;
import ShopQuanLyAoQuan.entity.TaiKhoan;
import com.fsm.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends ShopAoQuanDAO<NhanVien,String> {
    final String INSERT_SQL ="insert into NhanVien(MaNV, HoTen, DiaChi, SDT, Email, NgaySinh, Luong, GhiChu) VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE NhanVien SET HoTen = ?, DiaChi= ?, SDT = ?, Email= ?, NgaySinh= ?, Luong= ?, GhiChu=? where MaNV = ?";
    final String DELETE_SQL ="DELETE from NhanVien WHERE MaNV = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL ="SELECT * FROM NhanVien WHERE MaNV= ?";

    @Override
    public void insert(NhanVien entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaNV(),entity.getHoTen(),entity.getDiaChi(),entity.getSDT(), entity.getEmail(), entity.getNgaySinh(),entity.getLuong(), entity.getGhiChu());
    }

    @Override
    public void update(NhanVien entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getHoTen(),entity.getDiaChi(),entity.getSDT(), entity.getEmail(), entity.getNgaySinh(),entity.getLuong(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<NhanVien> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setLuong(rs.getFloat("Luong"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<NhanVien> timkiemByMaNVOrTenNV(String keyword, boolean isSearchByMaNV) {
    String sql;
    List<NhanVien> list = new ArrayList<>();
    try {
        if (isSearchByMaNV) {
            sql = "SELECT * FROM NhanVien WHERE MaNV LIKE ?";
        } else {
            sql = "SELECT * FROM NhanVien WHERE HoTen LIKE ?";
        }
        ResultSet rs = jdbcHelper.query(sql, "%" + keyword + "%");
        while (rs.next()) {
            NhanVien entity = new NhanVien();
            entity.setMaNV(rs.getString("MaNV"));
            entity.setHoTen(rs.getString("HoTen"));
            entity.setDiaChi(rs.getString("DiaChi"));
            entity.setSDT(rs.getString("SDT"));
            entity.setEmail(rs.getString("Email"));
            entity.setNgaySinh(rs.getDate("NgaySinh"));
            entity.setLuong(rs.getFloat("Luong"));
            entity.setGhiChu(rs.getString("GhiChu"));
            list.add(entity);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
    }
    public NhanVien selectBySDT(String sdt) {
    String SELECT_BY_SDT_SQL = "SELECT * FROM NhanVien WHERE SDT = ?";
    try {
        ResultSet rs = jdbcHelper.query(SELECT_BY_SDT_SQL, sdt);
        if (rs.next()) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV(rs.getString("MaNV"));
            // Cập nhật các trường thông tin khác tương ứng
            return nhanVien;
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return null;
}

public NhanVien selectByEmail(String email) {
    String SELECT_BY_EMAIL_SQL = "SELECT * FROM NhanVien WHERE Email = ?";
    try {
        ResultSet rs = jdbcHelper.query(SELECT_BY_EMAIL_SQL, email);
        if (rs.next()) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV(rs.getString("MaNV"));
            // Cập nhật các trường thông tin khác tương ứng
            return nhanVien;
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return null;
}
public boolean maNVExists(String maNV) {
    // Viết truy vấn kiểm tra sự tồn tại của MaNV trong cơ sở dữ liệu
    String sql = "SELECT COUNT(*) FROM NhanVien WHERE MaNV = ?";
    try {
        ResultSet rs = jdbcHelper.query(sql, maNV);
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return false;
}

    
}
