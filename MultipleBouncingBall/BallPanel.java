package com.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

import javax.swing.JPanel;

public class BallPanel extends JPanel {

	private static final int REFRESH_RATE = 60;
	private Ball[] balls;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	private static Random rand = new Random();
	
	private static int generateAngleInDegree() {
		return rand.nextInt(360);
	}
	
	// Avoid overlapping at start
	private static boolean isOverlapping(int x1, int y1, int x2, int y2, int radius) {
		int distX = x2 - x1;
		int distY = y2 - y1;
		double distSquared = Math.pow(distX, 2) + Math.pow(distY, 2) ;
		
		if(distSquared <= Math.pow(radius * 2,2)) {
			return true;
		}
		return false;
	}
	public BallPanel(int width, int height) {
		
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		int radius = 50;
		int x, y;
		int lastX = 0, lastY = 0;
		int speed = 8;
		Color[] ballColor = {new Color(95, 75, 139), new Color(251, 204, 209), new Color(0, 178, 202), new Color(51, 41, 33), new Color(46, 86, 46)};

		balls = new Ball[5];
		for (int i = 0; i < balls.length; i++) {
			x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
			y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
			if(i != 0) {
				while(isOverlapping(lastX, lastY, x, y, radius)) {
					x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
				}
			}
			lastX = x;
			lastY = y;
			balls[i] = new Ball(x, y, radius, speed, generateAngleInDegree(), ballColor[i]);
		}
		box = new BallArea(0, 0, width, height, new Color(240, 238, 233), Color.WHITE);
		// untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, width, height);
			}
		});
		startThread();
	}

	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < balls.length; i++) {
						balls[i].collide(box);
						for (int j = 0; j < balls.length; j++) {
							if(i == j) {
								continue;
							}
							balls[i].collide(balls[j]);
						}
					}
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		gameThread.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		box.draw(g);
		for (int i = 0; i < balls.length; i++) {
			balls[i].draw(g);
		}
	}

}
