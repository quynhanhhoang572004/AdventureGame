package main;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	File soundFile[] = new File[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	
	public Sound() {
		// getClass().getResource will cause errors since this structure is old
		soundFile[0] = new File("res/sound/BackgroundMusic/Cats.wav");	
		soundFile[1] = new File("res/sound/coin.wav");
		soundFile[2] = new File("res/sound/powerup.wav");
		soundFile[3] = new File("res/sound/unlock.wav");
		soundFile[4] = new File("res/sound/fanfare.wav");
		soundFile[5] = new File("res/sound/Sword/KatanaHit.wav");
		soundFile[6] = new File("res/sound/OOF.wav");
		soundFile[7] = new File("res/sound/bear_polar.wav");
		soundFile[8] = new File("res/sound/Rank_up.wav");
		soundFile[9] = new File("res/sound/Option/Hover.wav");
		soundFile[10] = new File("res/sound/Option/StoreOpen.wav");
		soundFile[11] = new File("res/sound/Option/EquipAttach.wav");
		soundFile[12] = new File("res/sound/Option/Energy_boost.wav");
		soundFile[13] = new File("res/sound/Potion/ThrowNade.wav");
		soundFile[14] = new File("res/sound/Potion/Potion_break.wav");
		soundFile[15] = new File("res/sound/cuttree.wav");
		soundFile[16] = new File("res/sound/gameover.wav");
		soundFile[17] = new File("res/sound/stairs.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
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
	
	public void checkVolume() {
		switch (volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
}
