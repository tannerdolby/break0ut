package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {
	
	private Handler handler;
	
	public Ball(int x, int y, ID id, String name, Handler handler) {
		super(x, y, id, name);
		
		this.handler = handler;
		
		velX = 2;
		velY = 2;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// bounds check to keep ball bouncing off side walls
		if (x <= 0 || x >= Game.WIDTH - 15) velX *= -1;
		
		// TODO: If player misses and ball goes through the floor, reduce a player life
		// also if player gets a breakout and ball goes through ceiling handle win condition
		if (y <= 0) {
			System.out.println("BREAKOUT");
			this.setVelocityY(0);
		}
		
		if (y >= Game.HEIGHT) {
			if (HUD.LIVES > 3) {
				System.out.println("Game over :)");
			}
			HUD.LIVES += 1;
			// TODO: Reserve ball
			this.setY(150);
		}

		collision();
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
	
	/**
	 * Detect collision between the gameball and a colored block GameObject.
	 */
	public void collision() {
		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);
			// check if the game object is a ColorBlock with ID.<Color>Block
			if (Game.colorBlocks.containsKey(temp.id)) {
				// check for collision between ball and color block
				if (getBounds().intersects(temp.getBounds())) {
					this.setVelocityY(4);
					HUD.SCORE += Game.colorBlocks.get(temp.id);
					this.handler.removeObject(temp);
				}
			}
		}
	}
}
