package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.BulletPool;

/**
 * Player ship class.
 */
public class PlayerShip extends SpaceShip {
    /**
     * Constructor.
     *
     * @param region      - space ship elements object texture atlas.
     * @param bulletPool  - pool of bullet objects associated with spaceship.
     * @param worldBounds - bound of the game world.
     */
    public PlayerShip(TextureRegion region, BulletPool bulletPool, Rectangle worldBounds) {
        super(region, bulletPool, worldBounds);
    }
}
