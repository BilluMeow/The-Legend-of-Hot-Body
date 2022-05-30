package com.project.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.project.view.GamePanel;

public abstract class InteractableObjects {
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	
	public Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
	public int collisionAreaDefaultX = 0;
	public int collisionAreaDefaultY = 0;
	
	public int worldX, worldY;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void render(Graphics2D g2, GamePanel panel) {
		
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
		
		
		if(worldX + panel.tilesize > panel.player.worldX - panel.player.screenX &&
		   worldX - panel.tilesize < panel.player.worldX + panel.player.screenX &&
		   worldY + panel.tilesize > panel.player.worldY - panel.player.screenY &&
		   worldY - panel.tilesize < panel.player.worldY + panel.player.screenY) {
					
			g2.drawImage(image, screenX, screenY, panel.tilesize, panel.tilesize, null);
		}
		else if(panel.player.screenX > panel.player.worldX ||
				panel.player.screenY > panel.player.worldY ||
				rightRemaining > panel.width - panel.player.worldX ||
				bottomRemaining > panel.height - panel.player.worldY) {
			
			g2.drawImage(image, screenX, screenY, panel.tilesize, panel.tilesize, null);
		}
	}
}
