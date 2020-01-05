
package Enemy;

import java.awt.Image;

public interface IEnemy {
    
    void moveRight();
    void moveLeft();

    Image getImage();

    int getX();
    int getY();

    boolean isAlive();
}
