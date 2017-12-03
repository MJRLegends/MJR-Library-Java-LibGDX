package com.mjr.library.gui;

import com.badlogic.gdx.graphics.Color;
import com.mjr.library.graphics.DrawManager2D;

public class GUIButton extends GUIComponent{

	private String text = "";
	private boolean selected = false;
	private boolean enabled = false;
	private Color textColour = null;
	private Color buttonColour = null;

	public GUIButton(int id, int x, int y, int width, int height, Color textColour, Color buttonColour, String text) {
		super(id, x, y, width, height);
		this.setTextColour(textColour);
		this.setButtonColour(buttonColour);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Color getTextColour() {
		return textColour;
	}

	public void setTextColour(Color textColour) {
		this.textColour = textColour;
	}

	public Color getButtonColour() {
		return buttonColour;
	}

	public void setButtonColour(Color buttonColour) {
		this.buttonColour = buttonColour;
	}

	@Override
	public void render() {
		DrawManager2D.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getButtonColour(), true);
		DrawManager2D.drawString(this.getX() + this.getWidth() / 2, this.getY() + (this.getHeight() / 2), this.text, 0.2f, this.getTextColour(), true);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void dispose() {
		
	}

}
