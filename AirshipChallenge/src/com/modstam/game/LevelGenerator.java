package com.modstam.game;

import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator {

	public static int MAX_GENERATE_X = 800;
	public static int MAX_GENERATE_Y = 480;

	public static int LIMELARGE_WIDTH = 40;
	public static int LIMELARGE_HEIGHT= 40;

	public static int LIMELONG_WIDTH = 100;
	public static int LIMELONG_HEIGHT = 10;
	public static int MAX_TERRAIN_HEIGHT = 10;

	public LevelGenerator() {
		// TODO Auto-generated constructor stub
	}


	public static ArrayList[] newLevel(int offset, Boolean obstacle){
		ArrayList<Tile> returnList = new ArrayList<Tile>();
		Random dice = new Random();
		
		//		System.out.println(obstacleX + ", " + obstacleY);

		//MAKING OUTLINES
		for(int tileY = 0; tileY < MAX_GENERATE_Y; tileY = tileY + LIMELONG_HEIGHT){			
			for(int tileX = offset; tileX < (MAX_GENERATE_X+offset); tileX = tileX + LIMELONG_WIDTH){

				if((tileY == 0) || (tileY == MAX_GENERATE_Y-LIMELONG_HEIGHT )){
					returnList.add(new Tile(tileX,tileY,5));
				}

			}			

		}
		
		

		//DOING HEIGHTS
		int terrainheight = 0;
		for(int tileX = offset; tileX < (MAX_GENERATE_X+offset); tileX = tileX + LIMELONG_WIDTH){

			terrainheight = dice.nextInt(7);
			for(int i = 0; i <= MAX_TERRAIN_HEIGHT; i++){
				if(i<=terrainheight){
					returnList.add(new Tile(tileX,0 + (i*LIMELONG_HEIGHT) ,5));
				}
				else if(i > terrainheight){
					returnList.add(new Tile(tileX,MAX_GENERATE_Y-LIMELONG_HEIGHT - ((i-terrainheight)*LIMELONG_HEIGHT),5));
				}
			}

		}
		
		
		//OBSTACLES
		ArrayList<Tile> obstacles = new ArrayList<Tile>();
		if(obstacle){
//		int obstacleY = (dice.nextInt((MAX_GENERATE_Y/LIMELARGE_WIDTH))*LIMELARGE_HEIGHT);
//		int obstacleX = (dice.nextInt(((MAX_GENERATE_X)/LIMELARGE_HEIGHT))*LIMELARGE_HEIGHT)+MAX_GENERATE_X;
		int obstacleY = (dice.nextInt((MAX_GENERATE_Y/LIMELARGE_HEIGHT)-4)*LIMELARGE_HEIGHT) + (2*LIMELARGE_HEIGHT);
		int obstacleX = (MAX_GENERATE_X + offset)/2; 
			obstacles.add(new Tile(obstacleX, obstacleY,4));
			obstacles.add(new Tile(obstacleX, obstacleY+40,4));
			obstacles.add(new Tile(obstacleX, obstacleY+80,4));
		}
		
		ArrayList[] returner = {returnList, obstacles};

		return returner;
	}

}
