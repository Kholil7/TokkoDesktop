    package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbtokko {
    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        if (mysqlconfig == null || mysqlconfig.isClosed()) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_kasirtoko";
                String user = "root";
                String password = "";

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

    public static void main(String[] args) {
        try {
            configDB();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/db_kasirtoko";
    String user = "root";
    String password = "";
    return DriverManager.getConnection(url, user, password);
}
}
