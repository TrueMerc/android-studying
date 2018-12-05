package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.MenuButton;
import ru.ryabtsev.game.object.Star;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private static final int STARS_COUNT = 50;

    private TextureAtlas textureAtlas;
    private Star[] stars;
    private MenuButton playButton;
    private MenuButton exitButton;

    public MenuScreen(StarShooterGame game) {
        super(game, HEIGHT_AXIS_SCALE);
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        stars = new Star[STARS_COUNT];
        for(int i = 0; i < stars.length; ++i) {
            stars[i] = new Star(textureAtlas);
        }
        playButton = new MenuButton( textureAtlas.findRegion("btPlay"), new Vector2( -0.3f, 0) );
        exitButton = new MenuButton( textureAtlas.findRegion("btExit"), new Vector2( 0.3f, 0) );
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for(int i = 0; i < stars.length; ++i) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        batch.begin();
        for(int i = 0; i < stars.length; ++i) {
            stars[i].draw(batch);
        }
        playButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        for(int i = 0; i < stars.length; ++i) {
            stars[i].resize(worldBounds);
        }
        playButton.resize(worldBounds);
        exitButton.resize(worldBounds);
        playButton.setPosition(new Vector2( -0.3f, 0));
        exitButton.setPosition( new Vector2( 0.3f, 0));
    }


    @Override
    public boolean mouseMoved(Vector2 position) {
        if(playButton.isInside(position)) {
            playButton.setScale(1.25f);
        }
        else {
            playButton.setScale(1f);
        }
        if(exitButton.isInside(position)) {
            exitButton.setScale(1.25f);
        }
        else {
            exitButton.setScale(1f);
        }
        return true;
    }

    @Override
    public boolean touchDown(Vector2 position, int pointer, int button) {
        if( playButton.isInside(position) ) {
            System.out.println("Play button clicked.");
            game.setScreen(StarShooterGame.ScreenType.GAME);
            return true;
        }
        if( exitButton.isInside(position) ) {
            System.out.println("Exit button clicked.");
            exitButton.setScale(1.25f);
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 position, int pointer, int button) {
        playButton.setScale(1f);
        exitButton.setScale(1f);
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
    }
}
