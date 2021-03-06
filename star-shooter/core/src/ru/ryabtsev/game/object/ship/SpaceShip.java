package ru.ryabtsev.game.object.ship;


import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.Weapon;
import ru.ryabtsev.game.object.bullet.Bullet;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.Sprite;
import ru.ryabtsev.game.object.explosion.Explosion;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

/**
 * Base class for all space ships in the game.
 */
public abstract class SpaceShip extends Sprite implements Destroyable {

    protected static final float VELOCITY_SCALE = 0.001f;

    protected SpaceShipType spaceShipType;
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    protected Rectangle worldBounds;
    protected Vector2 velocity;
    protected Vector2 destination;
    protected Vector2 temporary;  // Temporary vector for usage in update() method.

    protected int hitPoints;
    /**
     * Constructor.
     * @param type - space ship type.
     * @param bulletPool - pool of bullet objects associated with spaceship.
     * @param worldBounds - bound of the game world.
     */
    public SpaceShip(SpaceShipType type, BulletPool bulletPool, ExplosionPool explosionPool, Rectangle worldBounds) {
        super(type.getTextureRegion(0));
        spaceShipType = type;
        center.set( 0, 0);
        this.worldBounds = worldBounds;

        destination = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);

        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        hitPoints = type.getHitPoints();
    }

    @Override
    public void update(float delta) {
        temporary.set(destination);
        if( temporary.sub(center).len() > spaceShipType.getSpeed() ) {
            center.add(velocity);
        }
        else {
            center.set(destination);
        }
    }

    @Override
    public void resize(final Rectangle worldBounds) {
        super.resize(worldBounds);
        super.setHeight(spaceShipType.getHeight());
    }

    public void setDestination(final Vector2 position) {
        stop();
        destination.set( handleBounds(position) );
        velocity.set( destination.cpy().sub(center) ).setLength( VELOCITY_SCALE );

        System.out.println("Current coordinates: " + center );
        System.out.println("Destination coordinates: " + destination );
        System.out.println("Velocity = " + velocity);
    }

    public void moveTo( final Vector2 position ) {
        stop();
        if( worldBounds.isIntersect(this ) && !worldBounds.isInside(position) ) {
            return;
        }
        handleBounds( position );
        center.set( position );
    }

    private Vector2 handleBounds(final Vector2 position) {
        if(worldBounds.isInside( this.clone().move(position) )) {
            return position;
        }
        else {
            float x = position.x;
            float y = position.y;
            float halfWidth = getWidth() / 2f;
            float halfHeight = getHeight() / 2f;
            if( x - halfWidth < worldBounds.getLeft()) { x = worldBounds.getLeft() + halfWidth; }
            if( x + halfWidth > worldBounds.getRight()) { x = worldBounds.getRight() - halfWidth; }
            if( y - halfHeight < worldBounds.getBottom()) { y = worldBounds.getBottom() + halfHeight; }
            if( y + halfHeight > worldBounds.getTop()) { y = worldBounds.getTop() - halfHeight; }
            return new Vector2(x, y);
        }
    }

    /**
     * Starts fire.
     */
    public void fire() {
        Weapon weapon = spaceShipType.getWeapon();
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, center, weapon.getBulletType(), worldBounds);
        weapon.getSound().play(0.75f);
    }

    /**
     * Stops space ship.
     */
    public void stop() {
        velocity.set( 0f, 0f);
    }

    /**
     * Returns space ship hit points number.
     * @return space ship hit points number.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Damages space ship on given hit points number.
     */
    public void damage(int hitPoints) {
        this.hitPoints -= hitPoints;
        if(this.hitPoints <= 0) {
            System.out.println("Space ship destroyed:" + spaceShipType.getDescription());
            destroy();
            explode();
        }
    }

    /**
     * @return true if the object destroyed and false if it isn't destroyed.
     */
    @Override
    public boolean isDestroyed() {
        return hitPoints <= 0;
    }

    /**
     * Destroys the object.
     */
    @Override
    public void destroy() {
        hitPoints = 0;
    }

    /**
     * Explodes the object.
     */
    public void explode() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), getCenter());
    }

    /**
     * Makes object alive.
     */
    @Override
    public void alive() {
        hitPoints = spaceShipType.getHitPoints();
    }

    /**
     * Returns true if bullet hits player space ship or false if it isn't.
     * @param bullet bullet.
     * @return true if bullet hits player space ship or false if it isn't.
     */
     public abstract boolean isHit(Bullet bullet);
}
