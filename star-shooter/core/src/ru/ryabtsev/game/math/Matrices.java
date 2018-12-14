package ru.ryabtsev.game.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

/**
 * Utility class for matrix operations.
 */
public class Matrices {

    /**
     * Calculates transition matrix between two rectangles and saves result in given matrix.
     * @param matrix - matrix, which saves calculation result.
     * @param from - initial rectangle.
     * @param to - target rectangle.
     */
    public static void setTransitionMatrix(Matrix4 matrix, Rectangle from, Rectangle to) {
        float scaleX = to.getWidth() / from.getWidth();
        float scaleY = to.getHeight() / from.getHeight();
        final Vector2 toCenter = to.getCenter();
        final Vector2 fromCenter = from.getCenter();
        matrix.idt().translate(toCenter.x, toCenter.y, 0f).scale(scaleX, scaleY, 1f).translate(-fromCenter.x, -fromCenter.y, 0f);

    }

    /**
     * Calculates transition matrix between two rectangles and saves result in given matrix.
     * @param matrix - matrix, which saves calculation result.
     * @param from - initial rectangle.
     * @param to - target rectangle.
     */
    public static void setTransitionMatrix(Matrix3 matrix, Rectangle from, Rectangle to) {
        float scaleX = to.getWidth() / from.getWidth();
        float scaleY = to.getHeight() / from.getHeight();
        final Vector2 toCenter = to.getCenter();
        final Vector2 fromCenter = from.getCenter();
        matrix.idt().translate(toCenter.x, toCenter.y).scale(scaleX, scaleY).translate(-fromCenter.x, -fromCenter.y);
    }
}
