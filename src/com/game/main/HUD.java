package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int SCORE = 0;
	public static int LIVES = 1;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		// left edge
		g.setColor(Color.white);
		g.drawRect(0, 0, 5, Game.HEIGHT);
		g.fillRect(0, 0, 5, Game.HEIGHT);
		
		// right edge
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH-5, 0, 5, Game.HEIGHT);
		g.fillRect(Game.WIDTH-5, 0, 5, Game.HEIGHT);
		
		// left edge paddle detail
		g.setColor(Color.blue);
		g.drawRect(0, Game.HEIGHT - 81, 6, 15);
		g.fillRect(0, Game.HEIGHT - 81, 6, 15);
		
		// right edge paddle detail
		g.setColor(Color.blue);
		g.drawRect(Game.WIDTH-5, Game.HEIGHT - 81, 6, 15);
		g.fillRect(Game.WIDTH-5, Game.HEIGHT - 81, 6, 15);
		
		g.setColor(Color.white);
		
		// Lives/number of balls
		g.drawString(Integer.toString(LIVES), Game.WIDTH - 150, 25);
		
		// Current Score
		g.drawString(Integer.toString(SCORE), 20, 25);
		
	}
}
