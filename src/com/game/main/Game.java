package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 487, HEIGHT = WIDTH / 10 * 12;
	public static HashMap<ID, Integer> colorBlocks = new HashMap<>();
	public static int FPS = 0;
	private Thread thread;
	private boolean isRunning;
	private Handler handler;
	public static boolean isStartingScreen = true;
	public static boolean isPlaying = false;
	private HUD hud;
	private Random r;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		r = new Random();
		
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Breakout", this);
		
		colorBlocks.put(ID.YellowBlock, 1);
		colorBlocks.put(ID.GreenBlock, 3);
		colorBlocks.put(ID.OrangeBlock, 5);
		colorBlocks.put(ID.RedBlock, 7);
		
		// TODO: Loading Screen
		// Player paddle is 100% width and the ball is bouncing in the playable area
		// but not breaking any blocks
		// Enter will start the match and serve the first ball
		handler.addObject(new Player(0, HEIGHT - 110, WIDTH, 10, ID.Player, "player1", handler));
		handler.addObject(new Ball(WIDTH/2-200, 150, ID.Ball, "gameball", handler));
		
		int maxPossibleScore = ((1*14)*2) + ((3*14)*2) + ((5*14)*2) + ((7*14)*2);
//		System.out.println("MAX POSSIBLE SCORE: " + Integer.toString(maxPossibleScore));
		
		// Create the colored block game objects consisting of 8 rows, each with 14 blocks (4 colors with 2 rows each)
		this.renderColorBlocks(handler);
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

	// game loop
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
				tick();
				delta--;
			}
			
			if (isRunning) {
				render();
			}
			
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				FPS = frames;
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
		
		g.setFont(new Font("Helvetica", Font.PLAIN, 30));
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// render graphics using the game object handler
		handler.render(g);
		
		hud.render(g);
		
		// dispose of graphics context and release any system resources its using
		g.dispose();
		
		// use buffer strategy to make the next available buffer visible
		bs.show();
	}
	
	public void renderColorBlocks(Handler handler) {
		int x = 0;
		
		for (int i=0; i < 14; i++) {
			x = ColorBlock.GAP * i;
			handler.addObject(new ColorBlock(x, 65, ID.RedBlock, "red-" + i, handler));
			handler.addObject(new ColorBlock(x, 74, ID.RedBlock, "red-" + i, handler));
			handler.addObject(new ColorBlock(x, 83, ID.OrangeBlock, "orange-" + i, handler));
			handler.addObject(new ColorBlock(x, 92, ID.OrangeBlock, "orange-" + i, handler));
			handler.addObject(new ColorBlock(x, 101, ID.GreenBlock, "green-" + i, handler));
			handler.addObject(new ColorBlock(x, 110, ID.GreenBlock, "green-" + i, handler));
			handler.addObject(new ColorBlock(x, 119, ID.YellowBlock, "yellow-" + i, handler));
			handler.addObject(new ColorBlock(x, 128, ID.YellowBlock, "yellow-" + i, handler));
		}
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
}
