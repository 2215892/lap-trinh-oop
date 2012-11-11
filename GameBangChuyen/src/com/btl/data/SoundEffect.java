package com.btl.data;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.btl.Model.ConversionFunction;

public enum SoundEffect {

	BACKGROUND("background.wav"), RIGHTBOX("right_box.wav"), WRONGBOX(
			"wrong_box.wav"), BUTTONCLICK("click.wav");

	public static enum Volume {
		MUTE, OK
	}

	public static Volume volume = Volume.OK;

	private Clip clip;

	SoundEffect(String soundFileName) {
		String fileDir = ConversionFunction.getCurrentDirectory() + "sound\\";
		try {
			File f = new File(fileDir + soundFileName);
			// Set up an audio input stream piped from the sound file.
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(f);
			// Get a clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Play or Re-play the sound effect from the beginning, by rewinding.
	public void play() {
		if (clip != null && volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop(); // Stop the player if it is still running
			clip.setFramePosition(0); // rewind to the beginning
			clip.start(); // Start playing
		}
	}

	public void loop() {
		if (clip != null && volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop(); // Stop the player if it is still running
			clip.setFramePosition(0); // rewind to the beginning
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

}
