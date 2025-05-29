package tokkoku;

import database.dbtokko;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


public class Pembelian_karyawan extends JFrame {
private int x = 210;


    public Pembelian_karyawan() {
        initComponents();
        aturListener();
        loadComboBoxPemasok();
        isiComboBoxidproduk();
        
         // ⏱️ Set tanggal pembelian ke hari ini
         // ⛔ Kunci input agar tidak bisa diketik
    isinamaproduk.setEditable(false);
    totaljumlah.setEditable(false);


        
        buttonmasukan.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
   
    }
});
       
          btn_penjualan.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_dashboard.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_pemasok.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_pengembalian.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_stok.setBackground(new java.awt.Color(255, 255, 255, 0));
          btn_laporan.setBackground(new java.awt.Color(255, 255, 255, 0));
          isinamapemasok.setBackground(new java.awt.Color(255, 255, 255, 0));
          isihargabeli.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          isihargajual.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          totaljumlah.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          isikodeproduk.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          isinamaproduk.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          buttontambah.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          buttonmasukan.setBackground(new java.awt.Color(255, 255, 255, 0)); 
          buttonhapus.setBackground(new java.awt.Color(255, 255, 255, 0)); 
         
         // Batasi spinner agar tidak bisa kurang dari 1
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, null, 1);
        spinjumlah.setModel(model);
    }

    private void aturListener() {  
    isikodeproduk.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            isiNamaProdukOtomatis();
        }
    });
}

    public void actionPerformed(ActionEvent e) {
        String idProduk = (String) isikodeproduk.getSelectedItem();
        if (idProduk != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasirtoko", "root", "")) {
                PreparedStatement ps = conn.prepareStatement("SELECT nama_produk FROM produk WHERE id_produk = ?");
                ps.setString(1, idProduk);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    isinamaproduk.setText(rs.getString("nama_produk"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Gagal menampilkan nama produk: " + ex.getMessage());
            }
        }
    }
  
private void isiNamaProdukOtomatis() {
    String idProduk = (String) isikodeproduk.getSelectedItem();
    if (idProduk != null) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasirtoko", "root", "");
             PreparedStatement ps = conn.prepareStatement("SELECT nama_produk FROM produk WHERE id_produk = ?")) {

            ps.setString(1, idProduk);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isinamaproduk.setText(rs.getString("nama_produk"));
            } else {
                isinamaproduk.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil nama produk: " + ex.getMessage());
        }
    }
}

private void isiComboBoxidproduk() {
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasirtoko", "root", "");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id_produk FROM produk")) {

        isikodeproduk.removeAllItems(); 
        while (rs.next()) {
            isikodeproduk.addItem(rs.getString("id_produk"));
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal mengisi kode produk: " + ex.getMessage());
    }
}

private void loadComboBoxPemasok() {
    try {
        Connection conn = dbtokko.configDB();
        String sql = "SELECT id_pemasok, nama FROM suplier";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        isinamapemasok.removeAllItems();
        while (rs.next()) {
            isinamapemasok.addItem(rs.getInt("id_pemasok") + " - " + rs.getString("nama"));

        }
        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal memuat data pemasok: " + e.getMessage());
    }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpembelian = new javax.swing.JTable();
        spinjumlah = new javax.swing.JSpinner();
        buttontambah = new javax.swing.JButton();
        buttonhapus = new javax.swing.JButton();
        buttonmasukan = new javax.swing.JButton();
        isihargabeli = new javax.swing.JTextField();
        isinamaproduk = new javax.swing.JTextField();
        tanggalkadaluwarsa = new de.wannawork.jcalendar.JCalendarComboBox();
        btn_penjualan = new javax.swing.JButton();
        btn_dashboard = new javax.swing.JButton();
        btn_pemasok = new javax.swing.JButton();
        btn_pengembalian = new javax.swing.JButton();
        btn_stok = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        isinamapemasok = new javax.swing.JComboBox<>();
        isihargajual = new javax.swing.JTextField();
        isikodeproduk = new javax.swing.JComboBox<>();
        tanggalPembelianChooser = new de.wannawork.jcalendar.JCalendarComboBox();
        totaljumlah = new javax.swing.JTextField();
        tanggalpembelian = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tabelpembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tanggal Pembelian", "Tanggal Kadaluarsa", "	Nama Pemasok", "Kode Produk", "Nama Produk", "	Harga Jual", "Harga Beli", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tabelpembelian);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 1200, 270));
        jPanel1.add(spinjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 590, 90, 30));

        buttontambah.setBorder(null);
        buttontambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontambahActionPerformed(evt);
            }
        });
        jPanel1.add(buttontambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 360, 220, 70));

        buttonhapus.setBorder(null);
        buttonhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonhapusActionPerformed(evt);
            }
        });
        jPanel1.add(buttonhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 650, 130, 40));

        buttonmasukan.setBorder(null);
        buttonmasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonmasukanMouseClicked(evt);
            }
        });
        buttonmasukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonmasukanActionPerformed(evt);
            }
        });
        jPanel1.add(buttonmasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 640, 170, 50));

        isihargabeli.setBorder(null);
        isihargabeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isihargabeliKeyTyped(evt);
            }
        });
        jPanel1.add(isihargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 370, 150, 40));

        isinamaproduk.setBorder(null);
        isinamaproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isinamaprodukActionPerformed(evt);
            }
        });
        jPanel1.add(isinamaproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, 200, 30));
        jPanel1.add(tanggalkadaluwarsa, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 560, -1, -1));

        btn_penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_penjualanMouseClicked(evt);
            }
        });
        btn_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_penjualanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_penjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 240, 50));

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
        jPanel1.add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 240, 50));

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
        jPanel1.add(btn_pemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 240, 50));

        btn_pengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengembalianMouseClicked(evt);
            }
        });
        jPanel1.add(btn_pengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 240, 50));

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
        jPanel1.add(btn_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 240, 50));

        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
        });
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });
        jPanel1.add(btn_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 240, 50));

        isinamapemasok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        isinamapemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isinamapemasokActionPerformed(evt);
            }
        });
        jPanel1.add(isinamapemasok, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 170, 30));

        isihargajual.setBorder(null);
        isihargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isihargajualActionPerformed(evt);
            }
        });
        isihargajual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                isihargajualKeyTyped(evt);
            }
        });
        jPanel1.add(isihargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, 150, 30));

        isikodeproduk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        isikodeproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isikodeprodukActionPerformed(evt);
            }
        });
        jPanel1.add(isikodeproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 170, 40));
        jPanel1.add(tanggalPembelianChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, -1, -1));

        totaljumlah.setBorder(null);
        totaljumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaljumlahActionPerformed(evt);
            }
        });
        totaljumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totaljumlahKeyTyped(evt);
            }
        });
        jPanel1.add(totaljumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 660, 150, 30));

        tanggalpembelian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Pembelian-Karyawan.png"))); // NOI18N
        jPanel1.add(tanggalpembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1460, 750));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonmasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonmasukanActionPerformed

String namaPemasok = (String) isinamapemasok.getSelectedItem();
String idProduk = (String) isikodeproduk.getSelectedItem();
String namaProduk = isinamaproduk.getText().trim();
String hargaJualString = isihargajual.getText().trim();
String hargaBeliString = isihargabeli.getText().trim();
int jumlah = (int) spinjumlah.getValue();

String tanggalKadaluarsa = "";
if (tanggalkadaluwarsa.getDate() != null) {
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    tanggalKadaluarsa = sdf.format(tanggalkadaluwarsa.getDate());
}

String tanggalPembelian = "";
if (tanggalPembelianChooser.getDate() != null) {
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    tanggalPembelian = sdf.format(tanggalPembelianChooser.getDate());
}

if (tanggalPembelian.isEmpty() || tanggalKadaluarsa.isEmpty() || namaPemasok == null || idProduk == null || 
    namaProduk.isEmpty() || hargaBeliString.isEmpty() || hargaJualString.isEmpty() || jumlah <= 0) {
    JOptionPane.showMessageDialog(this, "Lengkapi semua data ");
    return;
}

try {
    java.util.Date kadaluarsaDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tanggalKadaluarsa);
    java.util.Date pembelianDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tanggalPembelian);
    if (kadaluarsaDate.before(pembelianDate)) {
        JOptionPane.showMessageDialog(this, "Tanggal kadaluarsa tidak boleh sebelum tanggal pembelian.");
        return;
    }
} catch (ParseException ex) {
    JOptionPane.showMessageDialog(this, "Format tanggal salah.");
    return;
}

int hargaBeli, hargaJual;
try {
    hargaBeli = Integer.parseInt(hargaBeliString);
    hargaJual = Integer.parseInt(hargaJualString);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Harga harus berupa angka");
    return;
}

int totalItem = hargaBeli * jumlah;

javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabelpembelian.getModel();
model.insertRow(0, new Object[]{
    tanggalPembelian,   
    tanggalKadaluarsa,   
    namaPemasok,         
    idProduk,            
    namaProduk,          
    hargaJual,           
    hargaBeli,           
    jumlah,              
    totalItem            
});

isinamapemasok.setSelectedIndex(0);
isikodeproduk.setSelectedIndex(0);
isinamaproduk.setText("");
isihargabeli.setText("");
isihargajual.setText("");
spinjumlah.setValue(1);
totaljumlah.setText("");
tanggalkadaluwarsa.setDate(null);
tanggalPembelianChooser.setDate(null);

int totalHargaSemua = 0;
for (int i = 0; i < model.getRowCount(); i++) {
    Object value = model.getValueAt(i, 8); // kolom 8 = totalItem
    if (value != null) {
        totalHargaSemua += Integer.parseInt(value.toString());
    }
}

totaljumlah.setText(String.valueOf(totalHargaSemua));
System.out.println("Total Harga Semua: Rp " + totalHargaSemua);

    }//GEN-LAST:event_buttonmasukanActionPerformed

    private void buttontambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontambahActionPerformed
try {
    int idPengguna = LoginSession.getInstance().getIdPengguna();
    Connection conn = dbtokko.configDB();
    conn.setAutoCommit(false);

    DefaultTableModel model = (DefaultTableModel) tabelpembelian.getModel();
    int rowCount = model.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Tabel pembelian kosong!");
        return;
    }

    String tglBeliStr = model.getValueAt(0, 0).toString();
    if (tglBeliStr == null || tglBeliStr.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tanggal pembelian belum diisi di tabel!");
        return;
    }

    java.util.Date tglBeliDate;
    try {
        tglBeliDate = new SimpleDateFormat("yyyy-MM-dd").parse(tglBeliStr);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Format tanggal pembelian tidak valid: " + tglBeliStr);
        return;
    }
    String tanggalPembelian = new SimpleDateFormat("yyyy-MM-dd").format(tglBeliDate);

String namaSuplier = model.getValueAt(0, 2).toString();

 
if (namaSuplier.contains(" - ")) {
    namaSuplier = namaSuplier.split(" - ", 2)[1].trim();
}

String sqlCariSuplier = "SELECT id_pemasok FROM suplier WHERE nama = ?";
PreparedStatement pstCariSuplier = conn.prepareStatement(sqlCariSuplier);
pstCariSuplier.setString(1, namaSuplier);
ResultSet rsSuplier = pstCariSuplier.executeQuery();
    int idPemasok = -1;
    if (rsSuplier.next()) {
        idPemasok = rsSuplier.getInt("id_pemasok");
    } else {
        JOptionPane.showMessageDialog(this, "Pemasok tidak ditemukan!");
        return;
    }
    rsSuplier.close();
    pstCariSuplier.close();

    String sqlPembelian = "INSERT INTO pembelian (id_pemasok, total, tanggal_pembelian, id_pengguna) VALUES (?, 0, ?, ?)";
    PreparedStatement pstPembelian = conn.prepareStatement(sqlPembelian, Statement.RETURN_GENERATED_KEYS);
    pstPembelian.setInt(1, idPemasok);
    pstPembelian.setString(2, tanggalPembelian);
    pstPembelian.setInt(3, idPengguna);
    pstPembelian.executeUpdate();

    ResultSet rsPembelian = pstPembelian.getGeneratedKeys();
    int idPembelian = -1;
    if (rsPembelian.next()) {
        idPembelian = rsPembelian.getInt(1);
    }
    rsPembelian.close();
    pstPembelian.close();

    double totalKeseluruhan = 0;

    for (int i = 0; i < rowCount; i++) {
        String idProduk = model.getValueAt(i, 3).toString();
        String tglKedaluwarsaStr = model.getValueAt(i, 1).toString();

        java.sql.Date tglKedaluwarsa;
        try {
            java.util.Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(tglKedaluwarsaStr);
            tglKedaluwarsa = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format tanggal kadaluarsa tidak valid di baris " + (i + 1) + ": " + tglKedaluwarsaStr);
            conn.rollback();
            conn.close();
            return;
        }

        int jumlah = Integer.parseInt(model.getValueAt(i, 7).toString());
        double hargaBeli = Double.parseDouble(model.getValueAt(i, 6).toString());
        double hargaJual = Double.parseDouble(model.getValueAt(i, 5).toString());
        double subtotal = Double.parseDouble(model.getValueAt(i, 8).toString());

        totalKeseluruhan += subtotal;

        int idStok = -1;

        String cekStok = "SELECT id_stok FROM stok_produk WHERE id_produk = ? AND harga_beli = ? AND harga_jual = ? AND tanggal_kedaluwarsa = ?";
        PreparedStatement pstCek = conn.prepareStatement(cekStok);
        pstCek.setString(1, idProduk);
        pstCek.setDouble(2, hargaBeli);
        pstCek.setDouble(3, hargaJual);
        pstCek.setDate(4, tglKedaluwarsa);
        ResultSet rsCek = pstCek.executeQuery();

        if (rsCek.next()) {
            idStok = rsCek.getInt("id_stok");
            String updateStok = "UPDATE stok_produk SET stok = stok + ? WHERE id_stok = ?";
            PreparedStatement pstUpdate = conn.prepareStatement(updateStok);
            pstUpdate.setInt(1, jumlah);
            pstUpdate.setInt(2, idStok);
            pstUpdate.executeUpdate();
            pstUpdate.close();
        } else {
            String insertStok = "INSERT INTO stok_produk (id_produk, stok, harga_beli, harga_jual, tanggal_kedaluwarsa, id_pembelian) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstInsert = conn.prepareStatement(insertStok, Statement.RETURN_GENERATED_KEYS);
            pstInsert.setString(1, idProduk);
            pstInsert.setInt(2, jumlah);
            pstInsert.setDouble(3, hargaBeli);
            pstInsert.setDouble(4, hargaJual);
            pstInsert.setDate(5, tglKedaluwarsa);
            pstInsert.setInt(6, idPembelian);
            pstInsert.executeUpdate();

            ResultSet rsNew = pstInsert.getGeneratedKeys();
            if (rsNew.next()) {
                idStok = rsNew.getInt(1);
            }
            rsNew.close();
            pstInsert.close();
        }
        rsCek.close();
        pstCek.close();

        String sqlDetail = "INSERT INTO detail_pembelian (id_pembelian, id_produk, id_stok, jumlah, subtotal, harga_beli, harga_jual, tanggal_kedaluwarsa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstDetail = conn.prepareStatement(sqlDetail);
        pstDetail.setInt(1, idPembelian);
        pstDetail.setString(2, idProduk);
        pstDetail.setInt(3, idStok);
        pstDetail.setInt(4, jumlah);
        pstDetail.setDouble(5, subtotal);
        pstDetail.setDouble(6, hargaBeli);
        pstDetail.setDouble(7, hargaJual);
        pstDetail.setDate(8, tglKedaluwarsa);
        pstDetail.executeUpdate();
        pstDetail.close();
    }

    String updateTotal = "UPDATE pembelian SET total = ? WHERE id_pembelian = ?";
    PreparedStatement pstTotal = conn.prepareStatement(updateTotal);
    pstTotal.setDouble(1, totalKeseluruhan);
    pstTotal.setInt(2, idPembelian);
    pstTotal.executeUpdate();
    pstTotal.close();

    conn.commit();
    conn.close();

    JOptionPane.showMessageDialog(this, "Data pembelian berhasil disimpan!");
    model.setRowCount(0);
    totaljumlah.setText("");

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
}
    }//GEN-LAST:event_buttontambahActionPerformed

    private void buttonmasukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonmasukanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonmasukanMouseClicked

    private void buttonhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonhapusActionPerformed
        // TODO add your handling code here:    
    }//GEN-LAST:event_buttonhapusActionPerformed

    private void isinamaprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isinamaprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isinamaprodukActionPerformed

    private void btn_pengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengembalianMouseClicked
        // TODO add your handling code here:
        System.out.println("Sekarang Dalam Page Pengembalian Barang " + "Master");
    }//GEN-LAST:event_btn_pengembalianMouseClicked

    private void btn_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_stokActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void btn_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_penjualanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_penjualanActionPerformed

    private void btn_penjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penjualanMouseClicked
        Dashboard_karyawan dashboard = new Dashboard_karyawan();
        dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dashboard.setVisible(true);
        dispose();
        System.out.println("Sekarang Dalam Page Dashboard " + "Master");
    }//GEN-LAST:event_btn_penjualanMouseClicked

    private void btn_pemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pemasokMouseClicked
         //TODO add your handling code here:
        Pemasok_Karyawan pemasok = new Pemasok_Karyawan();
        pemasok.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pemasok.setVisible(true);
        dispose();
        System.out.println("Sekarang Dalam Page Pemasok " + "Master");
    }//GEN-LAST:event_btn_pemasokMouseClicked

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
    try {
        // TODO add your handling code here:
        Penjualan_karyawan penjualan = new Penjualan_karyawan();
        penjualan.setExtendedState(JFrame.MAXIMIZED_BOTH);
        penjualan.setVisible(true);
        dispose();
        System.out.println("Sekarang Dalam Page Penjualan " + "Master");
    } catch (SQLException ex) {
        Logger.getLogger(Pembelian_karyawan.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dashboardActionPerformed

    private void btn_stokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_stokMouseClicked
        // TODO add your handling code here:
        System.out.println("Sekarang Dalam Page Stok " + "Master");
    }//GEN-LAST:event_btn_stokMouseClicked

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        // TODO add your handling code here:
        System.out.println("Sekarang Dalam Page Laporan " + "Master");
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_pemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pemasokActionPerformed

    private void isinamapemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isinamapemasokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isinamapemasokActionPerformed

    private void isihargajualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isihargajualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isihargajualActionPerformed

    private void isikodeprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isikodeprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isikodeprodukActionPerformed

    private void totaljumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaljumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaljumlahActionPerformed

    private void isihargabeliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isihargabeliKeyTyped
      char c = evt.getKeyChar();
if (!Character.isDigit(c)) {
    evt.consume();
}

    }//GEN-LAST:event_isihargabeliKeyTyped

    private void isihargajualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_isihargajualKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
if (!Character.isDigit(c)) {
    evt.consume();
}
    }//GEN-LAST:event_isihargajualKeyTyped

    private void totaljumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totaljumlahKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totaljumlahKeyTyped

//    
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
            java.util.logging.Logger.getLogger(Pembelian_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembelian_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembelian_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembelian_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Pembelian_karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_dashboard;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_pemasok;
    private javax.swing.JButton btn_pengembalian;
    private javax.swing.JButton btn_penjualan;
    private javax.swing.JButton btn_stok;
    private javax.swing.JButton buttonhapus;
    private javax.swing.JButton buttonmasukan;
    private javax.swing.JButton buttontambah;
    private javax.swing.JTextField isihargabeli;
    private javax.swing.JTextField isihargajual;
    private javax.swing.JComboBox<String> isikodeproduk;
    private javax.swing.JComboBox<String> isinamapemasok;
    private javax.swing.JTextField isinamaproduk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinjumlah;
    private javax.swing.JTable tabelpembelian;
    private de.wannawork.jcalendar.JCalendarComboBox tanggalPembelianChooser;
    private de.wannawork.jcalendar.JCalendarComboBox tanggalkadaluwarsa;
    private javax.swing.JLabel tanggalpembelian;
    private javax.swing.JTextField totaljumlah;
    // End of variables declaration//GEN-END:variables
}
