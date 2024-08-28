package controller;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundController {
	private int volume = 65;

	public Clip buttonClip, ambientClip, musicClip, fxClip;
	private File filepath;

	public File getFilepath() {
		return filepath;
	}

	public void setFilepath(File filepath) {
		this.filepath = filepath;
	}

	public SoundController() {
		// set standard volume to 65 for not ear-exploding!
		setVolume(this.volume);

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
		if (buttonClip != null && buttonClip.isOpen()) {
			buttonClip.stop();
			buttonClip.flush();
		}
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("res/soundFX/fxEffects/button_click_Sound.wav"));
			buttonClip = AudioSystem.getClip();
			buttonClip.open(audioInputStream);
			adjustVolume(buttonClip, volume);
			buttonClip.setFramePosition(0);
			buttonClip.start();

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
		if (fxClip != null && buttonClip.isOpen()) {
			fxClip.stop();
			fxClip.flush();
		}
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
			fxClip = AudioSystem.getClip();
			fxClip.open(audioInputStream);
			adjustVolume(fxClip, volume);
			fxClip.setFramePosition(0);
			fxClip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Clip playMusicLoop(String filepath) {
	    try {
	        if (musicClip == null || !musicClip.isOpen()) { // Überprüfen, ob der Clip bereits existiert
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
	            musicClip = AudioSystem.getClip();
	            musicClip.open(audioInputStream);
	            adjustVolume(musicClip, volume);
	            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return musicClip;
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
	
	public void setVolume(int volume) {
		this.volume = volume;
		adjustVolume(getButtonClip(), volume);
		adjustVolume(ambientClip, volume);
		adjustVolume(getMusicClip(), volume);
		adjustVolume(getFxClip(), volume);
	}
	
	public int getVolume() {
	    return this.volume;
	}
	
	public void adjustVolume(Clip clip, int volume) {
	    if (clip != null && clip.isOpen() && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
	        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        
	        // Get the range of the volume control
	        float min = volumeControl.getMinimum(); // -80.0f
	        float max = volumeControl.getMaximum(); // 6.0206f
	        
	        // Scale the volume to fit within the range of the volume control
	        float scaledVolume = min + (volume / 100.0f) * (max - min);
	        
	        // Set the volume
	        volumeControl.setValue(scaledVolume);
	        System.out.println("Volume set to: " + scaledVolume);
	    } else {
	    	System.out.println("Clip not ready or not supported");
	    }
	}

}
