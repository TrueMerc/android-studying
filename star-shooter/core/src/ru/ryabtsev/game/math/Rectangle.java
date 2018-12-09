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
     * Sets left border abscissa.
     * @param leftAbscissa - left border abscissa.
     */
    public void setLeft(float leftAbscissa) {
        center.x = leftAbscissa + halfWidth;
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
     * Sets bottom border ordinate.
     */
    public void setBottom(float bottomOrdinate) {
        center.y = bottomOrdinate + halfHeight;
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
     * Sets new rectangle size.
     */
    public void resize(float width, float height) {
        this.halfWidth = width / 2;
        this.halfHeight = height / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Rectangle: pos" + center + " size(" + getWidth() + ", " + getHeight() + ")";
    }

    /**
     * Determines if the point is inside rectangle or not.
     * @param point - point coordinates.
     * @return true if the point is inside rectangle or false if it isn't.
     */
    public boolean isInside(Vector2 point) {
        return (point.x >= getLeft() && point.x <= getRight() && point.y >= getBottom() && point.y <= getTop());
    }

    /**
     * Determenes if the rectangle is inside current rectangle or not.
     * @param rectangle - given rectangle.
     * @return true if the rectangle is inside current rectangle or false if it isn't.
     */
    public boolean isInside(Rectangle rectangle) {
        return (this.getLeft() <= rectangle.getLeft()) &&
               (this.getRight() >= rectangle.getRight()) &&
               (this.getTop() >= rectangle.getTop()) &&
               (this.getBottom() <= rectangle.getBottom());
    }

    /**
     * Determines if the rectangle intersects current rectangle (both rectangles have common points).
     * @param rectangle - given rectangle.
     */
    public boolean isIntercect(Rectangle rectangle) {
        return (this.getLeft() > rectangle.getRight() || this.getRight() < rectangle.getLeft()) ||
               (this.getTop() < rectangle.getBottom() || this.getBottom() > rectangle.getTop()) ? false : true;
    }

    /**
     * Determines if the rectangle is outside current rectangle (rectangles haven't common points).
     * @param rectangle - given rectangle.
     */
    public boolean isOutside(Rectangle rectangle) {
        return !(isInside(rectangle) || isIntercect(rectangle));
    }
}
