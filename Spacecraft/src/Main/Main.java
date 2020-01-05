package Main;


import javax.swing.JFrame;


public class Main {
        
    public static void main (String[] args){
        
        //membuat jendela window
        JFrame window = new JFrame("Spacecraft Battle");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocation(200, 25);
        window.add(new Game());

        window.pack();
        window.setVisible(true);
    }
}


