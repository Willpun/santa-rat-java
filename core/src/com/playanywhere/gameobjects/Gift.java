package com.playanywhere.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Gift extends Projectile {
	
	private int bottomPointY;
	private Random r;
	
	private Rectangle boundingGift;
	
	public Gift(float x, float y, int width, int height, float scrollSpeed, int midPointY) {	
		super(x, y, width, height, scrollSpeed);
		this.bottomPointY = midPointY * 2;
		
		r = new Random();
		velocity.y = 50 - r.nextInt(20);
		acceleration = new Vector2(0, r.nextInt(60)+30);
		
		boundingGift = new Rectangle();
	}
	
	public void update(Vector2 santaPosition, float delta) {
		super.update(delta);
		if(position.y + height > this.bottomPointY) {
			this.reset(santaPosition.x, santaPosition.y);
			velocity.y = r.nextInt(20);
			acceleration = new Vector2(0, r.nextInt(60)+30);
		}
		
		boundingGift.set(position.x, position.y, width, height);
		
	}
	
	public Rectangle getBoundingGift() {
		return boundingGift;
	}

	public boolean IsGiftInChimneyTop(Rectangle chimneyTop) {
		if (position.x < chimneyTop.getX() + chimneyTop.getWidth()
				&& position.x > chimneyTop.getX()) {
			return (Intersector.overlaps(getBoundingGift(), chimneyTop)); }
		return false;
	}
	
	public void onRestart(float x, float y) {
	    position.y = y;
	    reset(x);
	}
}
