package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.screen.GameScreen;

/**
 * Button which moves players ship left.
 */
public class MoveRightButton extends Button {

    private GameScreen gameScreen;

    /**
     * Constructor.
     * @param region texture region correspoinding button image.
     * @param position position of button center on the screen.
     * @param gameScreen game screen.
     */
    public MoveRightButton(final TextureRegion region, final Vector2 position, final GameScreen gameScreen) {
        super(region, position);
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
            gameScreen.moveRight();
            return true;
        }
        else {
            return false;
        }
    }
}
