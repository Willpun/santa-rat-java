package com.playanywhere.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Santa {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private float rotation;
	private int width;
	private int height;
	private Circle boundingCircle;
	
	private Gift gift1, gift2, gift3;
	
	private boolean isAlive;
	
	private float originalY;
	
	private int giftCount;
	
	public Gift getGift1() {
		return gift1;
	}


	public Gift getGift2() {
		return gift2;
	}


	public Gift getGift3() {
		return gift3;
	}


	public Santa(float x, float y, int width, int height, int midPointY){
		this.width = width;
		this.height = height;
		
		this.originalY = y;
		
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		boundingCircle = new Circle();
		
		gift1 = new Gift(x, y, width, height, -59, midPointY);
		gift2 = new Gift(x, y, width, height, -29, midPointY);
		gift3 = new Gift(x, y, width, height, -9, midPointY);
		
		isAlive = true;
		
		giftCount = 0;
	}
	

	public void update(float delta){
//		Gdx.app.log("Acceleration.cpy().scl(delta)", acceleration.cpy().scl(delta) + "");		
		velocity.add(acceleration.cpy().scl(delta));
//		Gdx.app.log("Velocity", velocity + "");	
		if (velocity.y > 200) {
			velocity.y = 200;
		}	
		
        // CEILING CHECK 
        if (position.y < -5) {
            position.y = -5;
            velocity.y = 0;
        }
        
	//	position.add(velocity.cpy().scl(delta));	
		position.y += (velocity.cpy().scl(delta)).y;
	//	Gdx.app.log("Santa Velocity", velocity + "");

		boundingCircle.set(position.x + 9, position.y + 6f, 7f);
		
		// Rotate counterclockwise
		if (velocity.y < 0) {
			rotation -= 600 * delta;
			
			if (rotation < -20) {
				rotation = -20;
			}
		}
		
		// Rotate clockwise
		if (isFalling()) {
			rotation += 480 * delta;
			if (rotation > 45) {
				rotation = 45;
			}
		}
		
		if (isAlive) {
			gift1.update(position, delta);
			gift2.update(position, delta);
			gift3.update(position, delta);
		}
		else {
			rotation = 90;
		}
	}
	
	public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isFalling() {
		return velocity.y > 110;
	}
	
	public boolean stopCombustion() {
		return velocity.y > 70 || !isAlive;
	}
	
	public void onClick() {
		if(isAlive) {
			velocity.y = -140;
		}
	}

	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getRotation() {
		return rotation;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setY(float newY) {
		position.y = newY;
		if (isAlive) 
		rotation = 0;
		else
			rotation = 180;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void die() {
		isAlive = false;
		rotation = 90;
		velocity.y = 0;
	}
	
	public void decelerate() {
		acceleration.y = 0;
	}
	
	public void addGiftCount(int i) {
		giftCount += i;
	}
	
	public int getGiftCount() {
		return giftCount;
	}
	
    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
        giftCount = 0;
        gift1.onRestart(position.x, y);
        gift2.onRestart(position.x, y);
        gift3.onRestart(position.x, y);
    }
	
}
