package com.p.musicplayer;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class JLayerTest {
	public static void main(String[] args) {
		// SoundJLayer soundToPlay = new SoundJLayer("Test.mp3");
		SoundJLayer soundToPlay = new SoundJLayer("E:/mp3/Muskurane.mp3");
		soundToPlay.play();
	}
}

class SoundJLayer extends PlaybackListener implements Runnable {
	private String filePath;
	private AdvancedPlayer player;
	private Thread playerThread;

	public SoundJLayer(String filePath) {
		this.filePath = filePath;
	}

	public void play() {
		try {
			String urlAsString = "file:///"
					// + new java.io.File(".").getCanonicalPath() + "/"
					+ this.filePath;

			this.player = new AdvancedPlayer(new java.net.URL(urlAsString).openStream(),
					javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

			this.player.setPlayBackListener(this);

			this.playerThread = new Thread(this, "AudioPlayerThread");

			this.playerThread.start();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// PlaybackListener members

	public void playbackStarted(PlaybackEvent playbackEvent) {
		System.out.println("playbackStarted");
	}

	public void playbackFinished(PlaybackEvent playbackEvent) {
		System.out.println("playbackEnded");
	}

	// Runnable members

	public void run() {
		try {
			this.player.play();
		} catch (javazoom.jl.decoder.JavaLayerException ex) {
			ex.printStackTrace();
		}

	}
}