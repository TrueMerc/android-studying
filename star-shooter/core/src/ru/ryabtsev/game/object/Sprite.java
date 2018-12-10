package ru.ryabtsev.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;

/**
 * Base class for game objects which renders using sprites.
 */
public class Sprite extends Rectangle {

    protected float angle;
    protected float scale;

    protected TextureRegion regions[];
    protected int currentRegionId;


    /**
     * Default constructor.
     */
    public Sprite() {
        angle = 0f;
        scale = 1f;
        regions = new TextureRegion[1];
        currentRegionId = 0;
    }

    /**
     * Constructor.
     * @param region - sprite object texture.
     */
    public Sprite(TextureRegion region) {
        this();
        regions[0] = region;
    }

    /**
     * Draws object using given batch.
     * @param batch - batch for sprite object drawing.
     */
    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[currentRegionId],
                getLeft(), getBottom(),
                getWidth() / 2f, getHeight() / 2f,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    /**
     * Changes sprite size.
     * @param worldBounds - game world bounds.
     */
    public void resize(final Rectangle worldBounds) {

    }

    /**
     * Sets sprite object height and width.
     * Width calculates from current texture region size.
     */
    public void setHeight(float height) {
        TextureRegion region = regions[currentRegionId];
        float aspect = region.getRegionWidth() / (float) region.getRegionHeight();
        this.resize( height * aspect, height);
    }

    /**
     * Updates sprite image.
     * @param delta - screen refresh period.
     */
    public void update(float delta) {

    }

    /**
     * Processes cases when the screen was touched or a mouse button was pressed.
     */
    public boolean touchDown(final Vector2 point, int pointer) {
        return false;
    }

    /**
     * Processes cases when the screen was touched or a mouse button was released.
     */
    public boolean touchUp(final Vector2 point, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
