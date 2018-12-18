package ru.ryabtsev.game.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.button.ExitButton;
import ru.ryabtsev.game.object.button.Button;
import ru.ryabtsev.game.object.button.PlayButton;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private TextureAtlas textureAtlas;

    private Button playButton;
    private Button exitButton;

    /**
     * Constructor
     * @param game - 'Star Shooter' game instance.
     */
    public MenuScreen(StarShooterGame game) {
        super(game);
        textureAtlas = new TextureAtlas("textures/MenuScreen.pack");
        playButton = new PlayButton(
                textureAtlas.findRegion("ButtonPlay"),
                game
        );
        exitButton = new ExitButton(
                textureAtlas.findRegion("ButtonExit")
        );
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
        playButton.setPosition(new Vector2( -0.25f * worldBounds.getWidth(), 0));
        exitButton.setPosition(new Vector2( 0.25f * worldBounds.getWidth(), 0));
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
