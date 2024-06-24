package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ColorBlock extends GameObject {
	
	private Handler handler;
	private int width = 30;
	private int height = 5;
	
	public static int GAP = 35;
	
	public ColorBlock(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);
		this.handler = handler;
	}

	public void tick() {}
	
	public void render(Graphics g) {
		switch (id) {
			case GreenBlock:
				g.setColor(Color.green);
				break;
			case YellowBlock:
				g.setColor(Color.yellow);
				break;
			case OrangeBlock:
				g.setColor(Color.orange);
				break;
			case RedBlock:
				g.setColor(Color.red);
				break;
			default:
				break;
		}
		
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void collision() {}
}
