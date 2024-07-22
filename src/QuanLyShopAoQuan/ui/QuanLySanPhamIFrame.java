package QuanLyShopAoQuan.ui;

import ShopQuanLyAoQuan.dao.SanPhamDAO;
import ShopQuanLyAoQuan.entity.SanPham;
import ShopQuanLyAoQuan.dao.PhanLoaiSanPhamDAO;
import ShopQuanLyAoQuan.entity.PhanLoaiSanPham;
import com.fsm.utils.Auth;
import com.fsm.utils.MsgBox;
import com.fsm.utils.XImage;
import java.awt.Image;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MMSI
 */
public class QuanLySanPhamIFrame extends javax.swing.JInternalFrame {
    SanPhamDAO spDAO = new SanPhamDAO();
    PhanLoaiSanPhamDAO plspDAO = new PhanLoaiSanPhamDAO();
    JFileChooser filechooser = new JFileChooser(); // Khai báo và khởi tạo biến filechooser

    int row = 0;
    /**
     * Creates new form QuanLySanPhamIFrame
     */
    public QuanLySanPhamIFrame() {
        initComponents();
        fillTableSP();
        fillTableLoaiSP();
        fillTableSP2();
    }
    void fillTableSP() {
        DefaultTableModel tblModel = (DefaultTableModel) tblSanPham.getModel();
        tblModel.setRowCount(0);
        try {
            List<SanPham> listSP = spDAO.selectALl();
            for (SanPham sp : listSP) {
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getGiaNhap(), sp.getMaLoai(), sp.getSoLuongNhap(), sp.getGhiChu(), sp.getHinhAnh(), sp.getHinhAnh()};
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            System.out.println(e);
        }
    }

    void fillTableSP2() {
        DefaultTableModel tblModel = (DefaultTableModel) tblSanPham2.getModel();
        tblModel.setRowCount(0);
        try {
            List<SanPham> listSP = spDAO.selectALl();
            for (SanPham sp : listSP) {
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getGiaNhap(), sp.getMaLoai(), sp.getSoLuongNhap(), sp.getGhiChu()};
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            System.out.println(e);
        }
    }

    void fillTableLoaiSP() {
        DefaultTableModel tblModel = (DefaultTableModel) tblLoaiSP.getModel();
        tblModel.setRowCount(0);
        try {
            List<PhanLoaiSanPham> listPLSP = plspDAO.selectALl();
            for (PhanLoaiSanPham plsp : listPLSP) {
                Object[] row = {plsp.getMaLoai(), plsp.getTenLoai()};
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            System.out.println(e);
        }
    }

    void setFormSP(SanPham sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        // Hiển thị giá trị float với định dạng số thập phân
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        txtGiaNhap.setText(decimalFormat.format(sp.getGiaNhap()));
        txtMaLoai.setText(sp.getMaLoai());
        // Chuyển đổi int sang String trước khi đặt vào JTextField
        txtSoLuongNhap.setText(String.valueOf(sp.getSoLuongNhap()));
        txtGhiChu.setText(sp.getGhiChu());
        if (sp.getHinhAnh() != null) {
            lblHinhLogo.setToolTipText(sp.getHinhAnh());
            ImageIcon imageIcon = XImage.read(sp.getHinhAnh());

            int lblWidth = lblHinhLogo.getWidth();
            int lblHeight = lblHinhLogo.getHeight();

            Image img = imageIcon.getImage().getScaledInstance(lblWidth, lblHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);

            lblHinhLogo.setIcon(scaledIcon);
        }

    }

    void setFormPLSP(PhanLoaiSanPham plsp) {
        txtMaLoaiSP.setText(plsp.getMaLoai());
        txtTenLoaiSP.setText(plsp.getTenLoai());

    }

    void editSP() {
        try {
            String MaSP = (String) tblSanPham.getValueAt(row, 0);
            SanPham sp = spDAO.selectById(MaSP);
            if (sp != null) {
                setFormSP(sp);
//            setHinhAnh(sp.getHinhAnh());
                updateStatusSP();
                jTabbedPane1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    SanPham getFormSP() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());

        try {
            float giaNhap = Float.parseFloat(txtGiaNhap.getText());
            sp.setGiaNhap(giaNhap);
        } catch (NumberFormatException e) {
            // Xử lý khi người dùng nhập không đúng định dạng số
            sp.setGiaNhap(0); // Giá trị mặc định hoặc xử lý khác tùy vào yêu cầu của bạn
        }

        sp.setMaLoai(txtMaLoai.getText());

        // Chuyển đổi giá trị từ JTextField sang kiểu dữ liệu int cho số lượng nhập
        try {
            int soLuongNhap = Integer.parseInt(txtSoLuongNhap.getText());
            sp.setSoLuongNhap(soLuongNhap);
        } catch (NumberFormatException e) {
            // Xử lý khi người dùng nhập không đúng định dạng số
            sp.setSoLuongNhap(0); // Giá trị mặc định hoặc xử lý khác tùy vào yêu cầu của bạn
        }

        sp.setGhiChu(txtGhiChu.getText());
        sp.setHinhAnh(lblHinhLogo.getToolTipText());

        return sp;
    }

    void updateStatusSP() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblSanPham.getRowCount() - 1;
        txtMaSP.setEditable(edit);
        btnLuu.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    void clearFormSP() {
        this.setFormSP(new SanPham());
        this.updateStatusSP();
        row = - 1;
        updateStatusSP();
    }

    void insertSP() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền thêm sản phẩm!");
        } else {
            SanPham sp = getFormSP();
            String maSP = sp.getMaSP();
            String tenSP = sp.getTenSP();
            String giaNhapStr = txtGiaNhap.getText(); // Lấy dữ liệu từ JTextField
            String maLoai = sp.getMaLoai();
            String soLuongNhapStr = txtSoLuongNhap.getText(); // Lấy dữ liệu từ JTextField
            String hinhAnh = sp.getHinhAnh();

            // Kiểm tra tính hợp lệ của từng trường thông tin
            if (maSP.isEmpty()) {
                MsgBox.alert(this, "Mã sản phẩm không được để trống!");
                return;
            }
            if (!validateMaSP(maSP)) {
            MsgBox.alert(this, "Mã SP không đúng định dạng!");
            return;
        }

            if (spDAO.selectById(maSP) != null) {
                MsgBox.alert(this, "Mã sản phẩm " + maSP + " đã tồn tại!");
                return;
            }

            if (tenSP.isEmpty()) {
                MsgBox.alert(this, "Tên sản phẩm không được để trống!");
                return;
            }
            if (spDAO.selectByTenSP(tenSP) != null) {
                MsgBox.alert(this, "Tên sản phẩm " + tenSP + " đã tồn tại!");
                return;
            }

            float giaNhap;
            try {
                giaNhap = Float.parseFloat(giaNhapStr);
                if (giaNhap <= 0) {
                    MsgBox.alert(this, "Giá nhập phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                MsgBox.alert(this, "Giá nhập phải là số!");
                return;
            }

            if (maLoai.isEmpty()) {
                MsgBox.alert(this, "Mã loại không được để trống!");
                return;
            }

            int soLuongNhap;
            try {
                soLuongNhap = Integer.parseInt(soLuongNhapStr);
                if (soLuongNhap <= 0) {
                    MsgBox.alert(this, "Số lượng nhập phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                MsgBox.alert(this, "Số lượng nhập phải là số nguyên!");
                return;
            }

            if (hinhAnh.isEmpty()) {
                MsgBox.alert(this, "Hình ảnh không được để trống!");
                return;
            }
            if (!plspDAO.isMaLoaiExists(maLoai)) {
                MsgBox.alert(this, "Mã loại không tồn tại!");
                return;
            }

            try {
                spDAO.insert(sp);
                this.fillTableSP();
                this.clearFormSP();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                System.out.println(e);
            }
        }
    }
      boolean validateMaSP(String maSP) {
        // Kiểm tra định dạng mã sản phẩm, ví dụ: SP + số
        return maSP.matches("SP\\d+");
    }

    void updateSP() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền cập nhật sản phẩm!");
        }else if(tblSanPham.getSelectedRow()== -1)
            {
                MsgBox.alert(this, "Phải chọn dòng muốn sửa!");
            }   
        else {
            SanPham sp = getFormSP();
            String maSP = sp.getMaSP();
            String tenSP = sp.getTenSP();
            String giaNhapStr = txtGiaNhap.getText(); // Lấy dữ liệu từ JTextField
            String maLoai = sp.getMaLoai();
            String soLuongNhapStr = txtSoLuongNhap.getText(); // Lấy dữ liệu từ JTextField
            String hinhAnh = sp.getHinhAnh();

            // Kiểm tra tính hợp lệ của từng trường thông tin
            if (maSP.isEmpty()) {
                MsgBox.alert(this, "Mã sản phẩm không được để trống!");
                return;
            }

//            SanPham existingSP = spDAO.selectById(maSP);
//            if (existingSP != null && !existingSP.getMaSP().equals(sp.getMaSP())) {
//                MsgBox.alert(this, "Mã sản phẩm " + maSP + " đã tồn tại!");
//                return;
//            }

            if (tenSP.isEmpty()) {
                MsgBox.alert(this, "Tên sản phẩm không được để trống!");
                return;
            }

            // Kiểm tra trùng lặp chỉ khi giá trị mã sản phẩm hoặc tên sản phẩm được thay đổi
//             existingSP = spDAO.selectByTenSP(tenSP);
//            if (existingSP != null && !existingSP.getMaSP().equals(sp.getMaSP()) && !tenSP.equals(existingSP.getTenSP())) {
//                MsgBox.alert(this, "Tên sản phẩm " + tenSP + " đã tồn tại!");
//                return;
//            }


            float giaNhap;
            try {
                giaNhap = Float.parseFloat(giaNhapStr);
                if (giaNhap <= 0) {
                    MsgBox.alert(this, "Giá nhập phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                MsgBox.alert(this, "Giá nhập phải là số!");
                return;
            }

            if (maLoai.isEmpty()) {
                MsgBox.alert(this, "Mã loại không được để trống!");
                return;
            }

            int soLuongNhap;
            try {
                soLuongNhap = Integer.parseInt(soLuongNhapStr);
                if (soLuongNhap <= 0) {
                    MsgBox.alert(this, "Số lượng nhập phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                MsgBox.alert(this, "Số lượng nhập phải là số nguyên!");
                return;
            }

            if (hinhAnh.isEmpty()) {
                MsgBox.alert(this, "Hình ảnh không được để trống!");
                return;
            }
            if (!plspDAO.isMaLoaiExists(maLoai)) {
                MsgBox.alert(this, "Mã loại không tồn tại!");
                return;
            }

            try {
                spDAO.update(sp);
                this.fillTableSP();
                this.clearFormSP();
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
                System.out.println(e);
            }
        }
    }

    void deleteSP() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền xóa sản phẩm này!");
        } else if ((tblSanPham.getSelectedRow()== -1))
            {
                MsgBox.alert(this, "Phải chọn dòng cần xóa!");
            }
        else if (MsgBox.confirm(this, "Bạn thực sự muốn xóa sản phẩm này?")) {
            String masp = txtMaSP.getText();
            try {
                spDAO.delete(masp);
                this.fillTableSP();
                this.clearFormSP();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void firstSP() {
        row = 0;
        editSP();
        tblSanPham.setRowSelectionInterval(row, row);
    }

    void prevSP() {
        row--;
        if (row < 0) {
            row = 0;
        } else {
            tblSanPham.setRowSelectionInterval(row, row);
            editSP();
        }
    }

    void nextSP() {
        if (row < tblSanPham.getRowCount() - 1) {
            row++;
            tblSanPham.setRowSelectionInterval(row, row);
            editSP();

        }
    }

    void lastSP() {
        row = tblSanPham.getRowCount() - 1;
        tblSanPham.setRowSelectionInterval(row, row);
        editSP();

    }

    void editPLSP() {
        try {
            String MaLoai = (String) tblLoaiSP.getValueAt(row, 0);
            PhanLoaiSanPham plsp = plspDAO.selectById(MaLoai);
            if (plsp != null) {
                setFormPLSP(plsp);
                updateStatusPLSP();
                jTabbedPane1.setSelectedIndex(0);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
            System.out.println(e);
        }
    }

    PhanLoaiSanPham getFormPLSP() {
        PhanLoaiSanPham plsp = new PhanLoaiSanPham();
        plsp.setMaLoai(txtMaLoaiSP.getText());
        plsp.setTenLoai(txtTenLoaiSP.getText());

        return plsp;
    }

    void updateStatusPLSP() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblLoaiSP.getRowCount() - 1;
        txtMaLoaiSP.setEditable(!edit);
        btnLuu2.setEnabled(!edit);
        btnSua2.setEnabled(edit);
        btnXoa2.setEnabled(edit);
        btnFirst2.setEnabled(edit && !first);
        btnPrev2.setEnabled(edit && !first);
        btnNext2.setEnabled(edit && !last);
        btnLast2.setEnabled(edit && !last);
    }

    void clearForm() {
        this.setFormPLSP(new PhanLoaiSanPham());
        this.updateStatusPLSP();
        row = - 1;
        updateStatusPLSP();
    }

    void insertPLSP() {
        String maLoai = txtMaLoaiSP.getText();
        String tenLoai = txtTenLoaiSP.getText();

        if (maLoai.isEmpty()) {
            MsgBox.alert(this, "Mã loại không được để trống!");
            return;
        }

        if (tenLoai.isEmpty()) {
            MsgBox.alert(this, "Tên loại không được để trống!");
            return;
        }

        if (plspDAO.isMaLoaiExists(maLoai)) {
            MsgBox.alert(this, "Mã loại " + maLoai + " đã tồn tại! Vui lòng chọn mã khác.");
            return;
        }

        if (plspDAO.isTenLoaiExists(tenLoai)) {
            MsgBox.alert(this, "Tên loại " + tenLoai + " đã tồn tại! Vui lòng chọn tên khác.");
            return;
        }

        PhanLoaiSanPham model = new PhanLoaiSanPham();
        model.setMaLoai(maLoai);
        model.setTenLoai(tenLoai);

        try {
            plspDAO.insert(model);
            fillTableLoaiSP();
            clearForm();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại! Lỗi: " + e.getMessage());
        }
    }

    void updatePLSP() {
    String maLoai = txtMaLoaiSP.getText();
    String tenLoai = txtTenLoaiSP.getText();
    PhanLoaiSanPham plsp = getFormPLSP();

    if (maLoai.isEmpty()) {
        MsgBox.alert(this, "Mã loại không được để trống!");
        return;
    }

    if (tenLoai.isEmpty()) {
        MsgBox.alert(this, "Tên loại không được để trống!");
        return;
    }


    PhanLoaiSanPham model = new PhanLoaiSanPham();
    model.setMaLoai(maLoai);
    model.setTenLoai(tenLoai);

    try {
        plspDAO.update(model);
        fillTableLoaiSP();
        MsgBox.alert(this, "Cập nhật thành công!");
    } catch (Exception e) {
        MsgBox.alert(this, "Cập nhật thất bại! Lỗi: " + e.getMessage());
    }
}


    void deletePLSP() {
        String maLoai = txtMaLoaiSP.getText();

        if (!maLoai.isEmpty()) { // Kiểm tra mã loại có tồn tại để xóa hay không
            if (MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa không?")) {
                try {
                    plspDAO.delete(maLoai); // Gọi hàm xóa từ DAO hoặc Service của bạn
                    fillTableLoaiSP();
                    clearForm();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại! Lỗi: " + e.getMessage());
                }
            }
        } else {
            MsgBox.alert(this, "Vui lòng chọn loại sản phẩm cần xóa!");
        }
    }

    void firstPLSP() {
        row = 0;
        editPLSP();
        tblLoaiSP.setRowSelectionInterval(row, row);
        if (jTabbedPane1.getSelectedIndex() != 1) {
            jTabbedPane1.setSelectedIndex(1); // Set the tab back to "Loại Sản Phẩm"
        }
    }

    void prevPLSP() {
        row--;
        if (row < 0) {
            row = 0;
        } else {
            tblLoaiSP.setRowSelectionInterval(row, row);
            editPLSP();
            if (jTabbedPane1.getSelectedIndex() != 1) {
                jTabbedPane1.setSelectedIndex(1); // Set the tab back to "Loại Sản Phẩm"
            }
        }
    }

    void nextPLSP() {
        if (row < tblLoaiSP.getRowCount() - 1) {
            row++;
            tblLoaiSP.setRowSelectionInterval(row, row);
            editPLSP();
            if (jTabbedPane1.getSelectedIndex() != 1) {
                jTabbedPane1.setSelectedIndex(1); // Set the tab back to "Loại Sản Phẩm"
            }

        }
    }

    void lastPLSP() {
        row = tblLoaiSP.getRowCount() - 1;
        tblLoaiSP.setRowSelectionInterval(row, row);
        editPLSP();
        if (jTabbedPane1.getSelectedIndex() != 1) {
            jTabbedPane1.setSelectedIndex(1); // Set the tab back to "Loại Sản Phẩm"
        }

    }

    void ChonAnh() {
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();

            // Kiểm tra xem tệp đã chọn có phải là hình ảnh không
            if (file.isFile() && isImageFile(file)) {
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());

                // Lấy kích thước của JLabel
                int lblWidth = lblHinhLogo.getWidth();
                int lblHeight = lblHinhLogo.getHeight();

                // Thay đổi kích thước hình ảnh để vừa với kích thước của JLabel mà không làm biến dạng hình ảnh
                Image img = icon.getImage().getScaledInstance(lblWidth, lblHeight, Image.SCALE_SMOOTH);

                ImageIcon newIcon = new ImageIcon(img); // Tạo ImageIcon mới từ hình ảnh đã thay đổi kích thước
                lblHinhLogo.setIcon(newIcon); // Đặt ImageIcon mới vào JLabel
                lblHinhLogo.setToolTipText(file.getName()); // Gán tên tệp tin làm thông tin công cụ gợi ý
                lblHinhLogo.repaint(); // Cập nhật giao diện người dùng
            } else {
                // Xử lý khi tệp tin không phải là hình ảnh
                // Thông báo hoặc xử lý khác tùy thuộc vào yêu cầu của bạn
            }
        }
    }

    void setHinhAnh(String imagePath) {
        if (imagePath != null) {
            lblHinhLogo.setToolTipText(imagePath);
            lblHinhLogo.setIcon(new ImageIcon(imagePath));
        }
    }

    boolean isImageFile(File file) {
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif");
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        rdoMaSP = new javax.swing.JRadioButton();
        rdoTenSP = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuongNhap = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtMaLoai = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblHinhLogo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiSP = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMaLoaiSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTenLoaiSP = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnLuu2 = new javax.swing.JButton();
        btnLast2 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnFirst2 = new javax.swing.JButton();
        btnPrev2 = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("QUẢN LÝ SẢN PHẨM");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá SP", "Mã loại", "SL nhập", "Mô tả", "Hinh"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        btnTimKiem.setBackground(new java.awt.Color(255, 204, 255));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMaSP);
        rdoMaSP.setSelected(true);
        rdoMaSP.setText("Mã sản phẩm");
        rdoMaSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoMaSPItemStateChanged(evt);
            }
        });
        rdoMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMaSPActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTenSP);
        rdoTenSP.setText("Tên sản phẩm");
        rdoTenSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoTenSPItemStateChanged(evt);
            }
        });

        jLabel9.setText("Thông tin sản phẩm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(rdoMaSP)
                    .addComponent(rdoTenSP)
                    .addComponent(btnTimKiem))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(rdoMaSP)
                .addGap(18, 18, 18)
                .addComponent(rdoTenSP)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(31, 31, 31))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tùy chỉnh"));

        jLabel1.setText("Mã sản phẩm:");

        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        jLabel6.setText("SL nhập");

        jLabel2.setText("Tên sản phẩm:");

        jLabel4.setText("Giá nhập:");

        jLabel7.setText("Mô tả:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jLabel5.setText("Mã loại:");

        jPanel6.setBackground(new java.awt.Color(255, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblHinhLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHinhLogoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinhLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(txtMaLoai)
                    .addComponent(txtTenSP)
                    .addComponent(txtGiaNhap))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(356, 356, 356)
                    .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(241, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(135, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SẢN PHẨM", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("LOẠI SẢN PHẨM");

        tblLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Loại", "Tên Loại"
            }
        ));
        tblLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLoaiSPMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblLoaiSP);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("SẢN PHẨM");

        tblSanPham2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Giá SP", "SL tồn kho", "Mô tả"
            }
        ));
        jScrollPane4.setViewportView(tblSanPham2);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/ShopAoQuan2.png"))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tùy chỉnh"));

        jLabel12.setText("Mã loại sản phẩm:");

        jLabel13.setText("Tên loại sản phẩm:");

        btnThem2.setBackground(new java.awt.Color(255, 204, 255));
        btnThem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Add.png"))); // NOI18N
        btnThem2.setText("Mới");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnSua2.setBackground(new java.awt.Color(255, 204, 255));
        btnSua2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/files-edit-icon.png"))); // NOI18N
        btnSua2.setText("Sửa");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
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

        btnLuu2.setBackground(new java.awt.Color(255, 204, 255));
        btnLuu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Save.png"))); // NOI18N
        btnLuu2.setText("Lưu");
        btnLuu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu2ActionPerformed(evt);
            }
        });

        btnLast2.setBackground(new java.awt.Color(255, 204, 255));
        btnLast2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Last.png"))); // NOI18N
        btnLast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast2ActionPerformed(evt);
            }
        });

        btnNext2.setBackground(new java.awt.Color(255, 204, 255));
        btnNext2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Next.png"))); // NOI18N
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        btnFirst2.setBackground(new java.awt.Color(255, 204, 255));
        btnFirst2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/First.png"))); // NOI18N
        btnFirst2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst2ActionPerformed(evt);
            }
        });

        btnPrev2.setBackground(new java.awt.Color(255, 204, 255));
        btnPrev2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Previous.png"))); // NOI18N
        btnPrev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrev2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnFirst2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPrev2))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnThem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnNext2)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast2))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSua2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenLoaiSP)
                    .addComponent(txtMaLoaiSP))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem2)
                    .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa2)
                    .addComponent(btnLuu2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLast2)
                    .addComponent(btnNext2)
                    .addComponent(btnPrev2)
                    .addComponent(btnFirst2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel10))
                    .addComponent(jScrollPane4))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LOẠI SẢN PHẨM", jPanel3);

        btnThem.setBackground(new java.awt.Color(255, 204, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Add.png"))); // NOI18N
        btnThem.setText("Mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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

        btnLast.setBackground(new java.awt.Color(255, 204, 255));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu)
                                .addGap(198, 198, 198)
                                .addComponent(btnFirst)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPrev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoa)
                        .addComponent(btnLuu))
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrev2ActionPerformed
        prevPLSP();
    }//GEN-LAST:event_btnPrev2ActionPerformed

    private void btnFirst2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst2ActionPerformed
        // TODO add your handling code here:
        firstPLSP();
    }//GEN-LAST:event_btnFirst2ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        nextPLSP();
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnLast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast2ActionPerformed
        lastPLSP();
    }//GEN-LAST:event_btnLast2ActionPerformed

    private void btnLuu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu2ActionPerformed
        // TODO add your handling code here:
        insertPLSP();
    }//GEN-LAST:event_btnLuu2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
        deletePLSP();
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        updatePLSP();
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void tblLoaiSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSPMousePressed
        if(evt.getClickCount()==1)
        {
            row = tblLoaiSP.getSelectedRow();
            this.row = tblLoaiSP.rowAtPoint(evt.getPoint());
            editPLSP();

            // Check if the tab index is not 1 (index of "Loại Sản Phẩm" tab)
            if (jTabbedPane1.getSelectedIndex() != 1) {
                jTabbedPane1.setSelectedIndex(1); // Set the tab back to "Loại Sản Phẩm"
            }
        }
    }//GEN-LAST:event_tblLoaiSPMousePressed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        insertSP();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        lastSP();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        nextSP();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed

        // TODO add your handling code here:
        prevSP();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        firstSP();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lblHinhLogoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhLogoMousePressed
        this.ChonAnh();
    }//GEN-LAST:event_lblHinhLogoMousePressed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void rdoTenSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoTenSPItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()== java.awt.event.ItemEvent.SELECTED){
            rdoMaSP.setSelected(false);
        }
    }//GEN-LAST:event_rdoTenSPItemStateChanged

    private void rdoMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMaSPActionPerformed

    private void rdoMaSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoMaSPItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange()== java.awt.event.ItemEvent.SELECTED){
            rdoTenSP.setSelected(false);
        }
    }//GEN-LAST:event_rdoMaSPItemStateChanged

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

        String searchText = txtTim.getText().trim();
        boolean searchByMaSP = rdoMaSP.isSelected();
        boolean searchByTenSP = rdoTenSP.isSelected();
        if(searchText.isEmpty()){
            MsgBox.alert(this, "Vui lòng nhập thông tin cần tìm!");
            return;
        }
        //        if (!searchByMaSP && !searchByTenSP) {
            //            MsgBox.alert(this, "Vui lòng chọn một trong hai loại tìm kiếm.");
            //            return;
            //        }

        try {
            List<SanPham> listSP;

            if (searchByMaSP) {
                listSP = spDAO.timKiem(searchText, true);
            } else {
                listSP = spDAO.timKiem(searchText, false);
            }

            DefaultTableModel tblModel = (DefaultTableModel) tblSanPham.getModel();
            tblModel.setRowCount(0);

            if (listSP.isEmpty()) {
                MsgBox.alert(this, "Không tìm thấy sản phẩm.");
                return;
            }

            for (SanPham sp : listSP) {
                Object[] row = {sp.getMaSP(), sp.getMaLoai(), sp.getTenSP(), sp.getGiaNhap(), sp.getSoLuongNhap(), sp.getGhiChu(), sp.getHinhAnh()};
                tblModel.addRow(row);
            }
        } catch (Exception ex) {
            MsgBox.alert(this, "Lỗi khi tìm kiếm sản phẩm");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteSP();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateSP();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        clearFormSP();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if(evt.getClickCount()==1)
        {
            row = tblSanPham.getSelectedRow();
            this.row = tblSanPham.rowAtPoint(evt.getPoint());
            editSP();
        }
    }//GEN-LAST:event_tblSanPhamMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst2;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast2;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuu2;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrev2;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHinhLogo;
    private javax.swing.JRadioButton rdoMaSP;
    private javax.swing.JRadioButton rdoTenSP;
    private javax.swing.JTable tblLoaiSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPham2;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaLoaiSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuongNhap;
    private javax.swing.JTextField txtTenLoaiSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
