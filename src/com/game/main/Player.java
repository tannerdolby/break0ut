package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private Handler handler;
	public static int width;
	public static int height;
	private Color paddleColor = Color.blue;

	public Player(int x, int y, int width, int height, ID id, String name, Handler handler) {
		super(x, y, id, name);
		this.handler = handler;
		Player.width = width;
		Player.height = height;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 5, Game.WIDTH - 30);
		y = Game.clamp(y, 0, Game.HEIGHT - 36);
		
		collision();
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(paddleColor);

		g2d.draw(getBounds());

		g.fillRect(x, y, Player.width, Player.height);
	}
	
	/**
	 * Get the boundaries for Player game object
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, Player.width, Player.height);
	}
	
	
	/**
	 * Detect collision between Player/Paddle and Ball GameObject.
	 */
	public void collision() {
		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);
			if (temp.id == ID.Ball) {
				if (getBounds().intersects(temp.getBounds())) {
					temp.setVelocityY(-5);
				}
			}
		}
	}
}
