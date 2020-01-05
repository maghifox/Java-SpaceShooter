/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PowerUp;

import Player.Player;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class Shield extends PowerUp {

    private int x_pos;
    private int y_pos;
    private Image shield;

    private boolean shieldActive;

    public Shield(int x, int y) {
        this.x_pos = x;
        this.y_pos = y;

        ImageIcon img = new ImageIcon("image/shield.png");
        shield        = img.getImage();

        shieldActive = false;
    }

    @Override
    public int shieldX(){
        return this.x_pos;
    }

    @Override
    public int shieldY(){
        return this.y_pos;
    }

    @Override
    public void moveShield(Player p){
        this.x_pos = p.getX() - 15;
        this.y_pos = p.getY() - 15;
    }

    @Override
    public Image getImage(){
        return shield;
    }

    @Override
    public boolean isShieldActive(){
        return shieldActive;
    }

    @Override
    public void setShield(boolean is){
        shieldActive = is;
    }

    @Override
    public void keyPressed(int k){
        switch (k){
            case KeyEvent.VK_S:
                shieldActive = true;
                break;
            case KeyEvent.VK_F:
                shieldActive = false;
                break;
        }
    }

    @Override
    public void keyReleased(int k){
        switch (k){
            case KeyEvent.VK_S:
                shieldActive = true;
                break;
            case KeyEvent.VK_F:
                shieldActive = false;
                break;
        }
    }
}
