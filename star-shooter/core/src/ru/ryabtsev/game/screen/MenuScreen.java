package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.object.MenuButton;
import ru.ryabtsev.game.object.Star;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private static final int STARS_COUNT = 100;

    private TextureAtlas textureAtlas;
    private Star[] stars;
    private MenuButton playButton;
    private MenuButton exitButton;

    public MenuScreen() {
        super(HEIGHT_AXIS_SCALE);
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
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
    }
}
