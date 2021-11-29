package com.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JPanel;

public class BallPanel extends JPanel implements KeyListener {

	private static final int REFRESH_RATE = 60;
	private List<Ball> balls;
	private BallArea box;
	private int areaWidth;
	private int areaHeight;
	private static Random rand = new Random();
	public int radius;
	public int speed;
	static Color[] ballColor = {new Color(95, 75, 139), new Color(251, 204, 209), new Color(0, 178, 202), new Color(51, 41, 33), new Color(46, 86, 46)};
	
	
	private static int generateAngleInDegree() {
		return rand.nextInt(360);
	}
	
	private void createBall(int radius, int speed, char character) {
		int x = rand.nextInt(this.areaWidth - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(this.areaHeight - radius * 2 - 20) + radius + 10;
		
		balls.add(new Ball(x, y, radius, speed, generateAngleInDegree(), ballColor[balls.size() % 5], character));
	}
	public BallPanel(int width, int height) {
		
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		radius = 30;
		speed = 3;
		

		balls = new ArrayList<Ball>();
		box = new BallArea(0, 0, width, height, new Color(240, 238, 233), Color.WHITE);
		
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
		this.addKeyListener(this);
		this.setFocusable(true);
		startThread();
	}

	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					
					for (int i = 0; i < balls.size(); i++) {
						balls.get(i).collide(box);
						for (int j = 0; j < balls.size(); j++) {
							if(i == j) {
								continue;
							}
							balls.get(i).collide(balls.get(j));
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
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).draw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		boolean isCorrect = Pattern.matches("[A-Z]", String.valueOf(c)) || Pattern.matches("[0-9]", String.valueOf(c));
		if(isCorrect) {
			createBall(radius, speed, c);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
