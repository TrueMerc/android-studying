package ru.ryabtsev.game.object.bullet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Destroyable;
import ru.ryabtsev.game.object.Sprite;

/**
 * Bullet object.
 */
public class Bullet extends Sprite implements Destroyable {

    private Rectangle worldBounds;

    private Vector2 velocity = new Vector2();

    private Object owner;

    private int damage;

    private boolean isDestroyed;

    /**
     * Constructor.
     */
    public Bullet() {
        isDestroyed = false;
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rectangle worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.center.set(pos0);
        this.velocity.set(v0);
        setHeight(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        center.mulAdd(velocity, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void alive() {
        isDestroyed = false;
    }
}
