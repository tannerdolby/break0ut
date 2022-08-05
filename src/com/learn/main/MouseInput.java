package com.learn.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public void mouseClicked(MouseEvent e) {
//		System.out.println(e.getComponent());
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getComponent());
		System.out.println(e.getX() + ", " + e.getY());
		
	}
	
	public void isMouseReleased(MouseEvent e) {
		
	}
}
