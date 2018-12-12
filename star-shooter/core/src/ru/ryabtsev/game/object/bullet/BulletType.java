package ru.ryabtsev.game.object.bullet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Class for description different bullet types.
 */
public class BulletType {

    private TextureRegion textureRegion;
    private float height;
    private Vector2 velocity;
    private int damage;

    /**
     * Constructor.
     * @param textureRegion - region of texture which contains bullet picture.
     * @param height - height in world coordinates.
     * @param velocity - bullet velocity.
     * @param damage - damage rate.
     */
    public BulletType( TextureRegion textureRegion, float height, Vector2 velocity, int damage) {
        this.textureRegion = textureRegion;
        this.height = height;
        this.velocity = velocity;
        this.damage = damage;
    }

    /**
     * Constructor.
     * @param velocity - bullet velocity.
     * @param damage - damage rate.
     */
    public BulletType( Vector2 velocity, int damage) {
        this( null, 0f, velocity, damage );
    }


    /**
     * Returns texture region contains bullet image.
     */
    TextureRegion getTextureRegion() {
        return textureRegion;
    }

    float getHeight() {
        return height;
    }

    /**
     * Returns velocity of bullet of given type .
     */
    Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Returns damage of bullet of given type.
     */
    int getDamage() {
        return damage;
    }
}
