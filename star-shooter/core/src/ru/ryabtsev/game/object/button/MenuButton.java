package ru.ryabtsev.game.object.button;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class MenuButton extends Button {

    protected static final float DEFAULT_HEIGHT = 0.3f;

    /**
     * Creates button with given texture region and height.
     * @param region region which contains sprite object texture.
     * @param height button height in the game world coordinates.
     */
    MenuButton(TextureRegion region, float height) {
        super(region, height);
    }

    /**
     * Creates button with given texture region and height.
     * @param region region which contains sprite object texture.
     */
    MenuButton(TextureRegion region) {
        this(region, DEFAULT_HEIGHT);
    }
}
