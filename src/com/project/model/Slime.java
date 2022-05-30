package com.project.model;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.project.interfaces.AI;

public abstract class Slime extends GameEntity implements AI{
	

	Slime(String name, String PackagePathName){
		
		speed = 1;
		direction = "up";
		this.name = name;
		maxLife = 2;
		life = maxLife;
		invincibletime = -1;
		
		
		collisionArea.x = 3;
		collisionArea.y = 18;
		collisionArea.width = 42;
		collisionArea.height = 30;
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		
		getImages(PackagePathName);
	}
	
	@Override
	protected void getImages(String PackagePathName) {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void Update() {
		
		setAction();
		super.Update();
		
		if(invincibletime > -1)
			invincibletime--;
	}
	
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
	
	public void dealDamage(int damage){
		if(invincibletime < 0) {
			life -= damage;
			invincibletime = 40;
			panel.playSoundEffect(7);
		}
	}
}

















