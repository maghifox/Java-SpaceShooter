
package Enemy;

import Weapon.SpaceCraftWeapon;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class SpaceCraft implements IEnemy {

    private Image enemyImg;
    private int x_pos;
    private int y_pos;
    private boolean alive;

    private int currentFireDelay = 0;

    public ArrayList<SpaceCraftWeapon> fireShots = new ArrayList<>();

    public SpaceCraft(int x, int y){
        this.x_pos = x;
        this.y_pos = y;
        this.alive = true;
        ImageIcon img = new ImageIcon("image/enemy.png");
        enemyImg      = img.getImage();
    }

    @Override
    public void moveRight(){
        x_pos += 2;
    }

    @Override
    public void moveLeft(){
        x_pos -= 2;
    }

    public void moveForward(int moveSpeed){
        y_pos += moveSpeed;
    }

    public void setAlive(boolean is){
        this.alive = is;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Image getImage(){
        return enemyImg;
    }

    public void setImage(String name){
        ImageIcon img = new ImageIcon(name);
        enemyImg      = img.getImage();
    }

    @Override
    public int getX(){
        return this.x_pos;
    }

    @Override
    public int getY(){
        return this.y_pos;
    }

    public void setX(int x){
        this.x_pos = x;
    }

    public void setY(int y){
        this.y_pos = y;
    }

    public SpaceCraftWeapon shoot(){
        SpaceCraftWeapon fireShot = new SpaceCraftWeapon(x_pos, y_pos);
        fireShots.add(fireShot);

        return fireShot;
    }

    public int getCurrentFireDelay() {
        return currentFireDelay;
    }

    public void setCurrentFireDelay(int delay) {
        currentFireDelay = delay;
    }

    //untuk membuat enemy hitbox
    public Rectangle getBounds(){
        return new Rectangle(x_pos, y_pos, enemyImg.getWidth(null), enemyImg.getHeight(null));
    }
}
