package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.screen.GameScreen;

/**
 * Button which moves players ship left.
 */
public final class MoveLeftButton extends ControlButton {

    private GameScreen gameScreen;

    /**
     * Constructor.
     * @param region texture region corresponding button image.
     * @param gameScreen game screen.
     */
    public MoveLeftButton(final TextureRegion region, final GameScreen gameScreen) {
        super(region);
        this.gameScreen = gameScreen;
    }

    /**
     * Moves player ship left when touched.
     * @param touchPosition touch position.
     * @return
     */
    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if( isInside(touchPosition)) {
            gameScreen.moveShipLeft();
            return true;
        }
        else {
            return false;
        }
    }
}
