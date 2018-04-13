package mousedrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MouseDrawing extends JFrame 
{
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenuItem blueMenuItem = new JMenuItem("Blue");
    JMenuItem whiteMenuItem = new JMenuItem("White");
    JMenuItem blackMenuItem = new JMenuItem("Black");
    JMenuItem yellowMenuItem = new JMenuItem("Yellow");
    JMenuItem pinkMenuItem = new JMenuItem("Pink");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem smallMenuItem = new JMenuItem("Small");
    JMenuItem mediumMenuItem = new JMenuItem("Medium");
    JMenuItem largeMenuItem = new JMenuItem("Large");
    
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[18];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;

    public static void main(String args[]) 
    {
        // construct frame
        new MouseDrawing().setVisible(true);
    }
    
    public MouseDrawing()
    {
        // frame constructor
        setTitle("EtchaSketch by your Boi Eli Tanner! March 2018");
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        Color backgroundColor = new Color(72,170,75);
        getContentPane().setBackground(backgroundColor);
        
        // build menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        canvasMenu.setMnemonic('C');
        penMenu.setMnemonic('P');
        Color menubarColor = new Color(128, 32, 0);
        mainMenuBar.setBackground(menubarColor);
        colorPanel.setBackground(menubarColor);
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        mainMenuBar.add(canvasMenu);
        canvasMenu.add(blackMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(blueMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(yellowMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(pinkMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(whiteMenuItem);
        mainMenuBar.add(penMenu);
        penMenu.add(smallMenuItem);
        penMenu.addSeparator();
        penMenu.add(mediumMenuItem);
        penMenu.addSeparator();
        penMenu.add(largeMenuItem);
        newMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              newMenuItemActionPerformed(e);
          }
        });
        exitMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              exitMenuItemActionPerformed(e);
          }
        });
        blackMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              blackMenuItemActionPerformed(e);
          }
        });
        whiteMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              whiteMenuItemActionPerformed(e);
          }
        });
        yellowMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              yellowMenuItemActionPerformed(e);
          }
        });
        blueMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              blueMenuItemActionPerformed(e);
          }
        });
        pinkMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              pinkMenuItemActionPerformed(e);
          }
        });
        
        smallMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              smallMenuItemActionPerformed(e);
          }
        });
        mediumMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              mediumMenuItemActionPerformed(e);
          }
        });
        largeMenuItem.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              largeMenuItemActionPerformed(e);
          }
        });
        
        drawPanel.setPreferredSize(new Dimension(1000, 700));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel, gridConstraints);
        drawPanel.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                drawPanelMousePressed(e);
            }
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter()
        {
           public void mouseDragged(MouseEvent e)
           {
               drawPanelMouseDragged(e);
           }
        });
        drawPanel.addMouseListener(new MouseAdapter()
        {
           public void mouseReleased(MouseEvent e)
           {
               drawPanelMouseReleased(e);
           }
        });
        
        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel, gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
        colorPanel.setPreferredSize(new Dimension(80,360));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel, gridConstraints);
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 18; i++)
        {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 9;
            colorPanel.add(colorLabel[i], gridConstraints);
            if(i==8)
            {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
        colorLabel[0].setBackground(Color.BLACK);
        colorLabel[9].setBackground(Color.WHITE);
        colorLabel[1].setBackground(Color.DARK_GRAY);
        colorLabel[10].setBackground(Color.GRAY);
        colorLabel[2].setBackground(Color.MAGENTA);
        colorLabel[11].setBackground(Color.PINK);
        Color drawColor = new Color(204, 0, 0);
        colorLabel[3].setBackground(drawColor);
        colorLabel[12].setBackground(Color.RED);
        colorLabel[4].setBackground(Color.ORANGE);
        drawColor = new Color(255, 153, 102);
        colorLabel[13].setBackground(drawColor);
        colorLabel[5].setBackground(Color.YELLOW);
        drawColor = new Color(255, 255, 153);
        colorLabel[14].setBackground(drawColor);
        drawColor = new Color(0, 153, 51);
        colorLabel[6].setBackground(drawColor);
        drawColor = new Color(102, 255, 102);
        colorLabel[15].setBackground(drawColor);
        colorLabel[7].setBackground(Color.BLUE);
        drawColor = new Color(51, 204, 255);
        colorLabel[16].setBackground(drawColor);
        drawColor = new Color(102, 0, 204);
        colorLabel[8].setBackground(drawColor);
        drawColor = new Color(153, 102, 255);
        colorLabel[17].setBackground(drawColor);
        leftColor = colorLabel[0].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[9].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())),getWidth(),getHeight());
        g2D = (Graphics2D) drawPanel.getGraphics();
    }
    
    private void colorMousePressed(MouseEvent e)
    {
        Component clickedColor = e.getComponent();
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        }else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
    
    private void drawPanelMousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
        {
            xPrevious = e.getX();
            yPrevious = e.getY();
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                drawColor = leftColor;
            } else
            {
                drawColor = rightColor;
            }
        }
    }
    
    private void drawPanelMouseReleased(MouseEvent e) 
    {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
        {
            Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
            g2D.setPaint(drawColor);
            g2D.draw(myLine);
        }
    }
    
    private void drawPanelMouseDragged(MouseEvent e) 
    {
        Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious = e.getX();
        yPrevious = e.getY();
    }
    
    private void newMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?","New Drawing",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
        }
    }
    
    private void exitMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the program?","Exit Program",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION)
        {
            return;
        }else
        {
            exitForm(null);
        }
    }
    
    private void blackMenuItemActionPerformed(ActionEvent e) {
            drawPanel.setBackground(Color.BLACK);
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
            }
    private void whiteMenuItemActionPerformed(ActionEvent e) {
            drawPanel.setBackground(Color.WHITE);
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
            }
    private void yellowMenuItemActionPerformed(ActionEvent e) {
            drawPanel.setBackground(Color.YELLOW);
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
            }
    private void blueMenuItemActionPerformed(ActionEvent e) {
            drawPanel.setBackground(Color.BLUE);
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
            }
    private void pinkMenuItemActionPerformed(ActionEvent e) {
            drawPanel.setBackground(Color.PINK);
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),drawPanel.getHeight()));
            }
    
    private void smallMenuItemActionPerformed(ActionEvent e) {
                g2D.setStroke(new BasicStroke(1));
            }
    private void mediumMenuItemActionPerformed(ActionEvent e) {
                g2D.setStroke(new BasicStroke(7));
            }
    private void largeMenuItemActionPerformed(ActionEvent e) {
                g2D.setStroke(new BasicStroke(15));
            }
    
    private void exitForm(WindowEvent e)
    {
        g2D.dispose();
        System.exit(0);
    }
}
