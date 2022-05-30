package com.project.model;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectHeart extends InteractableObjects{
	
	public ObjectHeart() {
		
		name = "heart";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/InteractableObjects/heart_blank.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
