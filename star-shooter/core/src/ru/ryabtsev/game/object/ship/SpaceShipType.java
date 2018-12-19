package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ryabtsev.game.object.Weapon;
import ru.ryabtsev.game.object.bullet.BulletType;

/**
 * Class for different ship types description.
 */
public class SpaceShipType {
    private TextureRegion[] textureRegions;
    private Weapon weapon;
    private float height;
    private float speed;
    private int hitPoints;
    private String description;



    /**
     * Constructor
     * @param textureRegions - regions of texture contains images for given ship type.
     * @param weapon - ship main weapon.
     * @param height - ship height.
     * @param speed - speed for ships of given type.
     * @param hitPoints - ship hit points.
     * @param description - ship type description.
     */
    public SpaceShipType(TextureRegion[] textureRegions,
                         Weapon weapon,
                         float height,
                         float speed,
                         int hitPoints,
                         String description)
    {
        this.textureRegions = textureRegions;
        this.weapon = weapon;
        this.height = height;
        this.speed = speed;
        this.hitPoints = hitPoints;
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
     * @param number texture region number.
     */
    public TextureRegion getTextureRegion(int number) {
        return textureRegions[number];
    }

    /**
     * Returns type of bullets which space ships of this type using.
     */
    public Weapon getWeapon() {
        return weapon;
    }


    /**
     * Returns height for space ships of given type.
     * @return height for space ships of given type.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Returns speed for given ship type.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Returns hit points number for space ships of given type.
     * @return hit points number for space ships of given type.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Return description for given ship type.
     */
    public String getDescription() {
        return description;
    }
}
