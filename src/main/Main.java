package main;

import javax.swing.JFrame;

public class Main {
	public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        window.setResizable(false);
        window.setTitle("Frost King's tale");
//      window.setUndecorated(true);	// Turn off the navigation bar of the JPanel window                
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn == true) {
        	window.setUndecorated(true);
        }
        
        window.pack(); 
        window.setLocationRelativeTo(null); 
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
