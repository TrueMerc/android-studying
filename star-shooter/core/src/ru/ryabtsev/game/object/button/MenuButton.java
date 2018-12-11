package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Sprite;

/**
 * Game menu buttons class.
 */
abstract public class MenuButton extends Sprite {

    private static final float DEFAULT_HEIGHT = 0.15f;

    private static final float SCALE_WHEN_SELECTED = 1.25f;

    /**
     * Constructor.
     * @param region - sprite object texture.
     * @param position - position on the screen.
     */
    public MenuButton(final TextureRegion region, final Vector2 position) {
        super(region);
        setHeight(DEFAULT_HEIGHT);
        center.set(position);
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
     * @param touchPosition
     * @return
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
