package com.modstam.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;

import com.modstam.framework.Screen;
import com.modstam.framework.implementation.AndroidFileIO;
import com.modstam.framework.implementation.AndroidGame;
import com.modstam.spaceshipchallenge.R;



public class StartGame extends AndroidGame  {

	public static String map;
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		InputStream is = getResources().openRawResource(R.raw.map1);
		map = convertStreamToString(is);
		

		return new SplashLoadingScreen(this);

	}
	
	public void readFiles(){
		AndroidFileIO io = new AndroidFileIO(this);
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(io.readFile("playerstats")));
			String line;
			while((line = reader.readLine()) != null){
				if(line.contains("score")){
					String[] tmp = line.split(":");
					try{
					Assets.contents.add(line);
					Assets.best_score = Integer.parseInt(tmp[1]);
					System.out.println("Loaded best_score: " + tmp[1]);
					}catch(NumberFormatException e){
						System.out.println("Couldn't parse int");
					}
				}
				else{
					Assets.contents.add(line);
					System.out.println("Loaded achievment: " + line);
				}
			}
			
			

			reader.close();
			
			System.out.println("Successfully read files");
		} catch (IOException e) {
			System.out.println("Couldn't read files");
			e.printStackTrace();
		}

	}
	
	public void saveFiles(){
		AndroidFileIO io = new AndroidFileIO(this);
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(io.writeFile("playerstats")));
//			writer.write("" + Assets.best_score);
//			System.out.println("Saved best_score: " + Assets.best_score);
			Iterator iter = Assets.contents.iterator();
			while(iter.hasNext()){
				writer.println("" + iter.next());
				writer.flush();
			}
			writer.close();
			System.out.println("Successfully saved files");
		} catch (IOException e) {
			System.out.println("Couldn't save files");
			e.printStackTrace();
		}
		

	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	@Override
	@SuppressLint("NewApi")
	public void vibrate(long duration){
		try{
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		if(v.hasVibrator()){
			v.vibrate(duration);
		}
		else{
			System.out.println("Cannot vibrate-- no vibrator available on hardware");
		}
		}catch(NoSuchMethodError e){
			System.out.println("Seems to be running an old API");
		}
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();
		if(Assets.SOUND){
		Assets.theme.play();
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		if(Assets.SOUND){
		Assets.theme.pause();
		}

	}
	


	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLeaderboard(){
		startActivityForResult(Assets.mHelper.getGamesClient().getLeaderboardIntent(
				"CgkIuJO15sEPEAIQEA"), 1);
	}
	
	@Override
	public void showAchievements(){
		startActivityForResult(Assets.mHelper.getGamesClient().getAchievementsIntent(), 1);
	}
	
	@Override
	public void showPopup(){

		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				showAlert("You need to be signed in to g+ to access this!");
			}
			
		});
	}
	
}