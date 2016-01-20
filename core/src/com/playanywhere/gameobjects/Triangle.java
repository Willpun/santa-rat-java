package com.playanywhere.gameobjects;

public class Triangle {
	public float x0, y0;
	public float x1, y1;
	public float x2, y2;
	
	public Triangle(){
		x0 = y0 = x1 = y1 = x2 = y2 = 0;
	}
	
	public void setVertices(float x0, float y0, float x1, float y1, float x2, float y2){
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}
