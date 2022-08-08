package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 487, HEIGHT = WIDTH / 10 * 12;
	
	public static HashMap<ID, Integer> colorBlocks = new HashMap<>();
	
	private Thread thread;
	private boolean isRunning;
	
	private Handler handler;
	private HUD hud;
	private Random r;
	
	private int coloredBlockOrigin = 0;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		r = new Random();
		
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "break0ut", this);
		
		colorBlocks.put(ID.YellowBlock, 1);
		colorBlocks.put(ID.GreenBlock, 3);
		colorBlocks.put(ID.OrangeBlock, 5);
		colorBlocks.put(ID.RedBlock, 7);
		
		handler.addObject(new Player(WIDTH/2-64, HEIGHT - 80, ID.Player, "player1", handler));
		handler.addObject(new Ball(WIDTH/2-64, 145, ID.Ball, "gameball", handler));
		
		// TODO: this needs some fine tuning for more snug placement
		// Create the colored blocks consisting of 8 rows, each with 14 blocks (4 colors with 2 rows each)
		for (int i=0; i < 14; i++) {
			coloredBlockOrigin = ColorBlock.GAP * i;
			handler.addObject(new ColorBlock(coloredBlockOrigin, 70, ID.RedBlock, "red-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 80, ID.RedBlock, "red-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 90, ID.OrangeBlock, "orange-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 100, ID.OrangeBlock, "orange-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 110, ID.GreenBlock, "yellow-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 120, ID.GreenBlock, "yellow-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 130, ID.YellowBlock, "green-" + i, handler));
			handler.addObject(new ColorBlock(coloredBlockOrigin, 140, ID.YellowBlock, "green-" + i, handler));
		}
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			isRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		this.requestFocus();
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
	
	public void tick() {
		handler.tick();
		hud.tick();
	}
	
	public void render() {
		
		// get the buffer strategy used by this game component
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			// create a new buffer strategy with 3 buffers
			this.createBufferStrategy(3);
			return;
		}
		
		// Create a Graphics Context for the drawing buffer
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// render graphics using the handler
		handler.render(g);
		
		hud.render(g);
		
		// dispose of graphics context and release any system resources its using
		g.dispose();
		
		// use buffer strategy to make the next available buffer visible
		bs.show();
		
	}
	
	public static int clamp(int v, int min, int max) {
		if (v <= min) {
			return min;
		} else if (v >= max) {
			return max;
		} else {
			return v;
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
