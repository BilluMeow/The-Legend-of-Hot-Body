package com.project.control;

import com.project.model.GameEntity;
import com.project.model.Player;
import com.project.view.GamePanel;
import com.project.view.GameState;
import com.project.view.GameWindow;
import com.project.view.OnScreenUI;

public class GameEngine implements Runnable{

	private Thread GameThread;
	private GamePanel panel;
	private Player player;
	@SuppressWarnings("unused")
	private GameWindow window;
	private TileManager tilecontroller;
	
	private KeyHandler keyhandler;
	private int FPS = 144;
	
	

	public GameEngine() {
		
		this.keyhandler = new KeyHandler();
		player = new Player(keyhandler);
		
		tilecontroller = new TileManager();
		
		CollisionDetector detect = new CollisionDetector();
		ObjectManager objectmanager = new ObjectManager();
		
		OnScreenUI UIDisplay = new OnScreenUI();
		
		panel = new GamePanel(player, keyhandler, tilecontroller, detect, objectmanager, new SoundManager(), new SoundManager(), UIDisplay);
		
		this.keyhandler.Init(panel);
		detect.Init(panel);
		objectmanager.Init(panel);
		tilecontroller.InitTileManager(panel);
		player.Init(panel);
		UIDisplay.Init(panel);
		this.window = new GameWindow(panel);
		
		panel.setObjectsOnMapAndPlayMusic();
		
		StartGameThread();
	}


	public void StartGameThread() {
		this.GameThread = new Thread(this);
		GameThread.start();
	}
	

	@Override
	public void run() {
		
		double DrawInterval = 1000000000/FPS;
		double NextDrawTime = DrawInterval + System.nanoTime();
		
		while(GameThread != null) {
			
			if(panel.gamestate == GameState.play)
				update();
			
			panel.repaint();			
			
			try {
				double RemainingTime = NextDrawTime - System.nanoTime();
				RemainingTime /= 1000000;
				
				if(RemainingTime <= 0) {
					RemainingTime = 0;
				}
				
				Thread.sleep((long) RemainingTime);
				
				NextDrawTime += DrawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	
	
	private void update() {
		panel.player.Update();
		
		for(GameEntity i : panel.NPCS)
			i.Update();
		
		for(GameEntity i : panel.monsters)
			i.Update();
	}
	
}
