package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectKey extends InteractableObjects{
	
	public ObjectKey() {
		
		name = "key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/silverkey.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
