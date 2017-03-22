
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public class Line extends Shape {
    
    public void drawShape(Graphics g, int x, int y, int w, int h, Color c, BasicStroke bs, boolean filled, float av, GradientPaint gp, TexturePaint tp) 
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(c);
        g2d.setStroke(bs);
        AlphaComposite ac = AlphaComposite.getInstance(3, av);
        g2d.setComposite(ac);
        if (gp != null) 
        {
            g2d.setPaint(gp);
        }
        g2d.drawLine(x, y, w, h);
    }
    
}
