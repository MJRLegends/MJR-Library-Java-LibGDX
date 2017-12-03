package com.mjr.library.screens;

import java.util.ArrayList;
import java.util.List;

public class ScreenManager {

	private Screen currentScreen = null;
	
	private List<Screen> screens = new ArrayList<Screen>();
	
	public ScreenManager(){
		
	}
	
	public Screen getCurrentScreen() {
		if(currentScreen == null)
			return screens.get(0);
		else
			return currentScreen;
	}

	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}
	
	public List<Screen> getScreens(){
		return this.screens;
	}
	
	public void addScreen(Screen newScreen)
	{
		this.screens.add(newScreen);
	}
	
	public void removeScreen(Screen newScreen)
	{
		for(int i = 0; i < this.screens.size(); i++){
			if(this.screens.get(i) == newScreen)
				this.screens.remove(i);
		}
	}
}
