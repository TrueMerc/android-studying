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

    public void set( Object owner, Vector2 pos0, BulletType type, Rectangle worldBounds ) {
        this.owner = owner;
        this.center.set(pos0);
        this.regions[0] = type.getTextureRegion();
        setHeight(type.getHeight());
        this.velocity.set( type.getVelocity() );
        this.damage = type.getDamage();
        this.worldBounds = worldBounds;
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
