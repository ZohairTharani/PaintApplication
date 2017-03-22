
import java.io.File;
import javax.swing.filechooser.FileFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public class MyFile extends FileFilter {
    
    public boolean accept(File pathname) 
    {
        if (pathname.getName().indexOf(".png") > 0) 
        {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return ".png";
    }
    
}
