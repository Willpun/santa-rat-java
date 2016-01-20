package com.playanywhere.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Snowflake extends Projectile {

	private int bottomPointY;
//	private Random r;
	private Rectangle boundingSnowflake;
	
	public Snowflake(float x, float y, int width, int height, float scrollSpeed, int midPointY) {	
		super(x, y, width, height, scrollSpeed);
		acceleration = new Vector2(0, 5);
		this.bottomPointY = midPointY * 2;
		
		boundingSnowflake = new Rectangle();
	}
	
	public void update(float delta) {
		super.update(delta);
		if(position.y + height > this.bottomPointY) {
			this.reset(this.getX());
			if (velocity.y < 10)
				acceleration = new Vector2(0, 1);
			else
				velocity.y = 5;
			
		}
		
		boundingSnowflake.set(position.x + 7, position.y + 7, width - 10, height - 10);	
	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
		position.y = 0;
		if (position.x < 50)
			position.x = 100;
		acceleration.y = 5;
	}
	
	public boolean collides(Santa santa) {
		if (position.x < santa.getX() + santa.getWidth()) {
			return (Intersector.overlaps(santa.getBoundingCircle(), boundingSnowflake));
		}
//			return RoofIntersector(santa.getBoundingCircle());}
		return false;
	}
	
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);
	}
	
	public void stop() {
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 0;
		
	}
}
