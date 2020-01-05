package Main;


import GameState.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class Game extends JPanel implements Runnable, KeyListener {
    
    //dimensi
    public static int LEBAR = 1440;
    public static int TINGGI = 900;
    
    //thread
    private Thread thread;
    private boolean running;
    
    //image
    private BufferedImage image;
    private Graphics2D g;
  
    private GameStateManager gsm;
 
    
    public Game (){    
        setPreferredSize(new Dimension(LEBAR, TINGGI));
        
        setFocusable(true);
        requestFocus();
        
        mulai();
    }
    
   
    public void mulai(){
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start(); //memanggil method run
        }
    }
    
    private void init(){
        
        image = new BufferedImage(LEBAR, TINGGI, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        
        running = true;
        
        gsm = new GameStateManager();
    }

    @Override
    public void run(){
        
        init();

        //game loop
        while(running){          
           
           update();
           draw();
           
            try {
                Thread.sleep(11);
            }
            catch(Exception e){
                System.out.println(e);
            } 
        }
    }
    
    private void update(){
        gsm.update();
    }
    
    private void draw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        gsm.draw(g);
        
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent key) {}

    @Override
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}



