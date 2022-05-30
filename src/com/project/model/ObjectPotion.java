package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectPotion extends InteractableObjects {

	public ObjectPotion() {

		name = "health potion";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/red_potion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
