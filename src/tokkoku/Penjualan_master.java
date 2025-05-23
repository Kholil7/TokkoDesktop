/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tokkoku;
import database.dbtokko;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.text.*;
import java.util.Locale;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
/**
 *
 * @author Yiung Za
 */
public class Penjualan_master extends javax.swing.JFrame {
    private JTextField ipt_jumlah;
    private JTextField txtHarga;
    private JTextField txtTotal;
    private int x = 210;
    JPanel activePanel = null;
    private Connection conn;
    DefaultTableModel model;

    //untuk menampilkan RTX
public class TransaksiGenerator {
    private Connection conn;
    private JTextField txtKodeTransaksi;

    public TransaksiGenerator(JTextField txtKodeTransaksi) {
        this.txtKodeTransaksi = txtKodeTransaksi;
        this.txtKodeTransaksi.setEditable(false);
        connectDB();
        generateKodeTransaksi(); // generate saat awal
    }

    private void connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasirtoko", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateKodeTransaksi() {
        String newKode = "RTX01";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_penjualan FROM penjualan ORDER BY id_penjualan DESC LIMIT 1");
            if (rs.next()) {
                String lastKode = rs.getString("id_penjualan");
                int angka = Integer.parseInt(lastKode.substring(3));
                angka++;
                newKode = String.format("RTX%02d", angka);
            }
            txtKodeTransaksi.setText(newKode);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Tambahan method untuk refresh setelah transaksi
    public void refreshKodeTransaksi() {
        generateKodeTransaksi();
    }

    public String getKodeTransaksi() {
        return txtKodeTransaksi.getText();
    }
}


    //database
    public class Koneksi {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_kasirtoko"; // Ganti dengan nama database Anda
                String user = "root"; // Sesuaikan dengan username database Anda
                String password = ""; // Sesuaikan dengan password database Anda

                Class.forName("com.mysql.cj.jdbc.Driver");
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Koneksi gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
//    untuk autocomplate
public class AutoCompleteBarang {
    private Connection conn;
    private JComboBox<String> comboBox;
    public AutoCompleteBarang(JComboBox<String> comboBox) throws SQLException {
        this.comboBox = comboBox;
        conn = dbtokko.configDB();
        loadNamaBarang();
        AutoCompleteDecorator.decorate(comboBox);
    }

    private void loadNamaBarang() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); // Menggunakan Statement biasa
            String sql = "SELECT nama_produk FROM produk";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                comboBox.addItem(rs.getString("nama_produk"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Pastikan semua resource ditutup setelah digunakan
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error saat menutup koneksi: " + e.getMessage());
            }
        }}}

//        pt2
public Penjualan_master(JComboBox<String> comboBox, JTextField txtKodeTransaksi, JTextField txtHargaJual, JTextField kd_barang) {
        this.comboBox = comboBox;
        this.txtKodeTransaksi = txtKodeTransaksi;
        this.txtHargaJual = txtHargaJual;
        this.kd_barang = kd_barang; // Pastikan kd_barang diinisialisasi
        conn = Koneksi.getKoneksi();

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanDetailBarang();
            }
        });
    }

private boolean stokHabisSudahDitampilkan = false; // Tambahkan ini sebagai variabel instance (di atas konstruktor)

private void tampilkanDetailBarang() {
    String namaBarang = (String) comboBox.getSelectedItem();
    if (namaBarang == null || namaBarang.isEmpty()) {
        return; // Tidak perlu peringatan apapun
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT p.id_produk, s.harga_jual, s.stok, s.tanggal_kedaluwarsa " +
                     "FROM produk p " +
                     "JOIN stok_produk s ON p.id_produk = s.id_produk " +
                     "WHERE p.nama_produk = ? AND s.stok > 0 " +
                     "ORDER BY s.tanggal_kedaluwarsa ASC LIMIT 1";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, namaBarang);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String idProduk = rs.getString("id_produk");
            int hargaJual = rs.getInt("harga_jual");

            if (kd_barang != null) kd_barang.setText(idProduk);
            if (txtHargaJual != null) txtHargaJual.setText(String.valueOf(hargaJual));

            stokHabisSudahDitampilkan = false; // Reset flag karena stok tersedia

        } else {
            // Hanya tampilkan popup jika belum pernah muncul
            if (!stokHabisSudahDitampilkan) {
                JOptionPane.showMessageDialog(null,
                        "Stok untuk barang \"" + namaBarang + "\" saat ini habis!",
                        "Stok Habis",
                        JOptionPane.INFORMATION_MESSAGE);
                stokHabisSudahDitampilkan = true;
            }

            if (kd_barang != null) kd_barang.setText("");
            if (txtHargaJual != null) txtHargaJual.setText("");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
                "Terjadi kesalahan SQL: " + e.getMessage(),
                "Error SQL",
                JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error saat menutup koneksi: " + e.getMessage());
        }
    }
}





    
    //untuk kalkulasi
private void updateTotalHarga() {
    try {
        // Ambil nilai harga dari txtHarga, buang titik (format ribuan)
        String hargaText = txtHarga.getText().replace(".", "");
        int jumlah = (int) spnJumlah.getValue();

        if (hargaText.isEmpty()) {
            txtTotal.setText("");
            return;
        }

        int harga = Integer.parseInt(hargaText);
        int total = harga * jumlah;

        // Format ke format ribuan (Indonesia)
        NumberFormat formatRibuan = NumberFormat.getInstance(new Locale("id", "ID"));
        txtTotal.setText(formatRibuan.format(total));

    } catch (NumberFormatException e) {
        txtTotal.setText("Input tidak valid!");
    }
}


//  totalharga
private void setupListeners() {
    txtHargaJual.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
            hitungTotalHarga();
        }
    });

    spnJumlah.addChangeListener(new javax.swing.event.ChangeListener() {
        @Override
        public void stateChanged(javax.swing.event.ChangeEvent e) {
            hitungTotalHarga();
        }
    });
}

private void hitungTotalHarga() {
    try {
        int harga = Integer.parseInt(txtHargaJual.getText());
        int jumlah = (int) spnJumlah.getValue();
        int total = harga * jumlah;

        txtRp.setText(String.valueOf(total));
    } catch (NumberFormatException e) {
        txtRp.setText("0");
    }
}


//tabel
private void initModel() {
    model = new DefaultTableModel();
    model.addColumn("ID Detail");
    model.addColumn("KODE TRANSAKSI");
    model.addColumn("KODE Produk");
    model.addColumn("ID Stok");
    model.addColumn("Jumlah");
    model.addColumn("Harga");
    model.addColumn("Subtotal");
    tabel_transaksi.setModel(model);
}

// Fungsi untuk menghitung total dari kolom subtotal
private void updateTotal() {
    double total = 0;

    // Mengambil data dari tabel dan menghitung total subtotal (kolom 6)
    for (int i = 0; i < model.getRowCount(); i++) {
        try {
            // Mengambil nilai subtotal dari setiap baris (kolom 6)
            double subtotal = Double.parseDouble(model.getValueAt(i, 6).toString());
            total += subtotal;
        } catch (NumberFormatException ex) {
            // Jika ada data yang tidak valid, lanjutkan ke baris berikutnya
            continue;
        }
    }

    // Format angka ke format Indonesia (titik sebagai ribuan, koma sebagai desimal)
    NumberFormat formatRibuan = NumberFormat.getInstance(new Locale("id", "ID"));

    // Menampilkan total ke JTextField
    showTotalField.setText(formatRibuan.format(total));
}


// Fungsi untuk menghitung selisih antara pembayaran dan total
private void updateKembali() {
    try {
        String totalText = showTotalField.getText().replace(".", "").replace(",", ".");
        String bayarText = textBayar.getText().replace(".", "").replace(",", ".");

        System.out.println("Total Field: " + totalText);
        System.out.println("Bayar Field: " + bayarText);

        double total = totalText.isEmpty() ? 0.0 : Double.parseDouble(totalText);
        double bayar = bayarText.isEmpty() ? 0.0 : Double.parseDouble(bayarText);

        double kembali = bayar - total;

        NumberFormat formatRibuan = NumberFormat.getInstance(new Locale("id", "ID"));
        textKembali.setText(formatRibuan.format(kembali));

    } catch (NumberFormatException ex) {
        System.out.println("Format tidak valid: " + ex.getMessage());
        textKembali.setText("0");
    }
}

//subtotalJumlahDatabase
//private double calculateTotal() {
//    double total = 0;
//    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
//    for (int i = 0; i < model.getRowCount(); i++) {
//        total += Double.parseDouble(model.getValueAt(i, 6).toString());  // Menjumlahkan kolom Subtotal
//    }
//    return total;
//}
//
//// Method untuk mereset form setelah pembayaran berhasil
//private void resetForm() {
//    // Reset semua field input dan tabel transaksi
//    txtKodeTransaksi.setText("");
//    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
//    model.setRowCount(0);  // Menghapus semua data pada tabel
//}
//
//// Method untuk mencetak struk pembayaran
//private void printStruk(String idPenjualan) {
//    // Ambil data transaksi berdasarkan idPenjualan untuk ditampilkan dalam format struk
//    try (Connection conn = dbtokko.configDB()) {
//        String query = "SELECT * FROM penjualan WHERE id_penjualan = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, idPenjualan);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    String struk = "Struk Pembelian\n\n";
//                    struk += "ID Penjualan: " + rs.getString("id_penjualan") + "\n";
//                    struk += "Total: " + rs.getDouble("total") + "\n";
//                    struk += "ID Pengguna: " + rs.getInt("id_pengguna") + "\n";
//                    struk += "\nTerima kasih telah berbelanja!\n";
//
//                    // Cetak struk (dapat disesuaikan dengan cara menampilkan atau mencetak)
//                    JOptionPane.showMessageDialog(null, struk);
//                }
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}





    public Penjualan_master() throws SQLException {
        initComponents();
        conn = dbtokko.configDB();
        setupListeners();
        SpinnerNumberModel modelJumlah = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        spnJumlah.setModel(modelJumlah);
        new AutoCompleteBarang(comboBox);
        new Penjualan_master(comboBox, txtKodeTransaksi, txtHargaJual, kd_barang);
        comboBox.setEditable(true);
        txtHarga = new JTextField();
        new TransaksiGenerator(txtKodeTransaksi);
        barcode.setBounds(-100, -100, 1, 1);
        barcode.setOpaque(false);
        barcode.setBorder(null);
        barcode.setFocusable(true);
        
        initModel();
        // Menambahkan TableModelListener untuk mendeteksi perubahan data
        model.addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
        // Memastikan perubahan terjadi pada kolom subtotal (kolom 6)
        if (e.getColumn() == 6 || e.getColumn() == TableModelEvent.ALL_COLUMNS) {
            updateTotal(); // Memanggil fungsi untuk update total
        }
    }
});

        // Menggunakan Listener untuk mendeteksi perubahan pada textBayar
textBayar.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateKembali();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateKembali();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateKembali();
    }
});


//Barcode
barcode.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String kode = barcode.getText().trim();

        try {
            String sql = "SELECT p.id_produk, p.nama_produk, p.barcode, s.harga_jual " +
                         "FROM produk p JOIN stok_produk s ON p.id_produk = s.id_produk " +
                         "WHERE p.barcode = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama_produk");
                int harga = rs.getInt("harga_jual");
                String idProduk = rs.getString("id_produk");

                kd_barang.setText(idProduk);
                txtHargaJual.setText(String.valueOf(harga));

                comboBox.setSelectedItem(nama);
                

                spnJumlah.setValue(1);

                btnTambah.doClick();
                
            } else {
                JOptionPane.showMessageDialog(null, "Produk tidak ditemukan!");
                barcode.setText("");
                kd_barang.setText("");
                txtHargaJual.setText("");
                comboBox.setSelectedItem(null);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kesalahan saat scan: " + ex.getMessage());
        }
    }
});




//Fokus
SwingUtilities.invokeLater(() -> {
    barcode.requestFocusInWindow();
});



        //sidebar
          btn_pembelian.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_pemasok.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_pengembalian.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_stok.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_laporan.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_dashboard.setBackground(new java.awt.Color(255, 255, 255, 0));
          comboBox.setBackground(new java.awt.Color(255, 255, 255, 0));
        txtKodeTransaksi.setBackground(new java.awt.Color(255, 255, 255, 0));
        kd_barang.setBackground(new java.awt.Color(255, 255, 255, 0));
        comboBox.setBackground(new java.awt.Color(255, 255, 255, 0));
        txtHargaJual.setBackground(new java.awt.Color(255, 255, 255, 0));
//        txtRp.setBackground(new java.awt.Color(255, 255, 255, 0));
        spnJumlah.setBackground(new java.awt.Color(255, 255, 255, 0));
        comboBox.setBackground(new java.awt.Color(255, 255, 255, 0));
        txtHargaJual.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnHapus.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnTambah.setBackground(new java.awt.Color(255, 255, 255, 0));
        txtRp.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnBayar.setBackground(new java.awt.Color(255, 255, 255, 0));
        textBayar.setBackground(new java.awt.Color(255, 255, 255, 0));
        textKembali.setBackground(new java.awt.Color(255, 255, 255, 0));
    }

//    input transparant
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JButton();
        btn_pembelian = new javax.swing.JButton();
        btn_pemasok = new javax.swing.JButton();
        btn_pengembalian = new javax.swing.JButton();
        btn_stok = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKodeTransaksi = new javax.swing.JTextField();
        kd_barang = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        comboBox = new javax.swing.JComboBox<>();
        txtRp = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        textBayar = new javax.swing.JTextField();
        textKembali = new javax.swing.JTextField();
        btnBayar = new javax.swing.JButton();
        spnJumlah = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        showTotalField = new javax.swing.JTextField();
        barcode = new javax.swing.JTextField();
        backgoundUtama = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/hamburger.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        sidebar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 70, 70));

        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });
        btn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dashboardActionPerformed(evt);
            }
        });
        sidebar.add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 240, 50));

        btn_pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pembelianMouseClicked(evt);
            }
        });
        btn_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pembelianActionPerformed(evt);
            }
        });
        sidebar.add(btn_pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 240, 50));

        btn_pemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pemasokMouseClicked(evt);
            }
        });
        btn_pemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pemasokActionPerformed(evt);
            }
        });
        sidebar.add(btn_pemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 240, 50));

        btn_pengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengembalianMouseClicked(evt);
            }
        });
        btn_pengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pengembalianActionPerformed(evt);
            }
        });
        sidebar.add(btn_pengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 240, 70));

        btn_stok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_stokMouseClicked(evt);
            }
        });
        btn_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stokActionPerformed(evt);
            }
        });
        sidebar.add(btn_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 240, 50));

        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
        });
        sidebar.add(btn_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 240, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/sidebar_penjualan.png"))); // NOI18N
        sidebar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 750));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/hamburger.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 240, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/sidebar-in2.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 750));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 750));

        txtKodeTransaksi.setBorder(null);
        txtKodeTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(txtKodeTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 45, 360, 30));

        kd_barang.setBorder(null);
        kd_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_barangActionPerformed(evt);
            }
        });
        getContentPane().add(kd_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 80, 360, 30));

        txtHargaJual.setBorder(null);
        txtHargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaJualActionPerformed(evt);
            }
        });
        getContentPane().add(txtHargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 85, 360, 28));

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 49, 357, 25));

        txtRp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRpActionPerformed(evt);
            }
        });
        txtRp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRpKeyReleased(evt);
            }
        });
        getContentPane().add(txtRp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, 390, 60));

        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 150, 50));

        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 150, 50));

        textBayar.setBorder(null);
        getContentPane().add(textBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 680, 210, 30));

        textKembali.setBorder(null);
        getContentPane().add(textKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, 210, 30));

        btnBayar.setBorder(null);
        btnBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBayarMouseClicked(evt);
            }
        });
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 650, 210, 70));

        spnJumlah.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnJumlahStateChanged(evt);
            }
        });
        spnJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spnJumlahKeyReleased(evt);
            }
        });
        getContentPane().add(spnJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1015, 120, 60, 30));

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID DETAIL", "KODE TRANSAKSI", "KODE PRODUK", "ID STOK", "JUMLAH", "SUBTOTAL"
            }
        ));
        jScrollPane1.setViewportView(tabel_transaksi);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, 1170, 270));

        showTotalField.setBorder(null);
        getContentPane().add(showTotalField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 680, 210, 30));

        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                barcodeKeyTyped(evt);
            }
        });
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(10000, 120, 150, -1));

        backgoundUtama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Master_penjualan.png"))); // NOI18N
        getContentPane().add(backgoundUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if (x == 0) { // Cek apakah sidebar dalam keadaan tertutup
            new Thread(() -> {
                try {
                    for (int i = 0; i <= 240; i += 10) { // Tambah ukuran per 10 biar smooth
                        Thread.sleep(3); // Delay biar animasi ga terlalu cepat
                        sidebar.setSize(i, 780);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }).start();

            x = 210; // Ubah x ke 210 biar nanti bisa ditutup lagi
        }

    }//GEN-LAST:event_jLabel3MouseClicked

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered
public class TransaksiForm {
    private JTextField ipt_trans; // Komponen UI untuk menampilkan ID transaksi

    public TransaksiForm() {
        // Inisialisasi JTextField
        ipt_trans = new JTextField(10);
        ipt_trans.setEditable(false);

        // **Panggil generateTransactionID() saat form dibuka**
        generateTransactionID();
    }

    // ✅ **Metode ini HARUS diletakkan di luar JTextField**
    public void generateTransactionID() {
        String newID = "RTX01"; // ID default jika belum ada transaksi

        try (Connection conn = (Connection) dbtokko.configDB()) { // Pastikan koneksi database valid
            if (conn == null) {
                System.out.println("❌ Gagal mendapatkan koneksi database.");
                return;
            } else {
                System.out.println("✅ Koneksi database berhasil.");
            }

            // Ambil ID transaksi terakhir dari database
            String queryLastID = "SELECT id_transaksi FROM transaksi ORDER BY id_transaksi DESC LIMIT 1";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(queryLastID)) {

                if (!rs.isBeforeFirst()) { // Mengecek apakah ResultSet kosong
                    System.out.println("📂 Tidak ada transaksi sebelumnya. Menggunakan ID default.");
                }

                if (rs.next()) {
                    String lastID = rs.getString("id_transaksi"); // Contoh: RTX01, RTX02, ...
                    System.out.println("🔍 ID terakhir di database: " + lastID);

                    if (lastID != null && lastID.matches("RTX\\d+")) { // Pastikan formatnya benar
                        try {
                            int lastNumber = Integer.parseInt(lastID.substring(3)); // Ambil angka dari ID
                            int nextNumber = lastNumber + 1; // Naikkan angka
                            newID = String.format("RTX%02d", nextNumber); // Format menjadi RTXxx
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Kesalahan format ID transaksi: " + e.getMessage());
                        }
                    } else {
                        System.out.println("⚠️ Format ID transaksi tidak sesuai atau null.");
                    }
                }
            }

            // Pastikan JTextField sudah diinisialisasi
            if (ipt_trans == null) {
                System.out.println("❌ JTextField ipt_trans belum diinisialisasi!");
                return;
            }

            // **Tampilkan ID transaksi di JTextField**
            ipt_trans.setText(newID);
            System.out.println("✅ ID transaksi baru ditampilkan: " + newID);

        } catch (SQLException e) {
            System.out.println("❌ Terjadi kesalahan database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        if (x == 210) {
            Timer timer = new Timer(5, new ActionListener() {
                int width = 240;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (width <= 0) { // Sidebar tetap menyisakan 50px
                        ((Timer) e.getSource()).stop();
                        x = 0;

                    } else {
                        width -= 10;
                        sidebar.setSize(width, 780);
                        //                sidebar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Border melengkung
                    }
                }
            });
            timer.start();
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtKodeTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeTransaksiActionPerformed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
comboBox.setEditable(true);


    }//GEN-LAST:event_comboBoxActionPerformed

    private void spnJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnJumlahKeyReleased
        spnJumlah.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateTotalHarga();
            }
        });
    }//GEN-LAST:event_spnJumlahKeyReleased

    private void spnJumlahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnJumlahStateChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_spnJumlahStateChanged

    private void txtRpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRpKeyReleased
        // TODO add your handling code here:


        spnJumlah.addChangeListener(e -> updateTotalHarga());
    
    }//GEN-LAST:event_txtRpKeyReleased

    private void txtRpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRpActionPerformed

    private void kd_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_barangActionPerformed

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
try {
    int idDetail = tabel_transaksi.getRowCount() + 1;
    String idPenjualan = txtKodeTransaksi.getText();
    String idProduk = kd_barang.getText();
    int jumlah = Integer.parseInt(spnJumlah.getValue().toString());
    int harga = Integer.parseInt(txtHargaJual.getText());
    int subtotal = jumlah * harga;

    String idStok = "";

    // Ambil id_stok dari stok_produk berdasarkan id_produk
    String sql = "SELECT id_stok FROM stok_produk WHERE id_produk = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, idProduk);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        idStok = rs.getString("id_stok");
    } else {
        JOptionPane.showMessageDialog(this, "Stok untuk produk ini tidak ditemukan.");
        return; // Hentikan proses jika stok tidak ditemukan
    }

    rs.close();
    ps.close();

    // Tambahkan ke tabel model
    model.addRow(new Object[]{
        idDetail,       // ID Detail
        idPenjualan,    // KODE TRANSAKSI
        idProduk,       // KODE Produk
        idStok,         // ID Stok
        jumlah,         // Jumlah
        harga,          // Harga
        subtotal        // Subtotal
    });

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Gagal menambahkan: " + e.getMessage());
}
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
        int selectedRow = tabel_transaksi.getSelectedRow(); 

if (selectedRow != -1) {
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Apakah Anda yakin ingin menghapus data ini?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        model.removeRow(selectedRow); // menghapus baris dari model
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
    }
} else {
    JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus terlebih dahulu.");
}

    }//GEN-LAST:event_btnHapusMouseClicked

    private void txtHargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaJualActionPerformed

    private void btnBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseClicked
        // TODO add your handling code here:
try {
    conn.setAutoCommit(false);
    TransaksiGenerator transaksiGenerator = new TransaksiGenerator(txtKodeTransaksi);
    SpinnerNumberModel modelJumlah = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
    String idPenjualan = txtKodeTransaksi.getText();
    int idPengguna = LoginSession.getInstance().getIdPengguna();
    int total = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        int subtotal = Integer.parseInt(model.getValueAt(i, 6).toString());
        total += subtotal;
    }

    int bayar = Integer.parseInt(textBayar.getText().replace(".", "").trim());
    int kembali = Integer.parseInt(textKembali.getText().replace(".", "").trim());

    String sqlPenjualan = "INSERT INTO penjualan (id_penjualan, total, bayar, kembalian, id_pengguna) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement psPenjualan = conn.prepareStatement(sqlPenjualan);

    String sqlDetail = "INSERT INTO detail_penjualan (id_penjualan, id_produk, jumlah, subtotal, harga_beli, harga_jual) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement psDetail = conn.prepareStatement(sqlDetail);

    String sqlStokFIFO = "SELECT id_stok, harga_beli, stok FROM stok_produk WHERE id_produk = ? AND stok > 0 ORDER BY tanggal_kedaluwarsa ASC";
    PreparedStatement psStokFIFO = conn.prepareStatement(sqlStokFIFO);

    String sqlNamaProduk = "SELECT nama_produk FROM produk WHERE id_produk = ?";
    PreparedStatement psNamaProduk = conn.prepareStatement(sqlNamaProduk);

    String sqlKurangiStok = "UPDATE stok_produk SET stok = stok - ? WHERE id_stok = ?";
    PreparedStatement psKurangiStok = conn.prepareStatement(sqlKurangiStok);


    for (int i = 0; i < model.getRowCount(); i++) {
        String idProduk = model.getValueAt(i, 2).toString();
        int jumlah = Integer.parseInt(model.getValueAt(i, 4).toString());

        psStokFIFO.setString(1, idProduk);
        ResultSet rsCekStok = psStokFIFO.executeQuery();

        int totalStokTersedia = 0;
        while (rsCekStok.next()) {
            totalStokTersedia += rsCekStok.getInt("stok");
        }
        rsCekStok.close();

        if (jumlah > totalStokTersedia) {
            String namaProduk = "Tidak Diketahui";
            psNamaProduk.setString(1, idProduk);
            ResultSet rsNama = psNamaProduk.executeQuery();
            if (rsNama.next()) {
                namaProduk = rsNama.getString("nama_produk");
            }
            rsNama.close();

            conn.rollback();
            JOptionPane.showMessageDialog(this,
                "Stok untuk produk '" + namaProduk + "' tidak mencukupi.\n" +
                "Jumlah diminta: " + jumlah + " | Stok tersedia: " + totalStokTersedia,
                "Stok Tidak Cukup", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    psPenjualan.setString(1, idPenjualan);
    psPenjualan.setInt(2, total);
    psPenjualan.setInt(3, bayar);
    psPenjualan.setInt(4, kembali);
    psPenjualan.setInt(5, idPengguna);
    psPenjualan.executeUpdate();

    for (int i = 0; i < model.getRowCount(); i++) {
        String idProduk = model.getValueAt(i, 2).toString();
        int jumlah = Integer.parseInt(model.getValueAt(i, 4).toString());
        int hargaJual = Integer.parseInt(model.getValueAt(i, 5).toString());

        psStokFIFO.setString(1, idProduk);
        ResultSet rsFIFO = psStokFIFO.executeQuery();
        int sisaJumlah = jumlah;

        while (rsFIFO.next() && sisaJumlah > 0) {
            int idStok = rsFIFO.getInt("id_stok");
            int stokTersedia = rsFIFO.getInt("stok");
            int hargaBeli = rsFIFO.getInt("harga_beli");

            int jumlahDiambil = Math.min(stokTersedia, sisaJumlah);

            psDetail.setString(1, idPenjualan);
            psDetail.setString(2, idProduk);
            psDetail.setInt(3, jumlahDiambil);
            psDetail.setInt(4, jumlahDiambil * hargaJual);
            psDetail.setInt(5, hargaBeli);
            psDetail.setInt(6, hargaJual);
            psDetail.executeUpdate();

            psKurangiStok.setInt(1, jumlahDiambil);
            psKurangiStok.setInt(2, idStok);
            psKurangiStok.executeUpdate();

            sisaJumlah -= jumlahDiambil;
        }
        rsFIFO.close();
    }

    conn.commit();
    JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");

        CetakStruk cs = new CetakStruk();
        cs.cetakStrukTransaksiTerakhir();


    // Reset form
    model.setRowCount(0);
    transaksiGenerator.refreshKodeTransaksi();
    txtRp.setText("");
    spnJumlah.setModel(modelJumlah);
    kd_barang.setText("");
    txtHargaJual.setText("");
    spnJumlah.setValue(1);

} catch (Exception e) {
    try {
        conn.rollback();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal rollback: " + ex.getMessage());
    }
    JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi: " + e.getMessage());
} finally {
    try {
        conn.setAutoCommit(true);
    } catch (SQLException ex) {
        System.out.println("Gagal setAutoCommit true: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_btnBayarMouseClicked

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btn_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pembelianActionPerformed

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        // TODO add your handling code here:
        Dashboard_master dashboard = new Dashboard_master();
        dashboard.setVisible(true);
        dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dispose();
        System.out.println("Sekarang Dalam Page Dashboard " + "Master");
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pembelianMouseClicked
        // TODO add your handling code here:
        Pembelian_master pembelian = new Pembelian_master();
        pembelian.setVisible(true);
        pembelian.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dispose();
        System.out.println("Sekarang Dalam Page Pembelian " + "Master");
    }//GEN-LAST:event_btn_pembelianMouseClicked

    private void btn_pemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pemasokActionPerformed

    private void btn_pemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pemasokMouseClicked
        // TODO add your handling code here:
        Pemasok_master pamasok = new Pemasok_master();
        pamasok.setVisible(true);
        pamasok.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dispose();
        System.out.println("Sekarang Dalam Page Pemasok " + "Master");
    }//GEN-LAST:event_btn_pemasokMouseClicked

    private void btn_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_stokActionPerformed

    private void btn_pengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pengembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pengembalianActionPerformed

    private void btn_pengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengembalianMouseClicked
        // TODO add your handling code here:
//        Pemasok_master pamasok = new Pemasok_master();
//        pamasok.setVisible(true);
//        pamasok.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        dispose();
          System.out.println("Sekarang Dalam Page Pengembalian Barang " + "Master");
    }//GEN-LAST:event_btn_pengembalianMouseClicked

    private void btn_stokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stokMouseClicked
        // TODO add your handling code here:
        Stok_master stok = new Stok_master();
        stok.setVisible(true);
        stok.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dispose();
        System.out.println("Sekarang Dalam Page Stok " + "Master");
    }//GEN-LAST:event_btn_stokMouseClicked

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dashboardActionPerformed

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        // TODO add your handling code here:
        System.out.println("Sekarang Dalam Page Laporan " + "Master");
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void barcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeKeyTyped
    private void resetTabel() {
        // Menghapus semua baris di tabel setelah pembayaran berhasil
        model.setRowCount(0);
    }
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
            java.util.logging.Logger.getLogger(Penjualan_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan_master.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Penjualan_master().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Penjualan_master.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgoundUtama;
    private javax.swing.JTextField barcode;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_pemasok;
    private javax.swing.JButton btn_pembelian;
    private javax.swing.JButton btn_pengembalian;
    private javax.swing.JButton btn_stok;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JTextField showTotalField;
    private javax.swing.JPanel sidebar;
    private javax.swing.JSpinner spnJumlah;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTextField textBayar;
    private javax.swing.JTextField textKembali;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtKodeTransaksi;
    private javax.swing.JTextField txtRp;
    // End of variables declaration//GEN-END:variables
public class CetakStruk {

    // Method untuk mendapatkan id_penjualan terakhir (varchar)
    public String ambilIdPenjualanTerakhir() {
        String idPenjualan = null;
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_kasirtoko", "root", "");

            String sql = "SELECT id_penjualan FROM penjualan ORDER BY tanggal_transaksi DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPenjualan = rs.getString("id_penjualan");
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPenjualan;
    }

    public void cetakStruk(String idPenjualan) {
        try { 
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_kasirtoko", "root", "");

            String reportPath = "src/laporan/struk.jasper";

            Map<String, Object> param = new HashMap<>();
            param.put("id_penjualan", idPenjualan);

            JasperPrint print = JasperFillManager.fillReport(reportPath, param, conn);
            JasperViewer.viewReport(print, false);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cetakStrukTransaksiTerakhir() {
        String idPenjualanTerakhir = ambilIdPenjualanTerakhir();
        if (idPenjualanTerakhir != null) {
            cetakStruk(idPenjualanTerakhir);
        } else {
            System.out.println("Tidak ada transaksi penjualan yang ditemukan.");
        }
    }}}

