/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tokkoku;

import database.dbtokko;
import support.SecurityUtil;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import support.SecurityUtil;
/**
 *
 * @author Yiung Za
 */
public class Login extends javax.swing.JFrame {
    
    /**
     * Creates new form login
     */
    public Login() {
        initComponents();
        ipt_username.setBackground(new java.awt.Color(255, 255, 255, 0));
        ipt_password.setBackground(new java.awt.Color(255, 255, 255, 0));
        showPass.setBackground(new java.awt.Color(255, 255, 255, 0));
        btn_submit.setBackground(new java.awt.Color(255, 255, 255, 0));
        btn_daftar.setBackground(new java.awt.Color(255, 255, 255, 0));
        ComboLogin.setBackground(new java.awt.Color(255, 255, 255, 0));
        
        ipt_rfid.getDocument().addDocumentListener(new DocumentListener() {
    private void checkRfid() {
        String rfid = ipt_rfid.getText().trim();

        // Misal RFID valid adalah 8 karakter, sesuaikan dengan kebutuhanmu
        if (rfid.length() == 10) {
            // panggil proses login langsung
            loginProsesViaRFID(rfid);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkRfid();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // bisa abaikan atau lakukan reset jika perlu
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // biasnya tidak dipakai di JTextField biasa
    }
});

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_submit = new javax.swing.JButton();
        ipt_username = new javax.swing.JTextField();
        ipt_password = new javax.swing.JPasswordField();
        btn_daftar = new javax.swing.JButton();
        showPass = new javax.swing.JCheckBox();
        ipt_rfid = new javax.swing.JTextField();
        ComboLogin = new javax.swing.JComboBox<>();
        BackgroundUtama = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_submit.setBackground(new java.awt.Color(51, 51, 54));
        btn_submit.setBorder(null);
        btn_submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitMouseClicked(evt);
            }
        });
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });
        getContentPane().add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 150, 50));

        ipt_username.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ipt_username.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ipt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(ipt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 360, 40));

        ipt_password.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        ipt_password.setBorder(null);
        getContentPane().add(ipt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 310, 40));

        btn_daftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_daftarMouseClicked(evt);
            }
        });
        btn_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_daftarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 560, 90, 30));

        showPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPassMouseClicked(evt);
            }
        });
        getContentPane().add(showPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 40, 40));
        getContentPane().add(ipt_rfid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 490, 200, -1));

        ComboLogin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Owner", "Karyawan" }));
        getContentPane().add(ComboLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 160, 40));

        BackgroundUtama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Pg_login.png"))); // NOI18N
        getContentPane().add(BackgroundUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_submitActionPerformed

    private void ipt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipt_usernameActionPerformed

    private void btn_daftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_daftarMouseClicked
        // TODO add your handling code here:
        Register Jregister = new Register();
        Jregister.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_daftarMouseClicked

    private void btn_submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitMouseClicked
String username = ipt_username.getText().trim();
String password = new String(ipt_password.getPassword()).trim();
String selectedRole = ComboLogin.getSelectedItem().toString().trim(); // Role yang dipilih user
String rfid = ipt_rfid.getText().trim();

try (Connection conn = dbtokko.configDB()) {
    if (!rfid.isEmpty()) {
        String rfidSql = "SELECT id_pengguna, role FROM pengguna WHERE rfid = ?";
        try (PreparedStatement rfidStmt = conn.prepareStatement(rfidSql)) {
            rfidStmt.setString(1, rfid);
            ResultSet rfidRs = rfidStmt.executeQuery();

            if (rfidRs.next()) {
                int idPengguna = rfidRs.getInt("id_pengguna");
                String role = rfidRs.getString("role");

                if (!role.equalsIgnoreCase(selectedRole)) {
                    JOptionPane.showMessageDialog(null, "Role tidak sesuai dengan kartu RFID", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LoginSession.getInstance().setIdPengguna(idPengguna);
                JOptionPane.showMessageDialog(null, "Selamat datang!", "Login Berhasil (RFID)", JOptionPane.INFORMATION_MESSAGE);

                if ("Owner".equalsIgnoreCase(role)) {
                    new Dashboard_owner().setVisible(true);
                } else if ("Karyawan".equalsIgnoreCase(role)) {
                    new Dashboard_karyawan().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Role tidak dikenali. Hubungi administrator.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "RFID tidak ditemukan, silakan login manual.", "RFID Tidak Valid", JOptionPane.WARNING_MESSAGE);
        ipt_rfid.setText("");
        ipt_rfid.requestFocus();
        return;
    }

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Username dan Password wajib diisi", "Login Gagal", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "SELECT id_pengguna, password, role FROM pengguna WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String dbPassword = rs.getString("password");
            String dbRole = rs.getString("role");
            String inputHashedPassword = SecurityUtil.hashPassword(password);

            if (inputHashedPassword != null && inputHashedPassword.equals(dbPassword)) {
                if (!dbRole.equalsIgnoreCase(selectedRole)) {
                    JOptionPane.showMessageDialog(null, "Role tidak sesuai dengan akun", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LoginSession.getInstance().setIdPengguna(rs.getInt("id_pengguna"));
                JOptionPane.showMessageDialog(null, "Selamat datang!", "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);

                if ("Owner".equalsIgnoreCase(dbRole)) {
                    new Dashboard_owner().setVisible(true);
                } else if ("Karyawan".equalsIgnoreCase(dbRole)) {
                    new Dashboard_karyawan().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Role tidak dikenali. Hubungi administrator.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Password salah", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username tidak ditemukan", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Kesalahan koneksi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btn_submitMouseClicked

    private void btn_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_daftarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_daftarActionPerformed

    private void showPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPassMouseClicked
        // TODO add your handling code here:
                if(showPass.isSelected()){
           ipt_password.setEchoChar((char)0);
       } else{
           ipt_password.setEchoChar('*');}
    }//GEN-LAST:event_showPassMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    SwingUtilities.invokeLater(() -> {
        ipt_rfid.requestFocusInWindow();
    });
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundUtama;
    private javax.swing.JComboBox<String> ComboLogin;
    private javax.swing.JButton btn_daftar;
    private javax.swing.JButton btn_submit;
    private javax.swing.JPasswordField ipt_password;
    private javax.swing.JTextField ipt_rfid;
    private javax.swing.JTextField ipt_username;
    private javax.swing.JCheckBox showPass;
    // End of variables declaration//GEN-END:variables
private void loginProsesViaRFID(String rfid) {
    try (Connection conn = dbtokko.configDB()) {
        String sql = "SELECT id_pengguna, username, role FROM pengguna WHERE rfid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rfid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idPengguna = rs.getInt("id_pengguna");
                String username = rs.getString("username");
                String role = rs.getString("role");

                LoginSession.getInstance().setIdPengguna(idPengguna);

                JOptionPane.showMessageDialog(null, "Selamat datang, " + username + "!", "Login Berhasil", JOptionPane.INFORMATION_MESSAGE);

                if ("Owner".equalsIgnoreCase(role)) {
                    Dashboard_owner dashboard = new Dashboard_owner();
                    dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    dashboard.setVisible(true);
                    dispose();
                } else if ("Karyawan".equalsIgnoreCase(role)) {
                    Dashboard_karyawan dashboard = new Dashboard_karyawan();
                    dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    dashboard.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Role tidak dikenali: " + role, "Login Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "RFID tidak ditemukan, silakan coba lagi.", "RFID Tidak Valid", JOptionPane.WARNING_MESSAGE);
                ipt_rfid.setText("");
                ipt_rfid.requestFocus();
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Kesalahan koneksi: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
