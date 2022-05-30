package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBoots extends InteractableObjects {

	public ObjectBoots() {

		name = "boots";

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/boots.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
