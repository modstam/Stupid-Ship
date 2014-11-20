package com.modstam.game;

import com.modstam.framework.Game;
import com.modstam.framework.Graphics;
import com.modstam.framework.Screen;
import com.modstam.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	
	
	public SplashLoadingScreen(Game game) {
		super(game);
		Assets.supergame = game;
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash= g.newImage("menu/Splash2.jpg", ImageFormat.RGB565);

		
		game.setScreen(new LoadingScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}