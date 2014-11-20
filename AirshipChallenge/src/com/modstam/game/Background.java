package com.modstam.game;

public class Background {
	
	private int bgX, bgY;
	private static int speedX;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = -5;
	}
	
	public void update() {
		bgX += speedX;

		if (bgX <= -2160){
			bgX += 4320;
			System.out.println("Changing background");
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public static int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	
	
	
}
