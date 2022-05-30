package com.project.view;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow(GamePanel panel) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("The Legend of Hot Body");
		
		this.add(panel);
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
