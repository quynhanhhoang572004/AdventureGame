package main;

import javax.swing.JFrame;

public class Main{
    public static void main (String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//This let the windows close when user clicks "x"
        window.setResizable(false);
        window.setTitle("Weird Adventure");

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);
        window.pack();// cause this window to be sized to fit

        window.setLocationRelativeTo(null);//Window will be display at the center of the screem
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
