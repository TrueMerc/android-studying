package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;

public class MenuButton extends Sprite {


    private static final float DEFAULT_HEIGHT = 0.3f;

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


}
