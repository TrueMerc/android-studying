package ru.ryabtsev.game.utils;

import java.util.List;

import ru.ryabtsev.game.object.bullet.Bullet;
import ru.ryabtsev.game.object.bullet.BulletPool;
import ru.ryabtsev.game.object.ship.EnemyShip;
import ru.ryabtsev.game.object.ship.EnemyShipPool;
import ru.ryabtsev.game.object.ship.SpaceShip;

public final class Collisions {
    public static void process(SpaceShip playerShip, EnemyShipPool enemyShips, BulletPool bulletPool) {
        List<EnemyShip> enemyList = enemyShips.getActiveObjects();
        for (EnemyShip enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getWidth() / 2 + playerShip.getWidth() / 2;
            if (enemy.getCenter().dst2(playerShip.getCenter()) < minDist * minDist) {
                int enemyHitPoints = enemy.getHitPoints();
                enemy.damage(2 * playerShip.getHitPoints());
                playerShip.damage(2 * enemyHitPoints);
                return;
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (EnemyShip enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != playerShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isHit(bullet)) {
                    enemy.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }

        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed() || bullet.getOwner() == playerShip) {
                continue;
            }
            if (playerShip.isHit(bullet)) {
                bullet.destroy();
                playerShip.damage(bullet.getDamage());
            }
        }
    }
}
