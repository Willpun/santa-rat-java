package com.playanywhere.helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.playanywhere.gameobjects.Santa;
import com.playanywhere.gameworld.GameWorld;
import com.playanywhere.ui.SimpleButton;

public class InputHandler implements InputProcessor{

	private Santa mySanta;
	private GameWorld myWorld;
	
    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;

    private float scaleFactorX;
    private float scaleFactorY;
	
	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
	     // myBird now represents the gameWorld's bird.
	    this.myWorld = myWorld;
	    mySanta = myWorld.getSanta();
	    
	    int midPointY = myWorld.getMidPointY();
	    
	    this.scaleFactorX = scaleFactorX;
	    this.scaleFactorY = scaleFactorY;
	    
	    menuButtons = new ArrayList<SimpleButton>();
	    playButton = new SimpleButton(136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                midPointY + 10, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
	}
	
	@Override
	public boolean keyDown(int keycode) {

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            mySanta.onClick();

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    screenX = scaleX(screenX);
	    screenY = scaleY(screenY);
	    System.out.println(screenX + " " + screenY);
	    
	    if (myWorld.isMenu()) {
	    	playButton.isTouchDown(screenX, screenY);
	    } else if (myWorld.isReady()) {
            myWorld.start();
        }

        mySanta.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            myWorld.restart();
        }

        return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
        }

        return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

}
