package com.playanywhere.gameworld;

import com.playanywhere.gameobjects.Santa;
import com.playanywhere.gameobjects.ScrollHandler;
import com.playanywhere.helpers.AssetLoader;

public class GameWorld {
	private Santa santa;
	private ScrollHandler scroller;
	private int groundY;
	
	private float runTime = 0;
	
	private int midPointY;
	
	private GameState currentState;
	
	public enum GameState {

	    MENU, READY, RUNNING, GAMEOVER, HIGHSCORE

	}
	
	public GameWorld(int midPointY) {
		this.midPointY = midPointY;
		currentState = GameState.MENU;
		santa = new Santa(33, midPointY - 5, 17, 12, midPointY);
		scroller = new ScrollHandler(this, midPointY);
		groundY = midPointY + 66;
	}
	
	public void update(float delta) {
			runTime += delta;
			
	        switch (currentState) {
	        case READY:
	        case MENU:
	            updateReady(delta);
	            break;

	        case RUNNING:
	            updateRunning(delta);
	            break;
	        default:
	            break;
	        }

	    }

	private void updateReady(float delta) {
	    santa.updateReady(runTime);
	    scroller.updateReady(delta);
	}
	
	public void updateRunning(float delta) {
		
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
		if (delta > .15f) {
            delta = .15f;
        }
        
		santa.update(delta);
		scroller.update(delta);
		
		if(santa.getGift1().IsGiftInChimneyTop(scroller.getHouse1().getChimneyTop())
			|| santa.getGift1().IsGiftInChimneyTop(scroller.getHouse2().getChimneyTop())
			|| santa.getGift1().IsGiftInChimneyTop(scroller.getHouse3().getChimneyTop()) ){
			
			santa.addGiftCount(1);
			AssetLoader.coin.play();
			santa.getGift1().reset(santa.getX(), santa.getY());
		
		}else if(santa.getGift2().IsGiftInChimneyTop(scroller.getHouse1().getChimneyTop())
				|| santa.getGift2().IsGiftInChimneyTop(scroller.getHouse2().getChimneyTop())
				|| santa.getGift2().IsGiftInChimneyTop(scroller.getHouse3().getChimneyTop()) ){
				
				santa.addGiftCount(1);
				AssetLoader.coin.play();
				santa.getGift2().reset(santa.getX(), santa.getY());
				
		}else if(santa.getGift3().IsGiftInChimneyTop(scroller.getHouse1().getChimneyTop())
				|| santa.getGift3().IsGiftInChimneyTop(scroller.getHouse2().getChimneyTop())
				|| santa.getGift3().IsGiftInChimneyTop(scroller.getHouse3().getChimneyTop()) ){
			
				santa.addGiftCount(1);
				AssetLoader.coin.play();
				santa.getGift3().reset(santa.getX(), santa.getY());
		}
				
		if(santa.isAlive() && scroller.collides(santa)) {
			// Clean up on game over
			scroller.stop();
			santa.die();
			AssetLoader.dead.play();
		}
		
        if (!santa.isAlive() && santa.getY() + santa.getHeight() >= groundY) {
//            scroller.stop();
 //           santa.die();
            santa.decelerate();
            currentState = GameState.GAMEOVER;
	        if (santa.getGiftCount() > AssetLoader.getHighScore()) {
	                AssetLoader.setHighScore(santa.getGiftCount());
	                currentState = GameState.HIGHSCORE;
	        }
			
		}
       
		if(santa.getY() + santa.getHeight() >= groundY) {
			santa.setY(groundY - santa.getHeight());
		}
	}
	
	public Santa getSanta() {
		return santa;
	}
	
	public int getMidPointY() {
		return midPointY;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}
	
    public void start() {
        currentState = GameState.RUNNING;
    }
	
    public void ready() {
    	currentState = GameState.READY;
    }
	
    public void restart() {
        currentState = GameState.READY;
        santa.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

	public boolean isReady() {
        return currentState == GameState.READY;
	}
    
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
    
    public boolean isMenu() {
    	return currentState == GameState.MENU;
    }
    
    public boolean isRunning() {
    	return currentState == GameState.RUNNING;
    }
    
    public int getGiftCount() {
    	return santa.getGiftCount();
    }
}
