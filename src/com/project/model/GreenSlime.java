package com.project.model;

import com.project.control.EuclideanDistance;

public class GreenSlime extends Slime {

	public GreenSlime(String name) {
		super(name, "/monster/greenslime");
		maxLife = 4;
		life = maxLife;
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
}
