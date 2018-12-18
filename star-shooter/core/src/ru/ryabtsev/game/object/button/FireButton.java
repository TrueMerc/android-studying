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
     * @param gameScreen game screen.
     */
    public FireButton(final TextureRegion region, final GameScreen gameScreen) {
        super(region);
        this.gameScreen = gameScreen;
    }

    /**
     * Performs player ship fire.
     * @param touchPosition touch position.
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
