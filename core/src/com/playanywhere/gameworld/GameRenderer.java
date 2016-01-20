package com.playanywhere.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.playanywhere.TweenAccessors.Value;
import com.playanywhere.TweenAccessors.ValueAccessor;
import com.playanywhere.gameobjects.Background;
import com.playanywhere.gameobjects.Gift;
import com.playanywhere.gameobjects.Grass;
import com.playanywhere.gameobjects.House;
import com.playanywhere.gameobjects.Santa;
import com.playanywhere.gameobjects.ScrollHandler;
import com.playanywhere.gameobjects.Snowflake;
import com.playanywhere.helpers.AssetLoader;
import com.playanywhere.helpers.InputHandler;
import com.playanywhere.ui.SimpleButton;

public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private int midPointY;
	
	// Game Objects
	private Santa santa;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private House house1, house2, house3;
	private Background frontBg, backBg;
	
	private Gift gift1, gift2, gift3;
	
	private Snowflake snowflake1, snowflake2, snowflake3;
	
	// Game Assets
    public static TextureRegion bg, grass;
    public static Animation santaAnimation;
    public static TextureRegion santa1, santa2, santa3;
    public static TextureRegion house;
    public static TextureRegion giftText;
    
    public static TextureRegion snowflakeText;
    
    // Tween stuff
    private TweenManager manager;
    private Value alpha = new Value();
    
    // Buttons
    private List<SimpleButton> menuButtons;
    
	public GameRenderer (GameWorld world, int gameHeight, int midPointY){
		myWorld = world;
		
		this.midPointY = midPointY;
		
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		
		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
        setupTweens();
	}
	
    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }
	
	private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        santaAnimation = AssetLoader.santaAnimation;
        santa1 = AssetLoader.santa;
        santa2 = AssetLoader.santa2;
        santa3 = AssetLoader.santa3;
        house = AssetLoader.house;
        giftText = AssetLoader.gift;
        snowflakeText = AssetLoader.snowflake;
	}

	private void initGameObjects() {
		santa = myWorld.getSanta();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		frontBg = scroller.getFrontBg();
		backBg = scroller.getBackBg();
		house1 = scroller.getHouse1();
		house2 = scroller.getHouse2();
		house3 = scroller.getHouse3();
		gift1 = santa.getGift1();
		gift2 = santa.getGift2();
		gift3 = santa.getGift3();
		snowflake1 = scroller.getSnowflake1();
		snowflake2 = scroller.getSnowflake2();
		snowflake3 = scroller.getSnowflake3();
	}
	
    private void drawSantaCentered(float runTime) {
        batcher.draw(santaAnimation.getKeyFrame(runTime), 59, santa.getY() - 15,
                santa.getWidth() / 2.0f, santa.getHeight() / 2.0f,
                santa.getWidth(), santa.getHeight(), 1, 1, santa.getRotation());
    }
	
    private void drawSanta(float runTime) {
        if (santa.stopCombustion()) {
            batcher.draw(santa1, santa.getX(), santa.getY(),
                    santa.getWidth() / 2.0f, santa.getHeight() / 2.0f,
                    santa.getWidth(), santa.getHeight(), 1, 1, santa.getRotation());

        } else {
            batcher.draw(santaAnimation.getKeyFrame(runTime), santa.getX(),
                    santa.getY(), santa.getWidth() / 2.0f,
                    santa.getHeight() / 2.0f, santa.getWidth(), santa.getHeight(),
                    1, 1, santa.getRotation());
        }
    }
    
    private void drawMenuUI() {
        batcher.draw(AssetLoader.srLogo, 136 / 2 - 56, midPointY - 50,
                AssetLoader.srLogo.getRegionWidth() / 1.2f,
                AssetLoader.srLogo.getRegionHeight() / 1.2f);

        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }

    }

    private void drawGiftCount() {    
    	
        AssetLoader.shadow.draw(batcher, "Your Score:", 0, 0); 
       	AssetLoader.font.setColor(Color.RED);
        AssetLoader.font.draw(batcher, "Your Score:", 0, -1);
        AssetLoader.font.setColor(Color.WHITE);
        
           // Convert integer into String
           String score = santa.getGiftCount() + "";

           // Draw shadow first
           AssetLoader.shadow.draw(batcher, "" + santa.getGiftCount(), (136 / 4) - (3 * score.length()), 10);
           // Draw text
           AssetLoader.font.draw(batcher, "" + santa.getGiftCount(), (136 / 4) - (3 * score.length() - 1), 9);
     
    }
    
	public void render(float delta, float runTime) {
		
		// Fill the entire screen with black, to prevent potential flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);
		// Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 75, 136, 50);

        // End ShapeRenderer
        shapeRenderer.end();

        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batcher.enableBlending(); 
        
        drawBackground();
        
        drawGrass();
        
        drawHouse();
        
        drawSnowflake();
        
        if (myWorld.isRunning()) {
        	drawGiftCount();
        	drawSanta(runTime);
        	drawGift();
        } else if (myWorld.isReady()) {
        	drawSanta(runTime);
            drawGiftCount();
        } else if (myWorld.isMenu()) {
        	drawSantaCentered(runTime);
        	drawMenuUI();
        } else if (myWorld.isGameOver()) {
        	drawGiftCount();
        	drawSanta(runTime);
        	drawGameOver();
        } else if (myWorld.isHighScore()) {
        	drawGiftCount();
        	drawSanta(runTime);
        	drawGameOver();
        }
 
        batcher.disableBlending(); 
        batcher.end();
        drawTransition(delta);
    }

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(1, 1, 1, alpha.getValue());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }
	
	private void drawBackground() {
		batcher.draw(bg, frontBg.getX(), frontBg.getY(),
                frontBg.getWidth(), frontBg.getHeight());
        batcher.draw(bg, backBg.getX(), backBg.getY(),
                backBg.getWidth(), backBg.getHeight());
	}
	
    private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }
    
    private void drawHouse() {
    	batcher.draw(house, house1.getX(), house1.getY(),
    			house1.getWidth(), house1.getHeight());
    	batcher.draw(house, house2.getX(), house2.getY(),
    			house2.getWidth(), house2.getHeight());
    	batcher.draw(house, house3.getX(), house3.getY(),
    			house3.getWidth(), house3.getHeight());
    	
    }
    
    private void drawSnowflake() {
    	batcher.draw(snowflakeText, snowflake1.getX(), snowflake1.getY(),
    			snowflake1.getWidth(), snowflake1.getHeight());    	
    	batcher.draw(snowflakeText, snowflake2.getX(), snowflake2.getY(),
    	    			snowflake2.getWidth(), snowflake2.getHeight());
    	batcher.draw(snowflakeText, snowflake3.getX(), snowflake3.getY(),
    			snowflake3.getWidth(), snowflake3.getHeight());
    }
    
    private void drawGift() {
       	batcher.draw(giftText, gift1.getX(), gift1.getY(), gift1.getHeight(), gift1.getWidth());
       	batcher.draw(giftText, gift2.getX(), gift2.getY(), gift2.getHeight(), gift2.getWidth());
       	batcher.draw(giftText, gift3.getX(), gift3.getY(), gift3.getHeight(), gift3.getWidth());
    }
    
    private void drawGameOver() {
         AssetLoader.shadow.draw(batcher, "Game Over", 37, 60);
         
        
         AssetLoader.font.draw(batcher, "Game Over", 36, 59);
         
         AssetLoader.font.setColor(Color.RED);
         AssetLoader.shadow.draw(batcher, "High Score:", 37, 100);
         AssetLoader.font.draw(batcher, "High Score:", 36, 99);
         
         String highScore = AssetLoader.getHighScore() + "";

         // Draw shadow first
         AssetLoader.shadow.draw(batcher, highScore, (136 / 2) - (3 * highScore.length()), 118);
         
         // Draw text
         AssetLoader.font.setColor(Color.WHITE);
         AssetLoader.font.draw(batcher, highScore, (136 / 2) - (3 * highScore.length() - 1), 117);

     //    AssetLoader.shadow.draw(batcher, "Tap to Try Again", 23, 76);
     //    AssetLoader.font.draw(batcher, "Tap to Try Again", 24, 75);  
    }
}
