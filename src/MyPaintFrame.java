
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zohai
 */
public class MyPaintFrame extends JFrame {
    
    private static final long svID = 1;
    private JPanel jContentPane = null;
    private JMenuBar jMB = null;
    private JMenu jMenu = null;
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null;
    private JMenuItem jMenuItem2 = null;
    private JMenuItem jMenuItem3 = null;
    private JMenuItem jMenuItem4 = null;
    private JPanel jPanel = null;
    private JPanel jPanel1 = null;
    private JPanel jPanel2 = null;
    private JPanel jPanel3 = null;
    private JSlider jSlider = null;
    private JSlider jSlider1 = null;
    private JSlider jSlider2 = null;
    private JSlider jSlider3 = null;
    private JSlider jSlider4 = null;
    private JToolBar jToolBar = null;
    private JToolBar jToolBar1 = null;
    private JButton jButton = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JButton jButton3 = null;    
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JButton jButton6 = null;
    private JButton jButton7 = null;
    private JButton jButton8 = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JCheckBox jCheckBox = null;
    private JCheckBox jCheckBox1 = null;
    private JTextField jTextField = null;
    private Color c;
    private BasicStroke bs;
    private int oldX;
    private int oldY;
    private Image bg;
    private Image fg;
    private Shape s;
    private boolean filled;   
    private Vector<Shape> v; 
    private float av;    
    private JToggleButton jToggleButton = null;
    private JToggleButton jToggleButton1 = null;
    private JToggleButton jToggleButton2 = null;
    private GradientPaint gp;
    private TexturePaint tp;

    private JMenuBar getJJMenuBar() {
        if (this.jMB == null) {
            this.jMB = new JMenuBar();
            this.jMB.add(this.getJMenu());
        }
        return this.jMB;
    }

    private JMenu getJMenu() {
        if (this.jMenu == null) {
            this.jMenu = new JMenu();
            this.jMenu.setText("File");
            this.jMenu.add(this.getJMenuItem());
            this.jMenu.add(this.getJMenuItem1());
            this.jMenu.add(this.getJMenuItem4());
            this.jMenu.add(this.getJMenuItem2());
            this.jMenu.add(this.getJMenuItem3());
        }
        return this.jMenu;
    }

    private JMenuItem getJMenuItem() {
        if (this.jMenuItem == null) {
            this.jMenuItem = new JMenuItem();
            this.jMenuItem.setText("Open");
            this.jMenuItem.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.openFile();
                }
            });
        }
        return this.jMenuItem;
    }

    private JMenuItem getJMenuItem1() {
        if (this.jMenuItem1 == null) {
            this.jMenuItem1 = new JMenuItem();
            this.jMenuItem1.setText("Save");
            this.jMenuItem1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.saveFile();
                }
            });
        }
        return this.jMenuItem1;
    }

    private JMenuItem getJMenuItem2() {
        if (this.jMenuItem2 == null) {
            this.jMenuItem2 = new JMenuItem();
            this.jMenuItem2.setText("Print");
            this.jMenuItem2.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.printFile();
                }
            });
        }
        return this.jMenuItem2;
    }

    private JMenuItem getJMenuItem3() {
        if (this.jMenuItem3 == null) {
            this.jMenuItem3 = new JMenuItem();
            this.jMenuItem3.setText("Exit");
            this.jMenuItem3.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.ExitProg();
                }
            });
        }
        return this.jMenuItem3;
    }

    public void printFile() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(new PageFormat());
        if (pj.printDialog()) {
            Book b = new Book();
            PrintSettings print = new PrintSettings(this.bg);
            b.append(print, pf);
            pj.setPageable(b);
            try {
                pj.print();
            }
            catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFile() {
        JFileChooser jfc = new JFileChooser(".");
        MyFile mf = new MyFile();
        jfc.setFileFilter(mf);
        if (jfc.showOpenDialog(null) == 0) {
            try {
                File f = jfc.getSelectedFile();
                this.bg = ImageIO.read(f);
                this.repaint();
            }
            catch (Exception e) {
                System.out.println("File couldn't be loaded. ");
                e.printStackTrace();
            }
        }
    }

    public void saveFile() {
        JFileChooser y = new JFileChooser(".");
        MyFile x = new MyFile();
        y.setFileFilter(x);
        if (y.showSaveDialog(null) == 0) {
            try {
                File f = y.getSelectedFile();
                ImageIO.write((RenderedImage)((Object)this.bg), "png", f);
            }
            catch (Exception e) {
                System.out.println("Error saving file");
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.drawSample();
        this.jPanel1.getGraphics().drawImage(this.bg, 0, 0, null);
    }

    private void drawSample() {
        Graphics g = this.jPanel3.getGraphics();
        this.c = this.getColor();
        this.bs = this.getBasicStroke();
        this.av = this.getAlphaVal();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(this.bs);
        g2d.setColor(this.c);
        AlphaComposite ac = AlphaComposite.getInstance(3, this.av);
        g2d.setComposite(ac);
        if (this.jToggleButton1.isSelected()) {
            g2d.setPaint(this.gp);
        }
        g2d.drawLine(0, 0, this.jPanel3.getWidth(), this.jPanel3.getHeight());
    }

    private float getAlphaVal() {
        return 1.0f - (float)this.jSlider4.getValue() / 100.0f;
    }

    private BasicStroke getBasicStroke() {
        int lineWidth = this.jSlider3.getValue();
        float[] fa = new float[]{10.0f, 10.0f, 10.0f};
        if (this.jCheckBox.isSelected()) {
            return new BasicStroke(lineWidth, 0, 2, 10.0f, fa, 10.0f);
        }
        return new BasicStroke(lineWidth);
    }

    private Color getColor() {
        return new Color(this.jSlider.getValue(), this.jSlider1.getValue(), this.jSlider2.getValue());
    }

    private void ExitProg() {
        this.dispose();
        System.exit(0);
    }

    private JPanel getJPanel() {
        if (this.jPanel == null) {
            GridLayout gl1 = new GridLayout();
            gl1.setRows(2);
            this.jPanel = new JPanel();
            this.jPanel.setLayout(gl1);
            this.jPanel.add((Component)this.getJToolBar(), (Object)null);
            this.jPanel.add((Component)this.getJToolBar1(), (Object)null);
        }
        return this.jPanel;
    }

    private JPanel getJPanel1() {
        if (this.jPanel1 == null) {
            this.jPanel1 = new JPanel();
            this.jPanel1.setLayout(new GridBagLayout());
            this.jPanel1.addMouseListener(new MouseAdapter(){

                public void mouseReleased(MouseEvent e) {
                    Graphics bgg = MyPaintFrame.this.bg.getGraphics();
                    Graphics fgg = MyPaintFrame.this.fg.getGraphics();
                    fgg.setColor(MyPaintFrame.this.c);
                    if (MyPaintFrame.this.jToggleButton.isSelected()) {
                        fgg.drawString(MyPaintFrame.this.jTextField.getText(), e.getX(), e.getY());
                    }
                    bgg.drawImage(MyPaintFrame.this.fg, 0, 0, null);
                    MyPaintFrame.this.repaint();
                }

                public void mousePressed(MouseEvent e) {
                    MyPaintFrame.getOldX(MyPaintFrame.this, e.getX());
                    MyPaintFrame.getOldY(MyPaintFrame.this, e.getY());
                    if (MyPaintFrame.this.bg == null) {
                        MyPaintFrame.getImage(MyPaintFrame.this, MyPaintFrame.this.createImage(MyPaintFrame.this.jPanel1.getWidth(), MyPaintFrame.this.jPanel1.getHeight()));
                        MyPaintFrame.getForeground(MyPaintFrame.this, MyPaintFrame.this.createImage(MyPaintFrame.this.jPanel1.getWidth(), MyPaintFrame.this.jPanel1.getHeight()));
                    }
                }
            });
            this.jPanel1.addMouseMotionListener(new MouseMotionAdapter(){

                public void mouseDragged(MouseEvent e) {
                    int w = Math.abs(e.getX() - MyPaintFrame.this.oldX);
                    int h = Math.abs(e.getY() - MyPaintFrame.this.oldY);
                    int x = MyPaintFrame.this.oldX < e.getX() ? MyPaintFrame.this.oldX : e.getX();
                    int y = MyPaintFrame.this.oldY < e.getY() ? MyPaintFrame.this.oldY : e.getY();
                    if (MyPaintFrame.this.s instanceof Line || MyPaintFrame.this.s instanceof MyFreehand) {
                        x = MyPaintFrame.this.oldX;
                        y = MyPaintFrame.this.oldY;
                        w = e.getX();
                        h = e.getY();
                    }
                    Graphics fgg = MyPaintFrame.this.fg.getGraphics();
                    fgg.drawImage(MyPaintFrame.this.bg, 0, 0, null);
                    if (!MyPaintFrame.this.jToggleButton.isSelected()) {
                        if (MyPaintFrame.this.jToggleButton1.isSelected() && !MyPaintFrame.this.jToggleButton2.isSelected()) {
                            MyPaintFrame.this.s.drawShape(fgg, x, y, w, h, MyPaintFrame.this.c, MyPaintFrame.this.bs, MyPaintFrame.this.filled, MyPaintFrame.this.av, MyPaintFrame.this.gp, null);
                        } else if (!MyPaintFrame.this.jToggleButton1.isSelected() && MyPaintFrame.this.jToggleButton2.isSelected()) {
                            MyPaintFrame.this.s.drawShape(fgg, x, y, w, h, MyPaintFrame.this.c, MyPaintFrame.this.bs, MyPaintFrame.this.filled, MyPaintFrame.this.av, null, MyPaintFrame.this.tp);
                        } else if (MyPaintFrame.this.jToggleButton1.isSelected() && MyPaintFrame.this.jToggleButton2.isSelected()) {
                            MyPaintFrame.this.s.drawShape(fgg, x, y, w, h, MyPaintFrame.this.c, MyPaintFrame.this.bs, MyPaintFrame.this.filled, MyPaintFrame.this.av, MyPaintFrame.this.gp, MyPaintFrame.this.tp);
                        } else {
                            MyPaintFrame.this.s.drawShape(fgg, x, y, w, h, MyPaintFrame.this.c, MyPaintFrame.this.bs, MyPaintFrame.this.filled, MyPaintFrame.this.av, null, null);
                        }
                    }
                    MyPaintFrame.this.jPanel1.getGraphics().drawImage(MyPaintFrame.this.fg, 0, 0, null);
                    if (MyPaintFrame.this.s instanceof MyFreehand) {
                        MyPaintFrame.getOldX(MyPaintFrame.this, w);
                        MyPaintFrame.getOldY(MyPaintFrame.this, h);
                        Graphics bgg = MyPaintFrame.this.bg.getGraphics();
                        bgg.drawImage(MyPaintFrame.this.fg, 0, 0, null);
                    }
                }
            });
        }
        return this.jPanel1;
    }

    private JPanel getJPanel2() {
        if (this.jPanel2 == null) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(1);
            this.jPanel2 = new JPanel();
            this.jPanel2.setPreferredSize(new Dimension(1000, 50));
            this.jPanel2.setLayout(gridLayout);
            this.jPanel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            this.jPanel2.add((Component)this.getJPanel3(), (Object)null);
            this.jPanel2.add((Component)this.getJSlider(), (Object)null);
            this.jPanel2.add((Component)this.getJSlider1(), (Object)null);
            this.jPanel2.add((Component)this.getJSlider2(), (Object)null);
            this.jPanel2.add((Component)this.getJSlider3(), (Object)null);
        }
        return this.jPanel2;
    }

    private JPanel getJPanel3() {
        if (this.jPanel3 == null) {
            this.jPanel3 = new JPanel();
            this.jPanel3.setLayout(new GridBagLayout());
        }
        return this.jPanel3;
    }

    private JSlider getJSlider() {
        if (this.jSlider == null) {
            this.jSlider = new JSlider();
            this.jSlider.setMaximum(255);
            this.jSlider.setPaintTicks(true);
            this.jSlider.setMajorTickSpacing(50);
            this.jSlider.setPaintLabels(true);
            this.jSlider.setBackground(new Color(200, 0, 0));
            this.jSlider.setValue(0);
            this.jSlider.setMinorTickSpacing(10);
            this.jSlider.addChangeListener(new ChangeListener(){

                public void stateChanged(ChangeEvent e) {
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jSlider;
    }

    private JSlider getJSlider1() {
        if (this.jSlider1 == null) {
            this.jSlider1 = new JSlider();
            this.jSlider1.setMaximum(255);
            this.jSlider1.setMinorTickSpacing(10);
            this.jSlider1.setPaintLabels(true);
            this.jSlider1.setPaintTicks(true);
            this.jSlider1.setBackground(new Color(0, 200, 0));
            this.jSlider1.setValue(0);
            this.jSlider1.setMajorTickSpacing(50);
            this.jSlider1.addChangeListener(new ChangeListener(){

                public void stateChanged(ChangeEvent e) {
                   MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jSlider1;
    }

    private JSlider getJSlider2() {
        if (this.jSlider2 == null) {
            this.jSlider2 = new JSlider();
            this.jSlider2.setMaximum(255);
            this.jSlider2.setPaintLabels(true);
            this.jSlider2.setPaintTicks(true);
            this.jSlider2.setBackground(new Color(0, 0, 200));
            this.jSlider2.setMajorTickSpacing(50);
            this.jSlider2.setValue(0);
            this.jSlider2.setMinorTickSpacing(10);
            this.jSlider2.addChangeListener(new ChangeListener(){

                public void stateChanged(ChangeEvent e) {
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jSlider2;
    }

    private JSlider getJSlider3() {
        if (this.jSlider3 == null) {
            this.jSlider3 = new JSlider();
            this.jSlider3.setToolTipText("Line Width");
            this.jSlider3.setPaintTicks(true);
            this.jSlider3.setMajorTickSpacing(20);
            this.jSlider3.setMinorTickSpacing(5);
            this.jSlider3.setValue(10);
            this.jSlider3.setPaintLabels(true);
            this.jSlider3.addChangeListener(new ChangeListener(){

                public void stateChanged(ChangeEvent e) {
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jSlider3;
    }

    private JToolBar getJToolBar() {
        if (this.jToolBar == null) {
            this.jToolBar = new JToolBar();
            this.jToolBar.setLayout(new FlowLayout(0));
            this.jToolBar.setOrientation(0);
            this.jToolBar.setPreferredSize(new Dimension(200, 50));
            this.jToolBar.add(this.getJButton());
            this.jToolBar.add(this.getJButton1());
            this.jToolBar.add(this.getJButton2());
            this.jToolBar.add(this.getJButton8());
            this.jToolBar.add(this.getJButton3());
        }
        return this.jToolBar;
    }

    private JButton getJButton() {
        if (this.jButton == null) {
            this.jButton = new OpenButton();
            this.jButton.setPreferredSize(new Dimension(30, 30));
            this.jButton.setBounds(new Rectangle(18, 15, 30, 30));
            this.jButton.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.openFile();
                }
            });
        }
        return this.jButton;
    }

    private JButton getJButton1() {
        if (this.jButton1 == null) {
            this.jButton1 = new SaveButton();
            this.jButton1.setSize(new Dimension(30, 30));
            this.jButton1.setPreferredSize(new Dimension(30, 30));
            this.jButton1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.saveFile();
                }
            });
        }
        return this.jButton1;
    }

    private JButton getJButton2() {
        if (this.jButton2 == null) {
            this.jButton2 = new PrintButton();
            this.jButton2.setPreferredSize(new Dimension(30, 30));
            this.jButton2.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.printFile();
                }
            });
        }
        return this.jButton2;
    }

    private JButton getJButton3() {
        if (this.jButton3 == null) {
            this.jButton3 = new CloseButton();
            this.jButton3.setPreferredSize(new Dimension(30, 30));
            this.jButton3.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.ExitProg();
                }
            });
        }
        return this.jButton3;
    }

    private JToolBar getJToolBar1() {
        if (this.jToolBar1 == null) {
            this.jLabel2 = new JLabel();
            this.jLabel2.setText("Transparency: ");
            this.jLabel1 = new JLabel();
            this.jLabel1.setText("Filled: ");
            this.jLabel1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            this.jLabel = new JLabel();
            this.jLabel.setText("Dashed:");
            this.jLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            this.jToolBar1 = new JToolBar();
            this.jToolBar1.setLayout(new FlowLayout(0));
            this.jToolBar1.add(this.getJButton4());
            this.jToolBar1.add(this.getJButton5());
            this.jToolBar1.add(this.getJButton6());
            this.jToolBar1.add(this.getJButton7());
            this.jToolBar1.add(this.jLabel);
            this.jToolBar1.add(this.getJCheckBox());
            this.jToolBar1.add(this.jLabel1);
            this.jToolBar1.add(this.getJCheckBox1());
            this.jToolBar1.add(this.jLabel2);
            this.jToolBar1.add(this.getJSlider4());
            this.jToolBar1.add(this.getJToggleButton1());
            this.jToolBar1.add(this.getJToggleButton2());
            this.jToolBar1.add(this.getJToggleButton());
            this.jToolBar1.add(this.getJTextField());
        }
        return this.jToolBar1;
    }

    private JButton getJButton4() {
        if (this.jButton4 == null) {
            this.jButton4 = new OvalButton();
            this.jButton4.setPreferredSize(new Dimension(30, 30));
            this.jButton4.setSize(new Dimension(30, 30));
            this.jButton4.setLocation(new Point(16, 10));
            this.jButton4.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getShape(MyPaintFrame.this, new Oval());
                }
            });
        }
        return this.jButton4;
    }

    private JButton getJButton5() {
        if (this.jButton5 == null) {
            this.jButton5 = new RectangleButton();
            this.jButton5.setSize(new Dimension(30, 30));
            this.jButton5.setPreferredSize(new Dimension(30, 30));
            this.jButton5.addActionListener(new ActionListener(){
           
                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getShape(MyPaintFrame.this, new MyRectangle());
                }
            });
        }
        return this.jButton5;
    }

    private JButton getJButton6() {
        if (this.jButton6 == null) {
            this.jButton6 = new LineButton();
            this.jButton6.setPreferredSize(new Dimension(30, 30));
            this.jButton6.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getShape(MyPaintFrame.this, new Line());
                }
            });
        }
        return this.jButton6;
    }

    private JButton getJButton7() {
        if (this.jButton7 == null) {
            this.jButton7 = new FreehandButton();
            this.jButton7.setText("");
            this.jButton7.setPreferredSize(new Dimension(30, 30));
            this.jButton7.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getShape(MyPaintFrame.this, new MyFreehand());
                }
            });
        }
        return this.jButton7;
    }

    private JCheckBox getJCheckBox() {
        if (this.jCheckBox == null) {
            this.jCheckBox = new JCheckBox();
            this.jCheckBox.setHorizontalAlignment(2);
            this.jCheckBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            this.jCheckBox.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jCheckBox;
    }

    private JCheckBox getJCheckBox1() {
        if (this.jCheckBox1 == null) {
            this.jCheckBox1 = new JCheckBox();
            this.jCheckBox1.setComponentOrientation(ComponentOrientation.UNKNOWN);
            this.jCheckBox1.setHorizontalAlignment(10);
            this.jCheckBox1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getFilled(MyPaintFrame.this, MyPaintFrame.this.jCheckBox1.isSelected());
                }
            });
        }
        return this.jCheckBox1;
    }

    private JButton getJButton8() {
        if (this.jButton8 == null) {
            this.jButton8 = new ClearButton();
            this.jButton8.setPreferredSize(new Dimension(30, 30));
            this.jButton8.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getImage(MyPaintFrame.this, MyPaintFrame.this.createImage(MyPaintFrame.this.jPanel1.getWidth(), MyPaintFrame.this.jPanel1.getHeight()));
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jButton8;
    }

    private JMenuItem getJMenuItem4() {
        if (this.jMenuItem4 == null) {
            this.jMenuItem4 = new JMenuItem();
            this.jMenuItem4.setText("Clear");
            this.jMenuItem4.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    MyPaintFrame.getImage(MyPaintFrame.this, MyPaintFrame.this.createImage(MyPaintFrame.this.jPanel1.getWidth(), MyPaintFrame.this.jPanel1.getHeight()));
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jMenuItem4;
    }

    private JSlider getJSlider4() {
        if (this.jSlider4 == null) {
            this.jSlider4 = new JSlider();
            this.jSlider4.setValue(0);
            this.jSlider4.setPreferredSize(new Dimension(100, 16));
            this.jSlider4.setBounds(new Rectangle(442, 7, 100, 16));
            this.jSlider4.addChangeListener(new ChangeListener(){

                public void stateChanged(ChangeEvent e) {
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jSlider4;
    }

    private JTextField getJTextField() {
        if (this.jTextField == null) {
            this.jTextField = new JTextField();
            this.jTextField.setPreferredSize(new Dimension(200, 20));
        }
        return this.jTextField;
    }

    private JToggleButton getJToggleButton() {
        if (this.jToggleButton == null) {
            this.jToggleButton = new JToggleButton();
            this.jToggleButton.setText("Place Text");
        }
        return this.jToggleButton;
    }

    private JToggleButton getJToggleButton1() {
        if (this.jToggleButton1 == null) {
            this.jToggleButton1 = new JToggleButton();
            this.jToggleButton1.setText("Set Gradiant");
            this.jToggleButton1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) {
                    if (MyPaintFrame.this.jToggleButton1.isSelected()) {
                        JColorChooser jcc = new JColorChooser();
                        Color c1 = null;
                        c1 = JColorChooser.showDialog(MyPaintFrame.this.jButton, "Choose color 1", c1);
                        System.out.println(c1);
                        Color c2 = null;
                        c2 = JColorChooser.showDialog(MyPaintFrame.this.jButton, "Choose color 2", c2);
                        MyPaintFrame.getGradient(MyPaintFrame.this, new GradientPaint(MyPaintFrame.this.jPanel1.getWidth() / 2, 0.0f, c1, MyPaintFrame.this.jPanel1.getWidth() / 2, MyPaintFrame.this.jPanel1.getHeight(), c2));
                    }
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jToggleButton1;
    }

    private JToggleButton getJToggleButton2() {
        if (this.jToggleButton2 == null) {
            this.jToggleButton2 = new JToggleButton();
            this.jToggleButton2.setText("Set Texture");
            this.jToggleButton2.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e) 
                {
                    if (MyPaintFrame.this.jToggleButton2.isSelected()) 
                    {
                        BufferedImage bi = new BufferedImage(164, 133, 6);
                        Graphics2D big = bi.createGraphics();
                        ImageIcon ii = new ImageIcon(this.getClass().getResource("wallpaper.jpg"));
                        Image img = ii.getImage();
                        big.drawImage(img, 0, 0, null);
                        Rectangle r2d = new Rectangle(164, 133);
                        MyPaintFrame.getTexture(MyPaintFrame.this, new TexturePaint(bi, r2d));
                    }
                    MyPaintFrame.this.repaint();
                }
            });
        }
        return this.jToggleButton2;
    }

    
    static void getOldX(MyPaintFrame myDrawing, int n) {
        myDrawing.oldX = n;
    }

    static void getOldY(MyPaintFrame myDrawing, int n) {
        myDrawing.oldY = n;
    }

    static void getImage(MyPaintFrame myDrawing, Image image) {
        myDrawing.bg = image;
    }

    static void getForeground(MyPaintFrame myDrawing, Image image) {
        myDrawing.fg = image;
    }

    static void getShape(MyPaintFrame myDrawing, Shape shape) {
        myDrawing.s = shape;
    }

    static void getFilled(MyPaintFrame myDrawing, boolean bl) {
        myDrawing.filled = bl;
    }

    static void getGradient(MyPaintFrame myDrawing, GradientPaint gradientPaint) {
        myDrawing.gp = gradientPaint;
    }

    static void getTexture(MyPaintFrame myDrawing, TexturePaint texturePaint) {
        myDrawing.tp = texturePaint;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){

            public void run() {
                MyPaintFrame thisClass = new MyPaintFrame();
                thisClass.setDefaultCloseOperation(3);
                thisClass.setVisible(true);
            }
        });
    }

    public MyPaintFrame() {
        this.initialize();
        this.s = new Oval();
        this.filled = false;
        this.v = new Vector();
    }

    private void initialize() {
        this.setSize(1000, 800);
        this.setJMenuBar(this.getJJMenuBar());
        this.setContentPane(this.getJContentPane());
        this.setTitle("JFrame");
        this.addComponentListener(new ComponentAdapter(){

            public void componentResized(ComponentEvent e) {
                MyPaintFrame.this.repaint();
            }
        });
    }

    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            this.jContentPane = new JPanel();
            this.jContentPane.setLayout(new BorderLayout());
            this.jContentPane.add((Component)this.getJPanel(), "North");
            this.jContentPane.add((Component)this.getJPanel1(), "Center");
            this.jContentPane.add((Component)this.getJPanel2(), "South");
        }
        return this.jContentPane;
    }
    
    
}
