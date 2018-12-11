package ru.ryabtsev.game.object.bullet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Class for description different bullet types.
 */
public class BulletType {

    private TextureRegion textureRegion;
    private Vector2 velocity;
    private int damage;

    /**
     * Constructor.
     * @param textureRegion - region of texture which contains bullet picture.
     * @param velocity - bullet velocity.
     * @param damage - damage rate.
     */
    BulletType( TextureRegion textureRegion, Vector2 velocity, int damage) {
        this.textureRegion = textureRegion;
        this.velocity = velocity;
        this.damage = damage;
    }

    /**
     * Constructor.
     * @param velocity - bullet velocity.
     * @param damage - damage rate.
     */
    BulletType( Vector2 velocity, int damage) {
        this( null, velocity, damage );
    }


    /**
     * Returns texture region contains bullet image.
     */
    TextureRegion getTextureRegion() {
        return textureRegion;
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
