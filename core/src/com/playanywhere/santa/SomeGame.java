package com.playanywhere.santa;

import com.badlogic.gdx.Game;
import com.playanywhere.helpers.AssetLoader;
import com.playanywhere.screens.SplashScreen;

public class SomeGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}