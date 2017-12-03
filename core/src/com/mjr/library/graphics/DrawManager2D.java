package com.mjr.library.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DrawManager2D {
	private static SpriteBatch batch = new SpriteBatch();
	private static ShapeRenderer shape = new ShapeRenderer();
	private static OrthographicCamera camera;

	public DrawManager2D() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
	}

	public static void updateCamera() {
		batch.setProjectionMatrix(camera.combined);
	}

	public static void drawTextured(float x, float y, Texture texture) {
		batch.enableBlending();
		batch.begin();
		batch.draw(texture, x, y);
		batch.end();
	}

	public static void drawTextured(float x, float y, float width,
			float height, Texture texture) {
		batch.enableBlending();
		batch.begin();
		batch.draw(new TextureRegion(texture), x, y, width / 2, height / 2,
				width, height, 1, 1, 0);
		batch.end();
	}

	public static void drawTextured(float x, float y, Texture texture, float deg) {
		batch.enableBlending();
		batch.begin();
		batch.draw(new TextureRegion(texture), x, y, 16, 16,
				texture.getWidth(), texture.getHeight(), 1, 1, deg);
		batch.end();
	}

	public static void drawTextured(float x, float y, float ooffx, float ooffy,
			Texture texture, int deg) {
		batch.enableBlending();
		batch.begin();
		batch.draw(new TextureRegion(texture), x, y, ooffx, ooffy,
				texture.getWidth(), texture.getHeight(), 1, 1, deg);
		batch.end();
	}

	public static void drawRect(float x, float y, float width, float height,
			Color color, boolean filled) {
		if (filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.rect(x, y, width, height);
		shape.end();
	}

	public static void drawCircle(float x, float y, int radius, Color color,
			boolean filled) {
		if (filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.circle(x, y, radius);
		shape.end();
	}

	public static void drawRect(float x, float y, float width, float height,
			float degrees, Color color, boolean filled) {
		if (filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.rect(x, y, width, height / 2, width, height, 1, 1, degrees);

		shape.end();
	}

	public static void drawTriangle(float x, float y, float width,
			float height, float degrees, Color color, boolean filled) {
		if (filled)
			shape.begin(ShapeType.Filled);
		else
			shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.triangle(x, y, x + width, y, x + (width / 2), y + height);
		shape.end();
	}

	public static void drawLine(float x, float y, float x2, float y2,
			Color color) {
		shape.begin(ShapeType.Line);
		shape.setColor(color);
		shape.line(x, y, x2, y2);
		shape.end();
	}
	
	public static void dispose() {
		batch.dispose();
	}
}