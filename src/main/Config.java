package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Config {
    GamePanel gp;
    
    public Config(GamePanel gp) {
        this.gp = gp;
    }
    
    public void saveConfig() {
        try {
            Path saveData = Path.of("config.txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(saveData.toFile()));
            
            // FULLSCREEN
            if (gp.fullScreenOn == true) {
                bw.write("FullScreen Status: On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("FullScreen Status: Off");
            }
            bw.newLine();
            
            // MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();
            
            // SFX VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();
            
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadConfig() {
        try {
            Path saveData = Path.of("config.txt");

            BufferedReader br = new BufferedReader(new FileReader(saveData.toFile()));
            String s = br.readLine();
            
            // FULLSCREEN
            if (s.equals("FullScreen Status: On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("FullScreen Status: Off")) {
                gp.fullScreenOn = false;
            }
            
            // MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            // SFX VOLUME
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);
            
            br.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
