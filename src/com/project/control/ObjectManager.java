package com.project.control;

import com.project.model.BlueSlime;
import com.project.model.GreenSlime;
import com.project.model.NPCBaba;
import com.project.model.NPCDarkGuy;
import com.project.model.ObjectBoots;
import com.project.model.ObjectChest;
import com.project.model.ObjectDoor;
import com.project.model.ObjectHeart;
import com.project.model.ObjectKey;
import com.project.model.ObjectPotion;
import com.project.model.PurpleSlime;
import com.project.view.GamePanel;

public class ObjectManager {
	
	GamePanel panel;
	
	public void Init(GamePanel panel) {
		this.panel = panel;
	}
	
	public void InitializeObjects() {
		
		panel.objects.add(new ObjectKey());
		panel.objects.get(0).worldX = 23 * panel.tilesize;
		panel.objects.get(0).worldY = 7 * panel.tilesize;
		
		panel.objects.add(new ObjectKey());
		panel.objects.get(1).worldX = 23 * panel.tilesize;
		panel.objects.get(1).worldY = 40 * panel.tilesize;
		
		panel.objects.add(new ObjectKey());
		panel.objects.get(2).worldX = 37 * panel.tilesize;
		panel.objects.get(2).worldY = 7 * panel.tilesize;
		
		panel.objects.add(new ObjectDoor());
		panel.objects.get(3).worldX = 10 * panel.tilesize;
		panel.objects.get(3).worldY = 11 * panel.tilesize;
		
		panel.objects.add(new ObjectDoor());
		panel.objects.get(4).worldX = 8 * panel.tilesize;
		panel.objects.get(4).worldY = 28 * panel.tilesize;
		
		panel.objects.add(new ObjectDoor());
		panel.objects.get(5).worldX = 12 * panel.tilesize;
		panel.objects.get(5).worldY = 22 * panel.tilesize;
		
		
		panel.objects.add(new ObjectChest());
		panel.objects.get(6).worldX = 10 * panel.tilesize;
		panel.objects.get(6).worldY = 7 * panel.tilesize;
		
		panel.objects.add(new ObjectBoots());
		panel.objects.get(7).worldX = 48 * panel.tilesize;
		panel.objects.get(7).worldY = 32 * panel.tilesize;
		
		panel.objects.add(new ObjectHeart());
		panel.objects.get(8).worldX = 46 * panel.tilesize;
		panel.objects.get(8).worldY = 32 * panel.tilesize;
		
		panel.objects.add(new ObjectPotion());
		panel.objects.get(9).worldX = 37 * panel.tilesize;
		panel.objects.get(9).worldY = 9 * panel.tilesize;
		
	}
	
	public void InitializeNPCS() {
		
		panel.NPCS.add(new NPCBaba());
		panel.NPCS.get(0).worldX = panel.tilesize * 22;
		panel.NPCS.get(0).worldY = panel.tilesize * 23;
		panel.NPCS.get(0).Initialize(panel);
		
		panel.NPCS.add(new NPCDarkGuy());
		panel.NPCS.get(1).worldX = panel.tilesize * 47;
		panel.NPCS.get(1).worldY = panel.tilesize * 18;
		panel.NPCS.get(1).Initialize(panel);
		
	}
	
	public void InitializeMonsters() {
		
		panel.monsters.add(new GreenSlime("Green Slime"));
		panel.monsters.get(0).worldX = panel.tilesize * 23;
		panel.monsters.get(0).worldY = panel.tilesize * 40;
		panel.monsters.get(0).Initialize(panel);
		
		panel.monsters.add(new GreenSlime("Green Slime"));
		panel.monsters.get(1).worldX = panel.tilesize * 23;
		panel.monsters.get(1).worldY = panel.tilesize * 44;
		panel.monsters.get(1).Initialize(panel);
		
		panel.monsters.add(new GreenSlime("Green Slime"));
		panel.monsters.get(2).worldX = panel.tilesize * 26;
		panel.monsters.get(2).worldY = panel.tilesize * 40;
		panel.monsters.get(2).Initialize(panel);
		
		panel.monsters.add(new GreenSlime("Green Slime"));
		panel.monsters.get(3).worldX = panel.tilesize * 26;
		panel.monsters.get(3).worldY = panel.tilesize * 40;
		panel.monsters.get(3).Initialize(panel);
		
		panel.monsters.add(new BlueSlime("Blue Slime"));
		panel.monsters.get(4).worldX = panel.tilesize * 23;
		panel.monsters.get(4).worldY = panel.tilesize * 7;
		panel.monsters.get(4).Initialize(panel);
		
		panel.monsters.add(new BlueSlime("Blue Slime"));
		panel.monsters.get(5).worldX = panel.tilesize * 23;
		panel.monsters.get(5).worldY = panel.tilesize * 7;
		panel.monsters.get(5).Initialize(panel);
		
		panel.monsters.add(new BlueSlime("Blue Slime"));
		panel.monsters.get(6).worldX = panel.tilesize * 37;
		panel.monsters.get(6).worldY = panel.tilesize * 9;
		panel.monsters.get(6).Initialize(panel);
		
		panel.monsters.add(new BlueSlime("Blue Slime"));
		panel.monsters.get(7).worldX = panel.tilesize * 37;
		panel.monsters.get(7).worldY = panel.tilesize * 9;
		panel.monsters.get(7).Initialize(panel);
		
		panel.monsters.add(new BlueSlime("Blue Slime"));
		panel.monsters.get(8).worldX = panel.tilesize * 37;
		panel.monsters.get(8).worldY = panel.tilesize * 10;
		panel.monsters.get(8).Initialize(panel);
		
		panel.monsters.add(new PurpleSlime("Purple Slime"));
		panel.monsters.get(9).worldX = panel.tilesize * 6;
		panel.monsters.get(9).worldY = panel.tilesize * 89;
		panel.monsters.get(9).Initialize(panel);
		
	}
	
}
