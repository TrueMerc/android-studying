package ru.ryabtsev.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import ru.ryabtsev.game.screen.GameScreen;
import ru.ryabtsev.game.screen.MenuScreen;

/**
 * Game main class.
 */
public class StarShooterGame extends Game {
	/**
	 * Enumeration for different screen types.
	 */
	public enum ScreenType {
		MENU(0),
		GAME(1);

		private final int index;
		ScreenType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	private static final int screensNumber = ScreenType.values().length;

	private Screen[] screens;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create () {
		screens = new Screen[ screensNumber ];
		screens[ScreenType.MENU.getIndex()] = new MenuScreen(this);
		screens[ScreenType.GAME.getIndex()] = new GameScreen( this );
		setScreen( ScreenType.MENU );
	}

	/**
	 * Sets game screen by given type.
	 * @param type - screen type.
	 */
	public void setScreen(ScreenType type) {
		setScreen( screens[type.getIndex()] );
	}
}
