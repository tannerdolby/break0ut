package com.learn.main;

import java.awt.Graphics;
import java.util.LinkedList;

// Handle the process of update and render all
// of our game objects to the screen
public class Handler {

	LinkedList<GameObject> objects = new LinkedList<>();
	
	/**
	 * Iterate the list of GameObject's and start game ticks.
	 */
	public void tick() {
		for (int i=0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
	}

	/**
	 * Iterate the list of GameObject's and render passed in graphics.
	 * @param g an instance of the Graphics class
	 */
	public void render(Graphics g) {
		for (int i=0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
}
