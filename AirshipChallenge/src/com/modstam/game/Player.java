package com.modstam.game;

import android.graphics.Rect;

public class Player {

	private int posx;
	private int posy;

	public static int speedx;
	public static int speedy;
	public static int fallspeed = 5;
	public static int risespeed = -5;
	public static int risebuffer = 0;
	public static int fallbuffer = 0;

	public static boolean rising = false;

	public static Rect playerbox;


	public Player(int x, int y) {
		// TODO Auto-generated constructor stub
		this.posx = x;
		this.posy = y;
		speedx = 0;
		speedy = fallspeed;


		playerbox = new Rect(posx, posy, posx + 40, posy + 40);
	}



	public void update(){

		posy += speedy;

		if(rising){
			fallbuffer = 0;
			if(risebuffer < 5){
				speedy = 1;
			}
//			else if(risebuffer < 10){
//				speedy = 0;
//			}
//			else if(risebuffer < 15){
//				speedy = -1;
//			}
//			else if(risebuffer < 20){
//				speedy = -2;
//			}
			else{
				speedy = risespeed;
			}
			risebuffer ++;
		}
		else if(!rising){
			risebuffer = 0;
			if(fallbuffer < 2){
				speedy = -1;
			}
			else if(fallbuffer < 5){
				speedy = 0;
			}
			else if(fallbuffer < 7){
				speedy = 1;
			}
			else{
			speedy = fallspeed+1;
			}
			fallbuffer ++;
		}

		Assets.current_score += 1;

		playerbox.set(posx+80, posy+10, posx + 200, posy + 50);
	}



	public int getPosx() {
		return posx;
	}



	public void setPosx(int posx) {
		this.posx = posx;
	}



	public int getPosy() {
		return posy;
	}



	public void setPosy(int posy) {
		this.posy = posy;
	}



	public int getSpeedx() {
		return speedx;
	}



	public void setSpeedx(int speedx) {
		this.speedx = speedx;
	}



	public int getSpeedy() {
		return speedy;
	}



	public void setSpeedy(int speedy) {
		this.speedy = speedy;
	}



	public boolean isRising() {
		return rising;
	}



	public void setRising(boolean rising) {
		this.rising = rising;
	}

}
