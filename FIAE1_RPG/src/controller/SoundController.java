package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
	
	Clip introMusicClip;
	
	public SoundController() {

	}
	
	public void playMainMenuMusic() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/music/intro_music.wav"));
			introMusicClip = AudioSystem.getClip();
			introMusicClip.open(audioInputStream);
			introMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopMainMenuMusic() {
		if (introMusicClip != null && introMusicClip.isOpen()) {
			introMusicClip.stop();
			introMusicClip.close();
		}
	}

}
