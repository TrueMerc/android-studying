package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.bullet.BulletPool;

/**
 * Enemy ships base class.
 */
public class EnemyShip extends SpaceShip implements Destroyable {

    private boolean isDestroyed;

    /**
     * {@inheritDoc}
     */
    public EnemyShip(SpaceShipType type, BulletPool bulletPool, Rectangle worldBounds) {
        super(type, bulletPool, worldBounds);
        velocity.set( 0, 0);
    }


    @Override
    public void update( float delta ) {
        super.update( delta );
        if (!worldBounds.isInside(center)) {
            destroy();
        }
    }


    @Override
    public void setDestination(final Vector2 position) {
        stop();
        destination.set( position );
        velocity.set( destination.cpy().sub(center) ).setLength( VELOCITY_SCALE );

        System.out.println("Current coordinates: " + center );
        System.out.println("Destination coordinates: " + destination );
        System.out.println("Velocity = " + velocity);
    }

    /**
     * @return true if the object destroyed and false if it isn't destroyed.
     */
    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Destroys the object.
     */
    @Override
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Makes object alive.
     */
    @Override
    public void alive() {
        isDestroyed = false;
    }
}
