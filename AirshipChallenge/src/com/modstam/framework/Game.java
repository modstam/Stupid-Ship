package com.modstam.framework;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.GamesClient;

public interface Game {

    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
    
    public void vibrate(long duration);
    
    public void readFiles();
    
    public void saveFiles();
    
    public void playerSignIn();
    
    public void playerSignOut();

	public GoogleApiClient getApiClient();

	public GamesClient getGamesClient();

	public void showLeaderboard();

	void showAchievements();

	void showPopup();
}