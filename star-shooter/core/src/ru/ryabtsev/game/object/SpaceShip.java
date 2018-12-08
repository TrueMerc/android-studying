package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Base class for all space ships in the game.
 */
public class SpaceShip extends Sprite {
    /**
     * Constructor.
     *
     * @param region - sprite object texture.
     */
    public SpaceShip(TextureRegion region) {
        super(region);
    }
}
