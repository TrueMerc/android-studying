package ru.ryabtsev.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarShooterGame extends Game {
	SpriteBatch batch;
	Texture background;
	Texture spaceShip;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("space_background.png");
		spaceShip = new Texture( "star_ship.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(spaceShip, 0, 0, spaceShip.getWidth() * 0.1f, spaceShip.getHeight() * 0.1f );
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		spaceShip.dispose();
	}
}
