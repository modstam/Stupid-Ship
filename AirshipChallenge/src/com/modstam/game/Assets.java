package com.modstam.game;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.example.games.basegameutils.GameHelper;
import com.modstam.framework.Game;
import com.modstam.framework.Image;
import com.modstam.framework.Music;

public class Assets {

	//MENU ITEMS
	public static Image menu, splash, pause;

	//GAME ASSETS
	public static int TILE_SIZE = 10;
	public static Image background, player; //, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	public static Image tilelimesmall,tilelimelong,tilelimelarge;
	public static Image mine1,mine2,mine3,mine4,mine5,mine6;
	public static Image metallic1,metallic2,metallic3,metallic4,metallic5,metallic6,metallic7,metallic8,metallic9,metallic10,metallic11,metallic12,metallic13,metallic14,metallic15
	,metallic16,metallic17,metallic18,metallic19,metallic20;
	public static Image spacehawk1, spacehawk2, spacehawk3, spacehawk4, spacehawk5;
	public static ArrayList<Image> metallicTiles;
	//	public static Image button;

	//SOUND ASSETS
	//	public static Sound click;
	public static Music theme;
	public static String music = "music/deepTheme.ogg";
	public static boolean SOUND = false;
	public static Music enginesound;
	public static String engine = "sound/130496__red3zzz__rocket-engine.ogg";

	//SCREEN ASSETS
	public static GameScreen gameScreen;
	
	//BUTTONS
	public static Image signinbutton;


	//RESOLUTION
	public static int width = 800;
	public static int height = 480;	

	//DEBUG	
	public static String DEBUG_MESSAGE = "";
	
	//GAME
	public static Game supergame; 
	
	//STATISTICS
	public static int current_score;
	public static int best_score;
	
	//SIGNING
	public static boolean SIGNED_IN;
	
	//ACHIEVMENTS
	public boolean Rookie_Pilot;
	public boolean Apprentice_Pilot;
	public boolean Getting_the_hang_of_it;
	public boolean Talent_Rising;
	public boolean Sore_Thumbs;
	public boolean Hardened_Thumbs;
	public boolean Fortified_Stamina;
	public boolean Ace_Pilot;
	public boolean Tree_Fiddy;
	public boolean A_Faaaasand_Points;
	public boolean True_elite;
	public boolean Are_we_there_yet;
	public boolean Feeling_cocky_yet;
	public boolean Over_nine_thousand;
	public boolean I_AM_ROBOTRON;
	
	//HASHMAP
	public static HashSet<String> contents;

	public static GameHelper mHelper;

	public static void load(StartGame startup) {
		// TODO Auto-generated method stub
		theme = startup.getAudio().createMusic(music);
		theme.setLooping(true);
		theme.setVolume(0.85f);
		if(SOUND == true){		
			theme.play();
		}
		best_score = 0;
		
		enginesound = startup.getAudio().createMusic(engine);
		enginesound.setLooping(true);
		enginesound.setVolume(1.00f);
	}

}
