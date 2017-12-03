package com.mjr.library.screens;

import java.util.ArrayList;
import java.util.List;

import com.mjr.library.gui.GUIComponent;

public abstract class Screen {
	
	private String screenName = "";
	private List<GUIComponent> guiComponents = new ArrayList<GUIComponent>();
	
	public Screen(String screenName){
		this.screenName = screenName;
	}
	
	public String getScreenName() {
		return screenName;
	}
	
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public List<GUIComponent> getGUIComponents(){
		return this.guiComponents;
	}
	
	public void addGUIComponent(GUIComponent guiComponents)
	{
		this.guiComponents.add(guiComponents);
	}
	
	public void removeGUIComponent(GUIComponent guiComponents)
	{
		for(int i = 0; i < this.guiComponents.size(); i++){
			if(this.guiComponents.get(i) == guiComponents)
				this.guiComponents.remove(i);
		}
	}
	
	public void updateGUIComponents(){
		for(int i = 0; i < this.guiComponents.size(); i++){
			this.guiComponents.get(i).update();
		}
	}
	
	public void renderGUIComponents(){
		for(int i = 0; i < this.guiComponents.size(); i++){
			this.guiComponents.get(i).render();
		}
	}
	
	public void disposeGUIComponents(){
		for(int i = 0; i < this.guiComponents.size(); i++){
			this.guiComponents.get(i).dispose();
		}
	}
	
	public abstract void render();
	public abstract void update();
	public abstract void dispose();
}
