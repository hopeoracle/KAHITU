

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package QuanLyShopAoQuan.ui;

import ShopQuanLyAoQuan.dao.DonHangDAO;
import ShopQuanLyAoQuan.dao.ThongKeDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MMSI
 */
public class BaoCaoThongKeIFrame extends javax.swing.JInternalFrame {
    ThongKeDAO dao = new ThongKeDAO();
    DonHangDAO dhDAO = new DonHangDAO();
    /**
     * Creates new form BaoCaoThongKeIFrame
     */
    public BaoCaoThongKeIFrame() {
        initComponents();
        this.selectTabs(0);
        fillComboBoxNgay();
        txtTongDoanhThu.setEditable(false);
        calculateSum();
        fillTableTonKho();
        buttonGroup1.clearSelection();
        fillComboBoxThang();
        fillComboBoxYear();
        fillComboBoxNgayBatDau();
        fillComboBoxNgayKetThuc();
    }
    
    void fillComboBoxNgay() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNgay.getModel();
        model.removeAllElements();
        List<Date> list = dhDAO.selectDay();
        for (Date ngay : list) {
            model.addElement(ngay);
        }
    }
    
    void fillComboBoxNgayBatDau() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTuNgay.getModel();
        model.removeAllElements();
        List<Date> list = dhDAO.selectDay();
        for (Date ngay : list) {
            model.addElement(ngay);
        }
    }
    
    void fillComboBoxNgayKetThuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDenNgay.getModel();
        model.removeAllElements();
        List<Date> list = dhDAO.selectDay();
        for (Date ngay : list) {
            model.addElement(ngay);
        }
    }
    
//    private static String formatDate(String pattern, String yyyyMM) {
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        return sdf.format(yyyyMM);
//    }
    
    private static String formatMonthYear(String monthYearString) {
        // Tách chuỗi tháng và năm
        String[] parts = monthYearString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        // Chuyển đổi chuỗi tháng và năm sang LocalDate
        LocalDate date = LocalDate.of(year, month, 1);

        // Định dạng lại thành yyyy,MM
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
    
    void fillComboBoxThang() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheoThang.getModel();
        model.removeAllElements();
        List<String> list = dhDAO.selectMonth();
        
        for (String month : list) {
            String monthAndYear = formatMonthYear(month);
            model.addElement(monthAndYear);
        }
    }
    
    void fillComboBoxYear() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheoNam.getModel();
        model.removeAllElements();
        List<Integer> list = dhDAO.selectYear();
        
        for (Integer year : list) {
            model.addElement(year);
        }
    }

    void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        if (cboNgay.getSelectedItem() != null) {
            Date ngay = (Date) cboNgay.getSelectedItem();
            List<Object[]> list = dao.getDoanhThu(ngay);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }
    
    void fillTableDoanhThuTheoThang() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        if (cboTheoThang.getSelectedItem() != null) {
            String month = (String) cboTheoThang.getSelectedItem();
            List<Object[]> list = dao.getDoanhThuTheoMonth(month);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }
    
    void fillTableDoanhThuTheoYear() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        if (cboTheoNam.getSelectedItem() != null) {
            Integer year = (Integer) cboTheoNam.getSelectedItem();
            List<Object[]> list = dao.getDoanhThuTheoYear(year);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
    }
    
    
   void fillTableDoanhThuTheoNgay() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        if(cboTuNgay.getSelectedItem() != null){
            Date ngayBatDau = (Date) cboTuNgay.getSelectedItem();
            if (cboDenNgay.getSelectedItem() !=null) {
            Date ngayKetThuc = (Date) cboDenNgay.getSelectedItem();
            List<Object[]> list = dao.getDoanhThuTheoKhoangNgay(ngayBatDau,ngayKetThuc);
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
        }
    }

    void fillTableTonKho() {
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getTonKho();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    private void calculateSum() {
        // Get the column index you want to sum
        int columnIndexToSum = 2; // Assuming you want to sum the third column (index 2)

        // Get the TableModel from the JTable
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();

        // Iterate through the rows and calculate the sum
        double sum = 0;
        for (int row = 0; row < model.getRowCount(); row++) {
            sum += (double) model.getValueAt(row, columnIndexToSum);
        }
        txtTongDoanhThu.setText(Double.toString(sum));
    }

    public void selectTabs(int index) {
        tabs.setSelectedIndex(index);
    }

    void fillTableTonKhoID() {
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getTonKhobyID(txtTim.getText());
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    void fillTableTonKhoName() {
        DefaultTableModel model = (DefaultTableModel) tblTonKho.getModel();
        model.setRowCount(0);

        List<Object[]> list = dao.getTonKhobyName(txtTim.getText());
        for (Object[] row : list) {
            model.addRow(row);
        }
    }
      public void selectTab(int index)
    {
        tabs.setSelectedIndex(index);
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
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboNgay = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JTextField();
        cboTheoThang = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTheoNam = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboTuNgay = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboDenNgay = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        rdoTenSP = new javax.swing.JRadioButton();
        rdoMaSP = new javax.swing.JRadioButton();
        btnTim = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTonKho = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Doanh Thu Theo Ngày");

        cboNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayActionPerformed(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nhân Viên", "Tổng Khách Hàng", "Doanh Thu"
            }
        ));
        jScrollPane1.setViewportView(tblDoanhThu);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 255));
        jLabel5.setText("DOANH THU HÀNG NGÀY");

        jLabel2.setText("Tổng Tiền:");

        txtTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongDoanhThuActionPerformed(evt);
            }
        });

        cboTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoThangActionPerformed(evt);
            }
        });

        jLabel6.setText("Theo Tháng:");

        jLabel7.setText("Theo Năm:");

        cboTheoNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNamActionPerformed(evt);
            }
        });

        jLabel8.setText("Doanh Thu Từ Ngày:");

        cboTuNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Đến:");

        cboDenNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDenNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDenNgayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tabs.addTab("DOANH THU", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jLabel4.setText("Thông tin");

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoTenSP);
        rdoTenSP.setSelected(true);
        rdoTenSP.setText("Tên sản phẩm");
        rdoTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTenSPActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoMaSP);
        rdoMaSP.setText("Mã sản phẩm");

        btnTim.setBackground(new java.awt.Color(255, 204, 255));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Search.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(rdoTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(rdoMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoMaSP)
                    .addComponent(rdoTenSP)))
        );

        tblTonKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số Lượng Nhập", "Tổng SP đã bán ra", "SL sản phẩm tồn kho"
            }
        ));
        jScrollPane2.setViewportView(tblTonKho);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        tabs.addTab("HÀNG TỒN KHO", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("TỔNG HỢP THỐNG KÊ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTimActionPerformed

    private void rdoTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTenSPActionPerformed

    private void txtTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongDoanhThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongDoanhThuActionPerformed

    private void cboNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThu();
        calculateSum();
    }//GEN-LAST:event_cboNgayActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nhập thông tin tìm kiếm1");
            fillTableTonKho();
        }
        else if(rdoMaSP.isSelected()){
            fillTableTonKhoID();
        }else if(rdoTenSP.isSelected()){
            fillTableTonKhoName();
        }else
        {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sản phẩm!");
            fillTableTonKho();
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboTheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoThangActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThuTheoThang();
        calculateSum();
    }//GEN-LAST:event_cboTheoThangActionPerformed

    private void cboTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNamActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThuTheoYear();
        calculateSum();
    }//GEN-LAST:event_cboTheoNamActionPerformed

    private void cboDenNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDenNgayActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThuTheoNgay();
        calculateSum();
    }//GEN-LAST:event_cboDenNgayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDenNgay;
    private javax.swing.JComboBox<String> cboNgay;
    private javax.swing.JComboBox<String> cboTheoNam;
    private javax.swing.JComboBox<String> cboTheoThang;
    private javax.swing.JComboBox<String> cboTuNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoMaSP;
    private javax.swing.JRadioButton rdoTenSP;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblTonKho;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}
