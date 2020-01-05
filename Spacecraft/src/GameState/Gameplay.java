package GameState;

import Enemy.SpaceCraft;
import Main.Game;
import Player.Player;
import PowerUp.Shield;
import Weapon.Blaster;
import Weapon.SpaceCraftWeapon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class Gameplay extends GameState {
    
    private Image bg;
    
    private int backgroundY;
    private int bgHeight;
    private final int MOTION = 2;
    
    private Player player;
    private int playerScore;
    private Shield shield;
    private int playerDeadY = 0;
       
    public ArrayList<SpaceCraft> enemies = new ArrayList<>();
    public ArrayList<SpaceCraftWeapon> fireShots = new ArrayList<>();
    
    private final int ENEMY_SPEED        = 9;
    private final int ENEMIES_SPAWN_Y    = 3000;
    private int ENEMIES_AMOUNT = 10;
    
    private int currentChoice = 0;
    
    Random acak = new Random();
    
    private String[] optDied = {
            "NEW GAME",
            "MAIN MENU",
            "EXIT"
    };
    
    public Gameplay(GameStateManager gsm){
        this.gsm = gsm;
        
        ImageIcon img = new ImageIcon("image/background.jpg");
        bg = img.getImage();
        
        player = new Player();
        shield = new Shield(player.getX(), player.getY());
        
        spawnEnemies(); 
        
        init();
        
    }
    
    @Override
    public void init() {
        bgHeight    = bg.getHeight(null);
        backgroundY = 0;
        
    }
    
    @Override
    public void update(){
        backgroundMovement();
         
        playerMove();
        moveEnemies();
        enemiesFire();
    }
    
    @Override
    public void draw(Graphics2D g){
        g.drawImage(bg, 0, backgroundY, null);
        g.drawImage(bg, 0, backgroundY - bgHeight, null);
        
        drawPlayers(g);
        drawWeapons(g);
        drawEnemies(g);
        drawStats(g);
        gameOver(g);
        
    }   
    
    public void backgroundMovement(){
        backgroundY += MOTION;
        if (backgroundY >= bgHeight) {
            backgroundY = 0;
        }
    }
    
    public void drawPlayers(Graphics g){
        if (player.isAlive()){
            g.drawImage(player.getImage(), player.getX(), player.getY(), null);
        } else {
            playerDeadY += MOTION;
            player.setImage("image/explosion.gif");
            g.drawImage(player.getImage(), player.getX(), player.getY() + playerDeadY, null);
        }
        
        if (shield.isShieldActive()){
             g.drawImage(shield.getImage(), shield.shieldX(), shield.shieldY(), null);
        }
    }
    
    public void playerMove(){
        if (player.isAlive()){
            if (player.isKeyLeft() && player.getX() > 10){
                player.moveLeft();
            }
            if (player.isKeyRight() && player.getX() < (Game.LEBAR - 100)){
                    player.moveRight();
            }
            if (player.isKeyUp() && player.getY() > 20){
                player.moveForward();
            }
            if (player.isKeyDown() && player.getY() < (Game.TINGGI - 110)){
                player.moveBack();
            }

            if (player.getBlasterDelay() > 0){
                player.setBlasterDelay();
            }
            
            if (player.isFire() && player.getBlasterDelay() == 0){
                player.generateBlaster();
            }
            
            if (player.isKeyLeft()){
                player.setImage("image/spacecraft-turn-left.png");
            }
            else if (player.isKeyRight()){
                player.setImage("image/spacecraft-turn-right.png");
            }
            else {
                player.setImage("image/spacecraft.png");
            }
                             
            shield.moveShield(player);

            detectCollisions();
            movePlayerWeapons();
            }
    }
    
    public void drawWeapons(Graphics g){
        if (player.isAlive()){
            ArrayList blasterShots = player.getBlasterShots();
            for (int i = 0; i < blasterShots.size(); ++i){
                Blaster temp = (Blaster)blasterShots.get(i);
                g.drawImage(temp.getBlasterImg(), temp.getXPos(), temp.getYPos(), null);
            }
        }
        
        for (int i = 0; i < fireShots.size(); ++i){
            SpaceCraftWeapon fire = fireShots.get(i);
            if (fire.isVisible()){
                g.drawImage(fire.getEnemyFireImg(), fire.getXPos(), fire.getYPos(), null);
            }
        }
    }
    
    public void movePlayerWeapons(){

        ArrayList blasterShots = player.getBlasterShots();
        
        //kalo tembakannya keliatan, moveshot
        for (int i = 0; i < blasterShots.size(); ++i){
            Blaster temp = (Blaster)blasterShots.get(i);
            if (temp.isVisible() == true){
                temp.moveShot();
            }
            else {
                blasterShots.remove(i);
            }
        }
      
    }
    
    private void spawnEnemies() {
        for (int i = 0; i < ENEMIES_AMOUNT ; ++i){
            int x_position = acak.nextInt(Game.LEBAR);
            int y_position = -acak.nextInt(ENEMIES_SPAWN_Y);

            enemies.add(new SpaceCraft(x_position, y_position));
        }
    }
    
    public void moveEnemies(){

        for (int i = 0; i < enemies.size(); ++i){
            SpaceCraft enemy = enemies.get(i);

            if (enemies.get(i).isAlive()){
                enemies.get(i).moveForward(ENEMY_SPEED);
                if (i % 2 == 0 && enemies.get(i).getY() > 0){
                    if (enemies.get(i).getX() > 0 && enemies.get(i).getX() < 600){
                        enemies.get(i).moveRight();
                    }
                    if (enemies.get(i).getX() > 700 && enemies.get(i).getX() < Game.LEBAR - 60){
                        enemies.get(i).moveLeft();
                    }
                }
            }

            if (!enemy.isAlive() || enemy.getY() > Game.TINGGI){
                int x_position = acak.nextInt(Game.LEBAR);
                int y_position = -acak.nextInt(ENEMIES_SPAWN_Y);
                enemy = new SpaceCraft(x_position, y_position);

                enemies.set(i, enemy);
            }
        }
    }
     
    public void enemiesFire(){
    
        int randIndex = acak.nextInt(enemies.size());
        SpaceCraft enemy = enemies.get(randIndex);
        int fireDelay = enemy.getCurrentFireDelay();

        if (fireDelay == 0 && enemy.isAlive()){
            SpaceCraftWeapon fire = enemies.get(randIndex).shoot();
            fireShots.add(fire);
            enemy.setCurrentFireDelay(fire.getFireDelay());
        } 
        else {
            enemy.setCurrentFireDelay(fireDelay - 10);
        }
       
        //move shots
        for (int i = 0; i < fireShots.size(); ++i){
            SpaceCraftWeapon fireShot = fireShots.get(i);
            if (fireShot.isVisible()){
                fireShot.moveShot();
            } else {
                fireShots.remove(i);
            }
        }
    }
    
    public void drawEnemies(Graphics g){
        for (int i = 0; i < enemies.size(); ++i){
            if (enemies.get(i).isAlive()){
                g.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), null);
            }
        }
    }
    
    public void detectCollisions(){

        ArrayList blasterShots = player.getBlasterShots();

        Rectangle playerBounds = player.getBounds();

        ArrayList<Rectangle> enemiesBounds = new ArrayList<>();

        //menambahkan hitbox ke semua musuh
        for (int i = 0; i < enemies.size(); ++i){
            enemiesBounds.add(enemies.get(i).getBounds());
        }
        
        //mengecek kalo blaster kena musuh
        for (int i = 0; i < blasterShots.size(); ++i){
            Blaster temp = (Blaster)blasterShots.get(i);
            Rectangle blasterBounds = temp.getBounds();

            for (int j = 0; j < enemies.size(); ++j){
                if (enemiesBounds.get(j).intersects(blasterBounds) && enemies.get(j).isAlive()){
                    enemies.get(j).setAlive(false);
                    temp.setVisible(false);
                    playerScore += 10;
                }
            }
        }
        
        //cek jika player nabrak musuh
        for (int i = 0; i < enemies.size(); ++i){
            if (playerBounds.intersects(enemies.get(i).getBounds()) &&
                    !shield.isShieldActive() && enemies.get(i).isAlive()){

                player.setAlive(false);
            }
        }

        // buat hitbox di tembakan musuh
        ArrayList<Rectangle> enemiesFire = new ArrayList<>();
        for (int i = 0; i < fireShots.size(); ++i){
            enemiesFire.add(fireShots.get(i).getBounds());
        }

        // cek player kena tembakan
        for (int i = 0; i < fireShots.size(); ++i){
            if (playerBounds.intersects(fireShots.get(i).getBounds()) &&
                    !shield.isShieldActive() && fireShots.get(i).isVisible()){

                fireShots.get(i).setVisible(false);
                player.setAlive(false);
            }
        }   
    }
    
     public void drawStats(Graphics g){
        g.setFont(new Font("SanSerif", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        g.drawString("PLAYER ONE", Game.LEBAR - 200, 30);
        g.drawString("POINTS: " + playerScore, Game.LEBAR - 200, 70);
        if (shield.isShieldActive()){
            g.drawString("SHIELD: ACTIVE", Game.LEBAR - 200, 100);
        } else {
            g.drawString("SHIELD: DISABLED", Game.LEBAR - 200, 100);
        }
    }
     
     public void gameOver(Graphics g){
        if (!player.isAlive()){
            g.setColor(new Color (0,0,0,100));
            g.fillRect(0, 0, Game.LEBAR, Game.TINGGI);
            g.setFont(new Font("SanSerif", Font.BOLD, 80));
            g.setColor(Color.red);
            g.drawString("GAME OVER", (Game.LEBAR - 500) / 2, Game.TINGGI / 2 - 200);
                
            g.setColor(MenuState.menuColor);
            g.setFont(MenuState.menuFont);
            for(int i = 0; i<optDied.length; i++){
                if(i == currentChoice){
                    g.setColor(Color.white);                  
                }
                else {
                    g.setColor(MenuState.menuColor);
                }
                    g.drawString(optDied[i], MenuState.buttonX  - 100 , MenuState.buttonY + i * 90);
            }
        }
    }
     
     public void newGame(){
        player.setAlive(true);
        playerScore = 0;
        player.setX(Game.LEBAR / 2 - 40);
        player.setY(Game.TINGGI - 115);
        enemies.clear();
        fireShots.clear();
        player.removeBlasters();
        enemies.add(new SpaceCraft(-1500,-1500));
        playerDeadY = 0;
     }
     
     private void select(){
        newGame();
        
        if(currentChoice == 0){
            spawnEnemies();
        }
        if(currentChoice == 1){
            spawnEnemies();
            gsm.setState((GameStateManager.MENUSTATE));
        }
        if(currentChoice == 2){
            System.exit(0);
        }
    }
     
    @Override
    public void keyPressed(int k){
        player.keyPressed(k);

        if (player.isAlive()){
            shield.keyPressed(k);
        }
        else{
            if(k == KeyEvent.VK_ENTER){
                select();
            }
            if(k == KeyEvent.VK_UP){
                currentChoice--;
                if(currentChoice == -1){
                    currentChoice = optDied.length - 1;
                }
            }
            if(k == KeyEvent.VK_DOWN){
                currentChoice++;
                if(currentChoice == optDied.length) {
                    currentChoice = 0;
                }
            }  
        }
    }
    
    @Override
    public void keyReleased(int k){
        player.keyReleased(k);
        if (player.isAlive()){
            shield.keyPressed(k);
        }
    }
}

