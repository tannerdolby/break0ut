package com.learn.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


// Single threaded game (not recommended using a single thread but good practice)
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// Specify dimensions at a specific aspect ratio
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Handler handler;
	
	private Random random;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput());

		new Window(WIDTH, HEIGHT, "Mouser", this);
		
		random = new Random();
		
//		for (int i=0; i < 25; i++) {
//			handler.addObject(new Player(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.Player));
//		}
		
//		Player p = new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player);
//		p.addMouseListener(new MouseInput());
		
		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join(); // Kill off the thread and stop it
			isRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	// game loop logic- not something I created, its a commonly used game loop equation
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				// tick
				tick();
				delta--;
			}
			
			if (isRunning) {
				render();
			}
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		// define the mechanism for handling complex memory (buffers/surfaces) on the Window
		BufferStrategy bs = this.getBufferStrategy();
		// if the buffer strategy is null, create a new buffer strategy for 3 buffers
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		// create a graphics context for the drawing buffer
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// utilize the handler to render GameObject's
		handler.render(g);
		
		g.dispose();
		// show the next available buffer
		bs.show();
	}


	public static void main(String[] args) {
		new Game();
	}

}
