package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectGoldenKey extends InteractableObjects{
	
	public ObjectGoldenKey() {
		
		name = "golden key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/goldenkey.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
