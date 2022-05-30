package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectChest extends InteractableObjects{

	public ObjectChest() {

		name = "chest";
		collision = true;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/chest.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
