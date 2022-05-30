package com.project.model;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.project.view.GamePanel;

public abstract class GameEntity {
	
	public GamePanel panel;
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	
	public Rectangle collisionArea = new Rectangle(0, 0, 40, 40);
	public int collisionAreaDefaultX, collisionAreaDefaultY;
	
	public boolean collisionOn = false;
	
	public int ActionCounter = 0;
	
	public int maxLife;
	public int life;
	public int invincibletime = 0;
	
	public int size = 0;
	
	public String name;
	
	public void Initialize(GamePanel panel) {
		this.panel = panel;
		size = panel.tilesize;
	}
	
	protected void getImages(String PackagePathName) {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_left_2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	public void Update() {
		
		collisionOn = false;
		panel.collisiondetector.detectCollision(this);
		//panel.collisiondetector.detectObjectCollision(this, false);
		
		panel.collisiondetector.detectPlayer(this);
		
		if(!collisionOn) {
			switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "right": worldX += speed; break;
				case "left": worldX -= speed; break;
			}
		}
		
		spriteCounter++;
		if(spriteCounter > 15) {
			if(spriteNumber == 1)
				spriteNumber = 2;
			else
				spriteNumber = 1;
			spriteCounter = 0;
		}
	}

	

	public void speak() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void render(Graphics2D g2) {

		BufferedImage image = null;
		
		int screenX = worldX - panel.player.worldX + panel.player.screenX;
		int screenY = worldY - panel.player.worldY + panel.player.screenY;
		
		if(panel.player.screenX > panel.player.worldX) {
			screenX = worldX;
		}
		
		if(panel.player.screenY > panel.player.worldY) {
			screenY = worldY;
		}
		
		int rightRemaining = panel.width - panel.player.screenX;
		if(rightRemaining > panel.worldWidth - panel.player.worldX) {
			screenX = panel.width - (panel.worldWidth - worldX);
		}
		
		int bottomRemaining = panel.height - panel.player.screenY;
		if(bottomRemaining > panel.worldHeight - panel.player.worldY) {
			screenY = panel.height - (panel.worldHeight - worldY);
		}
		
		if(invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		
		
		if(worldX + panel.tilesize > panel.player.worldX - panel.player.screenX &&
		   worldX - panel.tilesize < panel.player.worldX + panel.player.screenX &&
		   worldY + panel.tilesize > panel.player.worldY - panel.player.screenY &&
		   worldY - panel.tilesize < panel.player.worldY + panel.player.screenY) {
			
		switch(direction) {
			case "up":
				if(spriteNumber == 1)
					image = up1;
				else
					image = up2;
				break;
			case "down":
				if(spriteNumber == 1)
					image = down1;
				else
					image = down2;
				break;
			case "right":
				if(spriteNumber == 1)
					image = right1;
				else
					image = right2;
				break;
			case "left":
				if(spriteNumber == 1)
					image = left1;
				else
					image = left2;
				break;
			}
			
		
			g2.drawImage(image, screenX, screenY, size, size, null);
			
			
		}
		else if(panel.player.screenX > panel.player.worldX ||
				panel.player.screenY > panel.player.worldY ||
				rightRemaining > panel.width - panel.player.worldX ||
				bottomRemaining > panel.height - panel.player.worldY) {
			
			g2.drawImage(image, screenX, screenY, size, size, null);
			
		}
		
		if(invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
	}
}
