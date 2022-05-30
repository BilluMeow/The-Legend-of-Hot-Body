package com.project.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.project.control.CollisionDetector;
import com.project.control.KeyHandler;
import com.project.control.ObjectManager;
import com.project.control.SoundManager;
import com.project.control.TileManager;
import com.project.model.GameEntity;
import com.project.model.InteractableObjects;
import com.project.model.Player;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	final int originaltilesize = 16;
	final int scale = 3;
	
	public final int tilesize = originaltilesize * scale;
	
	public final int maxscreencolumn = 20;
	public final int maxscreenrow = 16;
	
	public final int width = maxscreencolumn*tilesize;
	public final int height = maxscreenrow*tilesize;	

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 100;
	
	public final int worldWidth = maxWorldCol*tilesize;
	public final int worldHeight = maxWorldRow*tilesize;
	
	
	public ArrayList <InteractableObjects> objects = new ArrayList<>();
	public ArrayList <GameEntity> NPCS = new ArrayList<>();
	public ArrayList <GameEntity> monsters = new ArrayList<>();
	public Player player;
	public TileManager tile;
	public ObjectManager objectmanager;
	public CollisionDetector collisiondetector;
	public SoundManager soundeffectsmanager;
	public SoundManager musicthememanager;
	public OnScreenUI UIDisplay;
	public KeyHandler keyhandler;
	
	public GameState gamestate = GameState.start;
	
	public GamePanel(Player player, KeyHandler keyhandler, TileManager tilecontroller, CollisionDetector collisionDetector, ObjectManager objectmanager, SoundManager musicManager, SoundManager themeManager, OnScreenUI UIDisplay) {

		this.collisiondetector = collisionDetector;
		this.objectmanager = objectmanager;
		
		this.soundeffectsmanager = musicManager;
		this.musicthememanager = themeManager;
		this.UIDisplay = UIDisplay;
		
		this.player = player;
		this.keyhandler = keyhandler;
		this.addKeyListener(keyhandler);
		this.tile = tilecontroller;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void setObjectsOnMapAndPlayMusic() {
		
		objectmanager.InitializeObjects();
		objectmanager.InitializeNPCS();
		objectmanager.InitializeMonsters();
		
		playMusic();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		
		if(gamestate == GameState.start) {
			UIDisplay.renderStartGameScreen(g2);
			return;
		}
		
		else if(gamestate == GameState.controlsview) {
			UIDisplay.renderControls(g2);
			return;
		}
		
		// Draw the Map
		tile.render(g2);
		
		// Draw the Objects
		for(InteractableObjects i : objects) {
			if(i != null)
				i.render(g2, this);
		}
		
		// Draw NPCS
		for(GameEntity i : NPCS) {
			if(i != null)
				i.render(g2);
		}
		
		// Draw Monsters
		for(GameEntity i : monsters) {
			if(i != null)
				i.render(g2);
		}
		
		// Draw Random Details
		UIDisplay.render(g2);
		
		// Draw the Player
		player.render(g2);
		
		if(gamestate == GameState.pause) {
			UIDisplay.renderPauseScreen(g2);
		}
		
		else if(gamestate == GameState.dialogue) {
			UIDisplay.renderDialouge(g2);
		}
		
		else if(gamestate == GameState.die) {
			UIDisplay.renderGameOverScreen(g2);
		}
		
		else if(gamestate == GameState.win) {
			UIDisplay.renderWinningScreen(g2);
		}
				
		g2.dispose();
	}
	
	
	
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	
	public void playMusic() {
		
		musicthememanager.soundToPlay(0);
		musicthememanager.play();
		musicthememanager.loop();
	}
	
	public void StopMusic() {
		
		musicthememanager.stop();
	}
	
	public void playSoundEffect(int i) {
		soundeffectsmanager.soundToPlay(i);
		soundeffectsmanager.play();
	}

}
