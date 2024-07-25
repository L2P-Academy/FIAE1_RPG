package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundController {

	public Clip buttonClip, ambientClip, musicClip, fxClip;
	private File filepath;

	public SoundController() {

	}

	// when calling one of the following methods, use resource String-Path like
	// "res/music/Boss_Music.wav"

	public Clip getButtonClip() {
		return buttonClip;
	}

	public void setButtonClip(Clip buttonClip) {
		this.buttonClip = buttonClip;
	}

	public Clip getAmbientClip() {
		return ambientClip;
	}

	public void setAmbientClip(Clip ambientClip) {
		this.ambientClip = ambientClip;
	}

	public Clip getMusicClip() {
		return musicClip;
	}

	public void setMusicClip(Clip musicClip) {
		this.musicClip = musicClip;
	}

	public Clip getFxClip() {
		return fxClip;
	}

	public void setFxClip(Clip fxClip) {
		this.fxClip = fxClip;
	}

	public void playButtonClickSound() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("res/soundFX/fxEffects/button_click_Sound.wav"));
			buttonClip = AudioSystem.getClip();
			buttonClip.open(audioInputStream);
			buttonClip.loop(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playAmbientSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			ambientClip = AudioSystem.getClip();
			ambientClip.open(audioInputStream);
			ambientClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playFxSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			fxClip = AudioSystem.getClip();
			fxClip.open(audioInputStream);
			fxClip.loop(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playMusicLoop(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			musicClip = AudioSystem.getClip();
			musicClip.open(audioInputStream);
			musicClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopAmbientSound() {
		if (ambientClip != null && ambientClip.isOpen()) {
			ambientClip.stop();
			ambientClip.close();
		}
	}

	public void stopMusicLoop() {
		musicClip.stop();
		musicClip.close();
	}
}
