package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.object.Star;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private static final int STARS_COUNT = 100;

    private TextureAtlas textureAtlas;
    private Star[] stars;

    public MenuScreen() {
        super(HEIGHT_AXIS_SCALE);
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        stars = new Star[STARS_COUNT];
        for(int i = 0; i < stars.length; ++i) {
            stars[i] = new Star(textureAtlas);
        }
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
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        for(int i = 0; i < stars.length; ++i) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
    }
}
