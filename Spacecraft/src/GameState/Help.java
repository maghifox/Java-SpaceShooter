/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author maghi
 */
public class Help extends GameState {
    
    private Image bg;
    
    public Help(GameStateManager gsm) {
        this.gsm = gsm;
        
        ImageIcon img = new ImageIcon("image/gameMenu.jpeg");
        bg = img.getImage();
        
        init();
       
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bg, 0, 0, null);
        g.setColor(new Color(0, 0, 0, 100));

        g.fillRect(0, 0, Game.LEBAR, Game.TINGGI);
        
        g.setColor(new Color(1, 178, 241));
        g.setFont(new Font("Arial", Font.BOLD, 26));
  
 
        g.drawString("Maju     :   UP", 500, 175);
        g.drawString("Mundur    :   DOWN", 500, 205);
        g.drawString("Ke Kanan  :   RIGHT", 500, 235);
        g.drawString("Ke Kiri  :   LEFT", 500, 265);
        g.drawString("Tembak :   SPACE", 500, 295);
        g.drawString("Shield ON :   S", 500, 325);
        g.drawString("Shield OFF:   F", 500, 350);


        g.drawString("Kembali Ke Main Menu: ESC", 500, 500);

    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState((GameStateManager.MENUSTATE));
        }
    }

    @Override
    public void keyReleased(int k) {
        
    }
    
}
