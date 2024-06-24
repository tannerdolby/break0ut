package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private int currPlayerMovementKey;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);

			// TODO: Handle serve button reserving ball and transitioning
			// from start screen to first game serve
			if (temp.id == ID.Player) {
				if (Game.isStartingScreen && key == KeyEvent.VK_ENTER) {
					System.out.println("GRRR");
					Game.isStartingScreen = false;
					Game.isPlaying = true;
//					Ball.visible = false;
					Player.width = 30;
					HUD.SHOW_SERVE_BTN = true;
					temp.setX(Game.WIDTH - 250);
				}
				
				if (Game.isPlaying) {
					this.currPlayerMovementKey = key;
					
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						temp.setVelocityX(-5);
					}

					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						temp.setVelocityX(5);
					}
				}
			}
			
			if (temp.id == ID.Ball) {
				if (Game.isPlaying) {
					System.out.println("Madge");
				}
				System.out.println("Foo");
			}
		}
	
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i=0; i < this.handler.objects.size(); i++) {
			GameObject temp = this.handler.objects.get(i);
			
			if (temp.id == ID.Player) {
				if (key != this.currPlayerMovementKey) {
					return;
				}
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					temp.setVelocityX(0);
				} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					temp.setVelocityX(0);
				} else {
					temp.setVelocityX(0);
				}
			}
		}
	}
}
