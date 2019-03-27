package Frontend;

import javax.swing.*;
import java.awt.*;

public class PaintCasierie extends JComponent {

    private int nrCozi;
    private int x;
    private int y;

    public PaintCasierie() {
    }

    public PaintCasierie(int nrCozi, int x, int y) {
        super();
        this.nrCozi = nrCozi;
        this.x = x;
        this.y = y;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 1; i <= nrCozi; i++) {
            g2d.setColor(new Color(0, 0, 0));
            g2d.fillRect(x, y * i + 5, 60, 40);
        }
        for (int i = 1; i <= nrCozi; i++) {
            g2d.setColor(new Color(255, 182, 193));
            g2d.setFont(new Font("Calibri", Font.BOLD, 40));
            g2d.drawString("" + i, x + 20, y * i + 38);
        }
    }
}