package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;

public class MenuButton extends Sprite {


    private static final float DEFAULT_HEIGHT = 0.3f;

    public MenuButton(TextureRegion region) {
        super(region);
        setHeight(DEFAULT_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(final Rectangle worldBounds) {
        super.resize(worldBounds);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    /**
     * Constructor.
     *
     * @param region - sprite object texture.
     */
    public MenuButton(TextureRegion region) {
        super(region);
    }
}
