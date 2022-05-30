 package com.project.model;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.project.control.KeyHandler;
import com.project.view.GamePanel;
import com.project.view.GameState;

public class Player extends GameEntity{
	KeyHandler handler;
	
	public int screenX;
	public int screenY;
	
	public int keysHolding = 0;
	public int healthpotions = 0;
	public int goldenkeys = 0;
	public int coinsholding = 0;

	public boolean caspercheat = false;
	public boolean saitamacheat = false;

	
	public boolean attacking = false;
	private Rectangle attackArea = new Rectangle(0, 0, 36, 36);
	public int talkingNPC = -1;
	
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;

	public int damage = 1;
	
	public Player(KeyHandler handler) {
		this.handler = handler;
	}
	
	public void Init(GamePanel panel) {
		Initialize(panel);
		
		worldX = panel.tilesize * 23;
		worldY = panel.tilesize * 21;
		
		//worldX = panel.tilesize * 47;
		//worldY = panel.tilesize * 47;
		
		screenX = panel.width/2 - (panel.tilesize/2);
		screenY = panel.height/2 - (panel.tilesize/2);
		
		speed = 4;
		direction = "down";
		
		collisionArea = new Rectangle();
		collisionArea.x = 8;
		collisionArea.y = 16;
		collisionArea.width = 25;
		collisionArea.height = 25;
		
		collisionAreaDefaultX = collisionArea.x;
		collisionAreaDefaultY = collisionArea.y;
		
		maxLife = 6;
		life = maxLife;
		invincibletime = 10;
		
		getImages("/player/boy");
	}
	
	@Override
	public void getImages(String PackagePathName) {
		
		super.getImages(PackagePathName);
		
		try {
			attackUp1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_up_1.png"));
			attackUp2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_up_2.png"));
			attackDown1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_down_1.png"));
			attackDown2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_down_2.png"));
			attackRight1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_right_1.png"));
			attackRight2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_right_2.png"));
			attackLeft1 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_left_1.png"));
			attackLeft2 = ImageIO.read(getClass().getResourceAsStream(PackagePathName+"_attack_left_2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void Update() {
		
		if(invincibletime > -1)
			invincibletime--;
		
		if(attacking) {
			
			attack();
		}
		
		else if(handler.up || handler.down || handler.right || handler.left) {
			
			if(handler.up) {
				direction = "up";
			}	
			else if(handler.down) {
				direction = "down";
			}
			else if(handler.right) {
				direction = "right";
			}
			else if(handler.left) {
				direction = "left";
			}
			
			collisionOn = false;
			
			if(!caspercheat) {
				panel.collisiondetector.detectCollision(this);
			}
			
			int objectindex = panel.collisiondetector.detectObjectCollision(this, true);
			pickUpInteractableObject(objectindex);
		
			if(invincibletime < 0) {
				int npcindex = panel.collisiondetector.detectGameEntitiesCollision(this, panel.NPCS);
				interactWithNPC(npcindex);

				int monsterindex = panel.collisiondetector.detectGameEntitiesCollision(this, panel.monsters);
				interactWithMonsters(monsterindex);
			}
				
			if(!collisionOn) {
				switch(direction) {
					case "up": worldY = (worldY - speed > 0) ?  worldY - speed : worldY; break;
					case "down": worldY = (worldY + speed < panel.maxWorldRow*panel.tilesize - panel.tilesize) ?  worldY + speed : worldY; break;
					case "right": worldX  = (worldX + speed < panel.maxWorldRow*panel.tilesize - panel.tilesize) ?  worldX + speed : worldX; break;
					case "left": worldX = (worldX - speed > 0) ?  worldX - speed : worldX; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 20) {
				if(spriteNumber == 1)
					spriteNumber = 2;
				else
					spriteNumber = 1;
				spriteCounter = 0;
			}
		}	
	}
	

	private void attack() {
		
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNumber = 1;
		}
		
		else if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNumber = 2;
			
			checkMonsterHit();
		}
		
		else if(spriteCounter > 25) {
			spriteNumber = 1;
			spriteCounter = 0;
			attacking = false;
		}
		
	}

	private void checkMonsterHit() {

		int currentWorldX = worldX;
		int currentWorldY = worldY;
		int collisionAreaWidth = collisionArea.width;
		int collisionAreaHeight = collisionArea.height;
		
		
		switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "right": worldX  += attackArea.width; break;
			case "left": worldX -= attackArea.width; break;
		}
		
		collisionArea.width = attackArea.width;
		collisionArea.height = attackArea.height;
		
		int monsterindex = panel.collisiondetector.detectGameEntitiesCollision(this, panel.monsters);
		dealDamageToMonster(monsterindex);
		
		collisionArea.width = collisionAreaWidth;
		collisionArea.height = collisionAreaHeight;
		worldX = currentWorldX;
		worldY = currentWorldY;
		
	}

	private void dealDamageToMonster(int monsterindex) {
		
		if(monsterindex < 0)
			return;
			
		Slime slime = (Slime) panel.monsters.get(monsterindex);
		slime.dealDamage(damage);
		
		if(slime.life <= 0) {
			if(slime.name.equals("Green Slime")) {
				panel.monsters.remove(monsterindex);
				
				ObjectPotion healthpotion = new ObjectPotion();
				healthpotion.worldX = slime.worldX;
				healthpotion.worldY = slime.worldY;
				panel.objects.add(healthpotion);
			}
			
			else if(slime.name.equals("Purple Slime")) {
				panel.monsters.remove(monsterindex);
				
				ObjectGoldenKey goldenkey = new ObjectGoldenKey();
				goldenkey.worldX = slime.worldX;
				goldenkey.worldY = slime.worldY;
				panel.objects.add(goldenkey);
			}
			
			else if(slime.name.equals("Blue Slime")) {
				panel.monsters.remove(monsterindex);
				
				ObjectBronzeCoin coin = new ObjectBronzeCoin();
				coin.worldX = slime.worldX;
				coin.worldY = slime.worldY;
				panel.objects.add(coin);
			}
		}
			
	}

	
	public void dealDamage(int damage) {
		
		if(invincibletime > 0)
			return;
		
		invincibletime  = 100;
		
		panel.playSoundEffect(5);
		if(saitamacheat)
			return;
		
		life -= damage;
		
		if(life < 1) {
			panel.gamestate = GameState.die;
			panel.StopMusic();
			panel.playSoundEffect(1);
		}
	}
	
	private void interactWithMonsters(int monsterindex) {
		if(monsterindex < 0)
			return;
		
		String monstername = panel.monsters.get(monsterindex).name;
	
		if(monstername.equals("Green Slime")) {
			dealDamage(1);
		}
		
		else if(monstername.equals("Blue Slime")) {
			IncreaseHealth(1);
			panel.playSoundEffect(2);
			panel.monsters.remove(monsterindex);
		}
		
		else if(monstername.equals("Purple Slime")) {
			dealDamage(3);
		}
	}
	
	private void interactWithNPC(int npcindex) {
		if(npcindex < 0)
			return;
		
		if(panel.keyhandler.talk) {
			talkingNPC = npcindex;
			NPCS npc = (NPCS) panel.NPCS.get(talkingNPC);
			
			if(npc.name == "DarkGuy") {
				NPCDarkGuy darkguy = (NPCDarkGuy) npc;
				if(coinsholding >= 5 && !darkguy.dialougechanged) {
					darkguy.changeDialouges();
					coinsholding -= 5;
					healthpotions += 4;
				}
			}
			
			panel.gamestate = GameState.dialogue;
			panel.NPCS.get(npcindex).speak();
		}
		
		panel.keyhandler.talk = false;
	}

	private void pickUpInteractableObject(int index) {
		if(index < 0)
			return;
		
		String objectName = panel.objects.get(index).name;
		
		if(objectName == "key") {
			keysHolding++;
			panel.objects.remove(index);
			panel.playSoundEffect(2);
		}
		
		else if(objectName == "door" && keysHolding > 0) {
			keysHolding--;
			panel.objects.remove(index);
			panel.playSoundEffect(4);
		}
		
		else if(objectName == "boots") {
			speed++;
			panel.objects.remove(index);
			panel.playSoundEffect(3);
		}
		
		else if (objectName == "health potion") {
			healthpotions++;
			panel.objects.remove(index);
			panel.playSoundEffect(2);
		}
		
		else if (objectName == "golden key") {
			goldenkeys++;
			panel.objects.remove(index);
			panel.playSoundEffect(2);
		}
		
		else if (objectName == "bronze coin") {
			coinsholding++;
			panel.objects.remove(index);
			panel.playSoundEffect(2);
		}
		
		else if (objectName == "heart") {
			maxLife += 2;
			life = maxLife;
			panel.objects.remove(index);
			panel.playSoundEffect(2);
		}
		
		else if (objectName == "chest" && goldenkeys > 0) {
			panel.StopMusic();
			panel.playSoundEffect(8);
			panel.gamestate = GameState.win;
		}

	}
	
	public void render(Graphics2D g2) {
		BufferedImage image = null;
		
		int imagewidth = panel.tilesize;
		int imageheight = panel.tilesize;
		
		int attackscreenadjustmentX = 0;
		int attackscreenadjustmentY = 0;
		
		switch(direction) {
		case "up":
			
			if(attacking) {
				attackscreenadjustmentY = panel.tilesize;
				imageheight = panel.tilesize*2;
				if(spriteNumber == 1) {
					image = attackUp1;
					
				}
				else {
					image = attackUp2;
				}
			}
			
			else {if(spriteNumber == 1) image = up1; else image = up2;}
			break;
		case "down":
			
			if(attacking) {
				imageheight = panel.tilesize*2;
				if(spriteNumber == 1) {
					image = attackDown1;
				}
				else {
					image = attackDown2;
				}
			}
			
			else {if(spriteNumber == 1) image = down1; else image = down2;}
			break;
		case "right":
			
			if(attacking) {
				imagewidth = panel.tilesize*2;
				if(spriteNumber == 1) {
					image = attackRight1; 
				}
				else {
					image = attackRight2;
				}
			}
			
			else {if(spriteNumber == 1) image = right1; else image = right2;}
			break;
		case "left": 
			
			if(attacking) {
				attackscreenadjustmentX = panel.tilesize;
				imagewidth = panel.tilesize*2;
				if(spriteNumber == 1) {
					image = attackLeft1;
				}
				else {
					image = attackLeft2;
				}
			}
			
			else {if(spriteNumber == 1) image = left1; else image = left2;}
			break;
		}
		
		int x = screenX, y = screenY;
		
		if(screenX > worldX) {
			x = worldX;
		}
		
		if(screenY > worldY) {
			y = worldY;
		}

		int rightRemaining = panel.width - screenX;
		if(rightRemaining > panel.worldWidth - worldX) {
			x = panel.width - (panel.worldWidth - worldX);
		}
		
		int bottomRemaining = panel.height - screenY;
		if(bottomRemaining > panel.worldHeight - worldY) {
			y = panel.height - (panel.worldHeight - worldY);
		}
		
		if(invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		
		
		g2.drawImage(image, x - attackscreenadjustmentX, y - attackscreenadjustmentY, imagewidth, imageheight, null);
		
		if(invincibletime > 0)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}

	public void IncreaseHealth(int healthincrease) {
		
		if(healthincrease > 2)
			healthincrease = 2;
		
		panel.player.life += (panel.player.life + healthincrease > panel.player.maxLife) ? healthincrease - 1 : healthincrease;
	}
	
	public void drinkHealthPotion() {
		
		if(life == maxLife || healthpotions < 1)
			return;
		
		IncreaseHealth(2);
		panel.playSoundEffect(3);
		healthpotions--;
	}
	
}
