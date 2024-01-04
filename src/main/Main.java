package main;

import javax.swing.JFrame;

public class Main {
	public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// This let the windows close when user clicks "x"
        window.setResizable(false);
        window.setTitle("2D Adventure");
//      window.setUndecorated(true);	// Turn off the navigation bar of the JPanel window                
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn == true) {
        	window.setUndecorated(true);
        }
        
        window.pack(); // Cause this window to be sized to fit
        window.setLocationRelativeTo(null); // Window will be display at the center of the screem
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
