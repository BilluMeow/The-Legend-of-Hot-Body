package com.project.control;

import java.util.Random;

public class EuclideanDistance {
	
	static EuclideanDistance instance = null;
	
	private EuclideanDistance() {
		
	}
	
	public static EuclideanDistance getInstance() {
		
		if(instance == null) {
			instance = new EuclideanDistance();
		}
		
		return instance;
	}
	
	public double getDistance(double x1, double y1, double x2, double y2) {
		
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public String getDirection(double x1, double y1, double x2, double y2) {
		String directiontosecondpoint = "";
		
		int axis = new Random().nextInt() % 2;
		
		if(Math.abs(x1 - x2) < Math.abs(y1 - y2))
			axis = 1;
		else if(Math.abs(x1 - x2) > Math.abs(y1 - y2))
			axis = 0;
		
		if (axis == 1) {
			if(y1 - y2 < 0) {
				directiontosecondpoint = "up";
			}
		
			else if(y1 - y2 > 0) {
				directiontosecondpoint = "down";
			}
		}
		
		else {
			if(x1 - x2 > 0) {
				directiontosecondpoint = "right";
			}
		
			else if(x1 - x2 < 0) {
				directiontosecondpoint = "left";
			}
		}

		return directiontosecondpoint;
	}
	
}
