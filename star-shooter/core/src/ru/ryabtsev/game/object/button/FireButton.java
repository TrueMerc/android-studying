package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.screen.GameScreen;

/**
 * Game screen button which controls player space ship fire.
 */
public class FireButton extends Button {

    private GameScreen gameScreen;

    /**
     * Constructor.
     * @param region texture region correspoinding button image.
     * @param position position of button center on the screen.
     * @param
     */
    public FireButton(final TextureRegion region, final Vector2 position, final GameScreen gameScreen) {
        super(region, position);
        this.gameScreen = gameScreen;
    }

    /**
     * Performs an action when 'touch' event occurs.
     * @param touchPosition
     * @return
     */
    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if(isInside(touchPosition)) {
            gameScreen.getPlayerShip().fire();
            return true;
        }
        return false;
    }
}
