package ru.ryabtsev.game.math;

import com.badlogic.gdx.math.Vector2;

// TODO Move several method in BaseRectangle class and make this class its descendant.
/**
 * Base class for different rectangle regions.
 * All coordinates in this class based on (0,0) point placed in down-left corner of a screen.
 */
public class Rectangle {

    protected Vector2 center;
    protected float halfWidth;
    protected float halfHeight;

    /**
     * Default constructor.
     */
    public Rectangle() {
        center = new Vector2(0, 0);
        halfWidth = 0;
        halfHeight = 0;
    }

    /**
     * Constructor.
     * @param width - rectangle width.
     * @param height - rectangle height.
     */
    public Rectangle(float width, float height) {
        center = new Vector2(width / 2f, height / 2f);
        halfWidth = width / 2;
        halfHeight = height / 2;
    }

    /**
     * Constructor.
     * @param centerX - rectangle center abscissa.
     * @param centerY - rectangle center ordinate.
     * @param width - rectangle width.
     * @param height - rectangle height.
     */
    public Rectangle(float centerX, float centerY, float width, float height) {
        center = new Vector2(centerX, centerY);
        halfWidth = width / 2f;
        halfHeight = height / 2f;
    }

    /**
     * Constructor.
     * @param center - position of rectangle center.
     * @param width - rectangle width.
     * @param height - rectangle height.
     */
    public Rectangle(Vector2 center, float width, float height) {
        this.center = new Vector2(center.x, center.y );
        halfWidth = width / 2f;
        halfHeight = height / 2f;
    }

    /**
     * Returns left border abscissa.
     */
    public float getLeft() {
        return center.x - halfWidth;
    }

    /**
     * Returns top border ordinate.
     */
    public float getTop() {
        return center.y + halfHeight;
    }

    /**
     * Returns right border abscissa.
     */
    public float getRight() {
        return center.x + halfWidth;
    }

    /**
     * Returns bottom border ordinate.
     */
    public float getBottom() {
        return center.y - halfHeight;
    }

    /**
     * Returns rectangle width.
     */
    public float getWidth() {
        return 2f * halfWidth;
    }

    /**
     * Returns rectangle height.
     */
    public float getHeight() {
        return 2f * halfHeight;
    }

    /**
     * Returns rectangle center position.
     */
    public Vector2 getCenter() {
        return center;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Rectangle: pos" + center + " size(" + getWidth() + ", " + getHeight() + ")";
    }
}
