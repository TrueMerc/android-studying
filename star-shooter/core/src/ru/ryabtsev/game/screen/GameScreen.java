package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.BulletPool;
import ru.ryabtsev.game.object.SpaceShip;

/**
 * Game main screen class.
 */
public class GameScreen extends Base2DScreen {
    private Texture spaceShipTexture;

    private BulletPool bulletPool;
    private SpaceShip spaceShip;

    public GameScreen(StarShooterGame game) {
        super(game, HEIGHT_AXIS_SCALE);
        spaceShipTexture = new Texture( "textures/star_ship.png");
        bulletPool = new BulletPool();
        spaceShip = new SpaceShip( new TextureRegion(spaceShipTexture), bulletPool, worldBounds );
    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
    }

    private void checkCollisions() {
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        spaceShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }


    @Override
    public void draw() {
        super.draw();
        batch.begin();
        spaceShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        spaceShipTexture.dispose();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        spaceShip.resize(worldBounds);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.DOWN:
                spaceShip.setDestination( spaceShip.getCenter().sub( 0, KEYBOARD_MOVEMENT_STEP) );
                break;
            case Input.Keys.LEFT:
                spaceShip.setDestination( spaceShip.getCenter().sub( KEYBOARD_MOVEMENT_STEP, 0) );
                break;
            case Input.Keys.RIGHT:
                spaceShip.setDestination( spaceShip.getCenter().add( KEYBOARD_MOVEMENT_STEP, 0 ) );
                break;
            case Input.Keys.UP:
                spaceShip.setDestination( spaceShip.getCenter().add( 0,  KEYBOARD_MOVEMENT_STEP) );
                break;
            case Input.Keys.M:
                game.setScreen( StarShooterGame.ScreenType.MENU );
                return true;
            case Input.Keys.SPACE:
                spaceShip.fire();
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 position, int pointer, int button) {
        spaceShip.setDestination(position);
        return true;
    }

    @Override
    public boolean touchDragged(Vector2 position, int pointer) {
        if( spaceShip.isInside( position )) {
            spaceShip.moveTo(position);
            return true;
        }
        return false;
    }
}
