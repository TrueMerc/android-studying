package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.math.MathUtils;

import java.util.HashSet;
import java.util.Set;

import ru.ryabtsev.game.math.Rectangle;

import ru.ryabtsev.game.object.SpritesPool;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private Set<SpaceShipType> shipTypes;
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rectangle worldBounds;

    public EnemyShipPool(SpaceShipType[] enemyShipTypes, BulletPool bulletPool, ExplosionPool explosionPool, Rectangle worldBounds) {
        this.shipTypes = new HashSet<SpaceShipType>();
        for( SpaceShipType type: enemyShipTypes) {
            shipTypes.add( type );
        }
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        SpaceShipType[] shipTypesArray = new SpaceShipType[shipTypes.size()];
        shipTypesArray = shipTypes.toArray(shipTypesArray);
        return new EnemyShip(
                shipTypesArray[MathUtils.random(0, shipTypesArray.length - 1)],
                bulletPool,
                explosionPool,
                worldBounds
        );
    }
}
