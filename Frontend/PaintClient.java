package Frontend;

import Backend.Client;

import javax.swing.*;
import java.awt.*;

public class PaintClient extends JComponent {

    private Client client;

    public PaintClient() {
    }

    public PaintClient(Client c) {
        this.client = c;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("Calibri", Font.BOLD, 15));
        g2d.drawString("T: " + client.getWaitingT(), client.getX() + 150, client.getY() - 3);
        //g2d.setColor(new Color(255, 182, 193));
        g2d.setColor(client.getColor());
        g2d.fillOval(client.getX() + 150, client.getY(), 21, 21);
        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("Calibri", Font.BOLD, 15));
        g2d.drawString(client.getId() + "", client.getX() + 155, client.getY() + 15);
    }
}