package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.ryabtsev.game.math.Rectangle;

/**
 * Game object for drawing stars.
 */
public class Star extends Sprite {

    private Rectangle worldBounds;

    private static final String STAR_TEXTURE_NAME = "star";
    private static final float DEFAULT_HEIGHT = 0.05f;

    private Vector2 velocity;

    public Star(TextureAtlas atlas) {
        this(atlas.findRegion(STAR_TEXTURE_NAME));
    }

    public Star(TextureRegion region) {
        super(region);
        setHeight(DEFAULT_HEIGHT);
        velocity = new Vector2(MathUtils.random(-0.005f, 0.005f), MathUtils.random(-0.5f, -0.25f));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(final Rectangle worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        float x = MathUtils.random(worldBounds.getLeft(), worldBounds.getRight());
        float y = MathUtils.random(worldBounds.getBottom(), worldBounds.getTop());
        center.set( x, y );
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        center.mulAdd(velocity, delta);
        handleBounds();
    }

    private void handleBounds() {
        if( getRight() < worldBounds.getLeft()) { setLeft(worldBounds.getRight()); }
        if( getLeft() > worldBounds.getRight()) { setLeft(worldBounds.getLeft()); }
        if( getTop() < worldBounds.getBottom()) { setBottom(worldBounds.getTop()); }
        // The fourth case is unnecessary because stars always move down.
    }
}
