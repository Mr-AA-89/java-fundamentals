//PiSaver Panel Class - Patka - 4/27/2014
//Panel for the screen saver about pi.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.io.*;
import javax.imageio.*;

public class PiSaverPanel extends JPanel
{
    private Point myMousePos = null;
    private Polygon myPi;
    private JLabel lblInstructions;
    
    private javax.swing.Timer TimerScreen_Left = null, TimerScreen_Right = null, TimerBackground = null;
    private int g_iAnimateStep = 0, g_iAnimateIterations = 0, g_iAnimateIterationsPerDirection = 0;
    
    private int g_iBackgroundIndex = 0, g_iFileCount = 0;
    private java.util.List<Image> backgroundList = new ArrayList<Image>();
    private final String g_strBackgroundsPath = "pisaver-data/";
    
    /*
     * Constructor
     */
    public PiSaverPanel() throws IOException
    {
        //components
        myPi = makePi(700, 100, 1);
        lblInstructions = new JLabel("Use the up/down arrow keys or hold and drag the Pi symbol with the mouse to change its vertical position. Press any other key to exit the screensaver.");
        lblInstructions.setOpaque(true);                //force component to paint every pixel within its bounds.
        lblInstructions.setForeground(Color.green);
        lblInstructions.setBackground(Color.black);
        
        //action listeners
        MouseActionListener MouseListener = new MouseActionListener();
        KeyboardActionListener KeyboardListener = new KeyboardActionListener();
        
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);
        addKeyListener(KeyboardListener);
        
        //design
        setBackground(Color.black);
        setPreferredSize(new Dimension(500, 500));
        setFocusable(true);
        add(lblInstructions);
        
        //background images
        File file;
        int i = 0;
        do
        {
            file = new File(g_strBackgroundsPath + i + ".jpg");
            Image tempPic;
            
            if (file.exists() && !file.isDirectory())
            {
                tempPic = Toolkit.getDefaultToolkit().getImage(g_strBackgroundsPath + i + ".jpg");
                backgroundList.add(tempPic);
                g_iFileCount++;
            }
            
            i++;
        }
        while(file.exists());
        
        //animation
        backgroundAnimate(5000);                //5000ms = 5sec per background
        animateAcrossScreen(myPi, 100, 10);     //100 pixels each step, 10 steps per side (right / left)
    }
    
    /*
     * Timer callback to change background
     */
    private final ActionListener animateBackground = new ActionListener()
    {
        public void actionPerformed(ActionEvent event)
        {
            repaint();
            
            if (g_iBackgroundIndex < g_iFileCount - 1)
            {
                g_iBackgroundIndex++;
            }
            else
            {
                g_iBackgroundIndex = 0;
            }
        }
    };
    
    /*
     * Change background image
     *
     * @param time              Number of milliseconds to wait between background changes
     * @return                  void.
     */
    public void backgroundAnimate(final int time)
    {
        g_iBackgroundIndex = 0;
        TimerBackground = new javax.swing.Timer(time, animateBackground);
        TimerBackground.setRepeats(true);
        TimerBackground.start();
    }
    
    /*
     * Create a Pi Polygon
     */
    public Polygon makePi(int defPointX, int defPointY, int scale)
    {
        int[] xPi = {defPointX, defPointX,        defPointX - 150, defPointX - 150, defPointX - 75,  defPointX,       defPointX - 75,  defPointX - 200, defPointX - 200, defPointX - 350, defPointX - 350, defPointX - 475, defPointX - 550, defPointX - 475, defPointX - 400, defPointX - 400, defPointX - 550, defPointX - 550};
        int[] yPi = {defPointY, defPointY + 100,  defPointY + 100, defPointY + 500, defPointY + 550, defPointY + 500, defPointY + 600, defPointY + 550, defPointY + 100, defPointY + 100, defPointY + 550, defPointY + 600, defPointY + 500, defPointY + 550, defPointY + 500, defPointY + 100, defPointY + 100, defPointY};
        
        for (int i = 0; i < xPi.length; i++)
        {
            xPi[i] = xPi[i] * scale;
            yPi[i] = yPi[i] * scale;
        }
        
        return new Polygon(xPi, yPi, xPi.length);
    }
    
    /*
     * Timer callback to move pi to the left
     */
    private final ActionListener Screen_animateTimer_left = new ActionListener()
    {
        public void actionPerformed(ActionEvent event)
        {
            if (g_iAnimateIterations < g_iAnimateIterationsPerDirection)
            {
                myPi.translate(-g_iAnimateStep, 0);   //left
                g_iAnimateIterations++;
                repaint();
            }
            else
            {
                resetTimers();
                g_iAnimateIterations = 0;
                
                TimerScreen_Right = new javax.swing.Timer(1000, Screen_animateTimer_right);
                TimerScreen_Right.setRepeats(true);
                TimerScreen_Right.start();
            }
        }
    };
    
    /*
     * Timer callback to move pi to the right
     */
    private final ActionListener Screen_animateTimer_right = new ActionListener()
    {
        public void actionPerformed(ActionEvent event)
        {
            if (g_iAnimateIterations < g_iAnimateIterationsPerDirection)
            {
                myPi.translate(g_iAnimateStep, 0);   //right
                g_iAnimateIterations++;
                repaint();
            }
            else
            {
                resetTimers();                
                g_iAnimateIterations = 0;
                
                TimerScreen_Left = new javax.swing.Timer(1000, Screen_animateTimer_left);
                TimerScreen_Left.setRepeats(true);
                TimerScreen_Left.start();
            }
        }
    };
    
    /*
     * Animate a Polygon across the screen
     *
     * @param myShape           Polygon to animate across the screen.
     * @param step              Integer number of pixels to move at each iteration.
     * @param iterations        Number of steps to take.
     * @return                  void.
     */
    public void animateAcrossScreen(final Polygon myShape, final int step, final int iterations)
    {
        g_iAnimateStep = step;
        g_iAnimateIterations = 0;
        g_iAnimateIterationsPerDirection = iterations;
        
        TimerScreen_Right = new javax.swing.Timer(1000, Screen_animateTimer_right);
        TimerScreen_Right.setRepeats(true);
        TimerScreen_Right.start();
    }
    
    /*
     * Paint graphics on the screen
     */ 
    public void paintComponent(Graphics thing)
    {
        int frameHeigth = PiSaver.getFrameHeight();
        int frameWidth = PiSaver.getFrameWidth();
        
        super.paintComponent(thing);
        if (frameHeigth > 0 && frameWidth > 0)
        {
            thing.drawImage(backgroundList.get(g_iBackgroundIndex), 0, 0, this);
        }
        
        thing.setColor(Color.yellow);
        thing.fillPolygon(myPi);
    }
    
    /*
     * Mouse action events
     */ 
    private class MouseActionListener implements MouseListener, MouseMotionListener
    {
        public void mouseMoved (MouseEvent event)
        {
            myMousePos = event.getPoint();
        }
        
        public void mouseClicked (MouseEvent event)
        {
            requestFocusInWindow(); //window gets focus
        }
        
        public void mouseDragged (MouseEvent event)
        {
            Point newMousePos = event.getPoint();
            Point transPoint = new Point();
            
            if (newMousePos != null && myMousePos != null)
            {
                transPoint.x = myMousePos.x;                    //transPoint.x = newMousePos.x - myMousePos.x;
                transPoint.y = newMousePos.y - myMousePos.y;
            }
            
            if (newMousePos != null && transPoint != null)
            {
                if (myPi.contains(newMousePos))
                {
                    myPi.translate(0, transPoint.y);            //myPi.translate(transPoint.x, transPoint.y); //myShape.translate(change in x, change in y)
                    repaint();
                }
            }
            
            myMousePos = newMousePos;
        }
        
        public void mousePressed (MouseEvent event){}
        public void mouseReleased (MouseEvent event){}
        public void mouseEntered (MouseEvent event){}
        public void mouseExited (MouseEvent event){}
    }
    
    /*
     * Keyboard action events
     */ 
    public class KeyboardActionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            switch (event.getKeyCode())
            {
                case KeyEvent.VK_UP:
                {
                    myPi.translate(0, -5);
                    break;
                }
                
                case KeyEvent.VK_DOWN:
                {
                    myPi.translate(0, 5);
                    break;
                }
                
                default:
                {
                    System.exit(0);
                }
            }
            
            repaint();
        }
        
        public void keyReleased (KeyEvent event){}
        public void keyTyped (KeyEvent event){}
    }
    
    /*
     * Reset animation timers
     */ 
    public void resetTimers()
    {
        if (TimerScreen_Left != null)
        {
            TimerScreen_Left.stop();
            TimerScreen_Left = null;
        }
        
        if (TimerScreen_Right != null)
        {
            TimerScreen_Right.stop();
            TimerScreen_Right = null;
        }
    }
}
