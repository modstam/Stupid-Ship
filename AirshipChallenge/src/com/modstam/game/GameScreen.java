package com.modstam.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.gms.games.Games;
import com.google.android.gms.internal.as;
import com.modstam.framework.Game;
import com.modstam.framework.Graphics;
import com.modstam.framework.Input.TouchEvent;
import com.modstam.framework.Screen;
import com.modstam.framework.implementation.AndroidFileIO;
import com.modstam.framework.implementation.AndroidGame;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver, Nothing
	}

	GameState state = GameState.Running;

	// Variable Setup

	public static int posx;

	//world setup
	public static boolean tilechange;
	//units	
	public static Player airship;
	public static Animation playeranim;

	//background
	private static Background bg1,bg2;


	//Paint objects
	Paint paint, paint2;

	//TileArray
	ArrayList<Tile> tileArray, tileArray1, tileArray2,obstacle, obstacle1; 


	public GameScreen(Game game) {
		super(game);

		posx = 0;
		tilechange = false;

		// Initialize game objects here
		state = GameState.Ready;

		//PLAYER SETUP
		this.playeranim = new Animation();
		playeranim.addFrame(Assets.spacehawk2, 35);
		playeranim.addFrame(Assets.spacehawk3, 35);
		playeranim.addFrame(Assets.spacehawk4, 35);
		playeranim.addFrame(Assets.spacehawk5, 35);
		Assets.player = Assets.spacehawk1;

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		airship = new Player(60, 200); // <-- ARBITRARY 

		ArrayList[] tmp = LevelGenerator.newLevel(0,false);

		tileArray = tmp[0];
		obstacle = tmp[1];

		tmp = LevelGenerator.newLevel(800,false);
		tileArray1= tmp[0];
		obstacle1 = tmp[1];
	}



	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}


	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();


		//background
		bg1.update();
		bg2.update();
		getNewTiles();
		//player
		if(len > 0 ){
			for(TouchEvent touch : touchEvents){
				if(touch.type == TouchEvent.TOUCH_DOWN){
					airship.rising = true;					


				}

				if(touch.type == TouchEvent.TOUCH_UP){
					airship.rising = false;

				}
			}
		}
		//Player
		airship.update();
		if(airship.isRising()){
			Assets.player = playeranim.getImage();
			if(Assets.SOUND){
				Assets.enginesound.play();
			}
		}else{
			Assets.player = Assets.spacehawk1;
			Assets.enginesound.stop();
		}
		animate();


		//tiles
		for(Tile tile : tileArray){
			tile.update();
		}
		for(Tile tile : tileArray1){
			tile.update();
		}
		for(Tile tile : obstacle){
			tile.update();
		}
		for(Tile tile : obstacle1){
			tile.update();
		}

	}
	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0){
			for(TouchEvent touch : touchEvents){
				if(touch.type == TouchEvent.TOUCH_DOWN){
					state = GameState.Running;
				}
			}
		}


	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						System.out.println("Resuming game");
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 480)) {
					System.out.println("Going to menu");
					//					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 600, 410 , 200 , 50)) {
					nullify();
					goToMenu();
					return;
				}
			}
		}

	}

	private void animate(){
		playeranim.update(10);
	}


	@Override
	public void paint(float deltaTime) {
		//FIRST PAINT EVERYTHING THAT IS ALWAYS NEEDED
		Graphics g = game.getGraphics();

		//Backgrounds
		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		//Tiles
		paintTiles(g);
		//player
		g.drawImage(Assets.player, airship.getPosx(), airship.getPosy());

		if (state == GameState.Ready){
			drawReadyUI();
		}
		if (state == GameState.Running){
			drawRunningUI();
		}
		if (state == GameState.Paused){ 
			drawPausedUI();		
		}
		if (state == GameState.GameOver){
			drawGameOverUI();		
		}
	}

	private void paintTiles(Graphics g) {

		//DRAW ALL THE TILES

		for(int i = 0; i < tileArray.size(); i++){
			Tile t = tileArray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
		for(int i = 0; i < tileArray1.size(); i++){
			Tile t = tileArray1.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
				//				System.out.println(t.getTileX() + Assets.width);
			}
		}
		for(Tile t : obstacle){
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
				//				System.out.println(t.getTileX() + Assets.width);
			}	
		}
		for(Tile t : obstacle1){
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
				//				System.out.println(t.getTileX() + Assets.width);
			}	
		}
		//		else if(tilechange){
		//			for(int i = 0; i < tileArray.size(); i++){
		//				Tile t = tileArray.get(i);
		//				if (t.type != 0) {
		//					g.drawImage(t.getTileImage(), t.getTileX()+Assets.width, t.getTileY());
		//				}
		//			}
		//			for(int i = 0; i < tileArray1.size(); i++){
		//				Tile t = tileArray1.get(i);
		//				if (t.type != 0) {
		//					g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
		//					//				System.out.println(t.getTileX() + Assets.width);
		//				}
		//			}
		//		}

	}

	public void getNewTiles(){
		//		System.out.println(posx);
		if(posx < -760){
			System.out.println(tilechange);
			if(!tilechange){

				ArrayList[] tmp = LevelGenerator.newLevel(800,true);

				tileArray = tmp[0];
				obstacle = tmp[1];
				//				System.out.println(tmp[1].size());

				tilechange = true;
			}
			else{

				ArrayList[] tmp = LevelGenerator.newLevel(800,true);

				tileArray1 = tmp[0];
				obstacle1 = tmp[1];
				//				System.out.println(tmp[1].size());

				tilechange = false;
			}
			posx = 0;
			//			System.out.println("Generating new tiles!");
		}
		else{
			posx += -10;
		}
	}


	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		g.drawString("Press screen to start.", 400, 360, paint);
		drawStatics();
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		drawStatics();

	}
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);
		drawStatics();

	}
	private void drawGameOverUI(){
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("GAME OVER", 400, 240, paint2);
		g.drawString("Score: " + Assets.current_score, 400, 340, paint2);
		//g.drawRect(600, 410 , 200 , 50, Color.CYAN);
		g.drawString("Press here", 700, 440, paint);

		drawStatics();

		//		g.drawString("Menu", 400, 360, paint);
	}

	private void drawStatics(){
		Graphics g = game.getGraphics();
		g.drawString("Score: "+ Assets.current_score, 150,30,paint);
		g.drawString("Best Score: "+ Assets.best_score, 550,30,paint);
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		//		nullify();
		game.setScreen(new MainMenuScreen(game));

	}


	/**
	 * Call to invoke gameOver status
	 *  
	 */
	public void gameOver(){
		state = GameState.GameOver;
		Assets.enginesound.stop();

		if(Assets.SIGNED_IN){


			if(!Assets.contents.contains("Rookie_Pilot") && Assets.best_score == 0){	
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQAQ");
				Assets.contents.add("Rookie_Pilot");
				System.out.println("Achievment gained: Rookie_Pilot");
			}

			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQAg",1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQAw", 1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQBA", 1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQBQ", 1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQBg", 1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQBw", 1);
			Assets.mHelper.getGamesClient().incrementAchievement("CgkIuJO15sEPEAIQCA", 1);

			if(!Assets.contents.contains("Tree_Fiddy") && (Assets.current_score >= 350)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQCQ");
				Assets.contents.add("Tree_Fiddy");
				System.out.println("Achievment gained: Tree_Fiddy");
			}

			if(!Assets.contents.contains("A_Faaaasand_Points") && (Assets.current_score >= 1000)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQCg");
				Assets.contents.add("A_Faaaasand_Points");
				System.out.println("Achievment gained: A_Faaaasand_Points");
			}

			if(!Assets.contents.contains("1337,_True_Elite") && (Assets.current_score == 1337)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQCw");
				Assets.contents.add("1337,_True_Elite");
				System.out.println("Achievment gained: 1337,_True_Elite");
			}

			if(!Assets.contents.contains("Are_we_there_yet?") && (Assets.current_score >= 2000)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQDA");
				Assets.contents.add("Are_we_there_yet?");
				System.out.println("Achievment gained: Are_we_there_yet?");
			}

			if(!Assets.contents.contains("Feeling_cocky_yet?") && (Assets.current_score >= 5000)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQDQ");
				Assets.contents.add("Feeling_cocky_yet?");
				System.out.println("Achievment gained: Feeling_cocky_yet?");
			}

			if(!Assets.contents.contains("Over_nine_thousand") && (Assets.current_score >= 9000)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQDg");
				Assets.contents.add("Over_nine_thousand");
				System.out.println("Achievment gained: Over_nine_thousand");
			}

			if(!Assets.contents.contains("I_AM_ROBOTRON") && (Assets.current_score >= 15000)){
				Assets.mHelper.getGamesClient().unlockAchievement("CgkIuJO15sEPEAIQDw");
				Assets.contents.add("I_AM_ROBOTRON");
				System.out.println("Achievment gained: I_AM_ROBOTRON");
			}
			Assets.mHelper.getGamesClient().submitScore("CgkIuJO15sEPEAIQEA", Assets.current_score);	
			game.saveFiles();
		}

		if(Assets.current_score > Assets.best_score){
			Assets.contents.remove("score:"+Assets.best_score);
			Assets.contents.add("score:"+Assets.current_score);
			Assets.best_score = Assets.current_score;
			game.saveFiles();

		}	





	}

	@Override
	public void pause() {

		if (state == GameState.Paused){
			state = GameState.Nothing;
			goToMenu();
		}

		else if (state == GameState.Ready){
			state = GameState.Paused;
			Assets.theme.stop();
			Assets.enginesound.stop();
		}

		else if (state == GameState.Running){
			state = GameState.Paused;
			Assets.theme.stop();
			Assets.enginesound.stop();
		}

		else if (state == GameState.GameOver){
			state = GameState.Nothing;
			goToMenu();
		}


		//		if (state != GameState.Paused && state != GameState.GameOver){
		//			state = GameState.Paused;
		//		}
		//		
		//		Assets.theme.stop();
		//		Assets.enginesound.pause();
	}	

	@Override
	public void resume() {
		if (state == GameState.Paused){
			state = GameState.Ready;
		}
		if(Assets.SOUND){
			Assets.theme.play();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		pause();

	}


	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		paint2 = null;
		bg1 = null;
		bg2 = null;
		state = GameState.Nothing;


		//world setup

		//units	
		airship = null;
		playeranim= null;

		//TileArray
		tileArray= null;
		tileArray1= null;
		tileArray2= null;
		obstacle= null;
		obstacle1 = null;


		// Call garbage collector to clean up memory.
		System.gc();

	}


}

