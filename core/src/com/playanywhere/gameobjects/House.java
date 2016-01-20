package com.playanywhere.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class House extends Scrollable {

	private Triangle roof1, roof2;
	private Rectangle chimney, body;
	
	private Random r;
	private static float groundY;
	
	private float px, py; // for intersection checks

	// When the constructor is invoked, invoke the super one
	public House(float x, float y, int width, int height, float scrollSpeed,
			float yPos) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		groundY = yPos + 66;
		chimney = new Rectangle();
		body = new Rectangle();
		roof1 = new Triangle();
		roof2 = new Triangle();
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		chimney.set(position.x + 33, position.y + 7, 13, 16);
		
		roof1.setVertices(
				position.x + 0,  position.y + 40,
				position.x + 56, position.y + 24,
				position.x + 62, position.y + 39);
		
		roof2.setVertices(
				position.x + 12, position.y + 24,
				position.x + 0,  position.y + 40,
				position.x + 56, position.y + 24);
		
		body.set(position.x + 8, position.y + 40, 49, 23);
	}



	public Rectangle getChimney() {
		return chimney;
	}

	public Triangle getRoof1() {
		return roof1;
	}

	public Triangle getRoof2() {
		return roof2;
	}

	public Rectangle getBody() {
		return body;
	}

	@Override
	public void reset(float newX) {
		super.reset(newX);
		position.y = ((int) groundY - r.nextInt(20)) - height + 12;
	}

	public boolean collides(Santa santa) {
		if (position.x < santa.getX() + santa.getWidth()) {
			return (Intersector.overlaps(santa.getBoundingCircle(), chimney)
			|| RoofIntersector(santa.getBoundingCircle())
			|| Intersector.overlaps(santa.getBoundingCircle(), body));}

//			return RoofIntersector(santa.getBoundingCircle());}
		return false;
	}
	
	private boolean RoofIntersector(Circle c) {
		
		for (px = c.x + c.radius; px >= c.x; px--)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof1.x0, roof1.y0, roof1.x1, roof1.y1, roof1.x2, roof1.y2)) {
					return true;
				}
		
		for (px = c.x + c.radius; px >= c.x; px--)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof1.x0, roof1.y0, roof1.x1, roof1.y1, roof1.x2, roof1.y2)) {
					return true;
				}
		
		for (px = c.x - c.radius; px <= c.x; px++)
			for (py = c.y - c.radius; py <= c.y; py++)
				if( Intersector.isPointInTriangle(px, py, roof1.x0, roof1.y0, roof1.x1, roof1.y1, roof1.x2, roof1.y2)) {
					return true;
				}
		
		for (px = c.x - c.radius; px <= c.x; px++)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof1.x0, roof1.y0, roof1.x1, roof1.y1, roof1.x2, roof1.y2)) {
					return true;
				}
		
		for (px = c.x + c.radius; px >= c.x; px--)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof2.x0, roof2.y0, roof2.x1, roof2.y1, roof2.x2, roof2.y2)) {
					return true;
				}
		
		for (px = c.x + c.radius; px >= c.x; px--)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof2.x0, roof2.y0, roof2.x1, roof2.y1, roof2.x2, roof2.y2)) {
					return true;
				}
		
		for (px = c.x - c.radius; px <= c.x; px++)
			for (py = c.y - c.radius; py <= c.y; py++)
				if( Intersector.isPointInTriangle(px, py, roof2.x0, roof2.y0, roof2.x1, roof2.y1, roof2.x2, roof2.y2)) {
					return true;
				}
		
		for (px = c.x - c.radius; px <= c.x; px++)
			for (py = c.y + c.radius; py >= c.y; py--)
				if( Intersector.isPointInTriangle(px, py, roof2.x0, roof2.y0, roof2.x1, roof2.y1, roof2.x2, roof2.y2)) {
					return true;
				}
		return false;
		
	}

	public Rectangle getChimneyTop() {
		return new Rectangle(chimney.x, chimney.y, chimney.width, 2);
	}
	
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);
	}
}
