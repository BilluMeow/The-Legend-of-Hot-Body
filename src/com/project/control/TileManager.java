package com.project.control;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.project.model.Tile;
import com.project.view.GamePanel;

public class TileManager {
	GamePanel panel;
	public Tile[] tiles;
	int[][] mapTileNumber;
	
	
	public TileManager() {
		tiles = new Tile[10];
		getTileImages();
	}
	
	public void InitTileManager(GamePanel panel) {
		this.panel = panel;
		mapTileNumber = new int[panel.maxWorldCol][panel.maxWorldRow];
		
		loadMap("/map/world01.txt");
	}
	
	public void loadMap(String path) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int row = 0, col = 0;
			
			while(col < panel.maxWorldCol && row < panel.maxWorldRow) {
				
				String line = br.readLine();
				while(col < panel.maxWorldCol){
					
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
				
					mapTileNumber[col][row] = num;
					col++;
				}
				if(col == panel.maxWorldCol) {
					col = 0;
					row++;
				}
 			}
			
		}
		catch(Exception e) {
			
		}
		
	}
	
	private void getTileImages() {
		
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tiles[1].collision = true;
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
			tiles[2].collision = true;
			
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tiles[4].collision = true;
			
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor01.png"));
			
			tiles[6] = new Tile();
			tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			tiles[7] = new Tile();
			tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow) {
			int num = mapTileNumber[worldCol][worldRow];
			
			int worldX = worldCol * panel.tilesize;
			int worldY = worldRow * panel.tilesize;
			
			int screenX = worldX - panel.player.worldX + panel.player.screenX;
			int screenY = worldY - panel.player.worldY + panel.player.screenY;
			
			if(panel.player.screenX > panel.player.worldX) {
				screenX = worldX;
			}
			
			if(panel.player.screenY > panel.player.worldY) {
				screenY = worldY;
			}
			
			int rightRemaining = panel.width - panel.player.screenX;
			if(rightRemaining > panel.worldWidth - panel.player.worldX) {
				screenX = panel.width - (panel.worldWidth - worldX);
			}
			
			int bottomRemaining = panel.height - panel.player.screenY;
			if(bottomRemaining > panel.worldHeight - panel.player.worldY) {
				screenY = panel.height - (panel.worldHeight - worldY);
			}
			
			if(worldX + panel.tilesize > panel.player.worldX - panel.player.screenX &&
			   worldX - panel.tilesize < panel.player.worldX + panel.player.screenX &&
			   worldY + panel.tilesize > panel.player.worldY - panel.player.screenY &&
			   worldY - panel.tilesize < panel.player.worldY + panel.player.screenY) {
				
				g2.drawImage(tiles[num].image, screenX, screenY, panel.tilesize, panel.tilesize, null);
			}
			else if(panel.player.screenX > panel.player.worldX ||
					panel.player.screenY > panel.player.worldY ||
					rightRemaining > panel.width - panel.player.worldX ||
					bottomRemaining > panel.height - panel.player.worldY) {
				
				g2.drawImage(tiles[num].image, screenX, screenY, panel.tilesize, panel.tilesize, null);
			}
			
			worldCol++;
			
			
			if(worldCol == panel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
				
			}
		}
	}
}
