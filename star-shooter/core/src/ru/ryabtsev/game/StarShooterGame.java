package ru.ryabtsev.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

import ru.ryabtsev.game.screen.GameScreen;
import ru.ryabtsev.game.screen.MenuScreen;

import static ru.ryabtsev.game.StarShooterGame.State.NEW;
import static ru.ryabtsev.game.StarShooterGame.State.PAUSED;

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

	/**
	 * Enumeration for different game states.
	 */
	public enum State { NEW, PLAYING, PAUSED, OVER };

	private static final int screensNumber = ScreenType.values().length;

	private Screen[] screens;

	private Music mainTheme;

	private State state;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create () {
		createScreens();
		createMusic();
		state = NEW;
	}

	private void createScreens() {
		screens = new Screen[ screensNumber ];
		screens[ScreenType.MENU.getIndex()] = new MenuScreen(this);
		screens[ScreenType.GAME.getIndex()] = new GameScreen( this );
		setScreen( ScreenType.MENU );
	}

	private void createMusic() {
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/main_theme.wav"));
		mainTheme.setVolume(0.25f);
		mainTheme.setLooping(true);
		mainTheme.play();
	}

	/**
	 * Sets game screen by given type.
	 * @param type - screen type.
	 */
	public void setScreen(ScreenType type) {
		setScreen( screens[type.getIndex()] );
	}

	/**
	 * Returns game state.
	 * @return game state.
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets game state.
	 * @param state - new state.
	 */
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public void dispose() {
		super.dispose();
		mainTheme.dispose();
	}
}
