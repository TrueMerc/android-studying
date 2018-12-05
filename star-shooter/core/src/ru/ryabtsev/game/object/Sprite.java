package ru.ryabtsev.game.object;

import ru.ryabtsev.game.math.Rectangle;

/**
 * Base class for game objects which renders using sprites.
 */
public class Sprite extends Rectangle {

    protected float angle;
    protected float scale;


    public Sprite() {
        angle = 0f;
        scale = 1f;
    }
}
