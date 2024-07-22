package QuanLyShopAoQuan.ui;

import com.fsm.utils.MsgBox;
import com.fsm.utils.XImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author MMSI
 */
public class TrangChu extends javax.swing.JFrame {
    int width = 200,height = 700;
    DangNhapDialog dangnhap = new DangNhapDialog(this, rootPaneCheckingEnabled);
    QuanLyTaiKhoanIFrame tk = new QuanLyTaiKhoanIFrame();
    QuanLyNhanVienIFrame nv = new QuanLyNhanVienIFrame();
    QuanLySanPhamIFrame sp = new QuanLySanPhamIFrame();
    QuanLyKhachHang kh = new QuanLyKhachHang();
    QuanLyDonHangIFrame dh = new QuanLyDonHangIFrame();
    ChamSocKhachHangIFrame cskh = new ChamSocKhachHangIFrame();
    BaoCaoThongKeIFrame bctk = new BaoCaoThongKeIFrame();
    HuongDanSuDungIFrame hdsd = new HuongDanSuDungIFrame();
    GioiThieuIFrame gt = new GioiThieuIFrame();
    public TrangChu() {
        initComponents();
        init();
    }
    void init() {
    	java.net.URL location = getClass().getResource("C:\\Users\\Huy Nguyen\\eclipse-workspace\\DuAn1_Nhom54");

    	if (location != null) {
    	    String urlString = location.toExternalForm();
    	    // Continue using the URL string
    	} else {
    	    System.err.println("Error: location is null");
    	}
        setIconImage(XImage.getAppIcon());
        setTitle("FSM - TRANG CHỦ");
        jDesktopPane.setBackground(Color.decode("#FFEDED"));
        setLayout(new BorderLayout());
        setSize(1200, 730);
        add(pnlThanhDuoi, BorderLayout.SOUTH);
        getContentPane().add(jDesktopPane);
        getContentPane().add(pnlMain);
        new Timer(1000, new ActionListener() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
        @Override
        public void actionPerformed(ActionEvent e) {
           lblDongHo.setText(format.format(new Date()));
        }
    }).start();
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);

        this.OpenWelcome();
        OpenLogin();
    }

    void openMenuBar() {
        // tạo luồng chạy song song với luồng chính
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < width; i++) {
                    pnlMenu.setSize(i, height);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    void closeMenuBar() {
        // tạo luồng chạy& song song với luồng chính
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = width; i > 0; i--) {
                    pnlMenu.setSize(i, height);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    void clear() {
        tk.dispose();
        nv.dispose();
        kh.dispose();
        sp.dispose();
        bctk.dispose();
        dh.dispose();
        cskh.dispose();
        hdsd.dispose();
        gt.dispose();
    }

    void OpenWelcome() {
        new ChaoDialog(this, true).setVisible(true);
    }

    void OpenLogin() {
//         new DangNhapJDialog(this, true).setVisible(true);
        DangNhapDialog dangNhap = new DangNhapDialog(this, true);
        dangNhap.setVisible(true);
        dangNhap.DangNhap();
        

    }

    void dangXuat() {
        pnlDangXuat.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);

        dispose();
        dangnhap.setVisible(true);
    }

    void ketThuc() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc làm việc?"));
        System.exit(0);
    }
    void QuanLyTaiKhoan()
    {
        pnlTaikhoan.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(tk).setVisible(true);
        
    }
    void QuanLyNhanVien()
    {
        pnlNhanVien.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(nv).setVisible(true);
        
    }
    void QuanLyKhachHang()
    {
        pnlKhachHang.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(kh).setVisible(true);
    }
    void QuanLySanPham()
    {
        pnlSanPham.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(sp).setVisible(true);
    }
    void QuanLyDonHang()
    {
        pnldonHang.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(dh).setVisible(true);
    } 
    void ChamSocKhachHang()
    {
        pnlChamSocKhachHang.setBackground(Color.decode("#FFDFDF"));
        pnlTrangChu.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(cskh).setVisible(true);
    }
    void TroGiup()
    {
         pnlTroGiup.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        jDesktopPane.removeAll();
        jDesktopPane.add(hdsd).setVisible(true);
    }
    void BaoCaoThongKe()
    {
        pnlThongKe.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(bctk).setVisible(true);
    }
    void openThongKe(int index)
    {
        
        pnlThongKe.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(bctk).setVisible(true);
        bctk.selectTab(index);
    }
    void gioiThieu()
    {
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        clear();
        jDesktopPane.removeAll();
        jDesktopPane.add(gt).setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblClose = new javax.swing.JLabel();
        pnlTrangChu = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        pnlTaikhoan = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        pnlChamSocKhachHang = new javax.swing.JPanel();
        lblChamSocKhachHang = new javax.swing.JLabel();
        pnldonHang = new javax.swing.JPanel();
        lblDonHang = new javax.swing.JLabel();
        pnlThongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        pnlTroGiup = new javax.swing.JPanel();
        lblTroGiup = new javax.swing.JLabel();
        pnlDangXuat = new javax.swing.JPanel();
        lblDangXuat = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        pnlThanhDuoi = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("ShopAoQuan5.png"));
        Image img = icon.getImage();
        jDesktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mniQuanLySanPham = new javax.swing.JMenuItem();
        mniQuanLyTaiKhoan = new javax.swing.JMenuItem();
        mniQuanLyNhanVien = new javax.swing.JMenuItem();
        mniQuanLyKhachHang = new javax.swing.JMenuItem();
        mniQuanLyDonHang = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniTKDoanhThu = new javax.swing.JMenuItem();
        mniTKHangTonKho = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        mnGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 237, 255));
        pnlMain.setPreferredSize(new java.awt.Dimension(1006, 600));

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setPreferredSize(new java.awt.Dimension(200, 590));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/ShopAoQuan3.png"))); // NOI18N
        pnlMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 6, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 255));
        jLabel2.setText("SHOP QUẦN ÁO KAHITU");
        pnlMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 107, -1, -1));
        pnlMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 133, 200, 10));
        pnlMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 468, 200, -1));

        lblClose.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblClose.setText("X");
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        pnlMenu.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 6, 17, -1));

        pnlTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        pnlTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTrangChuMousePressed(evt);
            }
        });

        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Home.png"))); // NOI18N
        lblTrangChu.setText("Trang Chủ");

        javax.swing.GroupLayout pnlTrangChuLayout = new javax.swing.GroupLayout(pnlTrangChu);
        pnlTrangChu.setLayout(pnlTrangChuLayout);
        pnlTrangChuLayout.setHorizontalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        pnlTrangChuLayout.setVerticalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTrangChu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 190, 40));

        pnlTaikhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlTaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTaikhoanMousePressed(evt);
            }
        });

        lblTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Unknown person.png"))); // NOI18N
        lblTaiKhoan.setText("Quản lý tài khoản");

        javax.swing.GroupLayout pnlTaikhoanLayout = new javax.swing.GroupLayout(pnlTaikhoan);
        pnlTaikhoan.setLayout(pnlTaikhoanLayout);
        pnlTaikhoanLayout.setHorizontalGroup(
            pnlTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaikhoanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pnlTaikhoanLayout.setVerticalGroup(
            pnlTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaikhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaiKhoan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlTaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 180, 190, 40));

        pnlNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnlNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlNhanVienMousePressed(evt);
            }
        });

        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Clien list.png"))); // NOI18N
        lblNhanVien.setText("Quản lý nhân viên");

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 190, 40));

        pnlKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlKhachHangMousePressed(evt);
            }
        });

        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/User group.png"))); // NOI18N
        lblKhachHang.setText("Quản lý khách hàng");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 190, 40));

        pnlChamSocKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlChamSocKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlChamSocKhachHangMousePressed(evt);
            }
        });

        lblChamSocKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/groups-icon.png"))); // NOI18N
        lblChamSocKhachHang.setText("Chăm sóc khách hàng");

        javax.swing.GroupLayout pnlChamSocKhachHangLayout = new javax.swing.GroupLayout(pnlChamSocKhachHang);
        pnlChamSocKhachHang.setLayout(pnlChamSocKhachHangLayout);
        pnlChamSocKhachHangLayout.setHorizontalGroup(
            pnlChamSocKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChamSocKhachHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblChamSocKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlChamSocKhachHangLayout.setVerticalGroup(
            pnlChamSocKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChamSocKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChamSocKhachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlChamSocKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 190, 40));

        pnldonHang.setBackground(new java.awt.Color(255, 255, 255));
        pnldonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnldonHangMousePressed(evt);
            }
        });

        lblDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Unordered list.png"))); // NOI18N
        lblDonHang.setText("Quản lý đơn hàng");

        javax.swing.GroupLayout pnldonHangLayout = new javax.swing.GroupLayout(pnldonHang);
        pnldonHang.setLayout(pnldonHangLayout);
        pnldonHangLayout.setHorizontalGroup(
            pnldonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldonHangLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblDonHang)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        pnldonHangLayout.setVerticalGroup(
            pnldonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldonHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDonHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnldonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 190, 40));

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlThongKeMousePressed(evt);
            }
        });

        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Bar chart.png"))); // NOI18N
        lblThongKe.setText("Thống kê");

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 190, 40));

        pnlTroGiup.setBackground(new java.awt.Color(255, 255, 255));
        pnlTroGiup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTroGiupMousePressed(evt);
            }
        });

        lblTroGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Actions-help-about-icon.png"))); // NOI18N
        lblTroGiup.setText("Trợ giúp");

        javax.swing.GroupLayout pnlTroGiupLayout = new javax.swing.GroupLayout(pnlTroGiup);
        pnlTroGiup.setLayout(pnlTroGiupLayout);
        pnlTroGiupLayout.setHorizontalGroup(
            pnlTroGiupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTroGiupLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTroGiup, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        pnlTroGiupLayout.setVerticalGroup(
            pnlTroGiupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTroGiupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTroGiup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlTroGiup, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 190, 40));

        pnlDangXuat.setBackground(new java.awt.Color(255, 255, 255));
        pnlDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlDangXuatMousePressed(evt);
            }
        });

        lblDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Log out.png"))); // NOI18N
        lblDangXuat.setText("Đăng xuất");

        javax.swing.GroupLayout pnlDangXuatLayout = new javax.swing.GroupLayout(pnlDangXuat);
        pnlDangXuat.setLayout(pnlDangXuatLayout);
        pnlDangXuatLayout.setHorizontalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangXuatLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        pnlDangXuatLayout.setVerticalGroup(
            pnlDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDangXuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 190, 40));

        pnlSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlSanPhamMousePressed(evt);
            }
        });

        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/T-Shirt-icon.png"))); // NOI18N
        lblSanPham.setText("Quản lý sản phẩm");
        lblSanPham.setToolTipText("");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSanPhamMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lblSanPham)
                .addGap(15, 15, 15))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        pnlThanhDuoi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Info.png"))); // NOI18N
        jLabel14.setText("Hệ thống quản lý shop quần áo KAHITU");
        jLabel14.setPreferredSize(new java.awt.Dimension(600, 16));

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Clock.png"))); // NOI18N
        lblDongHo.setText("00:00:00 AM");

        javax.swing.GroupLayout pnlThanhDuoiLayout = new javax.swing.GroupLayout(pnlThanhDuoi);
        pnlThanhDuoi.setLayout(pnlThanhDuoiLayout);
        pnlThanhDuoiLayout.setHorizontalGroup(
            pnlThanhDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhDuoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 943, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addGap(17, 17, 17))
        );
        pnlThanhDuoiLayout.setVerticalGroup(
            pnlThanhDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhDuoiLayout.createSequentialGroup()
                .addGroup(pnlThanhDuoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDongHo))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jDesktopPane.setBackground(new java.awt.Color(255, 237, 237));

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1113, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThanhDuoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                    .addGap(0, 204, Short.MAX_VALUE)
                    .addComponent(jDesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThanhDuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDesktopPane))
        );

        jDesktopPane.getAccessibleContext().setAccessibleName("");

        jMenu1.setText("☰");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Hệ thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangNhap.setBackground(new java.awt.Color(255, 204, 255));
        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        jMenu2.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Log out.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        jMenu2.add(mniDangXuat);
        jMenu2.add(jSeparator3);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        jMenu2.add(mniKetThuc);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Quản lý");

        mniQuanLySanPham.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQuanLySanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Yusuke-Kamiyamane-Fugue-T-shirt-print-gray.16.png"))); // NOI18N
        mniQuanLySanPham.setText("Quản lý sản phẩm");
        mniQuanLySanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuanLySanPhamActionPerformed(evt);
            }
        });
        jMenu6.add(mniQuanLySanPham);

        mniQuanLyTaiKhoan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQuanLyTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Gear.png"))); // NOI18N
        mniQuanLyTaiKhoan.setText("Quản lý tài khoản");
        mniQuanLyTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuanLyTaiKhoanActionPerformed(evt);
            }
        });
        jMenu6.add(mniQuanLyTaiKhoan);

        mniQuanLyNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Clien list.png"))); // NOI18N
        mniQuanLyNhanVien.setText("Quản lý nhân viên");
        mniQuanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuanLyNhanVienActionPerformed(evt);
            }
        });
        jMenu6.add(mniQuanLyNhanVien);

        mniQuanLyKhachHang.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQuanLyKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/User group.png"))); // NOI18N
        mniQuanLyKhachHang.setText("Quản lý khách hàng");
        mniQuanLyKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuanLyKhachHangActionPerformed(evt);
            }
        });
        jMenu6.add(mniQuanLyKhachHang);

        mniQuanLyDonHang.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniQuanLyDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Unordered list.png"))); // NOI18N
        mniQuanLyDonHang.setText("Quản lý đơn hàng");
        mniQuanLyDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniQuanLyDonHangActionPerformed(evt);
            }
        });
        jMenu6.add(mniQuanLyDonHang);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Thống kê");

        mniTKDoanhThu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mniTKDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Statistics.png"))); // NOI18N
        mniTKDoanhThu.setText("TK Doanh thu");
        mniTKDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKDoanhThuActionPerformed(evt);
            }
        });
        jMenu3.add(mniTKDoanhThu);

        mniTKHangTonKho.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mniTKHangTonKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Full basket.png"))); // NOI18N
        mniTKHangTonKho.setText("TK Hàng tồn kho");
        mniTKHangTonKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKHangTonKhoActionPerformed(evt);
            }
        });
        jMenu3.add(mniTKHangTonKho);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Globe.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        jMenu4.add(mniHuongDan);

        mnGioiThieu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyShopAoQuan/icon/Brick house.png"))); // NOI18N
        mnGioiThieu.setText("Giới thiệu sản phẩm");
        mnGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGioiThieuActionPerformed(evt);
            }
        });
        jMenu4.add(mnGioiThieu);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1317, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        openMenuBar();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        // TODO add your handling code here:
        closeMenuBar();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:
        OpenLogin();
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
        dangXuat();
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        // TODO add your handling code here:
        ketThuc();
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void pnlTrangChuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrangChuMousePressed
        // TODO add your handling code here:
        pnlTrangChu.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlSanPham.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);

        clear();
    }//GEN-LAST:event_pnlTrangChuMousePressed

    private void pnlTaikhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTaikhoanMousePressed
        // TODO add your handling code here:
        QuanLyTaiKhoan();
    }//GEN-LAST:event_pnlTaikhoanMousePressed

    private void pnlNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNhanVienMousePressed
        // TODO add your handling code here:
        QuanLyNhanVien();
    }//GEN-LAST:event_pnlNhanVienMousePressed

    private void pnlSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSanPhamMousePressed
        // TODO add your handling code here:
       QuanLySanPham();
    }//GEN-LAST:event_pnlSanPhamMousePressed

    private void pnlKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhachHangMousePressed
        // TODO add your handling code here:
       QuanLyKhachHang();
    }//GEN-LAST:event_pnlKhachHangMousePressed

    private void pnlChamSocKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlChamSocKhachHangMousePressed
        // TODO add your handling code here:
        ChamSocKhachHang();
    }//GEN-LAST:event_pnlChamSocKhachHangMousePressed

    private void pnldonHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnldonHangMousePressed
        // TODO add your handling code here:
        QuanLyDonHang();
    }//GEN-LAST:event_pnldonHangMousePressed

    private void pnlThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThongKeMousePressed
        // TODO add your handling code here:
        BaoCaoThongKe();
    }//GEN-LAST:event_pnlThongKeMousePressed

    private void pnlTroGiupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTroGiupMousePressed
        // TODO add your handling code here:
       TroGiup();
    }//GEN-LAST:event_pnlTroGiupMousePressed

    private void pnlDangXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDangXuatMousePressed
        // TODO add your handling code here:
       dangXuat();
    }//GEN-LAST:event_pnlDangXuatMousePressed

    private void lblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMousePressed
        // TODO add your handling code here:
        pnlSanPham.setBackground(Color.decode("#FFDFDF"));
        pnlChamSocKhachHang.setBackground(Color.white);
        pnlKhachHang.setBackground(Color.white);
        pnlDangXuat.setBackground(Color.white);
        pnlNhanVien.setBackground(Color.white);
        pnlTrangChu.setBackground(Color.white);
        pnlTaikhoan.setBackground(Color.white);
        pnlThongKe.setBackground(Color.white);
        pnlTroGiup.setBackground(Color.white);
        pnldonHang.setBackground(Color.white);
        
        clear();
        
        jDesktopPane.removeAll();
        jDesktopPane.add(sp).setVisible(true);
    }//GEN-LAST:event_lblSanPhamMousePressed

    private void mniQuanLySanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuanLySanPhamActionPerformed
        // TODO add your handling code here:
        QuanLySanPham();
    }//GEN-LAST:event_mniQuanLySanPhamActionPerformed

    private void mniQuanLyTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuanLyTaiKhoanActionPerformed
        // TODO add your handling code here:
        QuanLyTaiKhoan();
        
    }//GEN-LAST:event_mniQuanLyTaiKhoanActionPerformed

    private void mniQuanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuanLyNhanVienActionPerformed
        // TODO add your handling code here:
        QuanLyNhanVien();
    }//GEN-LAST:event_mniQuanLyNhanVienActionPerformed

    private void mniQuanLyKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuanLyKhachHangActionPerformed
        // TODO add your handling code here:
        QuanLyKhachHang();
    }//GEN-LAST:event_mniQuanLyKhachHangActionPerformed

    private void mniQuanLyDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniQuanLyDonHangActionPerformed
        // TODO add your handling code here:
        QuanLyDonHang();
    }//GEN-LAST:event_mniQuanLyDonHangActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        // TODO add your handling code here:
        TroGiup();
    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void mniTKDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKDoanhThuActionPerformed
        // TODO add your handling code here:
        openThongKe(0);
    }//GEN-LAST:event_mniTKDoanhThuActionPerformed

    private void mniTKHangTonKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKHangTonKhoActionPerformed
        // TODO add your handling code here:
        openThongKe(1);
    }//GEN-LAST:event_mniTKHangTonKhoActionPerformed

    private void mnGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGioiThieuActionPerformed
        // TODO add your handling code here:
        gioiThieu();
    }//GEN-LAST:event_mnGioiThieuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblChamSocKhachHang;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDonHang;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblTroGiup;
    private javax.swing.JMenuItem mnGioiThieu;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniQuanLyDonHang;
    private javax.swing.JMenuItem mniQuanLyKhachHang;
    private javax.swing.JMenuItem mniQuanLyNhanVien;
    private javax.swing.JMenuItem mniQuanLySanPham;
    private javax.swing.JMenuItem mniQuanLyTaiKhoan;
    private javax.swing.JMenuItem mniTKDoanhThu;
    private javax.swing.JMenuItem mniTKHangTonKho;
    private javax.swing.JPanel pnlChamSocKhachHang;
    private javax.swing.JPanel pnlDangXuat;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlTaikhoan;
    private javax.swing.JPanel pnlThanhDuoi;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTrangChu;
    private javax.swing.JPanel pnlTroGiup;
    private javax.swing.JPanel pnldonHang;
    // End of variables declaration//GEN-END:variables
}
