package support;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Yiung Za
 */

public class Transparant {

    public static void makeTransparent(JTextField textfield) {
        textfield.setBackground(new java.awt.Color(255, 255, 255, 0));
    }

    public static void buttonTransparant(JButton jbutton) {
        jbutton.setOpaque(false); // Menghilangkan latar belakang tombol
        jbutton.setContentAreaFilled(false); // Menghapus efek default button
        jbutton.setBorderPainted(false); // Menghapus border tombol
        jbutton.setBackground(new Color(255, 255, 255, 0)); // Warna transparan
    }
}



