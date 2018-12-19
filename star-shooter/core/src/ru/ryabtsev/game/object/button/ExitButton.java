package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Exit button.
 */
public final class ExitButton extends MenuButton {
    /**
     * Constructor.
     * @param region   - sprite object texture.
     */
    public ExitButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if( isInside(touchPosition) ) {
            Gdx.app.exit();
        }
        return false;
    }
}
