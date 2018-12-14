package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.bullet.Bullet;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.Sprite;

/**
 * Base class for all space ships in the game.
 */
public class SpaceShip extends Sprite {

    private static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f;
    protected static final float VELOCITY_SCALE = 0.001f;

    private SpaceShipType spaceShipType;
    private BulletPool bulletPool;
    protected Rectangle worldBounds;
    protected Vector2 velocity;
    protected Vector2 destination;
    protected Vector2 temporary;  // Temporary vector for usage in update() method.


    private Sound fireSound;

    /**
     * Constructor.
     * @param type - space ship type.
     * @param bulletPool - pool of bullet objects associated with spaceship.
     * @param worldBounds - bound of the game world.
     */
    public SpaceShip(SpaceShipType type, BulletPool bulletPool, Rectangle worldBounds) {
        super(type.getTextureRegion(0));
        spaceShipType = type;
        center.set( 0, 0);
        this.worldBounds = worldBounds;

        destination = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);

        this.bulletPool = bulletPool;

        fireSound = Gdx.audio.newSound( Gdx.files.internal("sounds/laser-shoot.wav"));
    }

    @Override
    public void update(float delta) {
        temporary.set(destination);
        if( temporary.sub(center).len() > VELOCITY_SCALE ) {
            center.add(velocity);
        }
        else {
            center.set(destination);
        }
    }

    @Override
    public void resize(final Rectangle worldBounds) {
        super.resize(worldBounds);
        super.setHeight(SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR);
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
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, center, spaceShipType.getBulletType(), worldBounds);
        fireSound.play(0.75f);
    }

    /**
     * Stops space ship.
     */
    public void stop() {
        velocity.set( 0f, 0f);
    }


    public void dispose() {
        fireSound.dispose();
    }
}
