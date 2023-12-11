package main;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	File soundFile[] = new File[30];
	
	public Sound() {
		soundFile[0] = new File("res/sound/BlueBoyAdventure.wav");	
		// getClass().getResource will cause errors since this structure is old
		soundFile[1] = new File("res/sound/coin.wav");
		soundFile[2] = new File("res/sound/powerup.wav");
		soundFile[3] = new File("res/sound/unlock.wav");
		soundFile[4] = new File("res/sound/fanfare.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
