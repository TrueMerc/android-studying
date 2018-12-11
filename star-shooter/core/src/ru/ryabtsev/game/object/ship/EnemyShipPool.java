package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashSet;
import java.util.Set;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Sprite;
import ru.ryabtsev.game.object.SpritesPool;
import ru.ryabtsev.game.object.bullet.BulletPool;

public class EnemyShipPool extends SpritesPool {

    private Set<ShipType> shipTypes;
    private TextureRegion[] textures;
    private BulletPool bulletPool;
    private Rectangle worldBounds;

    EnemyShipPool(TextureRegion[] textures, BulletPool bulletPool, Rectangle worldBounds) {
        this.shipTypes = new HashSet<ShipType>();
        this.textures = textures;
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    /**
     * Adds new ship type in enemy ship pool.
     * @param type
     */
    void addShipType(ShipType type) {
        shipTypes.add( type );
    }

    @Override
    protected Sprite newObject() {
        return new EnemyShip(textures[0], bulletPool, worldBounds);
    }
}
