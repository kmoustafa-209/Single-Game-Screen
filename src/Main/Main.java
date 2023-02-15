package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import Data.spriteInfo;
import Input.Keyb;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(0, 0, 0);
	public static stopWatchX timer = new stopWatchX(100);
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static ArrayList<BoundingBox> border = new ArrayList<>();
	public static ArrayList<BoundingBox> items = new ArrayList<>();
	public static Vector2D startPos =  new Vector2D(500, 500);
	public static spriteInfo gapster = new spriteInfo(startPos, "g1");
	public static boolean isWalking = false;
	public static boolean isBlocked = false;
	public static boolean isInteract = false;
	public static String action = "null";
	public static int currentSpriteIndex = 0;
	
	
	
	// End Static fields...
	
	public static void main(String[] args) 
	{
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */

	public static void start(){		
			//populate ArrayList Method
		int j = 1;
		
		for(int i = 0; i <= 1400; i += 10)
		{
			//Cycle Frames (Create String and Concatenate/Frame Counter)
		  sprites.add(new spriteInfo(new Vector2D(-100 + i, 100), "g" + j));
		  if(j == 16)
		  {
			  j = 1;
		  }
		  j++;
		}
	

		
			//Populating our Array list with Bounding Boxes 
		border.add(new BoundingBox(0, 0, 20, 720));
		border.add(new BoundingBox(0, 0, 1280, 10));
		border.add(new BoundingBox(0, 700, 1270, 720));
		border.add(new BoundingBox(1270, 0, 1270, 720));
		border.add(new  BoundingBox(603, 290, 610, 270));
		border.add(new  BoundingBox(945, 380, 945, 390));
		
			//Populating our Array list with Items
		items.add(new  BoundingBox(573, 270, 620, 310));
		items.add(new  BoundingBox(940, 375, 975, 420));
		
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		
		int x = gapster.getCoords().getX();
		int y = gapster.getCoords().getY();
		BoundingBox player = new BoundingBox(x, y, x + 128, y + 128);
			
		//Display Walls and Items
		ctrl.addSpriteToFrontBuffer(0,  0,  "w");
		ctrl.addSpriteToFrontBuffer(530, 230, "item1");
		ctrl.addSpriteToFrontBuffer(900,  350, "item2");
		
		// Detection
		for(int i = 0; i < items.size(); i++){
			if(BoundingBox.BoundaryCheck(player, items.get(i)))		
			{
				isInteract = true;
			} else {
				isInteract = false;
			}
		}
		
		wallCollision();

		// Player Animation / Movement
		if(isWalking == false)
		{	
			if (Keyb.last == 'w'){
				ctrl.addSpriteToFrontBuffer(x, y, "g5");
			} else if (Keyb.last == 'a'){
				ctrl.addSpriteToFrontBuffer(x, y, "g13");
			} else if (Keyb.last == 's'){
				ctrl.addSpriteToFrontBuffer(x, y, "g1");
			} else if (Keyb.last == 'd'){
				ctrl.addSpriteToFrontBuffer(x, y, "g9");
			} else {
				ctrl.addSpriteToFrontBuffer(x, y, "g1");
			}
		}
		else 
		{
		
			ctrl.addSpriteToFrontBuffer(x, y, "g" + (currentSpriteIndex + 1));
			animateX();
		}
		// Player Actions
		if(action == "interact"){
			if(BoundingBox.BoundaryCheck(player, items.get(0)))		
			{
				ctrl.drawString(200, 640, "What a beautifully red apple.", c);
				ctrl.addSpriteToFrontBuffer(0, 620, "tb");
				
			} else if(BoundingBox.BoundaryCheck(player, items.get(1)))		
			{
				ctrl.drawString(200, 640, "This is a delicious can of sustenanceTM", c); 
				ctrl.addSpriteToFrontBuffer(0, 620, "tb");
			}
		}
	}
	
	// Additional Static methods below...(if needed)
	// Animate sprite dependent on character input
	public static void animateX()
	{
		if (Keyb.last == 's'){
			currentSpriteIndex = 0;
			if (currentSpriteIndex == 3){
				currentSpriteIndex = 0;
			}
		} else if (Keyb.last == 'w'){
			currentSpriteIndex = 4;
			if (currentSpriteIndex == 7){
				currentSpriteIndex = 4;
			}
		} else if (Keyb.last == 'd'){
			currentSpriteIndex = 8;
			if (currentSpriteIndex == 11){
				currentSpriteIndex = 8;
			}
		} else if (Keyb.last == 'a'){
			currentSpriteIndex = 12;
			if (currentSpriteIndex == 15){
				currentSpriteIndex = 12;
			}
		}
		if (timer.isTimeUp()){
			currentSpriteIndex++;
			timer.resetWatch();
		}
	}
	
	public static void wallCollision()
	{
		int x = gapster.getCoords().getX();
		int y = gapster.getCoords().getY();
		
		// Boundary box for player that can be used for collision / interaction 
		BoundingBox player = new BoundingBox(x, y, x + 128, y + 128); 
		
		
		for(int i = 0; i < border.size(); i++)
		{
			
		
			if(BoundingBox.BoundaryCheck(player, border.get(i)) && Keyb.last == 'w')
				
			{
				isBlocked = true;
				gapster.getCoords().setY(y + 5);
				
			}else if(BoundingBox.BoundaryCheck(player, border.get(i)) && Keyb.last == 'a')
				
			{
				isBlocked = true;
				gapster.getCoords().setX(x + 5);
			}
			else if(BoundingBox.BoundaryCheck(player, border.get(i)) && Keyb.last == 's')
			{
				isBlocked = true;
				gapster.getCoords().setY(y - 5);
			}
			
			else if(BoundingBox.BoundaryCheck(player, border.get(i)) && Keyb.last == 'd')
			{
				isBlocked = true;
				gapster.getCoords().setX(x - 5);
			}
		}	
	}
}
