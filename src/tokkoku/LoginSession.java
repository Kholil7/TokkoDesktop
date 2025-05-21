/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokkoku;

/**
 *
 * @author Yiung Za
 */
// Kelas LoginSession
// LoginSession.java

public class LoginSession {
    private static LoginSession instance;
    private int idPengguna = -1; // Default -1 berarti belum login

    private LoginSession() {}

    // Mendapatkan instance singleton dari LoginSession
    public static LoginSession getInstance() {
        if (instance == null) {
            instance = new LoginSession();
        }
        return instance;
    }

    // Menyimpan ID Pengguna yang berhasil login
    public void setIdPengguna(int id) {
        this.idPengguna = id;
    }

    // Mengambil ID Pengguna, jika belum diset throw exception
    public int getIdPengguna() {
        if (this.idPengguna == -1) {
            throw new IllegalStateException("ID Pengguna belum diset.");
        }
        return this.idPengguna;
    }
}




