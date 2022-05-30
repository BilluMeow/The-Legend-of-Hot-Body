package com.project.model;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import com.project.view.GamePanel;

public class NPCDarkGuy extends NPCS{
	
	public boolean dialougechanged = false;

	public NPCDarkGuy() {
		dialouges = new ArrayList<>();
		
		dialouges.add("So you're going to challenge Hans\n");
		dialouges.add("I've seen many adventurers dying by the hands of \nhim");
		dialouges.add("Either way I don't care whether you live or die");
		dialouges.add("I just have a few potions on my hand");
		dialouges.add("I'll give you all for 5 bronze coins");
		
		dialougeiterator = dialouges.listIterator();
	}
	
	public void Initialize(GamePanel panel) {
		
		this.panel = panel;
		direction = "down";
		speed = 1;
		name = "DarkGuy";
		size = panel.tilesize;
		
		getImages("/NPC/merchant");
	}
	
	@Override
	public void Update() {}
	
	@Override
	protected void getImages(String PackagePathName) {
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void changeDialouges() {
		dialougechanged = true;
		dialouges.clear();
		
		dialouges.add("That's all I have\n");
		dialouges.add("Be safe on your adventure");
		
		dialougeiterator = dialouges.listIterator(0);
	}
	
}
