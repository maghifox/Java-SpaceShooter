package Player;


import Main.Game;
import Weapon.Blaster;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private int x_pos;
    private int y_pos;
    private Image spaceShip;

    private int moveX;
    private int moveY;

    private int delay = 15;
    private int blasterDelay = delay;
    
    static ArrayList blasterShots;

    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private boolean fire;
    private boolean isAlive;

    public Player(){

        ImageIcon img = new ImageIcon("image/spacecraft.png");
        spaceShip     = img.getImage();

        this.x_pos = Game.LEBAR / 2 - (spaceShip.getWidth(null)/ 2 );
        this.y_pos = Game.TINGGI - spaceShip.getHeight(null) - 30;
        this.moveX = 12;
        this.moveY = 12;

        this.isAlive = true;

        blasterShots = new ArrayList();
    }

    public void moveRight(){
        x_pos += moveX;
    }

    public void moveLeft(){
        x_pos -= moveX;
    }

    public void moveForward(){
        y_pos -= moveY;
    }

    public void moveBack(){
        y_pos += moveY;
    }

    public Image getImage(){
        return spaceShip;
    }

    public void setImage(String name){
        ImageIcon img = new ImageIcon(name);
        spaceShip     = img.getImage();
    }

    public int getX(){
        return x_pos;
    }

    public int getY(){
        return y_pos;
    }

    public void setX(int x){
        this.x_pos = x;
    }

    public void setY(int y){
        this.y_pos = y;
    }

    //membuat tembakan blaster
    public void generateBlaster(){
          blasterShots.add(new Blaster(x_pos, y_pos));
          blasterDelay = delay;
    }

    public ArrayList getBlasterShots(){
        return blasterShots;
    }
    
    public void removeBlasters(){
        blasterShots.clear();
    }
    
    
    public Rectangle getBounds(){
        return new Rectangle(x_pos, y_pos, spaceShip.getWidth(null), spaceShip.getHeight(null));
    }

    public int getBlasterDelay(){
        return this.blasterDelay;
    }

    public void setBlasterDelay(){
        --this.blasterDelay;
    }

    public boolean isKeyLeft(){
        return keyLeft;
    }

    public boolean isKeyRight(){
        return keyRight;
    }

    public boolean isKeyUp(){
        return keyUp;
    }

    public boolean isKeyDown(){
        return keyDown;
    }

    public boolean isFire(){
        return fire;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean is){
        this.isAlive = is;
    }

    public void keyPressed(int e){

        if (e == KeyEvent.VK_LEFT){
            keyLeft = true;
        }
        if (e == KeyEvent.VK_RIGHT){
            keyRight = true;
        }
        if (e == KeyEvent.VK_UP){
            keyUp = true;
        }
        if (e == KeyEvent.VK_DOWN){
            keyDown = true;
        }
        if (e == KeyEvent.VK_SPACE){
            fire = true;
        }
    }

    public void keyReleased(int e){

        if (e == KeyEvent.VK_LEFT){
            keyLeft = false;
        }
        if (e == KeyEvent.VK_RIGHT){
            keyRight = false;
        }
        if (e == KeyEvent.VK_UP){
            keyUp = false;
        }
        if (e == KeyEvent.VK_DOWN){
            keyDown = false;
        }
        if (e == KeyEvent.VK_SPACE){
            fire = false;
        }
    }
}
