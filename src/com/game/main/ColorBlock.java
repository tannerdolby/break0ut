package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ColorBlock extends GameObject {
	
	private Handler handler;
	
	public static int GAP = 35;
	
	public ColorBlock(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);

		this.handler = handler;
	}

	public void tick() {}
	
	public void render(Graphics g) {
		if (id == ID.GreenBlock) g.setColor(Color.green);
		if (id == ID.YellowBlock) g.setColor(Color.yellow);
		if (id == ID.OrangeBlock) g.setColor(Color.orange);
		if (id == ID.RedBlock) g.setColor(Color.red);
		
		g.drawRect(x, y, 30, 5);
		g.fillRect(x, y, 30, 5);

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 5);
	}
	
	public void collision() {}
}
