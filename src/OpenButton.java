
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
public class OpenButton extends JButton {
    
    private BufferedImage op;

    public OpenButton() {
        try {
            this.op = ImageIO.read(new File("open.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) 
    {
        super.paint(g);
        g.drawImage(this.op, 0, 0, this.getWidth() - 1, this.getHeight() - 1, 0, 0, 200, 200, null);
    }
    
}
