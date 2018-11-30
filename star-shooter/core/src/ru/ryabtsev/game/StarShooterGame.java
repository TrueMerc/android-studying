package ru.ryabtsev.game;

import com.badlogic.gdx.Game;

import ru.ryabtsev.game.screen.MenuScreen;

public class StarShooterGame extends Game {

	@Override
	public void create () {
		setScreen( new MenuScreen() );
	}
}
