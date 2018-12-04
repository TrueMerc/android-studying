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

    private static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f;
    private static final float VELOCITY_SCALE = 0.5f;

    private Texture backgroundTexture;
    private Texture spaceShipTexture;

    private Vector2 currentPosition;
    private Vector2 velocity;
    private Vector2 destinationPosition;
    private Vector2 temporary;  // Temporary vector for usage in render() method.

    private float spaceShipTextureBatchHeight;
    private float getSpaceShipTextureBatchWidth;

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("space_background.png");
        spaceShipTexture = new Texture( "star_ship.png");

        spaceShipTextureBatchHeight = spaceShipTexture.getHeight() * SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;
        getSpaceShipTextureBatchWidth = spaceShipTexture.getWidth() * SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR;

        currentPosition = new Vector2( 0 , 0);
        destinationPosition = new Vector2( 0, 0);
        velocity = new Vector2( 0, 0);
        temporary = new Vector2( 0, 0);

        Gdx.input.setInputProcessor(this);
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
        batch.draw(backgroundTexture, 0, 0);
        batch.draw(spaceShipTexture, currentPosition.x, currentPosition.y, getSpaceShipTextureBatchWidth, spaceShipTextureBatchHeight );
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
        destinationPosition.set( currentPosition );
        switch(keycode) {
            case Input.Keys.DOWN:
                destinationPosition.sub( 0, 10);
                break;
            case Input.Keys.LEFT:
                destinationPosition.sub( 10, 0);
                break;
            case Input.Keys.RIGHT:
                destinationPosition.add( 10, 0 );
                break;
            case Input.Keys.UP:
                destinationPosition.add( 0, 10);
                break;
        }
        velocity.set(destinationPosition.cpy().sub(currentPosition)).setLength(VELOCITY_SCALE);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destinationPosition.set( screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("Screen coordinates: x = " + screenX + ", y = " + screenY );
        System.out.println("Texture coordinates: x = " + destinationPosition.x + ", y = " + destinationPosition.y );

        velocity.set( destinationPosition.cpy().sub(currentPosition).setLength(VELOCITY_SCALE) );
        return super.touchDown(screenX, screenY, pointer, button);
    }




}
