package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.ExitButton;
import ru.ryabtsev.game.object.MenuButton;
import ru.ryabtsev.game.object.PlayButton;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private TextureAtlas textureAtlas;

    private MenuButton playButton;
    private MenuButton exitButton;

    public MenuScreen(StarShooterGame game) {
        super(game, HEIGHT_AXIS_SCALE);
        textureAtlas = new TextureAtlas("textures/menuAtlas.tpack");

        playButton = new PlayButton( textureAtlas.findRegion("btPlay"), new Vector2( -0.3f, 0), game );
        exitButton = new ExitButton( textureAtlas.findRegion("btExit"), new Vector2( 0.3f, 0) );
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

    @Override
    public void draw() {
        super.draw();
        batch.begin();
        playButton.draw(batch);
        exitButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        playButton.resize(worldBounds);
        exitButton.resize(worldBounds);
        playButton.setPosition(new Vector2( -0.3f, 0));
        exitButton.setPosition( new Vector2( 0.3f, 0));
    }

    @Override
    public boolean mouseMoved(Vector2 position) {
        playButton.onSelect(position);
        exitButton.onSelect(position);
        return true;
    }

    @Override
    public boolean touchDown(Vector2 position, int pointer, int button) {
        playButton.onTouchDown(position);
        exitButton.onTouchDown(position);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 position, int pointer, int button) {
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas.dispose();
    }
}
