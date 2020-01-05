/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import Main.Game;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class SpaceCraftWeapon implements IWeapon {

    private int x_pos;
    private int y_pos;
    private boolean visible;
    private Image enemyFireImg;

    private int fireDelay = 300;

    private final int FIRE_SPEED = 12;

    public SpaceCraftWeapon(int x, int y){
        this.x_pos = x + 20;
        this.y_pos = y + 20;
        visible    = true;

        ImageIcon img = new ImageIcon("image/enemyFire.png");
        enemyFireImg  = img.getImage();
    }

    @Override
    public int getXPos(){
        return this.x_pos;
    }

    @Override
    public int getYPos(){
        return this.y_pos;
    }

    @Override
    public void moveShot(){
        y_pos += FIRE_SPEED;
        if (y_pos > Game.TINGGI){
            visible = false;
        }
    }

    public int getFireDelay(){
        return fireDelay;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean is){
        this.visible = is;
    }

    public Image getEnemyFireImg(){
        return enemyFireImg;
    }

    //tembakan hitbox
    public Rectangle getBounds(){
        return new Rectangle(x_pos, y_pos, enemyFireImg.getWidth(null), enemyFireImg.getHeight(null));
    }
}