package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {
	
	Clip clip;
	File filepath;
	
	public SoundController() {

	}
	
	// when calling one of the following methods, use resource String-Path like
	// "res/music/Boss_Music.wav"
	
	public void playButtonClickSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/soundFX/fxEffects/button_click_Sound.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playAmbientSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playFxSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void playMusicLoop(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopAmbientSound() {
		if (clip != null && clip.isOpen()) {
			clip.stop();
			clip.close();
		}
	}
	
	public void stopMusicLoop() {
			clip.stop();
			clip.close();
		}
	}

