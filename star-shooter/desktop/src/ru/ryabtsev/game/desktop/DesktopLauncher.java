package ru.ryabtsev.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.ryabtsev.game.StarShooterGame;

/**
 * Class which launches application desktop version.
 */
public class DesktopLauncher {

	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 800;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DEFAULT_WIDTH;
		config.height = DEFAULT_HEIGHT;
		new LwjglApplication(new StarShooterGame(), config);
	}
}
