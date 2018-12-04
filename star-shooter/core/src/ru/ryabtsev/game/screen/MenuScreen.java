package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Game menu class.
 */
public class MenuScreen extends Base2DScreen {

    private static final float HEIGHT_AXIS_SCALE = 50f;
    private static final float KEYBOARD_MOVEMENT_STEP = 0.05f * HEIGHT_AXIS_SCALE;
    private static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f * HEIGHT_AXIS_SCALE;;
    private static final float VELOCITY_SCALE = 0.01f * HEIGHT_AXIS_SCALE;

    private Texture backgroundTexture;
    private Texture spaceShipTexture;

    private Vector2 currentPosition;
    private Vector2 velocity;
    private Vector2 destinationPosition;
    private Vector2 temporary;  // Temporary vector for usage in render() method.

    private float spaceShipTextureBatchHeight;
    private float getSpaceShipTextureBatchWidth;

    public MenuScreen() {
        super(HEIGHT_AXIS_SCALE);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("space_background.png");
        spaceShipTexture = new Texture( "star_ship.png");


        spaceShipTextureBatchHeight = SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;
        getSpaceShipTextureBatchWidth = SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;

        currentPosition = new Vector2( 0 , 0);
        destinationPosition = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);

        resize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       temporary.set(destinationPosition);
        if( temporary.sub(currentPosition).len() > VELOCITY_SCALE ) {
            currentPosition.add(velocity);
        }
        else {
            currentPosition.set(destinationPosition);
        }

        batch.begin();
        batch.draw(backgroundTexture, -.5f * HEIGHT_AXIS_SCALE, -.5f * HEIGHT_AXIS_SCALE, HEIGHT_AXIS_SCALE, HEIGHT_AXIS_SCALE);
        batch.draw(spaceShipTexture, currentPosition.x, currentPosition.y, getSpaceShipTextureBatchWidth, spaceShipTextureBatchHeight);
        batch.end();
    }

    @Override
    public void dispose() {
        spaceShipTexture.dispose();
        backgroundTexture.dispose();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        System.out.println("Screen bounds: width = " + screenBounds.getWidth() + ", y = " + screenBounds.getHeight() );
        System.out.println("Screen coordinates: x = " + screenX + ", y = " + screenY );

        destinationPosition.set( screenX, screenBounds.getHeight() - screenY).mul( screenToWorld );
        velocity.set( destinationPosition.cpy().sub(currentPosition) ).setLength( VELOCITY_SCALE );


        System.out.println("Current coordinates: x = " + currentPosition.x + ", y = " + currentPosition.y );
        System.out.println("Destination coordinates: x = " + destinationPosition.x + ", y = " + destinationPosition.y );
        System.out.println("Velocity = " + velocity);

        return false;
    }




}
