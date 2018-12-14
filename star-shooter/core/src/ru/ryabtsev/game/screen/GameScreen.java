package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.bullet.BulletType;
import ru.ryabtsev.game.object.ship.EnemyShip;
import ru.ryabtsev.game.object.ship.EnemyShipPool;
import ru.ryabtsev.game.object.ship.PlayerShip;
import ru.ryabtsev.game.object.ship.SpaceShipType;
import ru.ryabtsev.game.object.ship.SpaceShip;

import ru.ryabtsev.game.utils.Regions;

/**
 * Game main screen class.
 */
public class GameScreen extends Base2DScreen {

    private static final float KEYBOARD_MOVEMENT_STEP = 0.05f;
    private static final float PLAYER_SPACE_SHIP_SPEED = 0.01f;

    private TextureAtlas gameScreenTextures;

    private BulletPool bulletPool;

    private SpaceShip playerShip;

    private SpaceShipType[] enemyShipTypes = new SpaceShipType[3];
    private EnemyShipPool enemyShips;

    private float enemyResurrectionCounter = 0f;

    public GameScreen(StarShooterGame game) {
        super(game);
        gameScreenTextures = new TextureAtlas( "textures/GameScreen.pack") ;

        bulletPool = new BulletPool();
        initPlayer();
        initEnemies();
    }

    private void initPlayer() {

        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = gameScreenTextures.findRegion("PlayerShip");

        BulletType playerBulletType = new BulletType(
                gameScreenTextures.findRegion("BulletPlayer"), 0.01f,
                new Vector2(0, 0.5f), 1
        );

        SpaceShipType playerShipType = new SpaceShipType( textureRegions,
                playerBulletType, PLAYER_SPACE_SHIP_SPEED, "Simple player space ship"
        );

        playerShip = new PlayerShip(playerShipType, bulletPool, worldBounds);
    }

    private void initEnemies() {
        for(int i = 0; i < enemyShipTypes.length; ++i) {
            BulletType enemyBulletType = new BulletType( gameScreenTextures.findRegion("BulletEnemy"),
                    0.01f * (i + 1), new Vector2(0, -0.3f / (i + 1)), 1 * (i + 1)
            );

            StringBuffer stringBuffer = new StringBuffer("EnemyShip" + i);

            TextureRegion[] textureRegions;
            TextureRegion region = gameScreenTextures.findRegion(stringBuffer.toString());
            textureRegions = Regions.split( region, 1, 2, 2);

            enemyShipTypes[i] = new SpaceShipType( textureRegions,
                    enemyBulletType, PLAYER_SPACE_SHIP_SPEED, "Enemy space ship"
            );
        }
        enemyShips = new EnemyShipPool(enemyShipTypes, bulletPool, worldBounds);
    }



    @Override
    public void show() {
       super.show();
       playerShip.moveTo( new Vector2( 0, worldBounds.getBottom() + playerShip.getHeight()) );
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
        placeNewEnemy( delta );
        playerShip.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyShips.updateActiveSprites(delta);
    }

    private void placeNewEnemy(float delta) {
        enemyResurrectionCounter += delta;
        if( enemyResurrectionCounter > 240f * delta) {
            EnemyShip ship = enemyShips.obtain();
            ship.resize(worldBounds);
            float x = MathUtils.random( worldBounds.getLeft() + ship.getWidth(), worldBounds.getRight() - ship.getWidth());
            ship.moveTo( new Vector2(x, worldBounds.getTop() + ship.getHeight()));
            ship.setDestination( new Vector2( x, worldBounds.getBottom() - 0.1f ) );
            enemyResurrectionCounter = 0f;
        }
    }

    @Override
    public void draw() {
        super.draw();
        batch.begin();
        playerShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyShips.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        gameScreenTextures.dispose();
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
