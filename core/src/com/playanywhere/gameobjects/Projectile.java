package com.playanywhere.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Projectile extends Scrollable {
	
	protected Vector2 acceleration;
	
	public Projectile(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}
	
	@Override
	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta));
	//	position.add(velocity.cpy().scl(delta));
		super.update(delta);
	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
	}
	
	public void reset(float newX, float newY) {
		this.position.x = newX;
		this.position.y = newY;
		velocity.set(10, -50);
		acceleration.set(0, 60);
	}
}
