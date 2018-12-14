package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

/**
 * Enemy ships base class.
 */
public class EnemyShip extends SpaceShip implements Destroyable {

    private boolean isDestroyed;
    private boolean openFire;
    private float fireCounter;

    /**
     * {@inheritDoc}
     */
    public EnemyShip(SpaceShipType type, BulletPool bulletPool, ExplosionPool explosionPool, Rectangle worldBounds) {
        super(type, bulletPool, explosionPool, worldBounds);
        velocity.set( 0, 0);
        openFire = false;
        fireCounter = 0;
    }


    @Override
    public void update( float delta ) {
        super.update( delta );
        if( openFire && fireCounter > spaceShipType.getWeapon().getReloadingTime()) {
            this.fire();
            fireCounter = 0f;
        }
        else {
            if( worldBounds.isInside(this) ) {
                openFire = true;
            }
            fireCounter += delta;
        }

        if (!worldBounds.isInside(center) && center.y < worldBounds.getBottom()) {
            destroy();
        }
    }

    @Override
    public void moveTo( final Vector2 position ) {
        stop();
        center.set( position );
    }

    @Override
    public void setDestination(final Vector2 position) {
        stop();
        destination.set( position );
        velocity.set( destination.cpy().sub(center) ).setLength( spaceShipType.getSpeed() );

//        System.out.println("Current coordinates: " + center );
//        System.out.println("Destination coordinates: " + destination );
//        System.out.println("Velocity = " + velocity);
    }
}
