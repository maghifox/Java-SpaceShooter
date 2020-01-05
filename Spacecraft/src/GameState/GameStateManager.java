package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author maghi
 */
public class GameStateManager {
    
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    public static final int MENUSTATE = 0;
    public static final int GAMEPLAY = 1;
    public static final int HELP = 2;
    
    public GameStateManager(){
        
        gameStates = new ArrayList<>();
        
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Gameplay(this));
        gameStates.add(new Help(this));
        
    }
    
    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void update(){
        gameStates.get(currentState).update();
    }
    
    public void draw (Graphics2D g){
        gameStates.get(currentState).draw(g);
    }
    
    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }
    
    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }    
    
}
