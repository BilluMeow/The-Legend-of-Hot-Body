package com.project.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.project.model.InteractableObjects;
import com.project.model.ObjectBronzeCoin;
import com.project.model.ObjectGoldenKey;
import com.project.model.ObjectHeart;
import com.project.model.ObjectKey;
import com.project.model.ObjectPotion;

public class OnScreenUI {
	
	GamePanel panel;
	Font arial;
	BufferedImage keyimage, potionimage, goldenkeyimage, coinimage;
	BufferedImage full_heart, half_heart, blank_heart;
	public String currentDialouge = "";
	
	public OnScreenUI() {
		
		arial = new Font("Arial", Font.PLAIN, 40);
		
		InteractableObjects heart = new ObjectHeart();
		full_heart = heart.image;
		half_heart = heart.image2;
		blank_heart = heart.image3;
		
		
		keyimage = new ObjectKey().image;
		potionimage = new ObjectPotion().image;
		goldenkeyimage = new ObjectGoldenKey().image;
		coinimage = new ObjectBronzeCoin().image;
	}
	
	public void Init(GamePanel panel) {
		
		this.panel = panel;
	}
	
	public void render(Graphics2D g2) {
		
		
	
		renderPlayerHealth(g2);
		renderHealthPotions(g2);
		rendersilverKeys(g2);
		rendergoldenKeys(g2);
		renderCoins(g2);
	}
	
	
	public void renderPlayerHealth(Graphics2D g2) {
		int y = panel.tilesize / 2;
		
		int x = panel.tilesize/2;
		for(int i=0;i<panel.player.maxLife/2;i++) {
			g2.drawImage(blank_heart, x, y, panel.tilesize, panel.tilesize, null);
			x += panel.tilesize;
		}
		
		x = panel.tilesize/2;
		int i = 0;
		while(i < panel.player.life) {
			g2.drawImage(half_heart, x, y, panel.tilesize, panel.tilesize, null);
			
			i++;
			if(i < panel.player.life)
				g2.drawImage(full_heart, x, y, panel.tilesize, panel.tilesize, null);
			
			i++;
			x += panel.tilesize;
		}
		
	}
	
	public void renderHealthPotions(Graphics2D g2) {
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		g2.drawImage(potionimage, panel.tilesize/2, panel.tilesize*2, panel.tilesize, panel.tilesize, null);
		g2.drawString("x "+Integer.toString(panel.player.healthpotions), 74, 135);
	}
	
	public void rendersilverKeys(Graphics2D g2) {
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyimage, panel.tilesize/2, panel.tilesize*4, panel.tilesize, panel.tilesize, null);
		g2.drawString("x "+Integer.toString(panel.player.keysHolding), 74, 230);
	}
	
	public void rendergoldenKeys(Graphics2D g2) {
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		g2.drawImage(goldenkeyimage, panel.tilesize/2, panel.tilesize*6, panel.tilesize, panel.tilesize, null);
		g2.drawString("x "+Integer.toString(panel.player.goldenkeys), 74, 325);
	}
	
	public void renderCoins(Graphics2D g2) {
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		g2.drawImage(coinimage, panel.tilesize/2, panel.tilesize*8, panel.tilesize, panel.tilesize, null);
		g2.drawString("x "+Integer.toString(panel.player.coinsholding), 74, 420);
	}
	
	public void renderDialouge(Graphics2D g2) {
		int x = panel.tilesize*3;
		int y = panel.tilesize*2;
		int width = panel.width - (panel.tilesize*4);
		int height = panel.tilesize*5;
		
		g2.setColor(new Color(0, 0, 0, 0.7F));
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
		x += panel.tilesize;
		y += panel.tilesize;
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		
		for(String line : currentDialouge.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
			
	}
	
	public void renderStartGameScreen(Graphics2D g2) {
		
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		
		String gameovertext = "THE LEGEND OF HOT BODY";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
		int x = getCenterX(gameovertext, g2);
		int y = panel.height/2;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "PRESS ANY KEY TO START";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x = getCenterX(gameovertext, g2);
		y = 2*panel.height/3;
		g2.drawString(gameovertext, x, y);
	}

	public void renderPauseScreen(Graphics2D g2) {
		
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		String pausetext = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		int x = getCenterX(pausetext, g2);
		int y = panel.height/2;
		
		g2.drawString(pausetext, x, y);
		
	}
	
	public void renderGameOverScreen(Graphics2D g2) {
		
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		
		String gameovertext = "GAME OVER";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		int x = getCenterX(gameovertext, g2);
		int y = panel.height/2;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "PRESS ANY KEY TO EXIT";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x = getCenterX(gameovertext, g2);
		y = 2*panel.height/3;
		g2.drawString(gameovertext, x, y);
		
	}
	
	public void renderWinningScreen(Graphics2D g2) {
		
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		
		String gameovertext = "CONGRATULATIONS";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		int x = getCenterX(gameovertext, g2);
		int y = panel.height/3;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "YOU FOUND THE TREASURE";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
		x = getCenterX(gameovertext, g2);
		y = panel.height/2;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "PRESS ANY KEY TO EXIT";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x = getCenterX(gameovertext, g2);
		y = 2*panel.height/3;
		g2.drawString(gameovertext, x, y);
		
	}

	private int getCenterX(String pausetext, Graphics2D g2) {
		int len = (int)g2.getFontMetrics().getStringBounds(pausetext, g2).getWidth();
		int x = panel.width/2 - len/2;
		return x;
	}

	public void renderControls(Graphics2D g2) {
		g2.setFont(arial);
		g2.setColor(Color.WHITE);
		
		String gameovertext = "CONTROLS";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		int x = getCenterX(gameovertext, g2);
		int y = panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "UP    ---    W AND Up Arrow Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 2*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "DOWN    ---    S AND Down Arrow Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 3*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "LEFT    ---    A AND Left Arrow Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 4*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "RIGHT    ---    D AND Right Arrow Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 5*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "Attack    ---    F AND DEL Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 6*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "Talk    ---    G AND END Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 7*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "Dirnk Potion    ---    H AND PGDN Key";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x = getCenterX(gameovertext, g2);
		y = 8*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
		gameovertext = "PRESS ANY KEY TO START";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x = getCenterX(gameovertext, g2);
		y = 9*panel.height/10;
		g2.drawString(gameovertext, x, y);
		
	}

	
}
