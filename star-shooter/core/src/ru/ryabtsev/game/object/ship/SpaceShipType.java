package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.object.bullet.Bullet;
import ru.ryabtsev.game.object.bullet.BulletType;

/**
 * Class for different ship types desctiption.
 */
public class SpaceShipType {
    private TextureRegion[] textureRegions;
    private BulletType bulletType;
    private float speed;
    private String description;

    /**
     * Constructor
     * @param textureRegions - regions of texture contains images for given ship type.
     * @param bulletType - type of bullets which given space ship type using.
     * @param speed - speed for ships of given type.
     * @param description - ship type description.
     */
    public SpaceShipType(TextureRegion[] textureRegions, BulletType bulletType, float speed, String description) {
        this.textureRegions = textureRegions;
        this.bulletType = bulletType;
        this.speed = speed;
        this.description = description;
    }

    /**
     * Returns all texture regions for given space ship type.
     */
    public TextureRegion[] getTextureRegions() {
        return textureRegions;
    }

    /**
     * Returns specific texture region for given space ship type.
     * @param - texture region number.
     */
    public TextureRegion getTextureRegion(int number) {
        return textureRegions[number];
    }

    /**
     * Returns type of bullets which space ships of this type using.
     */
    public BulletType getBulletType() {
        return bulletType;
    }

    /**
     * Returns speed for given ship type.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Return description for given ship type.
     */
    public String getDescription() {
        return description;
    }
}
