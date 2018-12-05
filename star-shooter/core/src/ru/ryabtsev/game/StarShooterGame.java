package ru.ryabtsev.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import ru.ryabtsev.game.screen.GameScreen;
import ru.ryabtsev.game.screen.MenuScreen;

/**
 * Game main class.
 */
public class StarShooterGame extends Game {

	@Override
	public void create () {
		//setScreen( new GameScreen() );
		setScreen( new MenuScreen() );
	}


	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}
}
