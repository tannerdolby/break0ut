package com.learn.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);

		// TODO: Better randomize movement vectors for more dispersion, fun game idea, random square/object movement
		// and user has to click on the squares that have a certain marking (star, color, etc) clicking the correctly
		// marked squares increments points/score
//		velX = r.nextInt(3) + 1;
//		velY = r.nextInt(3);
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
	}

	public void addMouseListener(MouseAdapter m) {
		// TODO Auto-generated method stub
//		System.out.println("HERE" + m.mouseClicked(e););
		
		System.out.println("HERE");
		
	}

}
