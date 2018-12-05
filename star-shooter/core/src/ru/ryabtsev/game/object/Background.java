package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;

/**
 * Game background class.
 */
public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rectangle worldBounds) {
        super.setHeight(worldBounds.getHeight());
        center.set( worldBounds.getCenter() );
    }
}
