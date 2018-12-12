package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashSet;
import java.util.Set;

import ru.ryabtsev.game.math.Rectangle;
import ru.ryabtsev.game.object.Sprite;
import ru.ryabtsev.game.object.SpritesPool;
import ru.ryabtsev.game.object.bullet.BulletPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private Set<SpaceShipType> shipTypes;
    private TextureRegion[] textures;
    private BulletPool bulletPool;
    private Rectangle worldBounds;

    public EnemyShipPool(SpaceShipType[] enemyShipTypes, BulletPool bulletPool, Rectangle worldBounds) {
        this.shipTypes = new HashSet<SpaceShipType>();
        for( SpaceShipType type: enemyShipTypes) {
            shipTypes.add( type );
        }
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    /**
     * Adds new ship type in enemy ship pool.
     * @param type - enemy space ship type.
     */
    void addShipType(SpaceShipType type) {
        shipTypes.add( type );
    }

    @Override
    protected EnemyShip newObject() {
        SpaceShipType[] typesArray = shipTypes.toArray(new SpaceShipType[shipTypes.size()]);
        return new EnemyShip(typesArray[MathUtils.random(0, typesArray.length - 1)], bulletPool, worldBounds);
    }
}
