package com.learn.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends Canvas {

	private static final long serialVersionUID = -5308215727328528088L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		JLabel label = new JLabel("FOo");
		
		label.setLocation(50, 50);
		label.setVisible(true);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.add(label);
		
		// Add game class instance into the frame (JFrame)
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		game.start();
	}

	
}
