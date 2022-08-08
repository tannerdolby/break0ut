package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {
	
	private Handler handler;
	
	public Ball(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);
		
		this.handler = handler;
		
		velX = 3;
		velY = 3;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// bounds check to keep ball bouncing off side walls and ceiling
		if (x <= 0 || x >= Game.WIDTH - 15) velX *= -1;
		if (y <= 0) velY *= -1;
		
		// TODO: If player misses and ball goes through the floor, reduce a player life
		//if (y >= Game.HEIGHT) 
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x, y, 6, 6);
		
		g.setColor(Color.white);
		g.fillRect(x, y, 6, 6);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 6, 6);
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
					System.out.println("BLOCK COLLISION");
					//this.handler.removeObject(temp);
				}
			}
			
		}
	}
}
