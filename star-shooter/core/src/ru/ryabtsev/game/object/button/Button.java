package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Sprite;

/**
 * Game menu buttons class.
 */
abstract public class Button extends Sprite {

    private static final float SCALE_WHEN_SELECTED = 1.25f;

    /**
     * Creates button with given texture region and height.
     * @param region region which contains sprite object texture.
     * @param height button height in the game world coordinates.
     */
    Button(final TextureRegion region, float height) {
        super(region);
        setHeight(height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(final Rectangle worldBounds) {
        super.resize(worldBounds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(float delta) {
        super.update(delta);
    }

    /**
     * Sets menu button position on the screen.
     *
     * */
    public void setPosition(final Vector2 position) {
        center.set(position);
    }

    /**
     * Performs an action when 'touch' event occurs.
     * @param touchPosition touch position.
     * @return true if button is touched down and false if it isn't.
     */
    abstract public boolean onTouchDown(final Vector2 touchPosition);

    /**
     * Performs an action when 'selection' even occurs.
     * @return true - if button is selected or false if it isn't.
     */
    public boolean onSelect(final Vector2 touchPosition) {
        if (isInside(touchPosition)) {
            setScale(SCALE_WHEN_SELECTED);
            return true;
        }
        else {
            setScale(1f);
            return false;
        }
    }
}
