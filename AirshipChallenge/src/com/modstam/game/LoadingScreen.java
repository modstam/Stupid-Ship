package com.modstam.game;


import java.util.ArrayList;
import java.util.HashSet;

import com.modstam.framework.Game;
import com.modstam.framework.Graphics;
import com.modstam.framework.Graphics.ImageFormat;
import com.modstam.framework.Image;
import com.modstam.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu/mainmenu.png", ImageFormat.RGB565);
//		Assets.pause = g.newImage("menu/PauseScreen.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background/backgroundSpace.png", ImageFormat.RGB565);
		
		//PLAYER SETUP
		Assets.player = g.newImage("player/spacehawk1.png", ImageFormat.ARGB4444);
		Assets.spacehawk1 = g.newImage("player/spacehawk1.png", ImageFormat.ARGB4444);
		Assets.spacehawk2 = g.newImage("player/spacehawk2.png", ImageFormat.ARGB4444);
		Assets.spacehawk3 = g.newImage("player/spacehawk3.png", ImageFormat.ARGB4444);
		Assets.spacehawk4 = g.newImage("player/spacehawk4.png", ImageFormat.ARGB4444);
		Assets.spacehawk5 = g.newImage("player/spacehawk5.png", ImageFormat.ARGB4444);
		
		//BUTTONS
		Assets.signinbutton = g.newImage("button/white.png", ImageFormat.ARGB4444);
		
		
//		Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
//		Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
//		Assets.character3  = g.newImage("character3.png", ImageFormat.ARGB4444);
//		Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
//		Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);
//
//		
//		Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
//		Assets.heliboy2 = g.newImage("heliboy2.png", ImageFormat.ARGB4444);
//		Assets.heliboy3  = g.newImage("heliboy3.png", ImageFormat.ARGB4444);
//		Assets.heliboy4  = g.newImage("heliboy4.png", ImageFormat.ARGB4444);
//		Assets.heliboy5  = g.newImage("heliboy5.png", ImageFormat.ARGB4444);
//
//
//		
		//TILE SETUP
//		Assets.tilelimesmall = g.newImage("tile/limesmall.png", ImageFormat.RGB565);
//		Assets.tilelimelong = g.newImage("tile/limelong.png", ImageFormat.RGB565);
//		Assets.tilelimelarge = g.newImage("tile/limelarge.png", ImageFormat.RGB565);
//		
		Assets.mine1 = g.newImage("tile/mine1.png", ImageFormat.RGB565);
		Assets.mine2 = g.newImage("tile/mine2.png", ImageFormat.RGB565);
		Assets.mine3 = g.newImage("tile/mine3.png", ImageFormat.RGB565);
		Assets.mine4 = g.newImage("tile/mine4.png", ImageFormat.RGB565);
		Assets.mine5 = g.newImage("tile/mine5.png", ImageFormat.RGB565);
		Assets.mine6 = g.newImage("tile/mine6.png", ImageFormat.RGB565);
		
		Assets.metallic1 = g.newImage("tile/metallic1.png", ImageFormat.RGB565);
		Assets.metallic2 = g.newImage("tile/metallic2.png", ImageFormat.RGB565);
		Assets.metallic3 = g.newImage("tile/metallic3.png", ImageFormat.RGB565);
		Assets.metallic4 = g.newImage("tile/metallic4.png", ImageFormat.RGB565);
		Assets.metallic5 = g.newImage("tile/metallic5.png", ImageFormat.RGB565);
		Assets.metallic6 = g.newImage("tile/metallic6.png", ImageFormat.RGB565);
		Assets.metallic7 = g.newImage("tile/metallic7.png", ImageFormat.RGB565);
		Assets.metallic8 = g.newImage("tile/metallic8.png", ImageFormat.RGB565);
		Assets.metallic9 = g.newImage("tile/metallic9.png", ImageFormat.RGB565);
		Assets.metallic10 = g.newImage("tile/metallic10.png", ImageFormat.RGB565);
		Assets.metallic11 = g.newImage("tile/metallic11.png", ImageFormat.RGB565);
		Assets.metallic12 = g.newImage("tile/metallic12.png", ImageFormat.RGB565);
		Assets.metallic13 = g.newImage("tile/metallic13.png", ImageFormat.RGB565);
		Assets.metallic14 = g.newImage("tile/metallic14.png", ImageFormat.RGB565);
		Assets.metallic15 = g.newImage("tile/metallic15.png", ImageFormat.RGB565);
		Assets.metallic16 = g.newImage("tile/metallic16.png", ImageFormat.RGB565);
		Assets.metallic17 = g.newImage("tile/metallic17.png", ImageFormat.RGB565);
		Assets.metallic18 = g.newImage("tile/metallic18.png", ImageFormat.RGB565);
		Assets.metallic19 = g.newImage("tile/metallic19.png", ImageFormat.RGB565);
		Assets.metallic20 = g.newImage("tile/metallic20.png", ImageFormat.RGB565);
		
		Assets.metallicTiles = new ArrayList<Image>();
		
		Assets.metallicTiles.add(Assets.metallic1);
		Assets.metallicTiles.add(Assets.metallic2);
		Assets.metallicTiles.add(Assets.metallic3);
		Assets.metallicTiles.add(Assets.metallic4);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic6);
		Assets.metallicTiles.add(Assets.metallic7);
		Assets.metallicTiles.add(Assets.metallic8);
		Assets.metallicTiles.add(Assets.metallic9);
		Assets.metallicTiles.add(Assets.metallic10);
		Assets.metallicTiles.add(Assets.metallic11);
		Assets.metallicTiles.add(Assets.metallic12);
		Assets.metallicTiles.add(Assets.metallic13);
		Assets.metallicTiles.add(Assets.metallic14);
		Assets.metallicTiles.add(Assets.metallic15);
		Assets.metallicTiles.add(Assets.metallic16);
		Assets.metallicTiles.add(Assets.metallic17);
		Assets.metallicTiles.add(Assets.metallic18);
		Assets.metallicTiles.add(Assets.metallic19);
		Assets.metallicTiles.add(Assets.metallic20);
		
		
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		Assets.metallicTiles.add(Assets.metallic5);
		
		Assets.metallicTiles.add(Assets.metallic6);
		Assets.metallicTiles.add(Assets.metallic6);
		Assets.metallicTiles.add(Assets.metallic6);

//		Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);

		//This is how you would load a sound if you had one.
		//Assets.click = game.getAudio().createSound("explode.ogg");
		Assets.contents = new HashSet<String>();
		game.readFiles();
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		Assets.splash = null;
	}

	@Override
	public void backButton() {

	}
}