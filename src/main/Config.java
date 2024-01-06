package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;
	
	public Config(GamePanel gp) {
		this.gp = gp;
	}
	
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
