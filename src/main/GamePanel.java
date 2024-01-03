package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

// @SuppressWarnings("serial"): This line is modified to remove the warning when opening this file with Eclipse, can delete if not needed
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {  // JPanel is the subclass of GamePanel
	
	// GAME SETTING
    final int originalTileSize = 16; 						 //16x16: Tile standard for 2d game
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;	 // 48x48 pixels on the screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //WORLD MAP SETTING
    public final int maxWorldCol = 50; // Map width (equals to the X coordinate of the matrix in worldV2.txt)
    public final int maxWorldRow = 50; // Map height (equals to the Y coordinate of the matrix in worldV2.txt)

    //FPS: Frame per second
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyH);
    // Set objects' default positions (the inventory bar)
    public Entity obj[] = new Entity[20]; 
    // Set the NPC 
    public Entity npc[] = new Entity[10];
    //Set the monster
    public Entity monster[] = new Entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    //Projectile List
    public ArrayList<Entity> projectileList = new ArrayList<>();     
    //Entity List
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); // set the size for the class (JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an Offscreen Painting buffer
        this.addKeyListener(keyH);
        this.addMouseListener(keyH);
        this.setFocusable(true); // with this, this Game Panel can be "focused" to receive key input

    }

    public void setupGame () {
    	aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        // playMusic(0);
        gameState = titleState;
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
                remainingTime = remainingTime / 1000000; // Convert from nano to mili

                if ( remainingTime < 0){
                    remainingTime = 0;
                }
                //  This thread doesn't need to sleep since we already used to allocated time
                Thread.sleep((long) remainingTime); // "Long" only accept milisecond

                nextDrawTime += drawInterval;
                // A new drawtime will be repeated after a period
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update(){
        if (gameState == playState) {        	
        	// PLAYER
            player.update();
            
            // NPC
            for (int i = 0; i < npc.length; i++) {
            	if(npc[i] != null) {
            		npc[i].update();
            	}
            }
            
            // MONSTER
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false){
                        monster[i].update();
                    }
                    if(monster[i].alive == false){
                    	monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < iTile.length; i++) {
           	 if(iTile[i] != null) {
           		 iTile[i].update();
           	 }
           }
        }        
        if (gameState == pauseState){
            // Do nothing
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // GamePanel means the subclass of JPanel
        Graphics2D g2 =(Graphics2D)g; 
        //Graphics2D class extends the Graphics class to provide more sophisticated control over geometry...
        // This means we changes this graphics g to this Graphics2D class

        //DEBUG
        long drawStart = 0;
        if (KeyHandler.showDebugText == true) {
        drawStart = System.nanoTime();
        }

         //TITLE SCREEN
        if (gameState == titleState){
            ui.draw(g2);
        }
        
        // OTHERS
        else {        	
            // TILE
            tileM.draw(g2);
            
            // INTERACTIVE TILE
            for(int i = 0; i < iTile.length; i++) {
            	if(iTile[i] != null) {
            		iTile[i].draw(g2);
            	}
            }
            
            // ADD ENTITY TO THE LIST
            entityList.add(player);
            //for entity of npc
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }
            //for object
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            //for monster
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }
            //for projectile
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }
            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                    
                }
                
            });

            //DRAW ENTITY
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            for(int i = 0; i < entityList.size(); i++){
                entityList.clear();
            }
            // UI
            ui.draw(g2);
                    
        }
       
        //DEBUG
        if (KeyHandler.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.black);
            
            int x = 10;
            int y = 100;
            int lineHeight = 20;

            g2.drawString("World X " + player.worldX, x, y);
            y += lineHeight;

            g2.drawString("World Y " + player.worldY, x, y);
            y += lineHeight;

            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);
        
        }
        
        g2.dispose();
    }
    
    // MUSIC AND SOUND PLAYING
    public void playMusic (int i) {		// Background music
    	music.setFile(i);
    	music.play();
    	music.loop();    	    	
    }
    public void stopMusic() {
    	music.stop();
    }
    public void playSE (int i) {		// SFX
    	se.setFile(i);
    	se.play();
    }
}

