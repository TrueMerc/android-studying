package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.BulletPool;

/**
 * Enemy ships base class.
 */
public class EnemyShip extends SpaceShip {
    /**
     * Constructor.
     * @param region      - space ship elements object texture atlas.
     * @param bulletPool  - pool of bullet objects associated with spaceship.
     * @param worldBounds - bound of the game world.
     */
    public EnemyShip(TextureRegion region, BulletPool bulletPool, Rectangle worldBounds) {
        super(region, bulletPool, worldBounds);
    }
}
