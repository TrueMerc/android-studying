package ru.ryabtsev.game;

import com.badlogic.gdx.Game;

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
}
