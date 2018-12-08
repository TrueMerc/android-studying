package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.StarShooterGame;
import ru.ryabtsev.game.math.Matrices;
import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Background;
import ru.ryabtsev.game.object.Star;

/**
 * Base class for application screens.
 */
public class Base2DScreen implements Screen, InputProcessor {

    private static float GL_DEFAULT_WIDTH = 2f;
    private static float GL_DEFAULT_HEIGHT = 2f;
    private static float WORLD_DEFAULT_HEIGHT = 1f;

    protected static final float HEIGHT_AXIS_SCALE = 1f;
    protected static final float KEYBOARD_MOVEMENT_STEP = 0.05f * HEIGHT_AXIS_SCALE;
    protected static final float SPACESHIP_TEXTURE_DEFAULT_SCALE_FACTOR = 0.1f * HEIGHT_AXIS_SCALE;
    protected static final float VELOCITY_SCALE = 0.01f * HEIGHT_AXIS_SCALE;

    protected Rectangle screenBounds; // painting area bounds in pixels (screen bounds)
    protected Rectangle worldBounds;  // painting area bounds in game world coordinates
    private Rectangle glBounds;     // painting area bounds in OpenGL coordinates

    protected SpriteBatch batch;
    protected Texture backgroundTexture;
    private Background background;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    protected float worldHeight;

    protected Vector2 touch;
    protected Vector2 mousePosition;

    protected StarShooterGame game;

    private TextureAtlas textureAtlas;

    private static final int STARS_COUNT = 50;
    private Star[] stars;

    /**
     * Constructor.
     */
    Base2DScreen(StarShooterGame game, float worldHeight) {
        this.game  = game;
        screenBounds = new Rectangle();
        worldBounds = new Rectangle();
        glBounds = new Rectangle(0, 0, GL_DEFAULT_WIDTH, GL_DEFAULT_HEIGHT);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        this.worldHeight = worldHeight;
        this.touch = new Vector2();
        this.mousePosition = new Vector2();
        textureAtlas = new TextureAtlas("menuAtlas.tpack");

        stars = new Star[STARS_COUNT];
        for(int i = 0; i < stars.length; ++i) {
            stars[i] = new Star(textureAtlas);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        batch.getProjectionMatrix().idt();
        Gdx.input.setInputProcessor( this );

        backgroundTexture = new Texture("space_background.png");
        background = new Background( new TextureRegion(backgroundTexture) );
        resize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    /**
     * {@inheritDoc}
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    public void update(float delta) {
        for(int i = 0; i < stars.length; ++i) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        batch.begin();
        background.draw(batch);
        for(int i = 0; i < stars.length; ++i) {
            stars[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize(): width = " + width + ", height = " + height);
        screenBounds.resize(width, height);
        screenBounds.setLeft( 0 );
        screenBounds.setBottom( 0 );

        float aspect = width / (float) height;
        float worldWidth = worldHeight * aspect;
        worldBounds.resize(worldWidth, worldHeight);

        Matrices.inplaceSetTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        Matrices.inplaceSetTransitionMatrix(screenToWorld, screenBounds, worldBounds);
        background.resize(worldBounds);

        for(int i = 0; i < stars.length; ++i) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println( "keyDown key code = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println( "keyUp key code = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Screen bounds: width = " + screenBounds.getWidth() + ", y = " + screenBounds.getHeight() );
        System.out.println("Screen coordinates: x = " + screenX + ", y = " + screenY );
        touch.set( screenX, screenBounds.getHeight() - screenY).mul( screenToWorld );
        return touchDown( touch, pointer, button);
    }


    public boolean touchDown(final Vector2 position, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set( screenX, screenBounds.getHeight() - screenY).mul( screenToWorld );
        return mouseMoved(mousePosition);
    }

    public boolean mouseMoved(final Vector2 position) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set( screenX, screenBounds.getHeight() - screenY).mul( screenToWorld );
        return touchUp( touch, pointer, button);
    }

    public boolean touchUp(final Vector2 position, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
