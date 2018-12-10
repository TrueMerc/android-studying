package ru.ryabtsev.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Exit button.
 */
public class ExitButton extends MenuButton {
    /**
     * Constructor.
     * @param region   - sprite object texture.
     * @param position - position on the screen.
     */
    public ExitButton(TextureRegion region, Vector2 position) {
        super(region, position);
    }

    @Override
    public boolean onTouchDown(Vector2 touchPosition) {
        if( isInside(touchPosition) ) {
            Gdx.app.exit();
        }
        return false;
    }
}
