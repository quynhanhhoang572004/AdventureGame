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
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    //FPS: Frame per second
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    // Set player's default positions
    public Player player = new Player(this, keyH);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public SuperObject obj[] = new SuperObject[10]; 

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); // set the size for the class (Jpanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an offscreen Painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, this Game Panel can be "focused" to receive key input

    }

    public void setupGame () {
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this); // passing game panel to this thread constructor
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
            repaint();// the how we call paint componet method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000;//convert from nano to mili

                if ( remainingTime < 0){
                    remainingTime = 0;
                }
                //  this thread doesn't need to sleep since we already used to allocated time
                Thread.sleep((long) remainingTime);// long only accept mili second

                nextDrawTime += drawInterval;// sau khi sau 1 chu kỳ nó sẽ tự lặp lại 1 cái drawtime mới

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
        super.paintComponent(g); // GamePanel means the subclass of Jpanel
        Graphics2D g2 =(Graphics2D)g; //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry...
        // This means we changes this graphics g to this Graphics2D class

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
        g2.dispose();
    }
}

