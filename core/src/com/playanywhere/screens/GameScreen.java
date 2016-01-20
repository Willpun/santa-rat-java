package com.playanywhere.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.playanywhere.gameworld.GameRenderer;
import com.playanywhere.gameworld.GameWorld;
import com.playanywhere.helpers.InputHandler;

public class GameScreen implements Screen {

	private GameRenderer renderer;
	private GameWorld world;
	private float runTime;
	
	public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        if(screenWidth > screenHeight){
        	float temp = screenHeight;
        	screenHeight = screenWidth;
        	screenWidth = temp;
        }
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);
        
        runTime = 0;
        
		world = new GameWorld(midPointY);

		Gdx.app.log("Game Height", gameHeight + "");
		Gdx.app.log("Game Width", gameWidth + "");
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
		
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}