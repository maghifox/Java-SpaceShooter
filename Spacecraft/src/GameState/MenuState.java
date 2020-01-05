
package GameState;

import Main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

    public class MenuState extends GameState{
        
        private Image bg;
        private Image gameTitle;
        
        private int gameTitleX;
        private int gameTitleY;
        
        public static int buttonX = (Game.LEBAR/2) - 60;
        public static int buttonY    = Game.TINGGI - (Game.TINGGI - ((55 * Game.TINGGI) / 100));
        
        public static Color menuColor = new Color(1, 178, 241);
        public static Font menuFont = new Font("Arial", Font.BOLD, 40);
        
        private int currentChoice = 0;
        
        private String[] options = {
            "START",
            "HELP",
            "EXIT"
        };
        
        public MenuState(GameStateManager gsm){
            this.gsm = gsm;
            
            ImageIcon gameImg = new ImageIcon("image/gameTitle.png");
            gameTitle = gameImg.getImage();

            ImageIcon img = new ImageIcon("image/gameMenu.jpeg");
            bg = img.getImage();
            
            init();
         
        }
    
        @Override
        public void init(){
            gameTitleX = (Game.LEBAR / 2) - (gameTitle.getWidth(null) / 2);
            gameTitleY = Game.TINGGI - (Game.TINGGI - ((10 * Game.TINGGI) / 100));
        }
        
        @Override
        public void update(){}
        
        @Override
        public void draw(Graphics2D g){ 
            //gambar background
            g.drawImage(bg, 0,0, null);
                    
            //gambar kotak di sekitar title dan option
            g.setColor(new Color(0,0,0,150));
            g.fillRect(gameTitleX - 50, gameTitleY - 50, gameTitle.getWidth(null) + 100, gameTitle.getHeight(null) + 100);
            g.fillRect(buttonX - 115, buttonY -75, 350, 300);
           
            g.setColor(menuColor);
            g.drawRect(gameTitleX - 50, gameTitleY - 50, gameTitle.getWidth(null) + 100, gameTitle.getHeight(null) + 100);
            g.drawRect(buttonX - 115, buttonY -75, 350, 300);
            
            //gambar title
            g.drawImage(gameTitle, gameTitleX, gameTitleY,
            gameTitle.getWidth(null), gameTitle.getHeight(null), null);
        
            menuOption(g);
        }
        
        private void menuOption(Graphics2D g) {
            g.setColor(menuColor);
            g.setFont(menuFont);
            
            for(int i = 0; i<options.length; i++){
                if(i == currentChoice){
                    g.setColor(Color.white);                  
                }
                else {
                    g.setColor(menuColor);
                }
                g.drawString(options[i], buttonX + i * 7, buttonY + i * 90);
            }
        }
    
        private void select(){
            if(currentChoice == 0){
                //start
                gsm.setState((GameStateManager.GAMEPLAY));
            }
            if(currentChoice == 1){
                //help
                gsm.setState((GameStateManager.HELP));
            }
            if(currentChoice == 2){
                System.exit(0);
            }
        }
        
        
        @Override
        public void keyPressed(int k){
            if(k == KeyEvent.VK_ENTER){
                select();
            }
            if(k == KeyEvent.VK_UP){
                currentChoice--;
                if(currentChoice == -1){
                    currentChoice = options.length - 1;
                }
            }
            if(k == KeyEvent.VK_DOWN){
                currentChoice++;
                if(currentChoice == options.length) {
                    currentChoice = 0;
                }
            }
        }
        @Override
        public void keyReleased(int k){}
}
