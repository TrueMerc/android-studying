package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.ship.PlayerShip;
import ru.ryabtsev.game.object.ship.SpaceShip;

/**
 * Game main screen class.
 */
public class GameScreen extends Base2DScreen {

    private static final float KEYBOARD_MOVEMENT_STEP = 0.05f * HEIGHT_AXIS_SCALE;

    private Texture spaceShipTexture;

    private BulletPool bulletPool;
    private SpaceShip playerShip;

    public GameScreen(StarShooterGame game) {
        super(game, HEIGHT_AXIS_SCALE);
        spaceShipTexture = new Texture( "textures/star_ship.png");
        bulletPool = new BulletPool();
        playerShip = new PlayerShip(new TextureRegion(spaceShipTexture), bulletPool, worldBounds);
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
        playerShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }

    @Override
    public void draw() {
        super.draw();
        batch.begin();
        playerShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        spaceShipTexture.dispose();
        spaceShipTexture.dispose();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        playerShip.resize(worldBounds);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.DOWN:
            case Input.Keys.S:
                playerShip.setDestination( playerShip.getCenter().sub( 0, KEYBOARD_MOVEMENT_STEP) );
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                playerShip.setDestination( playerShip.getCenter().sub( KEYBOARD_MOVEMENT_STEP, 0) );
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                playerShip.setDestination( playerShip.getCenter().add( KEYBOARD_MOVEMENT_STEP, 0 ) );
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                playerShip.setDestination( playerShip.getCenter().add( 0,  KEYBOARD_MOVEMENT_STEP) );
                break;
            case Input.Keys.M:
                game.setScreen( StarShooterGame.ScreenType.MENU );
                return true;
            case Input.Keys.SPACE:
                playerShip.fire();
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
        playerShip.setDestination(position);
        return true;
    }

    @Override
    public boolean touchDragged(Vector2 position, int pointer) {
        if( playerShip.isInside( position )) {
            playerShip.moveTo(position);
            return true;
        }
        return false;
    }
}
