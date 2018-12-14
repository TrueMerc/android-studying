package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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

    private static final String TEXTURE_ATLAS_PATH = "textures/BaseScreen.pack";
    private static final String BACKGROUND_NAME = "Space";
    private static final String[] STAR_TEXTURE_NAMES =  { "Star", "Star1" };
    private static final int STARS_COUNT = 50;

    private Rectangle screenBounds; // painting area bounds in pixels (screen bounds)
    private Rectangle glBounds;     // painting area bounds in OpenGL coordinates
    protected Rectangle worldBounds;  // painting area bounds in game world coordinates

    protected SpriteBatch batch;

    private TextureAtlas textureAtlas;

    private Background background;
    private Star[] stars;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    protected float worldHeight;

    protected Vector2 touch;
    protected Vector2 mousePosition;

    protected StarShooterGame game;

    /**
     * Constructor.
     */
    Base2DScreen(StarShooterGame game) {
        final float GL_DEFAULT_WIDTH = 2f;
        final float GL_DEFAULT_HEIGHT = 2f;
        final float HEIGHT_AXIS_SCALE = 1f;
        this.game  = game;
        screenBounds = new Rectangle();
        worldBounds = new Rectangle();
        glBounds = new Rectangle(0, 0, GL_DEFAULT_WIDTH, GL_DEFAULT_HEIGHT);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        this.worldHeight = HEIGHT_AXIS_SCALE;
        this.touch = new Vector2();
        this.mousePosition = new Vector2();

        textureAtlas = new TextureAtlas(TEXTURE_ATLAS_PATH);
        createBackground();
        createStars();
    }

    private void createBackground() {
        background = new Background( textureAtlas.findRegion(BACKGROUND_NAME) );
    }

    private void createStars() {
        int i = 0;
       TextureRegion[] starTextureRegions = new TextureRegion[STAR_TEXTURE_NAMES.length];
        for( String name : STAR_TEXTURE_NAMES ) {
            starTextureRegions[i++] = textureAtlas.findRegion(name);
        }

        stars = new Star[STARS_COUNT];
        for(i = 0; i < stars.length; ++i) {
            stars[i] = new Star(starTextureRegions[MathUtils.random(0, starTextureRegions.length - 1)]);
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch.getProjectionMatrix().idt();

        Gdx.input.setInputProcessor( this );

        resize( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        draw();
    }

    /**
     * Updates screen objects.
     * @param delta - time period between method calls.
     */
    public void update(float delta) {
        for(int i = 0; i < stars.length; ++i) {
            stars[i].update(delta);
        }
    }

    /**
     * Draws screen objects.
     */
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

        Matrices.setTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        Matrices.setTransitionMatrix(screenToWorld, screenBounds, worldBounds);

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
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
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
        System.out.println( "Touch dragged: (" + screenX + ", "  + screenY + ")" );
        touch.set( screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        System.out.println( "Touch dragged: " + touch );
        return touchDragged( touch, pointer);
    }

    public boolean touchDragged( final Vector2 position, int pointer ) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
