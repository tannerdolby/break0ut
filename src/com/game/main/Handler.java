package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> objects = new LinkedList<>();

	/**
	 * Iterate the list of GameObject's and start game ticks.
	 */
	public void tick() {
		// execute/update the objects
		for (int i=0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.tick();
		}
	}
	
	/**
	 * Iterate the list of GameObject's and render passed in graphics.
	 * @param g an instance of the Graphics class representing a GameObject
	 */
	public void render(Graphics g) {
		for (int i=0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
}
