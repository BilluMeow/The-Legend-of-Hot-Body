package com.project.control;

import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	Clip clip;
	ArrayList<URL> URL = new ArrayList<>();
	
	public SoundManager() {
		
		URL.add(getClass().getResource("/SoundEffects/theme.wav"));
		URL.add(getClass().getResource("/SoundEffects/gameover.wav"));
		URL.add(getClass().getResource("/SoundEffects/coin.wav"));
		URL.add(getClass().getResource("/SoundEffects/powerup.wav"));
		URL.add(getClass().getResource("/SoundEffects/unlock.wav"));
		URL.add(getClass().getResource("/SoundEffects/receivedamage.wav"));
		URL.add(getClass().getResource("/SoundEffects/cheat.wav"));
		URL.add(getClass().getResource("/SoundEffects/hitmonster.wav"));
		URL.add(getClass().getResource("/SoundEffects/wingame.wav"));
		
	}
	
	public void soundToPlay(int index) {
		
		try {
			
			AudioInputStream audiostream = AudioSystem.getAudioInputStream(URL.get(index));
			clip = AudioSystem.getClip();
			clip.open(audiostream);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void stop() {
		
		clip.stop();
	}
	
	@SuppressWarnings("static-access")
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
}
