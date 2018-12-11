package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.BulletPool;

/**
 * Enemy ships base class.
 */
public class EnemyShip extends SpaceShip {
    /**
     * {@inheritDoc}
     */
    public EnemyShip(SpaceShipType type, BulletPool bulletPool, Rectangle worldBounds) {
        super(type, bulletPool, worldBounds);
    }
}
