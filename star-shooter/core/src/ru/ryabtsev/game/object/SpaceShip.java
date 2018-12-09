package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;

/**
 * Base class for all space ships in the game.
 */
public class SpaceShip extends Sprite {

    private static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f;
    private static final float VELOCITY_SCALE = 0.01f;

    private Rectangle worldBounds;
    private Vector2 velocity;
    private Vector2 destination;
    private Vector2 temporary;  // Temporary vector for usage in update() method.

    private BulletPool bulletPool;
    private TextureAtlas atlas;

    /**
     * Constructor.
     * @param region - space ship elements object texture atlas.
     * @param bulletPool - pool of bullet objects associated with spaceship.
     * @param worldBounds - bound of the game world.
     */
    public SpaceShip(TextureRegion region, BulletPool bulletPool, Rectangle worldBounds) {
        super(region);
        center.set( new Vector2(0f, 0f) );
        this.worldBounds = worldBounds;

        destination = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);

        atlas = new TextureAtlas("mainAtlas.tpack");
        this.bulletPool = bulletPool;
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
        bullet.set(this, atlas.findRegion("bulletMainShip"), center, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    }

    /**
     * Stops space ship.
     */
    public void stop() {
        destination.set(center);
    }

}
