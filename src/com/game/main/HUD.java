package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int SCORE = 0;
	public static int LIVES = 1;
	
	// Change HUD display to reflect 'Breakout' score and lives left
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		// top bar
		g.setColor(Color.white);
//		g.drawRect(0, 0, Game.WIDTH, 15);
//		g.fillRect(0, 0, Game.WIDTH, 15);
		
		// left bar
//		g.setColor(Color.cyan);
		g.drawRect(0, 0, 5, Game.HEIGHT);
		g.fillRect(0, 0, 5, Game.HEIGHT);
		
		// right bar
//		g.setColor(Color.cyan);
		g.drawRect(Game.WIDTH-5, 0, 5, Game.HEIGHT);
		g.fillRect(Game.WIDTH-5, 0, 5, Game.HEIGHT);
		
		
		// left footer bar detail
		g.setColor(Color.cyan);
		g.drawRect(0, Game.HEIGHT - 81, 6, 15);
		g.fillRect(0, Game.HEIGHT - 81, 6, 15);
		
		// right footer bar detail
		g.setColor(Color.cyan);
		g.drawRect(Game.WIDTH-5, Game.HEIGHT - 81, 6, 15);
		g.fillRect(Game.WIDTH-5, Game.HEIGHT - 81, 6, 15);
		
		// Lives/number of balls
		g.setColor(Color.white);
		g.drawString(Integer.toString(LIVES), 25, 15);
		
		// Current Score
		g.drawString(Integer.toString(SCORE), 25, 55);
		
	}
}
