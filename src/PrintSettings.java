
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public class PrintSettings implements Printable {
    
    Image img;

    public PrintSettings(Image img) 
    {
        this.img = img;
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException 
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.img, 0, 0, null);
        return 0;
    }    
    
}
