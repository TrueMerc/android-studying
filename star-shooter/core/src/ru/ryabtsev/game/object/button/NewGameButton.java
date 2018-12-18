package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;

/**
 * Starts new game after old game is over.
 */
public class NewGameButton extends Button {

    private StarShooterGame game;

    /**
     * Constructor.
     * @param region sprite object texture.
     * @param game - game instance.
     */
    public NewGameButton(TextureRegion region, StarShooterGame game) {
        super(region);
        this.game = game;
    }

    /**
     * Performs an action when 'touch' event occurs.
     * @param touchPosition
     * @return
     */
    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if(isInside(touchPosition)) {
            game.setState( StarShooterGame.State.NEW );
            return true;
        }
        return false;
    }
}
