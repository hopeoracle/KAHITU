/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import com.fsm.utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String [] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
   
    
    public List<Object[]> getDoanhThu(Date day){
        String sql ="{CALL sp_Tho"
                + "ngKeDoanhThu(?)}";
        String[] cols ={"NhanVien","SoKH","DoanhThu"};
        return getListOfArray(sql, cols, day);
    }
    
    public List<Object[]> getDoanhThuTheoMonth(String month){
        String sql ="{CALL sp_ThongKeDoanhThuTheoMonth(?)}";
        String[] cols ={"NhanVien","SoKH","DoanhThu"};
        return getListOfArray(sql, cols, month);
    }
    
    public List<Object[]> getDoanhThuTheoYear(int year){
        String sql ="{CALL sp_ThongKeDoanhThuTheoYear(?)}";
        String[] cols ={"NhanVien","SoKH","DoanhThu"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getDoanhThuTheoKhoangNgay(Date dayFrom,Date dayTo){
        String sql ="{CALL sp_ThongKeDoanhThuTheoNgay(?,?)}";
        String[] cols ={"NhanVien","SoKH","DoanhThu"};
        return getListOfArray(sql, cols, dayFrom,dayTo);
    }
    
    public List<Object[]> getTonKho(){
        String sql ="{CALL sp_HangTonKho()}";
        String[] cols ={"TenSP","SoLuongNhap","SoLuongBan","ConLai"};
        return getListOfArray(sql, cols);
    }
    
    public List<Object[]> getTonKhobyID(String id){
        String sql ="{CALL sp_HangTonKhoID(?)}";
        String[] cols ={"TenSP","SoLuongNhap","SoLuongBan","ConLai"};
        return getListOfArray(sql, cols,id);
    }
    
    public List<Object[]> getTonKhobyName(String name){
        String sql ="{CALL sp_HangTonKhoName(?)}";
        String[] cols ={"TenSP","SoLuongNhap","SoLuongBan","ConLai"};
        return getListOfArray(sql, cols,name);
    }
  
}
