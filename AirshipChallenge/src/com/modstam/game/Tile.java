package com.modstam.game;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Vibrator;

import com.modstam.framework.Image;

public class Tile {

	private int tileX, tileY; 
	private int speedX = -10;
	public int type;
	public Image tileImage;
	private int TILE_WIDTH;
	private int TILE_HEIGHT;
	private Animation tileAnim;
	private Random dice = new Random();
	
	private Rect tilebox;
	
	
	private Player airship = GameScreen.airship;

	public Tile(int x, int y, int typeInt) {
		tileX = x;
		tileY = y;
		this.type = typeInt;
		

		if(this.type == 1){
			tileImage = Assets.tilelimesmall;
			TILE_HEIGHT= 10;
			TILE_WIDTH = 10;
		}
		if(this.type == 2){
			TILE_HEIGHT= 10;
			TILE_WIDTH = 100;
			tileImage = Assets.tilelimelong;
		}
		if(this.type == 3){
			TILE_HEIGHT= 40;
			TILE_WIDTH = 40;
			tileImage = Assets.tilelimelarge;
		}
		if(this.type == 4){
			tileAnim = new Animation();
			tileAnim.addFrame(Assets.mine1, 40);
			tileAnim.addFrame(Assets.mine2, 40);
			tileAnim.addFrame(Assets.mine3, 40);
			tileAnim.addFrame(Assets.mine4, 40);
			tileAnim.addFrame(Assets.mine5, 40);
			tileAnim.addFrame(Assets.mine6, 40);
			
			tileAnim.addFrame(Assets.mine5, 40);
			tileAnim.addFrame(Assets.mine4, 40);
			tileAnim.addFrame(Assets.mine3, 40);
			tileAnim.addFrame(Assets.mine2, 40);

			
			TILE_HEIGHT= 40;
			TILE_WIDTH = 40;
			tileImage = tileAnim.getImage();
		}
		
		if(this.type == 5){
			TILE_HEIGHT= 10;
			TILE_WIDTH = 100;
			tileImage = Assets.metallicTiles.get(dice.nextInt(Assets.metallicTiles.size()));
		}
		else if(this.type == 0){
			this.type = 0;
		}
		
		tilebox = new Rect(tileX, tileY, tileX+TILE_WIDTH, tileY+TILE_HEIGHT);
	}

	public void update() {
//		speedX = Background.getSpeedX();		
		tileX += speedX;
		tilebox.set(tileX, tileY, tileX+TILE_WIDTH, tileY+TILE_HEIGHT);
		
		if(type == 4){
			animate();
			tileImage = tileAnim.getImage();
		}

		if (Rect.intersects(tilebox, airship.playerbox) && type != 0) {


			checkCollision(airship.playerbox);
			//			checkVerticalCollision(Robot.rect, Robot.rect2);
			//			checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
		}
	}
	
	public void animate(){
		tileAnim.update(10);
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	@SuppressLint("NewApi")
	public void checkCollision(Rect hitbox){
		if(Rect.intersects(hitbox, tilebox)){
			//TEMP
//			airship.setSpeedy(0);
//			if(airship.getPosy() >= tileY){
//				airship.setPosy(tileY + 40);
//			}else{
//				airship.setPosy(tileY - 100);
//			}
			Assets.supergame.vibrate(200);
			
//			System.out.println("COLLISION!");
			
			Assets.gameScreen.gameOver();
		}
	}
	
}
