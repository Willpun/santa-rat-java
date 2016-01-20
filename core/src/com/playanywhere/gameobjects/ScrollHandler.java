package com.playanywhere.gameobjects;

import com.playanywhere.gameworld.GameWorld;

public class ScrollHandler {
	private Grass frontGrass, backGrass;
	private House house1, house2, house3;
	private Snowflake snowflake1, snowflake2, snowflake3;
	
	private Background frontBg, backBg;

	public static final int SCROLL_SPEED = -59;
	public static final int HOUSE_GAP = 49;
	public static final int SNOWFLAKE_GAP = 49;
	public static final int BACKGROUND_SCROLL_SPEED = -10;
	
	public ScrollHandler(GameWorld gameWorld, float yPos) {
		
		frontGrass = new Grass(0, yPos + 52, 143, 26, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX(), yPos + 52, 143, 26,
				SCROLL_SPEED);

		frontBg = new Background(0, yPos, 136, 74, BACKGROUND_SCROLL_SPEED);
		backBg = new Background(frontBg.getTailX(), yPos, 136, 74, BACKGROUND_SCROLL_SPEED);
		
		house1 = new House(210, yPos + 66 - 64, 64, 64, SCROLL_SPEED, yPos);
		house2 = new House(house1.getTailX() + HOUSE_GAP, yPos + 66 - 64 - 8,
				64, 64, SCROLL_SPEED, yPos);
		house3 = new House(house2.getTailX() + HOUSE_GAP, yPos + 66 - 64, 64,
				64, SCROLL_SPEED, yPos);

		snowflake1 = new Snowflake(210, 0, 15, 15, SCROLL_SPEED, (int) yPos);
		snowflake2 = new Snowflake(snowflake1.getTailX() + SNOWFLAKE_GAP, 0,
				15, 15, SCROLL_SPEED, (int) yPos);
		snowflake3 = new Snowflake(snowflake2.getTailX() + SNOWFLAKE_GAP, 0,
				15, 15, SCROLL_SPEED, (int) yPos);

	}
	
	public void updateReady(float delta) {
		frontGrass.update(delta);
		backGrass.update(delta);
		
		frontBg.update(delta);
		backBg.update(delta);
		
		if (frontBg.isScrolledLeft()) {
			frontBg.reset(backBg.getTailX());
		} else if (backBg.isScrolledLeft()) {
			backBg.reset(frontBg.getTailX());
		}
		
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}
	}

	public void update(float delta) {
		frontBg.update(delta);
		backBg.update(delta);
		frontGrass.update(delta);
		backGrass.update(delta);
		house1.update(delta);
		house2.update(delta);
		house3.update(delta);
		snowflake1.update(delta);
		snowflake2.update(delta);
		snowflake3.update(delta);

		
		if (frontBg.isScrolledLeft()) {
			frontBg.reset(backBg.getTailX());
		} else if (backBg.isScrolledLeft()) {
			backBg.reset(frontBg.getTailX());
		}
		
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());

		}

		if (house1.isScrolledLeft()) {
			house1.reset(house3.getTailX() + HOUSE_GAP);
		} else if (house2.isScrolledLeft()) {
			house2.reset(house1.getTailX() + HOUSE_GAP);
		} else if (house3.isScrolledLeft()) {
			house3.reset(house2.getTailX() + HOUSE_GAP);
		}

		if (snowflake1.isScrolledLeft()) {
			snowflake1.reset(snowflake3.getTailX() + SNOWFLAKE_GAP);
		} else if (snowflake2.isScrolledLeft()) {
			snowflake2.reset(snowflake1.getTailX() + SNOWFLAKE_GAP);
		} else if (snowflake3.isScrolledLeft()) {
			snowflake3.reset(snowflake2.getTailX() + SNOWFLAKE_GAP);
		}

	}
	
	public Background getFrontBg() {
		return frontBg;
	}

	public Background getBackBg() {
		return backBg;
	}
	
	public Grass getFrontGrass() {
		return frontGrass;
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public House getHouse1() {
		return house1;
	}

	public House getHouse2() {
		return house2;
	}

	public House getHouse3() {
		return house3;
	}

	public Snowflake getSnowflake1() {
		return snowflake1;
	}

	public Snowflake getSnowflake2() {
		return snowflake2;
	}

	public Snowflake getSnowflake3() {
		return snowflake3;
	}

	public boolean collides(Santa santa) {
		return (house1.collides(santa) || house2.collides(santa) || house3
				.collides(santa) || snowflake1.collides(santa) || snowflake2.collides(santa) || snowflake3.collides(santa));
	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();
		frontBg.stop();
		backBg.stop();
		house1.stop();
		house2.stop();
		house3.stop();
		snowflake1.stop();
		snowflake2.stop();
		snowflake3.stop();
	}
	
    public void onRestart() {
        frontGrass.onRestart(0, SCROLL_SPEED);
        backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
        frontBg.onRestart(0, BACKGROUND_SCROLL_SPEED);
        backBg.onRestart(frontBg.getTailX(), BACKGROUND_SCROLL_SPEED);
        house1.onRestart(210, SCROLL_SPEED);
        house2.onRestart(house1.getTailX() + HOUSE_GAP, SCROLL_SPEED);
        house3.onRestart(house2.getTailX() + HOUSE_GAP, SCROLL_SPEED);
        snowflake1.onRestart(210, SCROLL_SPEED);
        snowflake2.onRestart(snowflake1.getTailX() + SNOWFLAKE_GAP, SCROLL_SPEED);
        snowflake3.onRestart(snowflake2.getTailX() + SNOWFLAKE_GAP, SCROLL_SPEED);        
    }
}
