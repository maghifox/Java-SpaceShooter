
package Weapon;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class Blaster implements IWeapon{

    private int x_pos;
    private int y_pos;
    private boolean visible;
    private Image blasterImg;

    private final int SHOT_SPEED = 15;

    public Blaster(int x, int y){
        this.x_pos = x + 25;
        this.y_pos = y + 20;
        visible    = true;
        ImageIcon img = new ImageIcon("image/blaster.png");
        blasterImg    = img.getImage();
    }

    public Image getBlasterImg(){
        return blasterImg;
    }

    public void setImage(String name){
        ImageIcon img = new ImageIcon(name);
        blasterImg    = img.getImage();
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean is){
        this.visible = is;
    }

    @Override
    public int getYPos(){
        return this.y_pos;
    }

    @Override
    public int getXPos(){
        return this.x_pos;
    }

    @Override
    public void moveShot(){
        y_pos -= SHOT_SPEED;
        if (y_pos < 0){
            visible = false;
        }
    }

    
    //hitbox tembakan blaster
    public Rectangle getBounds(){
        return new Rectangle(x_pos, y_pos, blasterImg.getWidth(null), blasterImg.getHeight(null));
    }
    
}
