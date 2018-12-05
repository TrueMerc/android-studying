package ru.ryabtsev.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

import ru.ryabtsev.game.math.Matrices;
import ru.ryabtsev.game.math.Rectangle;

/**
 * Base class for application screens.
 */
public class Base2DScreen implements Screen, InputProcessor {

    private static float GL_DEFAULT_WIDTH = 2f;
    private static float GL_DEFAULT_HEIGHT = 2f;
    private static float WORLD_DEFAULT_HEIGHT = 1f;

    protected Rectangle screenBounds; // painting area bounds in pixels (screen bounds)
    private Rectangle worldBounds;  // painting area bounds in game world coordinates
    private Rectangle glBounds;     // painting area bounds in OpenGL coordinates

    protected SpriteBatch batch;

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    protected float worldHeight;


    /**
     * Constructor.
     */
    Base2DScreen(float worldHeight) {
        screenBounds = new Rectangle();
        worldBounds = new Rectangle();
        glBounds = new Rectangle(0, 0, GL_DEFAULT_WIDTH, GL_DEFAULT_HEIGHT);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        this.worldHeight = worldHeight;
    }

    /**
     * Constructor.
     */
    Base2DScreen() {
        this( WORLD_DEFAULT_HEIGHT );
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch.getProjectionMatrix().idt();
        Gdx.input.setInputProcessor( this );
    }

    @Override
    public void render(float delta) {

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
