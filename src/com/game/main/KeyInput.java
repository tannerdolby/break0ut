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
			
			if (temp.id == ID.Player) {
				this.currPlayerMovementKey = key;
				
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					temp.setVelocityX(-5);
				}
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					temp.setVelocityX(5);
				}
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
				}
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					temp.setVelocityX(0);
				}
			}
		}
	}
}
