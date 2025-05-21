    package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbtokko {
    private static Connection mysqlconfig;

    // Metode untuk mendapatkan koneksi ke database
    public static Connection configDB() throws SQLException {
        if (mysqlconfig == null || mysqlconfig.isClosed()) { // Cek apakah koneksi null atau sudah tertutup
            try {
                String url = "jdbc:mysql://localhost:3306/db_kasirtoko"; // Ganti sesuai database
                String user = "root"; // Ganti jika ada username lain
                String password = ""; // Ganti jika ada password database

                // Load driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                mysqlconfig = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Koneksi gagal: " + e.getMessage());
                throw new SQLException("Tidak dapat terhubung ke database");
            }
        }
        return mysqlconfig;
    }

    // Tes koneksi ke database
    public static void main(String[] args) {
        try {
            configDB(); // Coba koneksi saat program dijalankan
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/db_kasirtoko";
    String user = "root";
    String password = ""; // atau sesuai password MySQL kamu
    return DriverManager.getConnection(url, user, password);
}

}
