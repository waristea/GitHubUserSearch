package githubusersearch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.border.AbstractBorder;

class CustomBorder extends AbstractBorder{
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        // TODO Auto-generated method stubs
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(new Color(36, 41, 46));
        g2d.drawRoundRect(x, y, width, height, 20, 20);
    }   
}

