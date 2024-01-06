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
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

import ai.PathFinder;

// @SuppressWarnings("serial"): This line is modified to remove the warning when opening this file with Eclipse, can delete if not needed
   @SuppressWarnings("serial")

public class GamePanel extends JPanel implements Runnable {  // JPanel is the subclass of GamePanel	
	// SCREEN SETTING
    final int originalTileSize = 16; 						 // 16x16: Tile standard for 2d game
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;	 // 48x48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    
    // WINDOW MODE
    public final int screenWidth = tileSize * maxScreenCol;  // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // WORLD MAP SETTING
    public final int maxWorldCol = 50; // Map width (equals to the X coordinate of the matrix in worldV2.txt)
    public final int maxWorldRow = 50; // Map height (equals to the Y coordinate of the matrix in worldV2.txt)
    public final int maxMap = 10;
    public int currentMap = 0;

    // FULLSCREEN MODIFICATIONS
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    //FPS: Frame per second
    int FPS = 60;
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    Config config = new Config(this);    
    public PathFinder pFinder = new PathFinder(this);

    // ENTITY AND OBJECTS
    public Player player = new Player(this, keyH);
    // Set objects' default positions (the inventory bar)
    public Entity obj[][] = new Entity[maxMap][20]; 
    // Set the NPC 
    public Entity npc[][] = new Entity[maxMap][10];
    //Set the monster
    public Entity monster[][] = new Entity[maxMap][20];
    //InteractiveTile List
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    //Projectile List
    public ArrayList<Entity> projectileList = new ArrayList<>();     
    //Entity List
    ArrayList<Entity> entityList = new ArrayList<>();
    //Particle List
    public ArrayList<Entity> particleList = new ArrayList<>();     
        
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    
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
//      playMusic(0);
        gameState = titleState;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
        if (fullScreenOn == true) {
        setFullScreen();
     }
}
    
    public void retry() {
    	player.setDefaultPositions();
    	player.restoreLifeAndMana();
    	aSetter.setNPC();
    	aSetter.setMonster();
    }
    
    public void restart() {
    	player.setDefaultValues();
    	player.setDefaultPositions();
    	player.restoreLifeAndMana();
    	player.setItems();
    	aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }
    
    public void setFullScreen() {
    	// GET LOCAL SCREEN DEVICE
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice(); 
    	gd.setFullScreenWindow(Main.window);
    	
    	// GET FULLSCREEN WIDTH AND HEIGHT
    	screenWidth2 = Main.window.getWidth();
    	screenHeight2 = Main.window.getHeight();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this); // Passing game panel to this thread constructor
        gameThread.start();
    }

    // GAME LOOP
    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen();
                drawToScreen();
                delta--;
            }
        }
    }
    
    public void update() {
        if (gameState == playState) {        	
        	// PLAYER
            player.update();
            
            // NPC
            for (int i = 0; i < npc[1].length; i++) {
            	if(npc[currentMap][i] != null) {
            		npc[currentMap][i].update();
            	}
            }
            
            // MONSTER
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                    	monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            
            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive == true){
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }
            
            for(int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive == true){
                    	particleList.get(i).update();
                    }
                    if(particleList.get(i).alive == false){
                    	particleList.remove(i);
                    }
                }
            }
            
            for (int i = 0; i < iTile[1].length; i++) {
           	 if(iTile[currentMap][i] != null) {
           		 iTile[currentMap][i].update();
           	 }
           }
        }        
        
        if (gameState == pauseState){
            // Do nothing
        }
    }

    public void drawToTempScreen() {
    	 // DEBUG
        long drawStart = 0;
        if (KeyHandler.showDebugText == true) {
        drawStart = System.nanoTime();
        }
         // TITLE SCREEN
        if (gameState == titleState){
            ui.draw(g2);
        } 
        // OTHERS
        else {        	
            // TILE
            tileM.draw(g2);
            
            // INTERACTIVE TILE
            for(int i = 0; i < iTile[1].length; i++) {
            	if(iTile[currentMap][i] != null) {
            		iTile[currentMap][i].draw(g2);
            	}
            }
            
            // ADD ENTITY TO THE LIST
            entityList.add(player);
            //for entity of npc
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            //for object
            for(int i = 0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            //for monster
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            //for projectile
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }
            //for particle
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    entityList.add(particleList.get(i));
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
            //UI
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

            g2.drawString("World X: " + player.worldX, x, y);
            y += lineHeight;

            g2.drawString("World Y: " + player.worldY, x, y);
            y += lineHeight;

            g2.drawString("Col(y): " + (player.worldX + player.solidArea.x)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Row(x): " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);        
        }
    }
    
    public void drawToScreen() {
        Graphics g = getGraphics();   
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();       
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

