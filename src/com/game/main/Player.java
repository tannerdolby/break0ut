package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private Handler handler;

	public Player(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);
		
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 55);
		y = Game.clamp(y, 0, Game.HEIGHT - 36);
		
		collision();
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(Color.blue);
		g.setColor(Color.cyan);
		g2d.draw(getBounds());
		
		g.setColor(Color.cyan);
		g.fillRect(x, y, 48, 12);
		
	}
	
	/**
	 * Get the boundaries for Player game object
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 48, 12);
	}
	
	
	/**
	 * Detect collision between Player and Ball GameObjects.
	 */
	public void collision() {
		
		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);
			
			if (temp.id == ID.Ball) {
				if (getBounds().intersects(temp.getBounds())) {
					// play around with this "bounce" value when ball
					// contacts the player board
					temp.setVelocityY(-2);

				}
			}
		}
	}
}
