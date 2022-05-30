package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBronzeCoin extends InteractableObjects{

	public ObjectBronzeCoin() {

		name = "bronze coin";
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/coin_bronze.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
