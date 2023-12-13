package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

// @SuppressWarnings("serial")
// This line is modified to remove the warning when opening this file with Eclipse, can delete if not needed
public class GamePanel extends JPanel implements Runnable { // JPanel is the subclass of GamePanel
	
	// GAME SETTING
    final int originalTileSize = 16; //16x16: Tile standard for 2d game

    final int scale = 3;
    public final int tileSize = originalTileSize * scale;// 48x48 pixels on the screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //WORLD MAP SETTING
    public final int maxWorldCol = 40; // Map width (equals to the X coordinate of the matrix in world01.txt)
    public final int maxWorldRow = 40; // Map height (equals to the Y coordinate of the matrix in world01.txt)

    //FPS: Frame per second
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Set player's default positions
    public Player player = new Player(this, keyH);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // Set objects' default positions (the inventory bar)
    public SuperObject obj[] = new SuperObject[10]; 

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); // set the size for the class (JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an Offscreen Painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, this Game Panel can be "focused" to receive key input

    }

    public void setupGame () {
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // Passing game panel to this thread constructor
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // this means we draw the screen every 0.016 seconds so we can draw the screen 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval; // the next drawtime will be plus this drawInterval

        while(gameThread!=null){
            //System.out.println("The game loop is running");
            //1 UPDATE: Update information such as character positions
            update();
            //2 DRAW : Draw the screen with the updated information
            repaint();// the how we call paint component method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000; // Convert from nano to mili

                if ( remainingTime < 0){
                    remainingTime = 0;
                }
                //  This thread doesn't need to sleep since we already used to allocated time
                Thread.sleep((long) remainingTime); // "Long" only accept mili second

                nextDrawTime += drawInterval;    // A new "drawtime" will be repetitied after an interval

            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update(){
        player.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); // GamePanel means the subclass of JPanel
        Graphics2D g2 =(Graphics2D)g; 
        //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry...
        // This means we changes this graphics g to this Graphics2D class

        //DEBUG
        long drawStart = 0;
        if (KeyHandler.checkDrawTime == true) {
        drawStart = System.nanoTime();
        }

        // TILE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        
        // PLAYER
        player.draw(g2);
        
        // UI
        ui.draw(g2);
        
        //DEBUG
        if (KeyHandler.checkDrawTime == true) {
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.yellow);
        g2.drawString("Draw Time: " + passed, 10, 400);
        System.out.println("Draw Time: " + passed);
        }



        g2.dispose();
    }
    
    // MUSIC AND SOUND PLAYING
    public void playMusic (int i) {	// Background music
    	music.setFile(i);
    	music.play();
    	music.loop();    	    	
    }
    public void stopMusic() {
    	music.stop();
    }
    public void playSE (int i) {	// SFX
    	se.setFile(i);
    	se.play();
    }
}