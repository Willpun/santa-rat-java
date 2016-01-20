package com.playanywhere.santa.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.playanywhere.santa.SomeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Santa";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new SomeGame(), config);
	}
}