package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball {
	float x, y;
	float speedX, speedY;
	float radius;
	private Color color;
	char c;
	Font font = new Font ("Comic Sans MS", Font.BOLD, 40);

	public Ball(float x, float y, float radius, float speed, float angleInDegree, Color color, char c) {
		this.x = x;
		this.y = y;
		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (speed * Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		this.c = c;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
		g.setColor(Color.WHITE);
		g.setFont(font);
		int cX = ((int) x) - (g.getFontMetrics().stringWidth(String.valueOf(c)) / 2);
		int cY = ((int) y) + (g.getFontMetrics().getHeight() / 4);
		g.drawString(String.valueOf(this.c), cX, cY);
	}

	public void collide(BallArea box) {
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;

		x += speedX;
		y += speedY;

		if (x < ballMinX) {
			speedX = -speedX;
			x = ballMinX;
		} else if (x > ballMaxX) {
			speedX = -speedX;
			x = ballMaxX;
		}

		if (y < ballMinY) {
			speedY = -speedY;
			y = ballMinY;
		} else if (y > ballMaxY) {
			speedY = -speedY;
			y = ballMaxY;
		}
	}

	public void collide(Ball ball) {

		int distX = (int) (ball.x - x);
		int distY = (int) (ball.y - y);
		double distSquared = Math.pow(distX, 2) + Math.pow(distY, 2);

		if (distSquared <= Math.pow(ball.radius + radius, 2)) {
			if (distSquared < Math.pow(ball.radius + radius, 2)) {
				double sinT = distY / Math.sqrt(distSquared);
				double cosT = distX / Math.sqrt(distSquared);
				x = ball.x - (float) (cosT * (radius + ball.radius));
				y = ball.y - (float) (sinT * (radius + ball.radius));
			}
			
			float tempSpeedX = speedX;
			float tempSpeedY = speedY;
			speedX = ball.speedX;
			speedY = ball.speedY;
			ball.speedX = tempSpeedX;
			ball.speedY = tempSpeedY;

		}

	}

}
