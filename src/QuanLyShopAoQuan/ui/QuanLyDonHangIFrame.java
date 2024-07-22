package QuanLyShopAoQuan.ui;
import ShopQuanLyAoQuan.dao.DonHangChiTietDAO;
import ShopQuanLyAoQuan.dao.DonHangDAO;
import ShopQuanLyAoQuan.dao.NhanVienDAO;
import ShopQuanLyAoQuan.dao.SanPhamDAO;
import ShopQuanLyAoQuan.entity.DonHang;
import ShopQuanLyAoQuan.entity.DonHangChiTiet;
import ShopQuanLyAoQuan.entity.NhanVien;
import ShopQuanLyAoQuan.entity.SanPham;
import com.fsm.utils.MsgBox;
import com.fsm.utils.XDate;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QuanLyDonHangIFrame extends javax.swing.JInternalFrame {
    DonHangDAO dao = new DonHangDAO();
    NhanVienDAO nvDao = new NhanVienDAO();
    SanPhamDAO spDao = new SanPhamDAO();
    DonHangChiTietDAO dhctDAO = new DonHangChiTietDAO();
    
    int row = 0;
    int index = 0;
    private List<NhanVien> danhSachNhanVien;
    private List<SanPham> danhSachSanPham;

    
    public QuanLyDonHangIFrame() {
        initComponents();
        fillToTable();
        fillComboboxMaNV();
        fillComboboxMaSP();
        txtTongTien.setEditable(false);
        txtNgayLap.setEditable(false);
    }
    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        model.setRowCount(0);
        try {
            List<DonHang> list = dao.selectALl();
            for (DonHang dh : list) {
                Object[] row
                        = {
                            dh.getMaDH(), dh.getMaNV(), dh.getMaKH(), dh.getNgayLap(), dh.getTongTien(), dh.getGhiChu()
                        };
                model.addRow(row);
            }
//            fillToTable_DHCT();
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }

    void fillToTable_DHCT() {
        DefaultTableModel model = (DefaultTableModel) tblDHCT.getModel();
        model.setRowCount(0);
        try {
            List<DonHangChiTiet> list = dhctDAO.selectALl();
            for (DonHangChiTiet dhct : list) {
                Object[] row
                        = {
                            dhct.getMaDHCT(), dhct.getMaDH(), dhct.getMaSP(), dhct.getSoLuong(), dhct.getDonGia(), dhct.getGhiChu()
                        };
                model.addRow(row);
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }
    
    void filltoTableDHCT_DH(){
        DefaultTableModel model = (DefaultTableModel) tblDHCT.getModel();
        model.setRowCount(0);
        double sum1 = 0;
        int i = tblDonHang.getSelectedRow();
        String maDH = String.valueOf(tblDonHang.getValueAt(i, 0));
        try {
            
            List<DonHangChiTiet> list = (List<DonHangChiTiet>) dhctDAO.selectByIdDH(maDH);
            for (DonHangChiTiet dhct : list) {
                Object[] row
                        = {
                            dhct.getMaDHCT(), dhct.getMaDH(), dhct.getMaSP(), dhct.getSoLuong(), dhct.getDonGia(), dhct.getGhiChu()
                        };
                model.addRow(row);
                
                sum1 += (double) dhct.getDonGia() * dhct.getSoLuong();
                
            }
            DonHang dh = new DonHang();
            dh.setMaDH(maDH);
            dh.setTongTien((float) sum1);
            dao.updateTongTien(dh);
            int roundedNumber;
            roundedNumber = (int) Math.round(sum1);
            txtTongTien.setText(String.valueOf(roundedNumber));
            fillToTable();
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }
    


    void fillComboboxMaNV() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        try {
            // Check and update the list of employees if it hasn't been initialized or is empty
            if (danhSachNhanVien == null || danhSachNhanVien.isEmpty()) {
                danhSachNhanVien = nvDao.selectALl();
            }

            for (NhanVien nv : danhSachNhanVien) {
                model.addElement(nv.getHoTen());  // Add employee names to the model
            }

            // Set the new model to the ComboBox
            cboNV.setModel(model);
            // Set a renderer to display additional information if needed
            cboNV.setRenderer(new EmployeeComboBoxRenderer());

        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    private float Double(double sum1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

// Renderer to display additional information in the ComboBox
    class EmployeeComboBoxRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof NhanVien) {
                NhanVien employee = (NhanVien) value;
                setText(employee.getHoTen());  // Display the employee name
                setToolTipText("Mã NV: " + employee.getMaNV());  // Set tooltip with additional information
            }

            return this;
        }
    }

    void fillComboboxMaSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSP.getModel();
        model.removeAllElements(); // Xóa tất cả các phần tử trong combobox trước khi thêm mới

        try {
            // Kiểm tra và cập nhật danh sách nhân viên nếu nó chưa được khởi tạo hoặc trống
            if (danhSachSanPham == null || danhSachSanPham.isEmpty()) {
                danhSachSanPham = spDao.selectALl();
            }

            for (SanPham sp : danhSachSanPham) {
                model.addElement(sp.getTenSP());
                sp.getMaSP();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
   
    public boolean check_HoaDon()
    {
        if(txtMaHD.getText().equals(""))
        {
            MsgBox.alert(this, "Không được để trống mã hóa đơn!");
            txtMaHD.requestFocus();
            return false;
        }
        
        if(txtMaKH.getText().equals(""))
        {
            MsgBox.alert(this, "Không được để trống mã khách hàng!");
            txtMaKH.requestFocus();
            return false;
        }
       
        if(txtTongTien.getText().isEmpty() || !txtTongTien.getText().matches("\\d+") || Integer.parseInt(txtTongTien.getText()) < 0) {
            MsgBox.alert(this, "Nhập tổng tiền là số nguyên dương!");
            txtTongTien.requestFocus();
            return false;
         }
        return true;    
        }
    public boolean check_HoaDonChiTiet()
    {
        if(txtMaHoaDon.getText().equals(""))
        {
            MsgBox.alert(this, "Không được để trống mã hóa đơn!");
            txtMaHoaDon.requestFocus();
            return false;
        }
          // Kiểm tra số lượng là số nguyên và lớn hơn 0
        if(!txtSoLuong.getText().matches("\\d+") || Integer.parseInt(txtSoLuong.getText()) <= 0) {
            MsgBox.alert(this, "Nhập số lượng là số nguyên lớn hơn 0!");
            txtSoLuong.requestFocus();
            return false;
        }
        if(txtDonGia.getText().equals("") || !txtDonGia.getText().matches("\\d+"))
        {
            MsgBox.alert(this, "Nhập đơn giá ( dưới dạng số) !");
            txtDonGia.requestFocus();
            return false;
        }
           
        return true;
    }

    void edit() {
        try {
            String maDH = (String) tblDonHang.getValueAt(this.row, 0);
            DonHang model = dao.selectById(maDH);
            if (model != null) {
                setFormDonHang(model);
                updateStatus();
//                tabs.setSelectedIndex(0); 
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void edit_DHCT() {
        try {
            String maDHCT = (String) tblDHCT.getValueAt(this.index, 0);
            DonHangChiTiet model = dhctDAO.selectById(maDHCT);
            if (model != null) {
                setFormDonHangchiTiet(model);
                updateStatus_DHCT();
//                tabs.setSelectedIndex(0); 
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu edit! " + e);
        }
    }

    DonHang getFormDonHang() throws Exception {
        DonHang dh = new DonHang();
        String TenSelect = (String) cboNV.getSelectedItem();
        NhanVien SelectNhanVien = getNhanVienBytenNV(TenSelect);

        if (SelectNhanVien != null) {
            String maNV = SelectNhanVien.getMaNV();
            dh.setMaNV(maNV);
        }

        dh.setMaDH(txtMaHD.getText());

        dh.setMaKH(txtMaKH.getText());

        dh.setTongTien(Float.parseFloat(txtTongTien.getText()));
        dh.setNgayLap(new Date());
        dh.setGhiChu(txtGhiChu.getText());
        return dh;
    }

    DonHangChiTiet getFormDonHangChiTiet() throws Exception {
        DonHangChiTiet dhct = new DonHangChiTiet();
        String TenSelect = (String) cboSP.getSelectedItem();
        SanPham SelectSanPham = getNhanVienBytenSP(TenSelect);

        if (SelectSanPham != null) {
            String maSP = SelectSanPham.getMaSP();
            dhct.setMaSP(maSP);
        }
        dhct.setMaDH(txtMaHoaDon.getText());

        dhct.setDonGia(Float.parseFloat(txtDonGia.getText()));
        dhct.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        dhct.setGhiChu(txtGhiChu.getText());
        return dhct;
    }
    
// Hàm tham chiếu qua danh sách nhân viên để lấy thông tin nhân viên bằng mã nhân viên
    private NhanVien getNhanVienByMaNV(String MaNV) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaNV().equals(MaNV)) {
                return nhanVien;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    private NhanVien getNhanVienBytenNV(String tenNV) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getHoTen().equals(tenNV)) {
                return nhanVien;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    private SanPham getNhanVienBytenSP(String tenSP) {
        for (SanPham sanPham : danhSachSanPham) {
            if (sanPham.getTenSP().equals(tenSP)) {
                return sanPham;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    void setFormDonHang(DonHang model) {
        try {
            txtMaHD.setText(model.getMaDH());
            txtMaKH.setText(model.getMaKH());

            txtNgayLap.setText(XDate.toString(model.getNgayLap(), "yyyy-MM-dd"));
            int roundedNumber;
            roundedNumber = Math.round(model.getTongTien());
            txtTongTien.setText(String.valueOf(roundedNumber));
            txtGhiChu.setText(model.getGhiChu());

            // Lấy mã nhân viên từ DonHang
            String maNV = model.getMaNV();

            // Kiểm tra xem danhSachNhanVien có được khởi tạo và có chứa dữ liệu hay không
            if (danhSachNhanVien != null && !danhSachNhanVien.isEmpty()) {
                // Tham chiếu qua lớp NhanVien để lấy thông tin nhân viên
                NhanVien nhanVien = getNhanVienByMaNV(maNV);

                // Kiểm tra xem có nhân viên tương ứng không
                if (nhanVien != null) {
                    // Đổ tên nhân viên lên cboNV
                    cboNV.setSelectedItem(nhanVien.getHoTen());
                } else {
                    // Xử lý khi không tìm thấy nhân viên tương ứng
                    MsgBox.alert(this, "Không tìm thấy nhân viên tương ứng.");
                }
            } else {
                // Nếu danhSachNhanVien chưa được khởi tạo hoặc không có dữ liệu,
                // bạn có thể cố gắng lấy danh sách nhân viên từ CSDL hoặc nguồn dữ liệu khác
                danhSachNhanVien = nvDao.selectALl();

                if (!danhSachNhanVien.isEmpty()) {
                    // Nếu có dữ liệu, tiếp tục thực hiện
                    NhanVien nhanVien = getNhanVienByMaNV(maNV);

                    if (nhanVien != null) {
                        cboNV.setSelectedItem(nhanVien.getHoTen());
                    } else {
                        MsgBox.alert(this, "Không tìm thấy nhân viên tương ứng.");
                    }
                } else {
                    // Xử lý khi danhSachNhanVien vẫn trống sau khi lấy từ nguồn dữ liệu
                    MsgBox.alert(this, "Danh sách nhân viên không tồn tại hoặc trống.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi: " + e.getMessage());
        }
    }

    private SanPham getSanPhamByMaSP(String maSP) {
        for (SanPham sanPham : danhSachSanPham) {
            if (sanPham.getMaSP().equals(maSP)) {
                return sanPham;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    void setFormDonHangchiTiet(DonHangChiTiet model) {
        try {
            txtMaHoaDon.setText(model.getMaDH());
            txtSoLuong.setText(String.valueOf(model.getSoLuong()));
            int roundedNumber;
            roundedNumber = (int) Math.ceil(model.getDonGia());
            txtDonGia.setText(String.valueOf(roundedNumber));
            txtGhiChu.setText(model.getGhiChu());

            // Lấy mã sản phẩm từ DonHangChiTiet 
            String maSP = model.getMaSP();

            // Kiểm tra xem danhSachSanPham có được khởi tạo và có chứa dữ liệu hay không
            if (danhSachSanPham != null && !danhSachSanPham.isEmpty()) {
                // Tham chiếu qua lớp SanPham để lấy thông tin sản phẩm
                SanPham sanPham = getSanPhamByMaSP(maSP);

                // Kiểm tra xem có sản phẩm tương ứng không
                if (sanPham != null) {
                    // Đổ tên sản phẩm lên cboSP
                    cboSP.setSelectedItem(sanPham.getTenSP());
                } else {
                    // Xử lý khi không tìm thấy sản phẩm tương ứng
                    MsgBox.alert(this, "Không tìm thấy sản phẩm tương ứng.");
                }
            } else {
                // Nếu danhSachSanPham chưa được khởi tạo hoặc không có dữ liệu,
                // bạn có thể cố gắng lấy danh sách sản phẩm từ CSDL hoặc nguồn dữ liệu khác
                danhSachSanPham = spDao.selectALl();

                if (!danhSachSanPham.isEmpty()) {
                    // Nếu có dữ liệu, tiếp tục thực hiện
                    SanPham sanPham = getSanPhamByMaSP(maSP);

                    if (sanPham != null) {
                        cboNV.setSelectedItem(sanPham.getTenSP());
                    } else {
                        MsgBox.alert(this, "Không tìm thấy sản phẩm tương ứng.");
                    }
                } else {
                    // Xử lý khi danhSachSanPham vẫn trống sau khi lấy từ nguồn dữ liệu
                    MsgBox.alert(this, "Danh sách sản phẩm không tồn tại hoặc trống.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi: " + e.getMessage());
        }
    }

    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblDonHang.getRowCount() - 1;
//        txt.setEditable(!edit);
        //khi insert thì k update, delete
        btnLuu.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void updateStatus_DHCT() {
        boolean edit = this.index >= 0;
        boolean first = this.index == 0;
        boolean last = this.index == tblDHCT.getRowCount() - 1;
//        txt.setEditable(!edit);
        //khi insert thì k update, delete
        btnThem.setEnabled(!edit);
        btnXoa2.setEnabled(edit);
        btnSuaDHCT.setEnabled(edit);

        btnFirst2.setEnabled(edit && !first);
        btnPrev2.setEnabled(edit && !first);
        btnNext2.setEnabled(edit && !last);
        btnLast2.setEnabled(edit && !last);
    }

    void clear() {
        DonHang dh = new DonHang();
        txtMaHD.setText("");
        txtMaKH.setText("");
        cboNV.setSelectedIndex(0);
        txtNgayLap.setText("");
        txtTongTien.setText("0");
        txtGhiChu.setText("");
        this.updateStatus();
        this.row = -1;
        updateStatus();
    }

    void clearDHCT() {
        DonHangChiTiet dhct = new DonHangChiTiet();
        txtMaHoaDon.setText("");
        txtSoLuong.setText("");
        cboSP.setSelectedIndex(0);
        txtDonGia.setText("");
        txtGhiChu.setText("");
        this.updateStatus_DHCT();
        this.row = -1;
        updateStatus_DHCT();
    }

    void insert() {
        try {
            
            if(check_HoaDon())
            {
                DonHang model = getFormDonHang();
                try {
                dao.insert(model);
                this.fillToTable();
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Thêm mới thất bại!");
                }  
            }
            
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi insert đơn hàng!");
        }
    }

    void insert_DHCT() {
        try {
            if(check_HoaDonChiTiet())
            {
               DonHangChiTiet model = getFormDonHangChiTiet();
                try {

                dhctDAO.insert(model);
                this.fillToTable_DHCT();
                index++;
                MsgBox.alert(this, "Thêm mới thành công!");
                } catch (Exception e) {
                e.printStackTrace();
                MsgBox.alert(this, "Thêm mới thất bại!");
                } 
            }
            
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi insert đơn hàng chi tiết!");
        }
    }
    
     public boolean existsInTable(JTable table, String maDH) {
        DefaultTableModel model = (DefaultTableModel) tblDonHang.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String currentMaDH = (String) model.getValueAt(i, 0); // Giả sử cột đầu tiên là cột chứa mã đơn hàng

            // Kiểm tra xem maDH có trùng với mã đơn hàng trong bảng không
            if (maDH.equals(currentMaDH)) {
                return true; // Mã đơn hàng đã tồn tại trong bảng
            }
        }

        return false; // Mã đơn hàng không tồn tại trong bảng
    }
     

    void update() throws Exception {
        if(tblDonHang.getSelectedRow()== -1)
            {
                MsgBox.alert(this, "Phải chọn dòng muốn sửa!");
            }else if(check_HoaDon())
        {
         DonHang model = getFormDonHang();
         String maDH = txtMaHD.getText();
         try {
             if(existsInTable(tblDonHang, maDH)){
            dao.update(model);
            this.fillToTable();
            MsgBox.alert(this, "Cập nhật thành công!");
             }else{
                 MsgBox.alert(this, "MaDH không tồn tại!");
             }
         } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
            System.out.println(e);
         }   
        }
    }
    

    
    void updateDHCT() throws Exception {
//       DonHangChiTiet model = getFormDonHangChiTiet();
        if(tblDHCT.getSelectedRow()== -1)
            {
                MsgBox.alert(this, "Phải chọn dòng muốn sửa!");
            }else if(check_HoaDonChiTiet())
        {
            try {
                int i = tblDHCT.getSelectedRow();
                String maDHCT = String.valueOf(tblDHCT.getValueAt(i, 0));
                DonHangChiTiet dhct = dhctDAO.selectById(maDHCT);
                dhct.setMaDH(txtMaHoaDon.getText());

                String TenSelect = (String) cboSP.getSelectedItem();
                SanPham SelectSanPham = getNhanVienBytenSP(TenSelect);

                if (SelectSanPham != null) {
                    String maSP = SelectSanPham.getMaSP();
                    dhct.setMaSP(maSP);
                }

                dhct.setDonGia(Float.parseFloat(txtDonGia.getText()));

                dhct.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                dhct.setGhiChu(txtGhiChu.getText());
                String maDH = txtMaHoaDon.getText();
                if(existsInTable(tblDonHang,maDH )){
                dhctDAO.update(dhct);

                this.fillToTable_DHCT();
                MsgBox.alert(this, "Cập nhật thành công!");
                }else{
                    MsgBox.alert(this, "MaDH không tồn tại");
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println(e);
            }
        }  
    }

    void delete() {
         if(tblDonHang.getSelectedRow()== -1)
            {
                MsgBox.alert(this, "Phải chọn dòng cần xóa!");
            }else
         {
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa đơn hàng này?")) {
            String maHD = txtMaHD.getText();
            try {
                dao.delete(maHD);
                this.fillToTable();
                this.clear();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }}
    }

    void delete_DHCT() {
         if(tblDHCT.getSelectedRow()== -1)
            {
                MsgBox.alert(this, "Phải chọn dòng cần xóa!");
            }
         else if (MsgBox.confirm(this, "Bạn thực sự muốn xóa đơn này?")) {
            int i = tblDHCT.getSelectedRow();
            String maHDCT = String.valueOf(tblDHCT.getValueAt(i, 0));
            try {
                dhctDAO.delete(maHDCT);
                this.fillToTable_DHCT();
                index--;
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void first() {
        row = 0;
        tblDonHang.setRowSelectionInterval(row, row);
        edit();
    }

    void prev() {
        if (row > 0) {
            
            row--;
            tblDonHang.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void next() {
        if (row < tblDonHang.getRowCount() - 1) {
            row++;
            tblDonHang.setRowSelectionInterval(row, row);
            edit();
        }
    }

    void last() {
        row = tblDonHang.getRowCount() - 1;
        tblDonHang.setRowSelectionInterval(row, row);
        edit();
    }

    void first2() {
        index = 0;
        tblDHCT.setRowSelectionInterval(index, index);
        edit_DHCT();
    }

    void prev2() {
        if (index > 0) {
            index--;
            tblDHCT.setRowSelectionInterval(index, index);
            edit_DHCT();
        }
    }

    void next2() {
        if (index < tblDHCT.getRowCount() - 1) {
            index++;
            tblDHCT.setRowSelectionInterval(index, index);
            edit_DHCT();
        }
    }

    void last2() {
        index = tblDHCT.getRowCount() - 1;
        tblDHCT.setRowSelectionInterval(index, index);
        edit_DHCT();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboNV = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboSP = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnPrev2 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnFirst2 = new javax.swing.JButton();
        btnLast2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChuHDCT = new javax.swing.JTextArea();
        btnSuaDHCT = new javax.swing.JButton();
        btnReSet = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDHCT = new javax.swing.JTable();
        btnXemAll = new javax.swing.JButton();

        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("QUẢN LÝ ĐƠN HÀNG");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tùy chỉnh đơn hàng"));

        jLabel3.setText("Mã DH:");

        jLabel4.setText("Nhân Viên:");

        jLabel5.setText("Ngày lập:");

        jLabel6.setText("Tổng tiền:");

        txtTongTien.setText("0");

        jLabel7.setText("Khách hàng:");

        jLabel1.setText("Ghi chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtGhiChu);

        btnMoi.setBackground(new java.awt.Color(255, 204, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Clipboard.png"))); // NOI18N
        btnMoi.setText("Reset");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/files-edit-icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(255, 204, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(255, 204, 255));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(255, 204, 255));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(255, 204, 255));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Previous.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 204, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(txtMaHD))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayLap))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTien))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnFirst)
                                .addGap(33, 33, 33)
                                .addComponent(btnPrev)
                                .addGap(43, 43, 43)
                                .addComponent(btnNext)
                                .addGap(37, 37, 37)
                                .addComponent(btnLast)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMaKH)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnPrev)
                        .addComponent(btnFirst)
                        .addComponent(btnNext))
                    .addComponent(btnLast))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tùy chỉnh đơn hàng chi tiết"));

        jLabel10.setText("Mã DH:");

        jLabel11.setText("Sản phẩm:");

        jLabel12.setText("Số lượng:");

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel13.setText("Đơn giá:");

        btnThem.setBackground(new java.awt.Color(255, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Save.png"))); // NOI18N
        btnThem.setText("Lưu");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(255, 204, 255));
        btnXoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Delete.png"))); // NOI18N
        btnXoa2.setText("Xoá");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnPrev2.setBackground(new java.awt.Color(255, 204, 255));
        btnPrev2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/First.png"))); // NOI18N
        btnPrev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev2ActionPerformed(evt);
            }
        });

        btnNext2.setBackground(new java.awt.Color(255, 204, 255));
        btnNext2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Last.png"))); // NOI18N
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        btnFirst2.setBackground(new java.awt.Color(255, 204, 255));
        btnFirst2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Previous.png"))); // NOI18N
        btnFirst2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst2ActionPerformed(evt);
            }
        });

        btnLast2.setBackground(new java.awt.Color(255, 204, 255));
        btnLast2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Next.png"))); // NOI18N
        btnLast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ghi chú:");

        txtGhiChuHDCT.setColumns(20);
        txtGhiChuHDCT.setRows(5);
        jScrollPane4.setViewportView(txtGhiChuHDCT);

        btnSuaDHCT.setBackground(new java.awt.Color(255, 204, 255));
        btnSuaDHCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/files-edit-icon.png"))); // NOI18N
        btnSuaDHCT.setText("Sửa");
        btnSuaDHCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDHCTActionPerformed(evt);
            }
        });

        btnReSet.setBackground(new java.awt.Color(255, 204, 255));
        btnReSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Clipboard.png"))); // NOI18N
        btnReSet.setText("Reset");
        btnReSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReSetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSoLuong)
                                        .addComponent(cboSP, 0, 185, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReSet)
                            .addComponent(btnSuaDHCT)
                            .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnPrev2)
                                .addGap(28, 28, 28)
                                .addComponent(btnFirst2)
                                .addGap(30, 30, 30)
                                .addComponent(btnLast2)
                                .addGap(36, 36, 36)
                                .addComponent(btnNext2))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnReSet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cboSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSuaDHCT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoa2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrev2)
                    .addComponent(btnFirst2)
                    .addComponent(btnLast2)
                    .addComponent(btnNext2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách đơn hàng"));

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HD", "NHÂN VIÊN", "KHÁCH HÀNG", "NGÀY LẬP", "TÔNG TIỀN", "GHI CHÚ"
            }
        ));
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDonHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tblDHCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ DHCT", "MÃ DH", "MÃ SP", "SỐ LƯỢNG", "ĐƠN GIÁ", "GHI CHÚ"
            }
        ));
        tblDHCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDHCTMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblDHCT);

        btnXemAll.setText("Xem tất cả");
        btnXemAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXemAll)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXemAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
        
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String maDH = txtMaHoaDon.getText();
            insert_DHCT();

        
//        fillToTable();
         DefaultTableModel model = (DefaultTableModel) tblDHCT.getModel();
        model.setRowCount(0);
        double sum1 = 0;
        try {
            
            List<DonHangChiTiet> list = (List<DonHangChiTiet>) dhctDAO.selectByIdDH(maDH);
            for (DonHangChiTiet dhct : list) {
                Object[] row
                        = {
                            dhct.getMaDHCT(), dhct.getMaDH(), dhct.getMaSP(), dhct.getSoLuong(), dhct.getDonGia(), dhct.getGhiChu()
                        };
                model.addRow(row);
                
                sum1 += (double) dhct.getDonGia() * dhct.getSoLuong();
                
            }
            DonHang dh = new DonHang();
            dh.setMaDH(maDH);
            dh.setTongTien((float) sum1);
            dao.updateTongTien(dh);
            int roundedNumber;
            roundedNumber = (int) Math.ceil(dh.getTongTien());
            txtTongTien.setText(String.valueOf(roundedNumber));
            fillToTable();
            txtSoLuong.setText("");
            txtDonGia.setText("");
            txtGhiChu.setText("");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
 
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        // TODO add your handling code here:
         
         last2();
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void tblDonHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMousePressed
        // TODO add your handling code here:
         if(evt.getClickCount() == 1)
        {
            row = tblDonHang.getSelectedRow();
            this.row = tblDonHang.rowAtPoint(evt.getPoint());
//            int i = tblDonHang.getSelectedRow();
            String maDH = String.valueOf(tblDonHang.getValueAt(row, 0));
            txtMaHoaDon.setText(maDH);
            txtDonGia.setText("");
            txtSoLuong.setText("");
            txtGhiChuHDCT.setText("");
            edit();
            filltoTableDHCT_DH();
        
        }
    }//GEN-LAST:event_tblDonHangMousePressed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            // TODO add your handling code here:
            update();
        } catch (Exception ex) {
            Logger.getLogger(QuanLyDonHangIFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        insert();
        fillToTable();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        delete_DHCT();      
         String maDH = txtMaHoaDon.getText();

//        fillToTable();
         DefaultTableModel model = (DefaultTableModel) tblDHCT.getModel();
        model.setRowCount(0);
        double sum1 = 0;
        try {
            List<DonHangChiTiet> list = (List<DonHangChiTiet>) dhctDAO.selectByIdDH(maDH);
            for (DonHangChiTiet dhct : list) {
                Object[] row
                        = {
                            dhct.getMaDHCT(), dhct.getMaDH(), dhct.getMaSP(), dhct.getSoLuong(), dhct.getDonGia(), dhct.getGhiChu()
                        };
                model.addRow(row);
                
                sum1 += (double) dhct.getDonGia() * dhct.getSoLuong();
                
            }
            DonHang dh = new DonHang();
            dh.setMaDH(maDH);
            dh.setTongTien((float) sum1);
            dao.updateTongTien(dh);
            int roundedNumber;
            roundedNumber = (int) Math.ceil(dh.getTongTien());
            txtTongTien.setText(String.valueOf(roundedNumber));
            fillToTable();
        txtDonGia.setText("");
        txtGhiChuHDCT.setText("");
        txtSoLuong.setText("");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnFirst2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst2ActionPerformed
        // TODO add your handling code here:
        prev2();
    }//GEN-LAST:event_btnFirst2ActionPerformed

    private void tblDHCTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDHCTMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount() == 1)
        {
            index = tblDHCT.getSelectedRow();
            this.index = tblDHCT.rowAtPoint(evt.getPoint());          
            edit_DHCT();
        }
    }//GEN-LAST:event_tblDHCTMousePressed

    private void btnLast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast2ActionPerformed
        // TODO add your handling code here:
        next2();
    }//GEN-LAST:event_btnLast2ActionPerformed

    private void btnPrev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrev2ActionPerformed
        // TODO add your handling code here:
        
        first2();
    }//GEN-LAST:event_btnPrev2ActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnReSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReSetActionPerformed
        // TODO add your handling code here:
        clearDHCT();
        updateStatus_DHCT();
        btnThem.setEnabled(true);
    }//GEN-LAST:event_btnReSetActionPerformed

    private void btnSuaDHCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDHCTActionPerformed
        try {
            // TODO add your handling code here:
            updateDHCT();
//           this.fillToTable_DHCT();
        } catch (Exception ex) {
       
        }
        String maDH = txtMaHoaDon.getText();

//        fillToTable();
         DefaultTableModel model = (DefaultTableModel) tblDHCT.getModel();
        model.setRowCount(0);
        double sum1 = 0;
        try {
            List<DonHangChiTiet> list = (List<DonHangChiTiet>) dhctDAO.selectByIdDH(maDH);
            for (DonHangChiTiet dhct : list) {
                Object[] row
                        = {
                            dhct.getMaDHCT(), dhct.getMaDH(), dhct.getMaSP(), dhct.getSoLuong(), dhct.getDonGia(), dhct.getGhiChu()
                        };
                model.addRow(row);
                
                sum1 += (double) dhct.getDonGia() * dhct.getSoLuong();
                
            }
            DonHang dh = new DonHang();
            dh.setMaDH(maDH);
            dh.setTongTien((float) sum1);
            dao.updateTongTien(dh);
            int roundedNumber;
            roundedNumber = (int) Math.ceil(dh.getTongTien());
            txtTongTien.setText(String.valueOf(roundedNumber));
            fillToTable();
        txtDonGia.setText("");
        txtGhiChuHDCT.setText("");
        txtSoLuong.setText("");
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSuaDHCTActionPerformed

    private void btnXemAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemAllActionPerformed
        // TODO add your handling code here:
        fillToTable_DHCT();
    }//GEN-LAST:event_btnXemAllActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst2;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast2;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrev2;
    private javax.swing.JButton btnReSet;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaDHCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXemAll;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JComboBox<String> cboNV;
    private javax.swing.JComboBox<String> cboSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblDHCT;
    private javax.swing.JTable tblDonHang;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtGhiChuHDCT;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
