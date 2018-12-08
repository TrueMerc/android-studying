package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;

/**
 * Base class for all space ships in the game.
 */
public class SpaceShip extends Sprite {

    private static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f;
    private static final float VELOCITY_SCALE = 0.01f;

    private Vector2 velocity;
    private Vector2 destination;
    private Vector2 temporary;  // Temporary vector for usage in update() method.

    /**
     * Constructor.
     * @param region - sprite object texture.
     */
    public SpaceShip(TextureRegion region) {
        super(region);
        center.set( new Vector2(0f, 0f) );

        destination = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);
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
        destination.set( position );
        velocity.set( destination.cpy().sub(center) ).setLength( VELOCITY_SCALE );
        System.out.println("Current coordinates: " + center );
        System.out.println("Destination coordinates: " + destination );
        System.out.println("Velocity = " + velocity);
    }
}
