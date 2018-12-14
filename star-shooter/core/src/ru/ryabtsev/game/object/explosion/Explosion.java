package ru.ryabtsev.game.object.explosion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.Sprite;

public class Explosion extends Sprite implements Destroyable {
    private float animateInterval = 0.017f;
    private float animateTimer;

    private Sound explosionSound;

    private boolean isDestroyed;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound explosionSound) {
        super(region, rows, cols, frames);
        this.explosionSound = explosionSound;
    }

    public void set(float height, Vector2 pos) {
        this.center.set(pos);
        setHeight(height);
        explosionSound.play();
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++currentRegionId == regions.length) {
                currentRegionId = 0;
                destroy();
            }
        }
    }

    /**
     * @return true if the object destroyed and false if it isn't destroyed.
     */
    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Destroys the object.
     */
    @Override
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Makes object alive.
     */
    @Override
    public void alive() {
        isDestroyed = false;
    }
}
