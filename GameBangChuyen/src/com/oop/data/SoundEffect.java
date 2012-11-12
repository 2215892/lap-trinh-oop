package com.oop.data;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.oop.model.Helper;

/**
 * The Enum SoundEffect. Thể hiện hiệu ứng âm thanh
 */
public enum SoundEffect {

	/** Âm thanh nền. */
	BACKGROUND("background.wav"), /** Âm khi button được click. */
	BUTTONCLICK("click.wav"), /** Âm khi hộp đến đúng terminal. */
	RIGHTBOX("right_box.wav"),
	/** Âm khi hộp đến terminal sai. */
	WRONGBOX("wrong_box.wav");

	/**
	 * The Enum Volume.
	 */
	public static enum Volume {

		/** Không tiếng. */
		MUTE,
		/** Có tiếng. */
		OK
	}

	/** Âm lượng mặc định. */
	public static Volume volume = Volume.OK;

	private Clip clip;

	/**
	 * Khởi tạo SoundEffect.
	 * 
	 * @param soundFileName
	 *            tên của âm thanh
	 */
	SoundEffect(String soundFileName) {
		String fileDir = Helper.getCurrentDirectory() + "sound\\";
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

	/**
	 * Chạy âm thanh lặp đi lặp lại.
	 */
	public void loop() {
		if (clip != null && volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop(); // Stop the player if it is still running
			clip.setFramePosition(0); // rewind to the beginning
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	/**
	 * Chạy âm thanh.
	 */
	public void play() {
		if (clip != null && volume != Volume.MUTE) {
			if (clip.isRunning())
				clip.stop(); // Stop the player if it is still running
			clip.setFramePosition(0); // rewind to the beginning
			clip.start(); // Start playing
		}
	}

}
