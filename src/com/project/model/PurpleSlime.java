package com.project.model;

import java.awt.Graphics2D;

import com.project.control.EuclideanDistance;

public class PurpleSlime extends Slime {

	public PurpleSlime(String name) {
		super(name, "/monster/purpleslime");
		maxLife = 20;
		life = maxLife;
		
		collisionArea.x = 30;
		collisionArea.y = 50;
		collisionArea.width = 120;
		collisionArea.height = 120;
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
	}

	@Override
	public void setAction() {

		EuclideanDistance distancecalculator = EuclideanDistance.getInstance();
		int distanceformplayer = (int) distancecalculator.getDistance(worldX, worldY, panel.player.worldX,
				panel.player.worldY) / panel.tilesize;

		if (distanceformplayer > -1) {
			super.setAction();
			return;
		}
		
		direction = distancecalculator.getDirection(worldX, worldY, panel.player.worldX, panel.player.worldY);
	}
	
	@Override
	public void render(Graphics2D g2) {
		size = panel.tilesize * 4;
		super.render(g2);
	}
}
