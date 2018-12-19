package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.bullet.Bullet;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

/**
 * Enemy ships base class.
 */
public class EnemyShip extends SpaceShip implements Destroyable {

    private static final float FAR_SPACE_SPEED = 0.25f;

    private boolean isInsideTheWorld;
    private float fireTimer;

    /**
     * {@inheritDoc}
     */
    public EnemyShip(SpaceShipType type, BulletPool bulletPool, ExplosionPool explosionPool, Rectangle worldBounds) {
        super(type, bulletPool, explosionPool, worldBounds);
        isInsideTheWorld = false;
        fireTimer = 0;
    }

    @Override
    public void update( float delta ) {
        super.update( delta );
        if( isInsideTheWorld ) {
            if( fireTimer > spaceShipType.getWeapon().getReloadingTime() ) {
                this.fire();
                fireTimer = 0f;
            }
            else {
                fireTimer += delta;
            }
        }
        else {
            if( worldBounds.isIntersect( this)) {
                velocity.set( destination.cpy().sub(center) ).setLength( spaceShipType.getSpeed() );
                isInsideTheWorld = true;
            }

        }

        if (!worldBounds.isInside(center) && center.y < worldBounds.getBottom()) {
            destroy();
        }
    }

    /**
     * Destroys the object.
     */
    @Override
    public void destroy() {
        super.destroy();
        isInsideTheWorld = false;
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
        velocity.set( destination.cpy().sub(center) ).setLength( FAR_SPACE_SPEED );
    }

    /**
     * Returns true if bullet hits player space ship or false if it isn't.
     * @param bullet bullet.
     * @return true if bullet hits player space ship or false if it isn't.
     */
    @Override
    public boolean isHit(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < getCenter().y
        );
    }


}
