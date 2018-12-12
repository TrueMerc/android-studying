package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.BulletPool;

/**
 * Player ship class.
 */
public class PlayerShip extends SpaceShip {
    /**
     * {@inheritDoc}
     */
    public PlayerShip(SpaceShipType type, BulletPool bulletPool, Rectangle worldBounds) {
        super(type, bulletPool, worldBounds);
    }
}
