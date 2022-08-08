package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected ID id;
	protected int x,y;
	protected int velX, velY;
	protected String name;
	
	public GameObject(int x, int y, ID id, String name) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.name = name;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return this.id;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setVelocityX(int velX) {
		this.velX = velX;
	}
	
	public int getVelocityX() {
		return this.velX;
	}
	
	public void setVelocityY(int velY) {
		this.velY = velY;
	}
	
	public int getVelocityY() {
		return this.velY;
	}
	
}
