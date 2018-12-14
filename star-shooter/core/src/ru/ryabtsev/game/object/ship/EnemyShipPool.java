package ru.ryabtsev.game.object.ship;

import com.badlogic.gdx.math.MathUtils;

import java.util.HashSet;
import java.util.Set;

import ru.ryabtsev.game.math.Rectangle;

import ru.ryabtsev.game.object.SpritesPool;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.explosion.ExplosionPool;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Pool of enemy space ships.
 */
public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private Set<SpaceShipType> shipTypes;
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rectangle worldBounds;

    /**
     * Constructor
     * @param enemyShipTypes - accessible enemy ships types.
     * @param bulletPool - bullet pool.
     * @param explosionPool - explosion pool.
     * @param worldBounds - world bounds.
     */
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
        return new EnemyShip(shipTypesArray[getTypeIndex()], bulletPool, explosionPool, worldBounds);
    }

    private int getTypeIndex() {
        float random = MathUtils.random(0f, 1f);

        int type = 0;

        if( random > 0.5f ) {
            type = 1;
        }
        if( random > 0.9f ) {
            type = 2;
        }

        return type;
    }
}
