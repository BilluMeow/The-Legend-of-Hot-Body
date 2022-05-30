package com.project.control;

import java.util.ArrayList;

import com.project.model.GameEntity;
import com.project.model.InteractableObjects;
import com.project.view.GamePanel;

public class CollisionDetector {
	private GamePanel panel;

	public CollisionDetector() {}
	
	public void Init(GamePanel panel) {
		this.panel = panel;
	}
	
public void detectCollision(GameEntity object) {
		
		int objectTopWorldY = object.worldY + object.collisionArea.y;
		int objectBottomWorldY = object.worldY + object.collisionArea.y + object.collisionArea.height;
		int objectRightWorldX = object.worldX + object.collisionArea.x + object.collisionArea.width;
		int objectLeftWorldX = object.worldX + object.collisionArea.x;

		int objectTopRow = objectTopWorldY/panel.tilesize;
		int objectBottomRow = objectBottomWorldY/panel.tilesize;
		int objectRightCol = objectRightWorldX/panel.tilesize;
		int objectLeftCol = objectLeftWorldX/panel.tilesize;
		
		int tile1, tile2;
		
		switch(object.direction) {
		case "up":
			objectTopRow = (objectTopWorldY - object.speed) / panel.tilesize;
			
			tile1 = panel.tile.mapTileNumber[objectLeftCol][objectTopRow];
			tile2 = panel.tile.mapTileNumber[objectRightCol][objectTopRow];
			
			if(panel.tile.tiles[tile1].collision || panel.tile.tiles[tile2].collision) {
				object.collisionOn = true;
			}
			
			break;
			
		case "down":
			objectBottomRow = (objectBottomWorldY + object.speed) / panel.tilesize;
			
			tile1 = panel.tile.mapTileNumber[objectLeftCol][objectBottomRow];
			tile2 = panel.tile.mapTileNumber[objectRightCol][objectBottomRow];
			
			if(panel.tile.tiles[tile1].collision || panel.tile.tiles[tile2].collision) {
				object.collisionOn = true;
			}
			
			break;
			
		case "right":
			objectRightCol = (objectRightWorldX + object.speed) / panel.tilesize;
			
			tile1 = panel.tile.mapTileNumber[objectRightCol][objectTopRow];
			tile2 = panel.tile.mapTileNumber[objectRightCol][objectBottomRow];
			
			if(panel.tile.tiles[tile1].collision || panel.tile.tiles[tile2].collision) {
				object.collisionOn = true;
			}
			
			break;
			
		case "left":
			objectLeftCol = (objectLeftWorldX - object.speed) / panel.tilesize;
			
			tile1 = panel.tile.mapTileNumber[objectLeftCol][objectTopRow];
			tile2 = panel.tile.mapTileNumber[objectLeftCol][objectBottomRow];
			
			if(panel.tile.tiles[tile1].collision || panel.tile.tiles[tile2].collision) {
				object.collisionOn = true;
			}
			
			break;	
		}
		
	}
	
	
	public int detectObjectCollision(GameEntity entity, boolean player) {
		
		int index = -1;
		int counter = 0;
		
		
		entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
		entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
		
		for(InteractableObjects i : panel.objects) {
			
			i.collisionArea.x = i.worldX + i.collisionArea.x;
			i.collisionArea.y = i.worldY + i.collisionArea.y;
			
			switch(entity.direction) {
				case "up": entity.collisionArea.y -= entity.speed; break;
				case "down": entity.collisionArea.y += entity.speed; break;
				case "right": entity.collisionArea.x +=entity. speed; break;
				case "left": entity.collisionArea.y -= entity.speed; break;
			}
			
			if(entity.collisionArea.intersects(i.collisionArea)) {
				if(i.collision) {
					entity.collisionOn = true;
				}
				if(player) {
					index = counter;
				}
				
				i.collisionArea.x = i.collisionAreaDefaultX;
				i.collisionArea.y = i.collisionAreaDefaultY;
				
				break;
			}
			
			i.collisionArea.x = i.collisionAreaDefaultX;
			i.collisionArea.y = i.collisionAreaDefaultY;
			
			counter++;
		}
		
		entity.collisionArea.x = entity.collisionAreaDefaultX;
		entity.collisionArea.y = entity.collisionAreaDefaultX;
		
		return index;
	}
	
	public int detectGameEntitiesCollision(GameEntity entity, ArrayList<GameEntity> targets) {
		int index = -1;
		int counter = 0;
		
		
		entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
		entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
		
		for(GameEntity i : targets) {
			
			i.collisionArea.x = i.worldX + i.collisionArea.x;
			i.collisionArea.y = i.worldY + i.collisionArea.y;
			
			switch(entity.direction) {
				case "up": entity.collisionArea.y -= entity.speed; break;
				case "down": entity.collisionArea.y += entity.speed; break;
				case "right": entity.collisionArea.x +=entity. speed; break;
				case "left": entity.collisionArea.y -= entity.speed; break;
			}
			
			if(entity.collisionArea.intersects(i.collisionArea)) {
				entity.collisionOn = true;
				index = counter;
				
				i.collisionArea.x = i.collisionAreaDefaultX;
				i.collisionArea.y = i.collisionAreaDefaultY;
				
				break;
			}
			
			i.collisionArea.x = i.collisionAreaDefaultX;
			i.collisionArea.y = i.collisionAreaDefaultY;
			
			counter++;
		}
		
		entity.collisionArea.x = entity.collisionAreaDefaultX;
		entity.collisionArea.y = entity.collisionAreaDefaultX;
		
		return index;
		
	}
	
	
	public void detectPlayer(GameEntity entity) {
		
		entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
		entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
		
		panel.player.collisionArea.x = panel.player.worldX + panel.player.collisionArea.x;
		panel.player.collisionArea.y = panel.player.worldY + panel.player.collisionArea.y;
		
		switch(entity.direction) {
			case "up": entity.collisionArea.y -= entity.speed; break;
			case "down": entity.collisionArea.y += entity.speed; break;
			case "right": entity.collisionArea.x +=entity. speed; break;
			case "left": entity.collisionArea.y -= entity.speed; break;
		}
		
		if(entity.collisionArea.intersects(panel.player.collisionArea)) {
			entity.collisionOn = true;
			
			if(entity.name.equals("Green Slime"))
				if(panel.player.invincibletime < 0)
					panel.player.dealDamage(1);
			
			if(entity.name.equals("Purple Slime"))
				if(panel.player.invincibletime < 0)
					panel.player.dealDamage(2);
		}
		
		panel.player.collisionArea.x = panel.player.collisionAreaDefaultX;
		panel.player.collisionArea.y = panel.player.collisionAreaDefaultY;
		
		entity.collisionArea.x = entity.collisionAreaDefaultX;
		entity.collisionArea.y = entity.collisionAreaDefaultX;
	}
	
}
