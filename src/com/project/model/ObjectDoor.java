package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectDoor extends InteractableObjects{
	
	public ObjectDoor() {
		
		name = "door";
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
