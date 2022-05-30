package com.project.model;


import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.project.interfaces.AI;
import com.project.view.GameState;

public abstract class NPCS extends GameEntity implements AI{
	
	protected ArrayList <String> dialouges;
	protected ListIterator <String> dialougeiterator;
	
	@Override
	public void setAction() {
		Random random = new Random();
		int rand = random.nextInt(100)+1;
		ActionCounter++;
		
		if(ActionCounter < 120)
			return;
		
		if(rand <= 25) {
			direction = "up";
		}
		else if(rand > 25 && rand <= 50){
			direction = "down";
		}
		else if(rand > 50 && rand <= 75){
			direction = "right";
		}
		else if(rand > 75){
			direction = "left";
		}
		
		ActionCounter = 0;
	}	
	
	@Override
	public void speak() {
		if(dialougeiterator.hasNext()) {
			panel.UIDisplay.currentDialouge = dialougeiterator.next();
		}
		else {
			dialougeiterator = dialouges.listIterator(0);
			panel.UIDisplay.currentDialouge = dialougeiterator.next();
			panel.gamestate = GameState.play;
			panel.player.invincibletime = 30;
		}
		
		switch(panel.player.direction) {
			case "up": direction = "down"; break;
			case "down": direction = "up"; break;
			case "right": direction = "left"; break;
			case "left": direction = "right"; break;
		}
	}
}
