/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class KartuPanel extends JPanel {
    public Image background;
    public String kodeRFID = "";

    public KartuPanel(String pathGambar) {
        this.background = new ImageIcon(pathGambar).getImage();
    }

    public void setKodeRFID(String kode) {
        this.kodeRFID = kode;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("RFID: " + kodeRFID, 20, getHeight() - 30);
    }
}

