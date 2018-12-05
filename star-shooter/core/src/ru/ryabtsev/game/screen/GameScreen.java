package ru.ryabtsev.game.screen;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Game main screen class.
 */
public class GameScreen extends Base2DScreen {

    private Texture spaceShipTexture;

    private Vector2 currentPosition;
    private Vector2 velocity;
    private Vector2 destinationPosition;
    private Vector2 temporary;  // Temporary vector for usage in render() method.

    private float spaceShipTextureBatchHeight;
    private float getSpaceShipTextureBatchWidth;

    public GameScreen() {
        super(HEIGHT_AXIS_SCALE);
    }

    @Override
    public void show() {
        super.show();

        spaceShipTexture = new Texture( "star_ship.png");

        spaceShipTextureBatchHeight = SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;
        getSpaceShipTextureBatchWidth = SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;

        currentPosition = new Vector2( 0 , 0);
        destinationPosition = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        temporary.set(destinationPosition);
        if( temporary.sub(currentPosition).len() > VELOCITY_SCALE ) {
            currentPosition.add(velocity);
        }
        else {
            currentPosition.set(destinationPosition);
        }

        batch.begin();
        batch.draw(spaceShipTexture, currentPosition.x, currentPosition.y, getSpaceShipTextureBatchWidth, spaceShipTextureBatchHeight);
        batch.end();
    }

    @Override
    public void dispose() {
        spaceShipTexture.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        destinationPosition.set( currentPosition ); // Break current movement if it is present.
        System.out.println("keyDown: currentPosition:" + currentPosition);
        switch(keycode) {
            case Input.Keys.DOWN:
                destinationPosition.sub( 0, KEYBOARD_MOVEMENT_STEP);
                break;
            case Input.Keys.LEFT:
                destinationPosition.sub( KEYBOARD_MOVEMENT_STEP, 0);
                break;
            case Input.Keys.RIGHT:
                destinationPosition.add( KEYBOARD_MOVEMENT_STEP, 0 );
                break;
            case Input.Keys.UP:
                destinationPosition.add( 0, KEYBOARD_MOVEMENT_STEP);
                break;
        }
        System.out.println("keyDown: destinationPosition:" + destinationPosition);
        velocity.set(destinationPosition.cpy().sub(currentPosition)).setLength(VELOCITY_SCALE);
        System.out.println("velocity: " + velocity);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//
//        System.out.println("Screen bounds: width = " + screenBounds.getWidth() + ", y = " + screenBounds.getHeight() );
//        System.out.println("Screen coordinates: x = " + screenX + ", y = " + screenY );
//
//
//        velocity.set( destinationPosition.cpy().sub(currentPosition) ).setLength( VELOCITY_SCALE );
//
//
//        System.out.println("Current coordinates: x = " + currentPosition.x + ", y = " + currentPosition.y );
//        System.out.println("Destination coordinates: x = " + destinationPosition.x + ", y = " + destinationPosition.y );
//        System.out.println("Velocity = " + velocity);
//
//        return false;
//    }


    @Override
    public boolean touchDown(Vector2 position, int pointer, int button) {
        destinationPosition.set( position );
        velocity.set( destinationPosition.cpy().sub(currentPosition) ).setLength( VELOCITY_SCALE );
        System.out.println("Current coordinates: x = " + currentPosition.x + ", y = " + currentPosition.y );
        System.out.println("Destination coordinates: x = " + destinationPosition.x + ", y = " + destinationPosition.y );
        System.out.println("Velocity = " + velocity);
        return true;
    }
}
