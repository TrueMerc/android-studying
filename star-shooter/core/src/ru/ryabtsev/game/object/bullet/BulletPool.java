package ru.ryabtsev.game.object.bullet;


import ru.ryabtsev.game.object.SpritesPool;

/**
 * Pool for bullet objects.
 */
public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
