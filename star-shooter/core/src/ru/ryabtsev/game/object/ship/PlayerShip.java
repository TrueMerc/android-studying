package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.Bullet;
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

    /**
     * Returns true if bullet hits player space ship or false if it isn't.
     * @param bullet
     * @return true if bullet hits player space ship or false if it isn't.
     */
    @Override
    public boolean isHit(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getCenter().y
                || bullet.getTop() < getBottom()
        );
    }
}
