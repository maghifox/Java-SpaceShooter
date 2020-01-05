/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PowerUp;

import Player.Player;
import java.awt.Image;

/**
 *
 * @author maghi
 */
public class PowerUp {
    private int x_pos;
    private int y_pos;
    private Image shield;

    private boolean shieldActive;

    public int shieldX(){
        return this.x_pos;
    }

    public int shieldY(){
        return this.y_pos;
    }

    public void moveShield(Player p){}

    public Image getImage(){
        return shield;
    }

    public boolean isShieldActive(){
        return shieldActive;
    }

    public void setShield(boolean is){}

    public void keyPressed(int k){}

    public void keyReleased(int k){}
    
}
