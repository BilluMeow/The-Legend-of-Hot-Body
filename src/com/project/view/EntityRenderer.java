package com.project.view;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.project.model.GameEntity;

public class EntityRenderer {
	
	public void render(Graphics2D g2, GameEntity entity) {
BufferedImage image = null;
		
		GamePanel panel = null;
		
		int worldX = 0;
		int screenX = worldX - panel.player.worldX + panel.player.screenX;
		int worldY = 0;
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
		
		if(panel.player.invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		
		
		int size = 0;
		if(worldX + panel.tilesize > panel.player.worldX - panel.player.screenX &&
		   worldX - panel.tilesize < panel.player.worldX + panel.player.screenX &&
		   worldY + panel.tilesize > panel.player.worldY - panel.player.screenY &&
		   worldY - panel.tilesize < panel.player.worldY + panel.player.screenY) {
			
		switch(panel.player.direction) {
			case "up":
				if(panel.player.spriteNumber == 1)
					image = panel.player.up1;
				else
					image = panel.player.up2;
				break;
			case "down":
				if(panel.player.spriteNumber == 1)
					image = panel.player.down1;
				else
					image = panel.player.down2;
				break;
			case "right":
				if(panel.player.spriteNumber == 1)
					image = panel.player.right1;
				else
					image = panel.player.right2;
				break;
			case "left":
				if(panel.player.spriteNumber == 1)
					image = panel.player.left1;
				else
					image = panel.player.left2;
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
		
		if(panel.player.invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		
	}
	
}
