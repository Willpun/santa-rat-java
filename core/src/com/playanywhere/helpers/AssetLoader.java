package com.playanywhere.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Preferences prefs;

    public static Texture texture; // , textureSanta;
    public static Texture textureHouse;
    public static Texture textureGift;
    public static Texture textureSnowflake;
    public static Texture textureGrass;
    public static Texture textureBg;
    public static Texture logoTexture;
    
    public static TextureRegion logo, srLogo, bg, grass, playButtonUp, playButtonDown;

    public static Animation santaAnimation;
    public static TextureRegion santa, santa2, santa3;

  //  public static TextureRegion skullUp, skullDown, bar;

    public static TextureRegion house;

    public static TextureRegion gift;

	public static TextureRegion snowflake;
    
    public static Sound dead, coin;
    
    public static BitmapFont font, shadow;

    
    public static void load() {

    	// Create (or retrieve existing) preferences file
    	prefs = Gdx.app.getPreferences("com.playanywhere.santa.settings");

    	// Provide default high score of 0
    	if (!prefs.contains("highScore")) {
    	    prefs.putInteger("highScore", 0);
    	}
    	
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);
    	
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
       // textureSanta = new Texture(Gdx.files.internal("data/santa.png"));
       // textureSanta.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        srLogo = new TextureRegion(texture, 0, 0, 135, 24);
        srLogo.flip(false, true);
        
        textureHouse = new Texture(Gdx.files.internal("data/house.png"));
        textureHouse.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        textureGift = new Texture(Gdx.files.internal("data/gift.png"));
        textureGift.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        textureSnowflake = new Texture(Gdx.files.internal("data/snowflake.png"));
        textureSnowflake.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        textureGrass = new Texture(Gdx.files.internal("data/grass.png"));
        textureGrass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        textureBg = new Texture(Gdx.files.internal("data/bg.png"));
        textureBg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        
        bg = new TextureRegion(textureBg, 0, 0, 136, 74);
        bg.flip(false, true);

        grass = new TextureRegion(textureGrass, 0, 0, 143, 26);//, 0, 43, 143, 11);
        grass.flip(false, true);

        santa3 = new TextureRegion(texture, 170, 0, 17, 15);
        santa3.flip(false, true);

        santa = new TextureRegion(texture, 136, 0, 17, 15);
        santa.flip(false, true);

        santa2 = new TextureRegion(texture, 153, 0, 17, 15);
        santa2.flip(false, true);

        TextureRegion[] santaFigures = { santa3, santa, santa2 };
        santaAnimation = new Animation(0.06f, santaFigures);
        santaAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

  /*      skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
*/
        house = new TextureRegion(textureHouse, 0, 0, 64, 64);
        house.flip(false, true);
        
        gift = new TextureRegion(textureGift, 0, 0, 16, 16);
        gift.flip(false, true);
        
        snowflake = new TextureRegion(textureSnowflake, 0, 0, 15, 15);
        snowflake.flip(false, true);
        
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.15f, -.15f);  // negative y (flipping)
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.15f, -.15f);
        
    }
    
 // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        textureHouse.dispose();
        textureGift.dispose();
        textureSnowflake.dispose();
        textureGrass.dispose();
        textureBg.dispose();
        
        logoTexture.dispose();
        
        dead.dispose();
        coin.dispose();
        
        font.dispose();
        shadow.dispose();
    }

}