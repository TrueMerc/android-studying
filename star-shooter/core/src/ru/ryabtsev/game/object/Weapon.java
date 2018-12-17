package ru.ryabtsev.game.object;

import com.badlogic.gdx.audio.Sound;

import ru.ryabtsev.game.object.bullet.BulletType;

/**
 * Base class for all weapons.
 */
public class Weapon {

    private BulletType bulletType;
    private Sound sound;
    private float reloadingTime;

    /**
     * Constructor
     * @param bulletType weapon bullet type.
     * @param sound weapon sound.
     * @param reloadingTime weapon reload rate.
     */
    public Weapon(BulletType bulletType, Sound sound, float reloadingTime) {
        this.bulletType = bulletType;
        this.sound = sound;
        this.reloadingTime = reloadingTime;
    }

    /**
     * Returns weapon bullet type.
     */
    public BulletType getBulletType() {
        return bulletType;
    }

    /**
     * Returns weapons sound.
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * Returns weapon reload rate.
     */
    public float getReloadingTime() {
        return reloadingTime;
    }
}
