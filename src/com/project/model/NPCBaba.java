package com.project.model;

import java.util.ArrayList;
import com.project.view.GamePanel;

public class NPCBaba extends NPCS{
	
	
	
	public NPCBaba() {
		dialouges = new ArrayList<>();
		
		dialouges.add("So your the Young man who has wandered on \nthis island.");
		dialouges.add("There was once prophecy about a young man.");
		dialouges.add("That he will be the one to kill Hans, and open the \nsecret treasure of crimson demon.");
		dialouges.add("Hans, also know as Deadly Poisonous Slime no \nHans.");
		dialouges.add("Hans was once the General of Demon King Army.");
		dialouges.add("After getting defeated by Kazuma, he has been \nhere");
		dialouges.add("He will come out of nowhere");
		dialouges.add("Even though he is weak, I cannot fight him with my \nold body.");
		dialouges.add("You can find blue slimes here, you can eat them to \nheal your self or kill them get a rewards");
		dialouges.add("You must stay away from green ones, they are \nposinous.");
		dialouges.add("Good luck in defeating Hans and getting the \ntreasure of Crimson demon.");
		
		dialougeiterator = dialouges.listIterator();
	}
	
	public void Initialize(GamePanel panel) {
		
		this.panel = panel;
		direction = "down";
		speed = 1;
		name = "Baba";
		size = panel.tilesize;
		
		getImages("/NPC/oldman");
	}
	
	@Override
	public void Update() {
		
		setAction();
		super.Update();
	}

}
