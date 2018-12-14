package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

/**
 * Player ship class.
 */
public class PlayerShip extends SpaceShip {
    /**
     * {@inheritDoc}
     */
    public PlayerShip(SpaceShipType type, BulletPool bulletPool, ExplosionPool explosionPool, Rectangle worldBounds) {
        super(type, bulletPool, explosionPool, worldBounds);
    }
}
