
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public class FreehandButton extends JButton {
    private BufferedImage freeHand;

    public FreehandButton() 
    {
        try 
        {
            this.freeHand = ImageIO.read(new File("pencil.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) 
    {
        super.paint(g);
        g.drawImage(this.freeHand, 0, 0, this.getWidth() - 1, this.getHeight() - 1, 0, 0, 200, 200, null);
    }  
}
