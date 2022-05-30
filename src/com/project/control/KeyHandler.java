package com.project.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.project.view.GamePanel;
import com.project.view.GameState;

public class KeyHandler extends KeyAdapter{

	
	public boolean up, down, left, right, attack, talk;
	
	public boolean cheat = false;
	public String cheatcode = "";
	
	public GamePanel panel;
	
	public void Init(GamePanel panel) {
		this.panel = panel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// Start Game
		startGame();
		
		// Check Win and Die GameState
		checkWinAndDieState();
		
		// Check for Cheats
		if(Cheats(e))
			return;
		
		int keycode = e.getKeyCode();
		
		// Speak next Dialogue
		if(panel.gamestate == GameState.dialogue && (keycode == KeyEvent.VK_G || keycode == KeyEvent.VK_END)) {
			panel.NPCS.get(panel.player.talkingNPC).speak();
		}
		
		// Check for Pause
		if(keycode == KeyEvent.VK_ESCAPE) {
			if(panel.gamestate == GameState.pause)
				panel.gamestate = GameState.play;
			else if(panel.gamestate == GameState.dialogue) {
				panel.gamestate = GameState.play;
				panel.player.invincibletime = 20;
			}
			else
				panel.gamestate = GameState.pause;
			return;
		}
		
		// Check for Movement and Attack
		if(panel.gamestate == GameState.play)
			checkAttackAndMovement(keycode);
		
		
	}



	private void startGame() {
		if(panel.gamestate == GameState.start)
			panel.gamestate = GameState.controlsview;
		else if(panel.gamestate == GameState.controlsview)
			panel.gamestate = GameState.play;
	}

	private void checkWinAndDieState() {
		if(panel.gamestate == GameState.win || panel.gamestate == GameState.die)
			System.exit(0);
	}

	private boolean Cheats(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_F1 && !cheat) {
			
			cheat = true;
			cheatcode = "";
		}
		
		else if(keycode == KeyEvent.VK_ENTER && cheat) {
			
			cheatChecker();
		}
		
		if(cheat) {
			if(!Character.isAlphabetic(keycode))
				return true;
			
			cheatcode = cheatcode + e.getKeyChar();
			return true;
		}
		return false;
	}

	private void checkAttackAndMovement(int keycode) {
		
		if (keycode == KeyEvent.VK_F || keycode == KeyEvent.VK_DELETE) {
			attack = true;
			panel.player.attacking = true;
		} else if (keycode == KeyEvent.VK_W || keycode == KeyEvent.VK_UP) {
			up = true;
		}

		else if (keycode == KeyEvent.VK_S || keycode == KeyEvent.VK_DOWN) {
			down = true;
		}

		else if (keycode == KeyEvent.VK_A || keycode == KeyEvent.VK_LEFT) {
			left = true;
		}

		else if (keycode == KeyEvent.VK_D || keycode == KeyEvent.VK_RIGHT) {
			right = true;
		}

		else if (keycode == KeyEvent.VK_H || keycode == KeyEvent.VK_PAGE_DOWN) {
			panel.player.drinkHealthPotion();
		}
		
		else if(keycode == KeyEvent.VK_G || keycode == KeyEvent.VK_END) {
			talk = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		if(keycode == KeyEvent.VK_F || keycode == KeyEvent.VK_DELETE) {
			attack = false;
		}
			
		
		else if(keycode == KeyEvent.VK_W || keycode == KeyEvent.VK_UP) {
			up = false;
		}
		
		else if(keycode == KeyEvent.VK_S || keycode == KeyEvent.VK_DOWN) {
			down = false;
		}
		
		else if(keycode == KeyEvent.VK_A || keycode == KeyEvent.VK_LEFT) {
			left = false;
		}
		
		else if(keycode == KeyEvent.VK_D || keycode == KeyEvent.VK_RIGHT) {
			right = false;
		}
		
		else if(keycode == KeyEvent.VK_G  || keycode == KeyEvent.VK_END) {
			talk = false;
		}
		
	}
	
	private void cheatChecker() {
		cheatcode = cheatcode.substring(1, cheatcode.length());
		
		boolean cheatsound = false;
		
		if(cheatcode.toLowerCase().equals("casper")) {
			panel.player.caspercheat = !panel.player.caspercheat;
			cheatsound = true;
		}
						
		else if(cheatcode.toLowerCase().equals("ban")) {
			panel.player.saitamacheat = !panel.player.saitamacheat;
			cheatsound = true;
		}
					
		else if(cheatcode.toLowerCase().equals("aspirine")) {
			panel.player.life = panel.player.maxLife;
			cheatsound = true;
		}
			
		else if(cheatcode.toLowerCase().equals("speedup")) {
			panel.player.speed++;
			cheatsound = true;
		}
			
		else if(cheatcode.toLowerCase().equals("speeddown")) {
			panel.player.speed--;
			cheatsound = true;
		}
			
		else if(cheatcode.toLowerCase().equals("musicon")) {
			panel.playMusic();
			cheatsound = true;
		}
			
		else if(cheatcode.toLowerCase().equals("musicoff")) {
			panel.StopMusic();
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("simsim")) {
			panel.player.keysHolding++;
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("goldenkey")) {
			panel.player.goldenkeys++;
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("getcoin")) {
			panel.player.coinsholding += 5;
			cheatsound = true;
		}
			
		else if(cheatcode.toLowerCase().equals("taz")) {
			panel.player.dealDamage(1);
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("die")) {
			panel.player.dealDamage(100);
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("win")) {
			panel.gamestate = GameState.win;
			panel.StopMusic();
			panel.playSoundEffect(8);
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("levelup")) {
			panel.player.maxLife += 2;
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("powerup")) {
			panel.player.damage++;
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("redpotion")) {
			panel.player.healthpotions++;
			cheatsound = true;
		}
		
		else if(cheatcode.toLowerCase().equals("saitama")) {
			panel.player.damage = 100;
			panel.player.saitamacheat = !panel.player.saitamacheat;
			cheatsound = true;
		}
			
		if(cheatsound) {
			panel.playSoundEffect(6);
		}
		
		cheat = false;
		
	}

}
