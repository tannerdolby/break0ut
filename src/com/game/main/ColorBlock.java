package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ColorBlock extends GameObject {
	
	private Handler handler;
	
	public static int GAP = 37;
	
	public ColorBlock(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);

		this.handler = handler;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (id == ID.GreenBlock) g.setColor(Color.green);
		if (id == ID.YellowBlock) g.setColor(Color.yellow);
		if (id == ID.OrangeBlock) g.setColor(Color.orange);
		if (id == ID.RedBlock) g.setColor(Color.red);
		
		g.drawRect(x, y, 30, 5);
		g.fillRect(x, y, 30, 5);
	}
	
	public void collision() {
		
		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);
			
			if (Game.colorBlocks.containsKey(temp.id)) {
				// check for collision between
				if (getBounds().intersects(temp.getBounds())) {
					// Update player score and then
					// destroy the colored block, & remove it from display
					HUD.SCORE += Game.colorBlocks.get(temp.id);
					//this.handler.removeObject(temp);
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 3);
	}
}
