package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class HUD {
	public static int SCORE = 0;
	public static int LIVES = 1;
	public static boolean SHOW_SERVE_BTN = false;
	public String scoreArr[] = {"0", "0", "0"};
	
	public void tick() {}
	
	public void render(Graphics g) {
		
		// left edge
		g.setColor(Color.white);
		g.drawRect(0, 0, 5, Game.HEIGHT - 50);
		g.fillRect(0, 0, 5, Game.HEIGHT - 50);
		
		// right edge
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH-5, 0, 5, Game.HEIGHT - 50);
		g.fillRect(Game.WIDTH-5, 0, 5, Game.HEIGHT - 50);
		
		// left edge paddle detail
		g.setColor(Color.blue);
		g.drawRect(0, Game.HEIGHT - 110, 6, 13);
		g.fillRect(0, Game.HEIGHT - 110, 6, 13);
		
		// right edge paddle detail
		g.setColor(Color.blue);
		g.drawRect(Game.WIDTH-5, Game.HEIGHT - 110, 6, 13);
		g.fillRect(Game.WIDTH-6, Game.HEIGHT - 110, 6, 13);
		
		// Draw left/right edges of each ColorBlock row
		g.setColor(Color.red);
		g.fillRect(0, 63, 6, 18);
		g.fillRect(Game.WIDTH-6, 63, 6, 18);
		
		g.setColor(Color.orange);
		g.fillRect(0, 81, 6, 18);
		g.fillRect(Game.WIDTH-6, 81, 6, 18);

		g.setColor(Color.green);
		g.fillRect(0, 99, 6, 18);
		g.fillRect(Game.WIDTH-6, 99, 6, 18);
		
		g.setColor(Color.yellow);
		g.fillRect(0, 117, 6, 18);
		g.fillRect(Game.WIDTH-6, 117, 6, 18);
		
		g.setFont(new Font("DINAlternate-Bold", Font.BOLD, 38));
		g.setColor(Color.white);
		
		// First row
		g.drawString("1", 20, 30);
		
		// Lives/number of balls
		g.drawString(Integer.toString(LIVES), Game.WIDTH - 150, 30);
		
		// Current Score
		this.formatScore();
		g.drawString(String.join("", this.scoreArr), 35, 60);
		
		this.formatScore();
		g.drawString(String.join("", new String[] {"0","0","0"}), Game.WIDTH - 135, 60);
		
		// Serve Button
		if (HUD.SHOW_SERVE_BTN) {
			g.setFont(new Font("DINAlternate-Bold", Font.BOLD, 14));
			g.setColor(Color.red);
			g.fillOval(Game.WIDTH / 2 - 20, Game.HEIGHT-75, 42, 38);
			g.setColor(Color.black);
			g.drawString("SERVE", Game.WIDTH / 2 - 20, Game.HEIGHT-51);
		}
	}
	
	public void formatScore() {		
		String scoreStr = Integer.toString(HUD.SCORE);
		if (HUD.SCORE < 10) {
			this.scoreArr[2] = scoreStr;
		} else if (HUD.SCORE >= 10 && HUD.SCORE < 100) {
			this.scoreArr[1] = Character.toString(scoreStr.charAt(0));
			this.scoreArr[2] = Character.toString(scoreStr.charAt(1));
		} else if (HUD.SCORE >= 100) {
			this.scoreArr[0] = Character.toString(scoreStr.charAt(0));
			this.scoreArr[1] = Character.toString(scoreStr.charAt(1));
			this.scoreArr[2] = Character.toString(scoreStr.charAt(2));
		}
	}
}
