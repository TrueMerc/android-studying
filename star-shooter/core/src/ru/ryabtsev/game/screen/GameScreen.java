package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.Sprite;
import ru.ryabtsev.game.object.Weapon;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.bullet.BulletType;
import ru.ryabtsev.game.object.button.Button;
import ru.ryabtsev.game.object.button.FireButton;
import ru.ryabtsev.game.object.button.MoveLeftButton;
import ru.ryabtsev.game.object.button.MoveRightButton;
import ru.ryabtsev.game.object.button.NewGameButton;
import ru.ryabtsev.game.object.explosion.ExplosionPool;
import ru.ryabtsev.game.object.ship.EnemyShip;
import ru.ryabtsev.game.object.ship.EnemyShipPool;
import ru.ryabtsev.game.object.ship.PlayerShip;
import ru.ryabtsev.game.object.ship.SpaceShipType;
import ru.ryabtsev.game.object.ship.SpaceShip;

import ru.ryabtsev.game.text.Font;
import ru.ryabtsev.game.utils.Collisions;
import ru.ryabtsev.game.utils.Regions;

/**
 * Game main screen class.
 */
public class GameScreen extends Base2DScreen {

    private static final float KEYBOARD_MOVEMENT_STEP = 0.5f;
    private static final float PLAYER_SPACE_SHIP_SPEED = 0.001f;
    private static final float NEXT_LEVEL_FRAG_SCALE = 0.5f;

    private static final float DEFAULT_FONT_SIZE = 0.025f;
    private static final String FRAGS_LABEL = "Frags: ";
    private static final String HIT_POINTS_LABEL = "HP: ";
    private static final String LEVEL_LABEL = "Level: ";

    private TextureAtlas gameScreenTextures;

    private BulletPool bulletPool;
    private Sound fireSound;
    private Sound explosionSound;
    private ExplosionPool explosionPool;

    private SpaceShip playerShip;

    private SpaceShipType[] enemyShipTypes = new SpaceShipType[3];
    private EnemyShipPool enemyShips;

    private Button fireButton;
    private Button moveLeftButton;
    private Button moveRightButton;
    private Button newGameButton;
    private Sprite gameOverMessage;

    private Font font;
    private StringBuilder stringBuilder;

    private float enemyResurrectionCounter = 0f;

    private int frags = 0;
    private int level = 0;
    private int nextLevelFrags = 5;

    /**
     * Constructor
     * @param game game instance.
     */
    public GameScreen(StarShooterGame game) {
        super(game);

        initResources();
        initPlayer();
        initEnemies();
    }

    private void initResources() {
        gameScreenTextures = new TextureAtlas( "textures/GameScreen.pack") ;

        gameOverMessage = new Sprite(gameScreenTextures.findRegion("MessageGameOver"));
        gameOverMessage.setHeight(0.1f);

        newGameButton = new NewGameButton(
                gameScreenTextures.findRegion("ButtonNewGame"),
                game
        );
        newGameButton.setHeight(0.05f);

        fireButton = new FireButton(
                gameScreenTextures.findRegion("ButtonFire"),
                this
        );
        fireButton.setHeight(0.15f);

        moveLeftButton = new MoveLeftButton(
                gameScreenTextures.findRegion("Triangle"),
                this
        );
        moveLeftButton.rotate(270f);

        moveRightButton = new MoveRightButton(
                gameScreenTextures.findRegion("Triangle"),
                this
        );
        moveRightButton.rotate(90f);

        bulletPool = new BulletPool();
        fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser-shoot.wav"));

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        explosionPool = new ExplosionPool(gameScreenTextures, explosionSound);

        font = new Font("fonts/font.fnt", "fonts/font.png");
        font.resize( DEFAULT_FONT_SIZE );

        stringBuilder = new StringBuilder();
    }


    private void initPlayer() {
        TextureRegion[] textureRegions = new TextureRegion[1];
        textureRegions[0] = gameScreenTextures.findRegion("PlayerShip");

        BulletType bulletType = new BulletType(
                gameScreenTextures.findRegion("BulletPlayer"), 0.01f,
                new Vector2(0, 0.5f), 5
        );

        Weapon weapon = new Weapon(bulletType, fireSound, 0.5f);

        SpaceShipType playerShipType = new SpaceShipType( textureRegions,
                weapon, 0.2f, 2* PLAYER_SPACE_SHIP_SPEED, 40, "Player space ship"
        );

        playerShip = new PlayerShip(playerShipType, bulletPool, explosionPool, worldBounds);
    }

    private void initEnemies() {
        for(int i = 0; i < enemyShipTypes.length; ++i) {
            BulletType bulletType = new BulletType( gameScreenTextures.findRegion("BulletEnemy"),
                    0.01f * (i + 1), new Vector2(0, -0.3f / (i + 1)), 5 * (i + 1)
            );

            Weapon weapon = new Weapon(bulletType, fireSound, 3f + 0.5f * i);

            StringBuffer stringBuffer = new StringBuffer("EnemyShip" + i);

            TextureRegion[] textureRegions;
            TextureRegion region = gameScreenTextures.findRegion(stringBuffer.toString());
            textureRegions = Regions.split( region, 1, 2, 2);

            enemyShipTypes[i] = new SpaceShipType( textureRegions,
                    weapon, 0.15f * (1f + 0.25f * i), PLAYER_SPACE_SHIP_SPEED / (i + 1), 10 * (i + 1), "Enemy space ship"
            );
        }
        enemyShips = new EnemyShipPool(enemyShipTypes, bulletPool, explosionPool, worldBounds);
    }

    @Override
    public void show() {
       super.show();
       if( game.getState() == StarShooterGame.State.NEW) {
           playerShip.moveTo(new Vector2(0, worldBounds.getBottom() + 2 * playerShip.getHeight()));
       }
       if( game.getState() != StarShooterGame.State.OVER ) {
           game.setState(StarShooterGame.State.PLAYING);
       }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if( game.getState() == StarShooterGame.State.PLAYING) {
            update(delta);
            processCollisions();
            deleteAllDestroyed();
        }
        draw();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if( game.getState() == StarShooterGame.State.NEW) {
            playerShip.alive();
            game.setState(StarShooterGame.State.PLAYING);
        }
        if( game.getState() == StarShooterGame.State.PLAYING) {
            playerShip.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyShips.updateActiveSprites(delta);
            explosionPool.updateActiveSprites(delta);
            placeNewEnemy(delta);
        }
    }

    private void processCollisions() {
        frags += Collisions.process( playerShip, enemyShips, bulletPool );
        if( playerShip.isDestroyed() ) {
            gameOver();
        }
        if( frags >= nextLevelFrags ) {
            levelUp();
        }
    }

    private void levelUp() {
        ++level;
        nextLevelFrags +=  NEXT_LEVEL_FRAG_SCALE * nextLevelFrags > 1f ? (int)(1f + NEXT_LEVEL_FRAG_SCALE) * nextLevelFrags : nextLevelFrags;
    }

    private void gameOver() {
        game.setState(StarShooterGame.State.OVER);
        playerShip.moveTo(new Vector2(0, worldBounds.getBottom() + playerShip.getHeight()));
        destroyList(enemyShips.getActiveObjects());
        destroyList(bulletPool.getActiveObjects());
        destroyList(explosionPool.getActiveObjects());
    }

    private <T extends Destroyable> void destroyList(List<T> list) {
        for( T element : list) {
            element.destroy();
        }
    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyShips.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
    }

    private void placeNewEnemy(float delta) {
        enemyResurrectionCounter += delta;
        if( enemyResurrectionCounter > 500f * delta) {
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
        if (game.getState() != StarShooterGame.State.OVER) {
            bulletPool.drawActiveSprites(batch);
            enemyShips.drawActiveSprites(batch);
            fireButton.draw(batch);
            moveLeftButton.draw(batch);
            moveRightButton.draw(batch);
            playerShip.draw(batch);
            explosionPool.drawActiveSprites(batch);
            drawStatistics();
        }
        else {
            gameOverMessage.draw(batch);
            newGameButton.draw(batch);
        }
        batch.end();
    }

    private void drawStatistics() {
        stringBuilder.setLength(0);
        font.draw(batch, stringBuilder.append(FRAGS_LABEL).append(frags), worldBounds.getLeft(), worldBounds.getTop());
        stringBuilder.setLength(0);
        font.draw(batch, stringBuilder.append(HIT_POINTS_LABEL).append(playerShip.getHitPoints()), 0f, worldBounds.getTop(), Align.center);
        stringBuilder.setLength(0);
        font.draw(batch, stringBuilder.append(LEVEL_LABEL).append(level), worldBounds.getRight(), worldBounds.getTop(), Align.right);
    }

    @Override
    public void dispose() {
        gameScreenTextures.dispose();
        fireSound.dispose();
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        playerShip.resize(worldBounds);

        fireButton.resize(worldBounds);
        fireButton.setLeft(worldBounds.getRight() - fireButton.getWidth());
        fireButton.setBottom(worldBounds.getBottom());

        moveLeftButton.resize(worldBounds);
        moveLeftButton.setLeft(worldBounds.getLeft());
        moveLeftButton.setBottom(worldBounds.getBottom());

        moveRightButton.resize(worldBounds);
        moveRightButton.setLeft(moveLeftButton.getRight() + 0.01f);
        moveRightButton.setBottom(worldBounds.getBottom());

        gameOverMessage.resize(worldBounds);
        newGameButton.resize(worldBounds);
        newGameButton.setPosition( new Vector2(0f, -0.2f) );
    }

    @Override
    public boolean keyDown(int keycode) {
        if( game.getState() == StarShooterGame.State.PLAYING) {
            switch(keycode) {
                case Input.Keys.DOWN:
                case Input.Keys.S:
                    playerShip.setDestination(playerShip.getCenter().cpy().sub(0, KEYBOARD_MOVEMENT_STEP));
                    return true;
                case Input.Keys.LEFT:
                case Input.Keys.A:
                    moveLeftButton.onTouchDown(moveLeftButton.getCenter());
                    return true;
                case Input.Keys.RIGHT:
                case Input.Keys.D:
                    moveRightButton.onTouchDown(moveRightButton.getCenter());
                    return true;
                case Input.Keys.UP:
                case Input.Keys.W:
                    playerShip.setDestination(playerShip.getCenter().cpy().add(0, KEYBOARD_MOVEMENT_STEP));
                    return true;
                case Input.Keys.SPACE:
                    fireButton.onTouchDown(fireButton.getCenter());
                    return true;
            }
        }

        if(keycode == Input.Keys.M) {
            game.setScreen(StarShooterGame.ScreenType.MENU);
            return true;
        }
        return false;
    }

    /**
     * Moves player's ship left.
     */
    public void moveShipLeft() {
        playerShip.setDestination(playerShip.getCenter().cpy().sub(KEYBOARD_MOVEMENT_STEP, 0));
    }

    /**
     * Moves player's ship right.
     */
    public void moveRight() {
        playerShip.setDestination(playerShip.getCenter().cpy().add(KEYBOARD_MOVEMENT_STEP, 0));
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean mouseMoved(Vector2 position) {
        if(game.getState() == StarShooterGame.State.OVER) {
            newGameButton.onSelect(position);
        }
        return true;
    }

    @Override
    public boolean touchDown(Vector2 position, int pointer, int button) {
        if( !playerShip.isDestroyed() ) {
            return (fireButton.onTouchDown(position) ||
                    moveLeftButton.onTouchDown(position) ||
                    moveRightButton.onTouchDown(position));
        }
        if( game.getState() == StarShooterGame.State.OVER) {
            return newGameButton.onTouchDown(position);
        }
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 position, int pointer) {
        if( playerShip.isInside( position )) {
            playerShip.moveTo(position);
            return true;
        }
        return false;
    }

    /**
     * Returns player space ship.
     * @return player space ship.
     */
    public SpaceShip getPlayerShip() {
        return playerShip;
    }
}
