package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ControlButton extends Button {

    protected static final float DEFAULT_HEIGHT = 0.15f;

    /**
     * Creates control button with given texture region and height.
     * @param region region which contains sprite object texture.
     * @param height button height in the game world coordinates.
     */
    ControlButton(TextureRegion region, float height) {
        super(region, height);
    }

    /**
     * Creates control button with given texture region and height.
     * @param region region which contains sprite object texture.
     */
    ControlButton(TextureRegion region) {
        this(region, DEFAULT_HEIGHT);
    }
}
