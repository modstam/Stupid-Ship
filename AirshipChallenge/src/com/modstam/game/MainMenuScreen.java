package com.modstam.game;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.modstam.framework.Game;
import com.modstam.framework.Graphics;
import com.modstam.framework.Screen;
import com.modstam.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen {

	Paint paint;
	Paint paint2;

	public MainMenuScreen(Game game) {
		super(game);

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(50);
		paint2.setTextAlign(Paint.Align.LEFT);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);



		if(Assets.mHelper.isSignedIn()){
			Assets.SIGNED_IN = true;
		}else{
			Assets.SIGNED_IN = false;
		}


	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		try{
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {

					//PLAY BUTTON
					if (inBounds(event,90, 90, 190, 80)) {
						Assets.gameScreen=new GameScreen(game);
						Assets.current_score = 0;
						game.setScreen(Assets.gameScreen);
					}

					//QUIT BUTTON
					else if (inBounds(event,90, 200, 190, 80)) {
						backButton();
					}

					//CHECK SIGNIN BUTTON
					else if(inBounds(event, 300, 385, 246, 54)){
						if(Assets.SIGNED_IN){
							game.playerSignOut();
							Assets.SIGNED_IN = false;
						}else{						
							game.playerSignIn();
							Assets.SIGNED_IN = true;
						}
					}

					//Leaderboards
					else if (inBounds(event,540,90,200, 70)) {
						if(Assets.mHelper.isSignedIn()){
							game.showLeaderboard();						
						}
						else{
							game.showPopup();
						}


					}
					//Achievments
					else if (inBounds(event,540,175, 200, 70)) {
						if(Assets.mHelper.isSignedIn()){
							game.showAchievements();
						}
						else{
							game.showPopup();
						}

					}


					//CHECK IF SOUND BUTTON IS PRESSED
					else if (inBounds(event, 540, 260, 200 , 70  )) {

						if(Assets.SOUND == true){
							System.out.println("Sound off");
							Assets.theme.stop();
							Assets.SOUND = false;
						}else if(Assets.SOUND == false){
							System.out.println("Sound on");
							Assets.theme.play();
							Assets.SOUND = true;						
						}

					}

				}
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Something strange happened with input handling");
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);

		//PLAY BUTTON
		//		g.drawRect(90, 90, 190, 80, Color.GREEN);
		g.drawString("PLAY", 120 , 150, paint2);
		//		
		//QUIT BUTTON
		//		g.drawRect(90, 200, 190, 80, Color.BLUE);
		g.drawString("QUIT", 120 , 260, paint2);
		//		
		//		//SOUND BUTTON
		//		g.drawRect(540, 260, 200 , 70, Color.MAGENTA);

		//LOGIN BUTTON
		//        g.drawRect(300, 385, 246, 54, Color.CYAN);

		//LEADERBOARDS BUTTON
		//        g.drawRect(540,90,200, 70, Color.RED);
		g.drawString("Leaderboards", 550 , 130, paint);

		//ACHIEVMENTS BUTTON
		//        g.drawRect(540,175, 200, 70, Color.LTGRAY);
		g.drawString("Achievements", 550 , 215, paint);

		if(Assets.mHelper.isSignedIn()){
			g.drawString("Sign out", 360, 420 , paint);
		}else{
			g.drawImage(Assets.signinbutton, 300, 385);
		}

		if(Assets.SOUND == true){
			//Drawing "Sound on" -button
			g.drawString("Sound on", 550, 305 , paint);
		}
		else if(Assets.SOUND == false){
			//Drawing "Sound off" -button
			g.drawString("Sound off", 550, 305 , paint);
		}
	}

	@Override
	public void pause() {
		Assets.theme.pause();
	}

	@Override
	public void resume() {
		if(Assets.SOUND){
			Assets.theme.play();
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		android.os.Process.killProcess(android.os.Process.myPid());

	}


}
